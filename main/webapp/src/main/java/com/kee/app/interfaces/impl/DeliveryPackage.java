package com.kee.app.interfaces.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.kee.app.interfaces.service.IDeliveryPackageService;
import com.kee.app.model.DResult1;
import com.kee.common.date.DateUtil;
import com.kee.model.tables.pojos.Order;
import com.kee.task.Msg;
import com.wfs.engine.Context;
import com.wfs.engine.common.EngineHelper;

import cn.kee.common.helper.BusinessLibraryHelper;
import cn.kee.common.helper.ImoMessageHelper;
import cn.kee.common.helper.MessageHelper;
import cn.kee.common.helper.OrderHelper;
import cn.kee.common.helper.RelatedinfoHelper;
import cn.kee.common.jooq.CodeEnum.MessageType;
import cn.kee.common.jooq.CodeEnum.OrderState;
import cn.kee.common.jooq.CodeEnum.PushMesType;
import cn.kee.common.jooq.CodeEnum.SendType;

public class DeliveryPackage implements IDeliveryPackageService{

	@Override
	public Msg<DResult1> getPakcageNum(String staffid) {
		Context context = EngineHelper.getContext();
		DResult1 dr1= new DResult1(); 
		Msg<DResult1> rmsg = new Msg<DResult1>();
		Msg<Integer> msg = OrderHelper.getDeliveryNum(context, OrderState.DQS.key, 
				staffid, DateUtil.getStartTime(), DateUtil.getEndTime());
		dr1.setLastnum(msg.getValue());
		msg = OrderHelper.getDeliveryNum(context, OrderState.YQS.key, 
				staffid, DateUtil.getStartTime(), DateUtil.getEndTime());
		dr1.setFinishnum(msg.getValue());
		dr1.setTotalnum(dr1.getLastnum()+dr1.getFinishnum());
		rmsg.setResult(true);
		rmsg.setValue(dr1);
		return rmsg;
	}

	@Override
	public Msg<String> uploadSignedInfo(String picture, String staffid,
			String userid, String orderid,Integer type) {
		Msg<String> rmsg = new Msg<String>();
		Context context = EngineHelper.getContext();
		String pictures [];
		if(!picture.equals("")){
			pictures =picture.split(",");
		}else{
			rmsg.setResult(false);
			return rmsg;
		}
		try{
			for(int i=0;i<pictures.length;i++){
				RelatedinfoHelper.uploadImage(context, userid, staffid,orderid, pictures[i], type);
			}			
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}		
		return rmsg;
	}

	@Override
	public Msg<String> replyMessage(String text, String staffid,
			String userid, String orderid,Integer type,String evatype) {
		Msg<String> rmsg = new Msg<String>();
		Context context=EngineHelper.getContext();
		Boolean flag = true;
		String sendtype="";
		String targetid = "";
		String imownerid="";
		try{			
			Order o = OrderHelper.getOrderById(context, orderid);
			//写入订单操作记录库
			Msg<String> blmsg=BusinessLibraryHelper.saveBL(context, o.getSendstafftel()+"回复客户"+o.getSigedtel()+"内容"+text, staffid, 
					userid, o.getOrderid(), o.getState());
			if(type==0){
				sendtype=SendType.UTE.value;
				targetid=staffid;
				imownerid=userid;
			}else{
				sendtype=SendType.ETU.value;
				targetid=userid;
				imownerid=staffid;
			}
			//写入消息队列					
			Msg<String> mmsg=MessageHelper.saveMes(context, text,userid,MessageType.IMM.value, staffid, o.getOrderid(), 
					0, 0, o.getStel(), o.getState(),sendtype,new Timestamp(new Date().getTime()),targetid, PushMesType.SRM.key);
			//记录imo信息
			Msg<String> imomsg = ImoMessageHelper.savImoMes(context, text, imownerid, evatype, orderid,type);
			//主动发通知
			MessageHelper.PushMes(context, mmsg.getValue());
				
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
