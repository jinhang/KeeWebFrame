package com.kcb.app.interfaces.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.kcb.app.assign.ImageApi;
import com.kcb.app.interfaces.service.IOrderCenterService;
import com.kcb.app.model.IMOResult;
import com.kcb.app.model.OrderDetail;
import com.kcb.app.model.OrderResult1;
import com.kcb.app.model.OrderResult2;
import com.kcb.common.util.DateUtil;
import com.wfs.common.helper.BusinessLibraryHelper;
import com.wfs.common.helper.EvaluationHelper;
import com.wfs.common.helper.ImoMessageHelper;
import com.wfs.common.helper.MessageHelper;
import com.wfs.common.helper.OrderHelper;
import com.wfs.common.helper.RelatedinfoHelper;
import com.wfs.common.helper.RelationHelper;
import com.wfs.common.helper.UserHelper;
import com.wfs.common.jooq.CodeEnum.EvaType;
import com.wfs.common.jooq.CodeEnum.MessageType;
import com.wfs.common.jooq.CodeEnum.PushMesType;
import com.wfs.common.jooq.CodeEnum.SendType;
import com.wfs.dialect.jooq.cluster.JClient;
import com.wfs.engine.Context;
import com.wfs.engine.annotation.TA;
import com.wfs.engine.annotation.TransactionManager;
import com.wfs.engine.common.EngineHelper;
import com.wfs.model.tables.pojos.Evaluation;
import com.wfs.model.tables.pojos.Imomessage;
import com.wfs.model.tables.pojos.Message;
import com.wfs.model.tables.pojos.Order;
import com.wfs.model.tables.pojos.User;
import com.wfs.task.Msg;

public class OrderCenterService implements IOrderCenterService{

	@Override
	public List<OrderResult1> getOrderList(String userid, String begintime,
			String endtime, String states, String suserid,String dstaffid,
			String staffid, Integer first, Integer limit) {
		Msg<List<OrderResult1>> rmsg = new Msg<List<OrderResult1>>();
		List<OrderResult1> or1s = new ArrayList<OrderResult1>();
		String state [];
		if(states!=null && !states.equals("")){
			state =states.split(",");
		}else{
			state = null;
		}
		Msg<List<Order>> msg = OrderHelper.getOrderList(EngineHelper.getContext(),state,
				begintime,endtime,userid,staffid,suserid,dstaffid,first,limit);
		if(msg.getResult()){
			List<Order> os = msg.getValue();
			for(int i=0;i<os.size();i++){
				OrderResult1 or1 = new OrderResult1();
				or1.setConsignee(os.get(i).getConsignee());
				or1.setReceiveprovince(os.get(i).getReceiveprovince());
				or1.setReceivecity(os.get(i).getReceivecity());
				or1.setReceivedistrict(os.get(i).getReceivedistrict());
				or1.setTelphone(os.get(i).getTelphone());
				or1.setAddress(os.get(i).getAddress());
				or1.setSendcode(os.get(i).getSendcode());
				or1.setState(os.get(i).getState());
				or1.setReserveinfo(os.get(i).getReserveinfo());
				or1.setTime(com.kcb.common.date.DateUtil.getCurrDateStr(com.kcb.common.date.DateUtil.formart9,os.get(i).getCreatetime()));
				or1.setOrderid(os.get(i).getOrderid());
				if(os.get(i).getAsbegin()!=null && os.get(i).getApend()!=null){
					or1.setAsbegin(com.kcb.common.date.DateUtil.getCurrDateStr(com.kcb.common.date.DateUtil.formart9,os.get(i).getAsbegin()));
					or1.setApend(com.kcb.common.date.DateUtil.getCurrDateStr(com.kcb.common.date.DateUtil.formart9,os.get(i).getApend()));
				}
				or1.setSender(os.get(i).getSender());
				or1.setStel(os.get(i).getStel());
				or1.setSenderaddress(os.get(i).getSenderaddress());
				or1.setSendstaffname(os.get(i).getSendstaffname());
				or1.setSendstafftel(os.get(i).getSendstafftel());
				or1.setSigedname(os.get(i).getSigedname());
				or1.setSigedtel(os.get(i).getSigedtel());
				or1.setSignedway(os.get(i).getSignedway());
				or1.setTakeaddress(os.get(i).getTakeaddress());
				or1.setSerialnum(os.get(i).getSerialnum());
				or1s.add(or1);
			}			
			rmsg.setResult(true);
			rmsg.setValue(or1s);
		}else{
			rmsg.setResult(false);
			return rmsg.getValue();
		}
		return rmsg.getValue();
	}

	@Override
	public Msg<OrderResult2> getOnlineOrderFee(String orderid) {
		Msg<OrderResult2> rmsg = new Msg<OrderResult2>();
		OrderResult2 or2 = new OrderResult2();
		try{
			Order o = OrderHelper.getOrderById(EngineHelper.getContext(), orderid);
			or2.setTotalprice(o.getTotalprice());
			or2.setExpresscharge(o.getExpresscharge());
			or2.setServerfee(o.getServerfee());
			or2.setDiscountfee(o.getDiscountfee());
			or2.setStaffid(o.getStaffid());
			rmsg.setResult(true);
			rmsg.setValue(or2);
		}catch(Exception e){
			rmsg.setResult(false);
			return rmsg;
		}
		return rmsg;
	}
	
	@Override
	public Msg<String> confermOnlineOrderPay(String totalprice, String serverfee,
			String discountfee, String expresscharge, String orderid,
			String userid, String staffid, String payway, String payfee) {
		Msg<String> rmsg = new Msg<String>();
		Context context=EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Boolean flag = true;
		try{
			if(OrderHelper.confirmPay(context, Double.valueOf(totalprice), Double.valueOf(serverfee), 
					Double.valueOf(discountfee), Double.valueOf(expresscharge), orderid, payway, Double.valueOf(payfee))){
				Order o = OrderHelper.getOrderById(context, orderid);
				//写入订单操作记录库
				Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, o.getStel()+"确认支付", o.getStaffid(), 
						o.getUserid(), o.getOrderid(), o.getState());
				//写入消息队列					
				Msg<String> mmsg=MessageHelper.saveMes(context, "用户"+o.getStel()+"寄往"+o.getAddress()+"已经支付成功"+o.getPayfee(),
						o.getUserid(),MessageType.BM.value, o.getStaffid(), o.getOrderid(), 0, 0, o.getStel(), o.getState(), 
						SendType.UTE.value,new Timestamp(new Date().getTime()),o.getStaffid(), PushMesType.NSPAYED.key);
				//主动发通知
				MessageHelper.PushMes(context, mmsg.getValue());
			}else{
				flag=false;
				manager.rollback(tx);
			}			
		}catch(Exception e){
			e.printStackTrace();
			flag=false;			
			rmsg.setResult(false);
			manager.rollback(tx);
			return rmsg;
		}finally {
			if (tx != null) {
				if (!tx.isCompleted())
					manager.commit(tx);
			}
		}	
		rmsg.setResult(flag);
		return rmsg;
	}

	@Override
	public Msg<OrderDetail> getOrderDetail(String orderid,String evatype) {
		Context context = EngineHelper.getContext();
		OrderDetail od = new OrderDetail();
		Msg<OrderDetail> rmsg = new Msg<OrderDetail>();
		try{
			Order o = OrderHelper.getOrderById(context, orderid);
			od.setTotalprice(o.getTotalprice());
			od.setServerfee(o.getServerfee());
			od.setDiscountfee(o.getDiscountfee());
			od.setExpresscharge(o.getExpresscharge());
			od.setOrderid(o.getOrderid());
			od.setUserid(o.getUserid());
			od.setStaffid(o.getStaffid());
			od.setConsignee(o.getConsignee());
			od.setReceiveprovince(o.getReceiveprovince());
			od.setReceivecity(o.getReceivecity());
			od.setReceivedistrict(o.getReceivedistrict());
			od.setTelphone(o.getTelphone());
			od.setAddress(o.getAddress());
			od.setSendcode(o.getSendcode());
			od.setState(o.getState());
			od.setStaffname(o.getStaffname());
			od.setStafftel(o.getStafftel());
			od.setSenderaddress(o.getSenderaddress());
			od.setStel(o.getStel());
			od.setSender(o.getSender());
			od.setSenderprovince(o.getSenderprovince());
			od.setSenderdistrict(o.getSenderdistrict());
			od.setSendercity(o.getSendercity());
			od.setWeight(o.getWeight());
			od.setSuserid(o.getSuserid());
			od.setSigedname(o.getSigedname());
			od.setSigedtel(o.getSigedtel());
			od.setDstaffid(o.getDstaffid());
			od.setSendstaffname(o.getSendstaffname());
			od.setSendstafftel(o.getSendstafftel());
			od.setSignedway(o.getSignedway());
			od.setPackagetype(o.getPackagetype());
			od.setWeightinfo(o.getWeightinfo());
			od.setSerialnum(o.getSerialnum());
			Msg<Evaluation> emsg = EvaluationHelper.getEvaluationByOrderid(context, orderid,evatype);
			Msg<String> imsg=RelatedinfoHelper.getImages(context, orderid);
			if(imsg.getResult()){
				od.setImage(imsg.getValue());
			}else{
				od.setImage(null);
			}
			if(emsg.getResult()){
				Evaluation e = emsg.getValue();
				od.setEtime(e.getEtime());
				od.setEattitude(e.getEattitude());
				od.setMemo(e.getMemo());
				od.setIsshow(e.getIsshow());
			}
			if(o.getStaffid()!=null && !o.getStaffid().equals("")){
				User staff=UserHelper.getUserById(context, o.getStaffid());
				od.setPickstaffimage(staff.getImage());
			}
			if(o.getUserid()!=null && !o.getUserid().equals("")){
				User user=UserHelper.getUserById(context, o.getUserid());
				od.setUserimage(user.getImage());
			}
			if(o.getDstaffid()!=null && !o.getDstaffid().equals("")){
				User dstaff=UserHelper.getUserById(context, o.getDstaffid());
				od.setDstaffimage(dstaff.getImage());
			}
			if(o.getSuserid()!=null && !o.getSuserid().equals("")){
				User suser=UserHelper.getUserById(context, o.getSuserid());
				od.setSuserimage(suser.getImage());
			}
//			Msg<String> reply = new Msg<String>();
//			if(evatype!=null && !evatype.equals("")){
//				if(evatype.equals(EvaType.OR.value)){
//					reply = MessageHelper.getReplyMes(context, o.getUserid(), o.getStaffid(), SendType.ETU.value, MessageType.IMM.value);
//				}else{
//					reply = MessageHelper.getReplyMes(context, o.getSuserid(), o.getDstaffid(), SendType.ETU.value, MessageType.IMM.value);
//				}
//				od.setReplycontext(reply.getValue());
//			}		
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			return rmsg;
		}		
		rmsg.setResult(true);
		rmsg.setValue(od);
		return rmsg;
	}

	@Override
	public Msg<String> followStaff(String staffid, String userid,
			String followtype) {
		return RelationHelper.saveRelation(EngineHelper.getContext(), userid, staffid, followtype);
	}

	@Override
	public Msg<String> evaluationStaff(String staffid, String userid,
			String orderid, String memo, Integer etime, Integer eattitude,
			Integer isshow,String evatype) {
		Msg<String> rmsg = new Msg<String>();
		Context context=EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Boolean flag = true;
		try{
			Msg<String> emsg = EvaluationHelper.saveEvaluation(context,staffid,userid,orderid, memo, etime, eattitude, isshow,evatype);
			if(emsg.getResult()){
				EvaluationHelper.StatisEva(context, staffid);
				Order o = OrderHelper.getOrderById(context, orderid);
				User staff = UserHelper.getUserById(context,staffid);
				User user = UserHelper.getUserById(context,userid);
				//写入订单操作记录库
				Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, user.getUsername()+"评价业务员"+staff.getUsername(), staff.getUserid(), 
						user.getUserid(), o.getOrderid(), o.getState());
				//写入消息队列					
				Msg<String> mmsg=MessageHelper.saveMes(context, "用户"+user.getUsername()+"评价业务员"+staff.getUsername(),
						user.getUserid(),MessageType.BM.value, staff.getUserid(), o.getOrderid(), 0, 0, staff.getUsername(), o.getState(), 
						SendType.UTE.value,new Timestamp(new Date().getTime()),staff.getUserid(), PushMesType.NSE.key);
				//记录imo信息
				Msg<String> imomsg = ImoMessageHelper.savImoMes(context, memo, userid, evatype, orderid,1);
				//主动发通知
				MessageHelper.PushMes(context, mmsg.getValue());
			}else{
				flag=false;
				manager.rollback(tx);
			}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
			rmsg.setResult(false);
			manager.rollback(tx);
			return rmsg;
		}finally {
			if (tx != null) {
				if (!tx.isCompleted())
					manager.commit(tx);
			}
		}	
		rmsg.setResult(flag);
		return rmsg;
	}

	@Override
	public List<IMOResult> getImoMes(String orderid,Integer first,Integer limit,String evatype) {
		Msg<List<Imomessage>> rmsg = new Msg<List<Imomessage>>();
		List<IMOResult> imos = new ArrayList<IMOResult>();
		rmsg = ImoMessageHelper.getImoMesList(EngineHelper.getContext(), orderid, first, limit,evatype);
		if(rmsg.getResult()){
			List<Imomessage> imosm= rmsg.getValue();
			for(int i=0;i<imosm.size();i++){
				IMOResult imo = new IMOResult();
				imo.setName(imosm.get(i).getName());
				imo.setCreatetime(com.kcb.common.date.DateUtil.getCurrDateStr(com.kcb.common.date.DateUtil.formart9,imosm.get(i).getCreatetime()));
				imo.setImage(imosm.get(i).getImage());
				imo.setText(imosm.get(i).getText());
				imos.add(imo);
			}
		}
		return imos;
	}

	@Override
	public Msg<String> uploadRecords(String orderid, String records) {		
		String filename=orderid+com.kcb.common.date.DateUtil.getCurrDateStr(com.kcb.common.date.DateUtil.formart8,new Date())+".mp3";
		return ImageApi.saveRecords(filename, records, "records");
	}
}
