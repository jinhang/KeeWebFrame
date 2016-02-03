package cn.kee.common.helper;

import static cn.kee.model.tables.Message.MESSAGE;
import static cn.kee.model.tables.Order.ORDER;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.common.sms.SendSms;
import com.kee.common.util.DateUtil;
import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Message;
import com.kee.model.tables.pojos.Order;
import com.kee.model.tables.pojos.User;
import com.kee.model.tables.records.MessageRecord;
import com.kee.model.tables.records.OrderRecord;
import com.kee.redis.RedisLock;
import com.kee.task.Msg;
import com.wfs.engine.Context;
import com.wfs.engine.common.EngineHelper;

import cn.kee.common.jooq.Expression;
import cn.kee.common.jooq.JooqHelper;
import cn.kee.common.jooq.CodeEnum.OrderState;
import cn.kee.common.jooq.CodeEnum.RedisLockType;

public class OrderHelper {
	
	private static final Logger logger = Logger.getLogger(OrderHelper.class);
	
	public static Msg<Integer> getStateNum(Context context,String states,String userid,Integer type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		JooqHelper helper = new JooqHelper().where(ORDER.STATE.eq(states));
		if(type==1){
			helper =helper.and(ORDER.USERID.eq(userid).or(ORDER.SUSERID.eq(userid)));
		}else{
			helper =helper.and(ORDER.STAFFID.eq(userid).or(ORDER.DSTAFFID.eq(userid)));
		}
		Msg<Integer> msg = new Msg<Integer>();
		try{
			Integer num = create.selectCount().from(ORDER)
					.where(helper.getCondition()).fetchOneInto(Integer.class);
			msg.setResult(true);
			msg.setValue(num);
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		return msg;
	}
	
	public static Msg<String> saveOrder(Context context,Order o){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		OrderRecord record = create.newRecord(ORDER);
		try{
			record.from(o);
			record.setOrderid(UUIDTool.getUUID());
			record.setCreatetime(new Timestamp((new Date()).getTime()));
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getOrderid());
		msg.setResult(true);
		return msg;
	}
	
	public static Order getOrderById(Context context,String orderid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Order o = new Order();
		o = create.fetchOne(ORDER, ORDER.ORDERID.eq(orderid)).into(Order.class);
		return o;
	}
	
	public static Boolean cancelOrder(Context context,String orderid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		try{
			OrderRecord o = create.fetchOne(ORDER,
					ORDER.ORDERID.eq(orderid));
			o.setState(OrderState.QXZD.key);
			o.update();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static Msg<List<Order>> getOrderList(Context context,String[] state,String begintime,
			String endtime,String userid,String staffid,String suserid,String dstaffid,Integer first,Integer limit){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<Order>> msg = new Msg<List<Order>>();
		JooqHelper helper = new JooqHelper().where(ORDER.ORDERID.isNotNull())
				.and(Expression.NotNull(userid,ORDER.USERID.eq(userid)))
				.and(Expression.NotNull(staffid,ORDER.STAFFID.eq(staffid).or(MESSAGE.OSTATE.eq(OrderState.DJD.key).and(ORDER.STATE.eq(OrderState.DJD.key)))))
				.and(Expression.NotNull(suserid,ORDER.SUSERID.eq(suserid)))
				.and(Expression.NotNull(dstaffid,ORDER.DSTAFFID.eq(dstaffid)));
		if(!(begintime==null || begintime.equals(""))){
			helper = helper.and(Expression.NotNull(begintime,ORDER.CREATETIME.ge(new Timestamp(DateUtil.getDateByFormatString(begintime,com.kee.common.date.DateUtil.formart9).getTime()))));
		}
		if(!(endtime==null || endtime.equals(""))){
			helper = helper.and(Expression.NotNull(endtime,ORDER.CREATETIME.le(new Timestamp(DateUtil.getDateByFormatString(endtime,com.kee.common.date.DateUtil.formart9).getTime()))));
		}
		if(state!=null && state.length>0){
			helper = helper.and(ORDER.STATE.in(state));
		}
		try{
			List<Order> os = create.selectDistinct(ORDER.fields()).from(ORDER)
					.leftOuterJoin(MESSAGE).on(MESSAGE.ORDERID.eq(ORDER.ORDERID).and(MESSAGE.STAFFID.eq(staffid)))
					.where(helper.getCondition()).orderBy(ORDER.CREATETIME.desc())
					.limit(limit).offset(first).fetchInto(Order.class);
			if(os.size()==0){
				msg.setResult(false);
			}else{
				msg.setResult(true);
				msg.setValue(os);
			}
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		return msg;
	}
	
	public static Boolean confirmPay(Context context,Double totalprice,
			Double serverfee,Double discountfee,Double expresscharge,
			String orderid,String payway,Double payfee){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		try{
			OrderRecord o = create.fetchOne(ORDER,
					ORDER.ORDERID.eq(orderid));
			o.setState(OrderState.PSZ.key);
			o.setTotalprice(totalprice);
			o.setServerfee(serverfee);
			o.setDiscountfee(discountfee);
			o.setExpresscharge(expresscharge);
			o.setPayway(payway);
			o.setPayfee(payfee);
			o.update();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static Boolean signedOrder(Context context,String orderid, String userid,
			String signway){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		boolean flag = false;
		RedisLock lock = new RedisLock(RedisLockType.ORDER.key+orderid);
		try{
			if (!lock.trylock()) {
				return flag;
			}
			Order o = OrderHelper.getOrderById(context, orderid);
			if(o.getState().equals(OrderState.YQS.key)){
				return flag;
			}
			create.update(ORDER).set(ORDER.STATE,OrderState.YQS.key)
			.set(ORDER.SIGNEDWAY,signway).where(ORDER.ORDERID.eq(orderid)).execute();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		} finally {
			lock.unlock();
		}
		return flag;
	}
	
	public static Msg<Order> getOrderByTelAndSendcode(Context context,String username,String sendcode){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<Order> msg = new Msg<Order>();
		List<Order> os = create.select().from(ORDER)
				.where(ORDER.SIGEDTEL.eq(username).and(ORDER.SENDCODE.eq(sendcode))).fetchInto(Order.class);
		if(os.size()>=1){
			if(os.get(0).getState().equals(OrderState.YQS.key)){
				msg.setResult(false);
				msg.setNode(OrderState.YQS.key);
				return msg;
			}
			msg.setResult(true);
			msg.setValue(os.get(0));
		}else{			
			msg.setResult(false);
		}
		return msg;
	}
	
	public static Msg<String> saveOrUpdateOrder(Context context,Order o){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		OrderRecord record = create.newRecord(ORDER);
		try{
			if(o.getOrderid()!=null && !o.getOrderid().equals("")){
				OrderRecord or = create.fetchOne(ORDER,
						ORDER.ORDERID.eq(o.getOrderid()));
				or.from(o);
				or.update();
			}else{
				record.from(o);
				record.setOrderid(UUIDTool.getUUID());
				record.setCreatetime(new Timestamp((new Date()).getTime()));
				record.store();	
			}			
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getOrderid());
		msg.setResult(true);
		return msg;
	}
	
	public static Msg<Integer> getDeliveryNum(Context context,String states,String userid,Date begin,Date end){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		JooqHelper helper = new JooqHelper().where(ORDER.STATE.eq(states)
				.and(ORDER.DSTAFFID.eq(userid))
				.and(ORDER.CREATETIME.greaterOrEqual(new Timestamp(begin.getTime())).and(ORDER.CREATETIME.lessOrEqual(new Timestamp(end.getTime())))
				.or(ORDER.RDTIME.greaterOrEqual(new Timestamp(begin.getTime())).and(ORDER.RDTIME.lessOrEqual(new Timestamp(end.getTime()))))));
		Msg<Integer> msg = new Msg<Integer>();
		try{
			Integer num = create.selectCount().from(ORDER)
					.where(helper.getCondition()).fetchOneInto(Integer.class);
			msg.setResult(true);
			msg.setValue(num);
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			msg.setValue(0);
			return msg;
		}
		return msg;
	}
	
	public static Boolean confirmTakeOrder(Context context,String orderid, String staffid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		boolean flag = false;
		RedisLock lock = new RedisLock(RedisLockType.ORDER.key+orderid);
		try{
			if (!lock.trylock()) {
				return flag;
			}
			Order o = OrderHelper.getOrderById(context, orderid);
			User  staff = UserHelper.getUserById(context, staffid);
			if(!o.getState().equals(OrderState.DJD.key)){
				return flag;
			}
			create.update(ORDER).set(ORDER.STATE,OrderState.DQJ.key)
			.set(ORDER.STAFFID,staffid).set(ORDER.STAFFNAME,staff.getNick())
			.set(ORDER.STAFFTEL,staff.getUsername()).where(ORDER.ORDERID.eq(orderid)).execute();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		} finally {
			lock.unlock();
		}
		return flag;
	}
	
	public static Boolean pickUpOrder(Context context,String orderid, String staffid,String sendcode,
			String serialnum,Double weight,Double expresscharge){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		boolean flag = false;
		RedisLock lock = new RedisLock(RedisLockType.ORDER.key+orderid);
		try{
			if (!lock.trylock()) {
				return flag;
			}
			Order o = OrderHelper.getOrderById(context, orderid);
			if(!o.getState().equals(OrderState.DQJ.key)){
				return flag;
			}
			create.update(ORDER).set(ORDER.STATE,OrderState.DZF.key)
			.set(ORDER.SENDCODE,sendcode)
			.set(ORDER.SERIALNUM,serialnum)
			.set(ORDER.WEIGHT,weight).set(ORDER.EXPRESSCHARGE,expresscharge)
			.set(ORDER.TOTALPRICE,expresscharge)
			.where(ORDER.ORDERID.eq(orderid)).execute();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		} finally {
			lock.unlock();
		}
		return flag;
	}
	
	public static Boolean updateAppointTime(Context context,String orderid, Date begin,Date end){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Boolean flag =true;
		try{
			create.update(ORDER).set(ORDER.ASBEGIN,new Timestamp(begin.getTime()))
			.set(ORDER.APEND,new Timestamp(end.getTime())).where(ORDER.ORDERID.eq(orderid)).execute();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		} 
		return flag;
	}
}
