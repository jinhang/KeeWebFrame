package com.kee.app.assign;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kee.common.sms.PushMsgToSingleDevice;
import com.kee.common.sms.SendSms;
import com.kee.model.tables.pojos.Logisticsdata;
import com.kee.model.tables.pojos.Message;
import com.kee.task.Msg;
import com.kee.zm.interfaces.UploadOdData;
import com.wfs.engine.Context;
import com.wfs.engine.common.EngineHelper;

import cn.kee.common.helper.LogisticsDataHelper;
import cn.kee.common.helper.MessageHelper;
import cn.kee.common.jooq.CodeEnum.ScanType;

public class AutoSendMessage {
	
	private boolean auto_audit = true;
	
	private boolean upload_zm_data =true;
	
	public Msg<String> AutoSend() throws Exception{
		Msg<String> rmsg = new Msg<String>();
		if(!auto_audit){
			rmsg.setResult(false);
			return rmsg;
		}
		Context context = EngineHelper.getContext();
		Date current = new Date();//当前时间
		List<Message> mess = new ArrayList<Message>();
//		long myTime=(current.getTime()/1000)+60*30; 
//		Date newDate = (Date)current.clone();//半小时后时间
//		newDate.setTime(myTime*1000);		 
		Msg<List<Message>> msg = MessageHelper.getWaitSendMes(context);
		if(msg.getResult()){
			mess=msg.getValue();
			for(int i=0;i<mess.size();i++){
				Message mes = mess.get(i);
				MessageHelper.PushMes(context, mes.getMessageid());
				//发送短信
				if(mes.getIssms()!=null && mes.getIssms()==1 && mes.getState()!=null && mes.getState()!=2){
					if(mes.getTime()!=null && mes.getTime().before(current)){
						MessageHelper.sendSMSMes(context, mes.getMessageid());
					}
				}
			}
		}else{
			rmsg.setResult(false);
			rmsg.setRollback(true);
			return rmsg;
		}
		return rmsg;
	}
	
	public Msg<String> AutoUploadZM() throws Exception{
		Msg<String> rmsg = new Msg<String>();
		if(!upload_zm_data){
			rmsg.setResult(false);
			return rmsg;
		}
		Context context = EngineHelper.getContext();
		Date current = new Date();//当前时间
		List<Logisticsdata> mess = new ArrayList<Logisticsdata>();
		try{			
			uploadZMData(context,ScanType.OD.key);
			uploadZMData(context,ScanType.YN.key);
			uploadZMData(context,ScanType.OK.key);
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}
		return rmsg;
	}
	
	private Msg<String> uploadZMData(Context context,String scantype){
		Msg<String> rmsg = new Msg<String>();
		try{
			Msg<List<Logisticsdata>> msg = LogisticsDataHelper.getWaitUploadLogsticData(context,scantype);
			if(msg.getResult()){
				List<String> ids = new ArrayList();
				List<String> nids = new ArrayList();
				List<Logisticsdata> lds = msg.getValue();
				for(int i=0;i<lds.size();i++){
					ids.add(lds.get(i).getLogisticsdataid());
				}
				Msg<List<String>> umsg=UploadOdData.OdData(lds, scantype);
				if(!umsg.getResult()){
					for(int i=0;i<umsg.getValue().size();i++){
						nids.add(lds.get(Integer.valueOf(umsg.getValue().get(i))).getLogisticsdataid());
					}
				}
				LogisticsDataHelper.updateLogisState(context, ids, nids);
			}
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}		
		return rmsg;
	}

	public boolean isAuto_audit() {
		return auto_audit;
	}

	public void setAuto_audit(boolean auto_audit) {
		this.auto_audit = auto_audit;
	}

	public boolean isUpload_zm_data() {
		return upload_zm_data;
	}

	public void setUpload_zm_data(boolean upload_zm_data) {
		this.upload_zm_data = upload_zm_data;
	}

}
