package com.kee.app.interfaces.impl;

import java.util.ArrayList;
import java.util.List;

import com.kee.app.interfaces.service.IHomeService;
import com.kee.app.model.HomeResult1;
import com.kee.model.tables.pojos.Message;
import com.kee.task.Msg;
import com.wfs.engine.common.EngineHelper;

import cn.kee.common.helper.MessageHelper;
import cn.kee.common.helper.OrderHelper;
import cn.kee.common.jooq.CodeEnum.OrderState;

public class HomeService implements IHomeService{

	@Override
	public Msg<HomeResult1> getOrderStateNum(String userid,Integer type) {
		HomeResult1 hr1 = new HomeResult1();
		Msg<HomeResult1> rmsg = new Msg<HomeResult1>();
		try{
			Msg<Integer> msg =  OrderHelper.getStateNum(EngineHelper.getContext(), OrderState.DQS.key, userid, type);
			if(msg.getResult()){
				if(type==1){
					hr1.setWsnum(msg.getValue());
				}else{
					hr1.setDlnum(msg.getValue());
				}			
			}
			msg =  OrderHelper.getStateNum(EngineHelper.getContext(), OrderState.DQJ.key, userid, type);
			if(msg.getResult()){
				if(type==0){
					hr1.setTknum(msg.getValue());
				}			
			}
			msg =  OrderHelper.getStateNum(EngineHelper.getContext(), OrderState.DZF.key, userid, type);
			if(msg.getResult()){
				if(type==0){
					hr1.setOtnum(hr1.getWsnum()+msg.getValue());
				}			
			}
			rmsg.setValue(hr1);
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}		
		return rmsg;
	}
	
	@Override
	public List<Message> getWaitOperationOrderMes(String userid, Integer type) {
		List<String> ids = new ArrayList();
		List<String> states = new ArrayList();
		Msg<List<Message>> rmsg = new Msg<List<Message>>();
		try{
			Msg<List<Message>> mess=MessageHelper.getMes(EngineHelper.getContext(), userid, type);
			if(mess.getResult() && mess.getValue().size()>0){
				for(int i=0;i<mess.getValue().size();i++){
					ids.add(mess.getValue().get(i).getMessageid());
				}
			}
			states.add(OrderState.DZF.key);
			states.add(OrderState.DQS.key);
			rmsg=MessageHelper.getMesByOrderState(EngineHelper.getContext(), ids, states);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}	
		return rmsg.getValue();
	}

	@Override
	public Boolean readMessage(List<String> messageids) {		
		return MessageHelper.updateMessageFinish(EngineHelper.getContext(), messageids);
	}

	@Override
	public List<Message> getWaitSendMeg(String userid,Integer type) {
		return MessageHelper.getMes(EngineHelper.getContext(), userid, type).getValue();
	}

}
