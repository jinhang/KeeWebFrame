package com.kee.common.util;

import org.apache.log4j.Logger;



public class UDPDefine {
	static final Logger runLog = Logger.getLogger(UDPDefine.class);
	public static final int  udp_msg_total=50;
	
	public static SendUDPMessageThread sudpThread = null;
	
	static{
		UDPDefine.sudpThread=new SendUDPMessageThread();
		runLog.info("SendUDPMessageThread Initialization is complete.");
		UDPDefine.sudpThread.start();
		runLog.info("SendUDPMessageThread startup !");
	}	
}
