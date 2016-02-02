package com.kcb.app.interfaces.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.kcb.app.interfaces.service.ITakeOnlineOrderService;
import com.wfs.common.helper.BusinessLibraryHelper;
import com.wfs.common.helper.MessageHelper;
import com.wfs.common.helper.OrderHelper;
import com.wfs.common.helper.RelatedinfoHelper;
import com.wfs.common.helper.UserHelper;
import com.wfs.common.jooq.CodeEnum.MessageType;
import com.wfs.common.jooq.CodeEnum.PushMesType;
import com.wfs.common.jooq.CodeEnum.SendType;
import com.wfs.engine.Context;
import com.wfs.engine.common.EngineHelper;
import com.wfs.model.tables.pojos.Order;
import com.wfs.model.tables.pojos.User;
import com.wfs.task.Msg;

public class TakeOnlineOrder implements ITakeOnlineOrderService{

	@Override
	public Msg<String> confirmOrder(String staffid, String orderid,
			String userid) {
		Msg<String> rmsg = new Msg<String>();
		Context context=EngineHelper.getContext();
		Boolean flag = true;
		try{
			if(OrderHelper.confirmTakeOrder(context, orderid, staffid)){
				Order o = OrderHelper.getOrderById(context, orderid);
				User u = UserHelper.getUserById(context, userid);
				//写入订单操作记录库
				Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, o.getStafftel()+"确认接单，客户:"+u.getUsername(), o.getStaffid(), 
						o.getUserid(), o.getOrderid(), o.getState());
				//写入消息队列					
				Msg<String> mmsg=MessageHelper.saveMes(context, "业务员"+o.getStafftel()+"已经确认接收您发往"+o.getAddress()+"的订单",
						o.getUserid(),MessageType.BM.value, o.getStaffid(), o.getOrderid(), 0, 0, u.getUsername(), o.getState(), 
						SendType.UTE.value,new Timestamp(new Date().getTime()),o.getUserid(), PushMesType.NUCP.key);
				//主动发通知
				MessageHelper.PushMes(context, mmsg.getValue());
			}else{
				flag=false;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
			rmsg.setRollback(true);
			rmsg.setResult(false);
			return rmsg;
		}
		if(!flag){
			rmsg.setRollback(true);
		}
		rmsg.setResult(flag);
		return rmsg;
	}

	@Override
	public Msg<String> pickUpOrder(String staffid, String orderid,
			String userid, String sendcode, String serialnum, String weight,
			String picture, String expresscharge) {
		Msg<String> rmsg = new Msg<String>();
		Context context=EngineHelper.getContext();
		Boolean flag = true;
		if(sendcode!=null && sendcode.equals("")){
			sendcode=null;
		}
		try{		
//			RelatedinfoHelper.uploadImage(context, userid, staffid,orderid,picture, 0);
			if(weight==null || weight.equals("")){
				weight="0";
			}
			if(OrderHelper.pickUpOrder(context, orderid, staffid,sendcode,serialnum,Double.valueOf(weight),Double.valueOf(expresscharge))){				
				Order o = OrderHelper.getOrderById(context, orderid);
				User u = UserHelper.getUserById(context, userid);				
				//写入订单操作记录库
				Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, "业务员"+o.getStafftel()+"确认取单，客户:"+u.getUsername(), o.getStaffid(), 
						o.getUserid(), o.getOrderid(), o.getState());
				//写入消息队列					
				Msg<String> mmsg=MessageHelper.saveMes(context, "业务员"+o.getStafftel()+"已经确认揽件您发往"+o.getAddress()+"的订单，请付款",
						o.getUserid(),MessageType.BM.value, o.getStaffid(), o.getOrderid(), 0, 0, u.getUsername(), o.getState(), 
						SendType.UTE.value,new Timestamp(new Date().getTime()),o.getUserid(), PushMesType.NUTO.key);
				//主动发通知
				MessageHelper.PushMes(context, mmsg.getValue());
			}else{
				flag=false;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
			rmsg.setRollback(true);
			rmsg.setResult(false);
			return rmsg;
		}
		if(!flag){
			rmsg.setRollback(true);
		}
		rmsg.setResult(flag);
		return rmsg;
	}

}
