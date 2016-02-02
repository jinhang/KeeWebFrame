package com.kcb.app.interfaces.impl;

import java.sql.Timestamp;
import java.util.Date;








import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.kcb.app.interfaces.service.ISignedOrderService;
import com.kcb.common.util.DateUtil;
import com.wfs.common.helper.BusinessLibraryHelper;
import com.wfs.common.helper.LogisticsDataHelper;
import com.wfs.common.helper.MessageHelper;
import com.wfs.common.helper.OrderHelper;
import com.wfs.common.jooq.CodeEnum.MessageType;
import com.wfs.common.jooq.CodeEnum.PushMesType;
import com.wfs.common.jooq.CodeEnum.SendType;
import com.wfs.common.jooq.CodeEnum.SignedWay;
import com.wfs.dialect.jooq.cluster.JClient;
import com.wfs.engine.Context;
import com.wfs.engine.annotation.TA;
import com.wfs.engine.annotation.TransactionManager;
import com.wfs.engine.common.EngineHelper;
import com.wfs.model.tables.pojos.Order;
import com.wfs.task.Msg;

public class SignedOrderService implements ISignedOrderService{

	@Override
	public Msg<String> appointSigned(String orderid, String begintime,
			String endtime, String userid) {
		Msg<String> rmsg = new Msg<String>();
		Context context=EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Boolean flag = true;
		try{
			Date begin = DateUtil.getDateByFormatString(begintime,com.kcb.common.date.DateUtil.formart9);
			Date end = DateUtil.getDateByFormatString(endtime,com.kcb.common.date.DateUtil.formart9);			
			Order o = OrderHelper.getOrderById(context, orderid);
			if(OrderHelper.updateAppointTime(context, orderid, begin, end)){
				//写入订单操作记录库
				Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, o.getSigedtel()+"预约签收订单，快递单号："+o.getSendcode(), o.getDstaffid(), 
						o.getSuserid(), o.getOrderid(), o.getState());
				//写入消息队列					
				Msg<String> mmsg=MessageHelper.saveMes(context, "用户"+o.getSigedtel()+"预约签收订单，时间从 "+begintime+" 到  "+endtime+"快递单号："+o.getSendcode(),
						o.getSuserid(),MessageType.BM.value, o.getDstaffid(), o.getOrderid(), 0, 0, o.getSendstafftel(), o.getState(), 
						SendType.UTE.value,new Timestamp(begin.getTime()),o.getDstaffid(), PushMesType.NSAS.key);
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
	@TransactionManager(value = TA.JC)
	public Msg<String> signedPackage(String orderid, String userid,
			String signway) {
		Msg<String> rmsg = new Msg<String>();
		Context context=EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Boolean flag = true;
		try{
//			if(!(signway.equals(SignedWay.JQS.key))){
				if(OrderHelper.signedOrder(context, orderid, userid, signway)){
					Order o = OrderHelper.getOrderById(context, orderid);
					//写入订单操作记录库
					Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, userid, o.getDstaffid(), 
							o.getUserid(), o.getOrderid(), o.getState());
					//写入消息队列					
					Msg<String> mmsg=MessageHelper.saveMes(context, "用户"+o.getStel()+SignedWay.chageKeyToValue(signway)+"签收订单，快递单号："+o.getSendcode(),
							o.getSuserid(),MessageType.BM.value, o.getDstaffid(), o.getOrderid(), 0, 0, o.getSigedtel(), o.getState(), 
							SendType.UTE.value,new Timestamp(new Date().getTime()),o.getSuserid(), PushMesType.NPS.key);					
					//主动发通知
					MessageHelper.PushMes(context, mmsg.getValue());
					if(!signway.equals(SignedWay.JQS.key)){
						LogisticsDataHelper.uploadOKLogiscData(context,orderid,o.getDstaffid());		
					}else{
						if(o.getSuserid()!=null && o.getSuserid().equals(userid)){
							LogisticsDataHelper.uploadYNLogiscData(context,o.getSendcode(), "02", 
									"客户拒收", o.getDstaffid());
						}
					}
				}
//			}
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
	public Msg<String> refusedPackage(String pronum, String memo, String staffid,String orderid) {
		Msg<String> rmsg = new Msg<String>();
		Context context=EngineHelper.getContext();
		JClient client = context.getJc().getDefaultClient();
		DataSourceTransactionManager manager = client.getTransactionManager();
		TransactionStatus tx =  manager.getTransaction(new DefaultTransactionDefinition());
		Boolean flag = true;
		try{			
			Order o = OrderHelper.getOrderById(context, orderid);
			LogisticsDataHelper.uploadYNLogiscData(context,o.getSendcode(), pronum, 
					memo,staffid);
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

}
