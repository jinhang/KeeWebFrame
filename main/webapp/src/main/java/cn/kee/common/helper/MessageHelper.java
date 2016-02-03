package cn.kee.common.helper;

import static cn.kee.model.tables.Message.MESSAGE;
import static cn.kee.model.tables.Order.ORDER;
import static cn.kee.model.tables.User.USER;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.common.sms.PushMsgToSingleDevice;
import com.kee.common.sms.SendSms;
import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Message;
import com.kee.model.tables.pojos.User;
import com.kee.model.tables.records.MessageRecord;
import com.kee.redis.RedisLock;
import com.kee.task.Msg;
import com.wfs.engine.Context;

import cn.kee.common.jooq.JooqHelper;
import cn.kee.common.jooq.CodeEnum.MessageType;
import cn.kee.common.jooq.CodeEnum.RedisLockType;
import cn.kee.common.jooq.CodeEnum.SendType;

public class MessageHelper {
	
	private static final Logger logger = Logger.getLogger(MessageHelper.class);
	
	public static Msg<String> saveMes(Context context,Message mes){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		MessageRecord record = create.newRecord(MESSAGE);
		try{
			record.setMessageid(UUIDTool.getUUID());
			record.setUserid(mes.getUserid());
			record.setType(mes.getType());
			record.setStaffid(mes.getStaffid());
			record.setSendtype(mes.getSendtype());
			record.setOrderid(mes.getOrderid());
			record.setIssms(mes.getIssms());
			record.setState(mes.getState());
			record.setTime(new Timestamp((new Date()).getTime()));
			record.setMobile(mes.getMobile());
			record.setContext(mes.getContext());
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		msg.setValue(record.getMessageid());
		return msg;
	}
	
	
	public static Boolean sendSMSMes(Context context,String messageid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		boolean flag = false;
		RedisLock lock = new RedisLock(RedisLockType.MESSAGE.key+messageid);
		try{
			if (!lock.trylock()) {
				return flag;
			}
			Message mes = create.fetchOne(MESSAGE, MESSAGE.MESSAGEID.eq(messageid)).into(Message.class);
			if(mes.getIssms()==1 && mes.getState()==0 && mes.getContext()!=null){				
				SendSms.SendSms(mes.getContext(), mes.getMobile());
			}
			create.update(MESSAGE).set(MESSAGE.STATE,2).where(MESSAGE.MESSAGEID.eq(messageid)).execute();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		} finally {
			lock.unlock();
		}
		return flag;
	}
	
	public static Msg<List<Message>> getMes(Context context,String userid,Integer type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<Message>> rmess = new Msg<List<Message>>();
		JooqHelper helper = new JooqHelper().where(MESSAGE.STATE.le(0));
		if(type==1){
			helper = helper.and(MESSAGE.USERID.eq(userid))
					.and(MESSAGE.SENDTYPE.eq(SendType.ETU.value).or(MESSAGE.SENDTYPE.eq(SendType.STUE.value)));
		}else{
			helper =helper.and(MESSAGE.STAFFID.eq(userid))
					.and(MESSAGE.SENDTYPE.eq(SendType.UTE.value).or(MESSAGE.SENDTYPE.eq(SendType.STUE.value)));
		}
		List<Message> mess = create.select().from(MESSAGE)
				.where(helper.getCondition()).orderBy(MESSAGE.TIME.desc()).fetchInto(Message.class);
		rmess.setValue(mess);
		rmess.setResult(true);
		return rmess;
	}
	
	
	public static Msg<List<Message>> getMesByOrderState(Context context,List<String> ids,List<String> states){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<Message>> rmess = new Msg<List<Message>>();
		JooqHelper helper = new JooqHelper().where(MESSAGE.MESSAGEID.in(ids)).and(MESSAGE.OSTATE.in(states));
		List<Message> mess = create.select().from(MESSAGE)
				.where(helper.getCondition()).orderBy(MESSAGE.TIME.desc()).fetchInto(Message.class);
		rmess.setValue(mess);
		rmess.setResult(true);
		return rmess;
	}
	
	
	public static Boolean updateMessageFinish(Context context,List<String> ids){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		try{
			create.update(MESSAGE).set(MESSAGE.STATE,2).where(MESSAGE.MESSAGEID.in(ids)).execute();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static Msg<String> saveMes(Context context,String text,String userid,String type,String staffid,
			String orderid,	Integer issms,Integer state,String moblile,String ostate,String sendtype,
			Timestamp time,String targetid,Integer pushtype){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		MessageRecord mes = create.newRecord(MESSAGE);
		try{
			mes.setContext(text);
			mes.setIssms(issms);
			mes.setMobile(moblile);
			mes.setOrderid(orderid);
			mes.setOstate(ostate);
			mes.setSendtype(sendtype);
			mes.setStaffid(staffid);
			mes.setState(state);
			mes.setType(type);
			mes.setUserid(userid);
			mes.setTime(time);
			mes.setTargetid(targetid);
			mes.setPushtype(pushtype);
			mes.setMessageid(UUIDTool.getUUID());
			mes.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setResult(true);
		msg.setValue(mes.getMessageid());
		return msg;
	}
	
	
	public static Msg<List<Message>> getWaitSendMes(Context context){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<Message>> rmess = new Msg<List<Message>>();
		JooqHelper helper = new JooqHelper().where(MESSAGE.STATE.le(0).or(MESSAGE.STATE.isNull()));
		List<Message> mess = create.select().from(MESSAGE)
				.where(helper.getCondition()).orderBy(MESSAGE.TIME.desc()).fetchInto(Message.class);
		rmess.setValue(mess);
		rmess.setResult(true);
		return rmess;
	}
	
	
	public static Msg<String> getReplyMes(Context context,String userid,String staffid,String sendtype,String type){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		JooqHelper helper = new JooqHelper().where(MESSAGE.USERID.eq(userid).and(MESSAGE.STAFFID.eq(staffid)
				.and(MESSAGE.TYPE.eq(type).and(MESSAGE.SENDTYPE.eq(sendtype)))));
		
		List<Message> mess = create.select().from(MESSAGE)
				.where(helper.getCondition()).orderBy(MESSAGE.TIME.desc()).fetchInto(Message.class);
		if(mess.size()==1){
			rmsg.setValue(mess.get(0).getContext());
		}else{
			rmsg.setValue("");
		}
		rmsg.setResult(true);
		return rmsg;
	}
	
	public static Message getMesById(Context context,String messageid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Message mes = new Message();
		mes = create.fetchOne(MESSAGE, MESSAGE.MESSAGEID.eq(messageid)).into(Message.class);
		return mes;
	}
	
	public static Msg<String> PushMes(Context context,String messageid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		try{
			Message mes = getMesById(context,messageid);
			mes = create.fetchOne(MESSAGE, MESSAGE.MESSAGEID.eq(messageid)).into(Message.class);
			if(mes.getTargetid()!=null && !mes.getTargetid().equals("")){				
				PushMsgToSingleDevice.PushMsgToTag(context,messageid);
				rmsg.setResult(true);
			}else{				
				create.update(MESSAGE).set(MESSAGE.STATE,1).where(MESSAGE.MESSAGEID.eq(messageid)).execute();
				rmsg.setResult(false);
				rmsg.setValue("没有找到目标用户");
			}
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}
		
		return rmsg;
	}
}
