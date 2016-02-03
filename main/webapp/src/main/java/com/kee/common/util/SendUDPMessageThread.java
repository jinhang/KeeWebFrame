package com.kee.common.util;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.kee.common.login.UDPMessage;

@SuppressWarnings({"unchecked","rawtypes"})
public class SendUDPMessageThread extends Thread {
	static final Logger runLog = Logger.getLogger(SendUDPMessageThread.class);
	private UDPConnect udpConnect=UDPConnect.getInstance();
	public LinkedList quene = null;
	private int maxMsgNum = 2048;

	public SendUDPMessageThread() {
		this.quene = new LinkedList();
	}

	public void run() {
		while (true) {
			try {
				Object obj=getTask();
				if(null == obj) 
					return;
				if(obj instanceof UDPMessage) {
					UDPMessage msg=(UDPMessage)obj;
					udpConnect.sendMsg(msg.getAddress(),msg.getEncoder(),msg.getMsg());
				}else{
					return;
				}
			} catch (Exception e) {
				runLog.error("SendUDPMessageThread run error", e);
			}
		}
	}

	public synchronized void addTask(Object obj) {
		if (this.quene.size() >= maxMsgNum) {
			this.quene.removeFirst();
		}
		this.quene.add(obj);
		super.notifyAll();
	}

	public synchronized Object getTask() {
		if (this.quene.isEmpty()){
			try {
				super.wait();
			} catch (InterruptedException e) {
			}
		}
		return this.quene.removeFirst();
	}
}
