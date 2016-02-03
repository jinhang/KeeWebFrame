package com.kee.common.socket.action;

import java.util.ArrayList;
import java.util.List;

import com.kee.engine.wrapper.model.Msg;
//import com.kcb.client.order.service.DwrCallServiceOrder;
import com.wfos.engine.common.WebConfig;

public class SomeServer {
	
	
	public int doSome(Object message) {
//		DwrCallServiceOrder orderservice =  (DwrCallServiceOrder)WebConfig.getBean("DwrCallServiceOrder");
		String msg = message.toString();
		
		List<String> useSendcode = new ArrayList<String>();
		
		int byteNum = Integer.parseInt(msg.substring(0,4).trim());
		int hasNum = byteNum/135;
		msg = msg.substring(5);
		
		for(int i = 0;i<hasNum;i++){
			String sendcode = msg.substring(3+(i*135),19+(i*135));
			useSendcode.add(sendcode.trim());
		}
		
//		Msg m = orderservice.handByMina(useSendcode);
		
//		return m.ivalue;
		return 1;
	}

}
