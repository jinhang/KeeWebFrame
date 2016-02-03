package com.kee.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.kee.common.login.LoginServlet;

/**
 * @author mingjin_ding@163.com
 */
public class UDPConnect {
	static final Logger runLog = Logger.getLogger(UDPConnect.class);
	private static UDPConnect instance;
	private static DatagramSocket socket=null;
	private  Properties properties = new Properties();
	
	public UDPConnect(){
		try {	
			InputStream inputStream = new BufferedInputStream(new FileInputStream(LoginServlet.webRootPath + "/WEB-INF/classes/jdbc.properties"));
	        properties.load(inputStream);
	        inputStream.close();
			socket=new DatagramSocket(new InetSocketAddress(Integer.parseInt(properties.getProperty("udpPort","7855"))));
		} catch (SocketException e) {
			runLog.error("init localSocket error",e);
		} catch (IOException e) {	
			runLog.error("load jdbc.properties error",e);
		}
	}
	
	public static synchronized UDPConnect getInstance() {
		if (instance == null) 
			instance = new UDPConnect();
		return instance;
	}
	
	public void sendMsg(InetSocketAddress remoteAddress,UDPEncoder udpEncoder,String message){
		try {
			udpEncoder.setSequenceId(UDPEncoder.getSEQ());
			byte[] req=udpEncoder.encode(message);
			DatagramPacket datagrampacket = new DatagramPacket(req, req.length,remoteAddress);	
			socket.send(datagrampacket);
			runLog.fatal("f["+socket.getLocalSocketAddress()+"] >>> t["+remoteAddress+"] : length["+req.length+"] send udp message successfully !");
		} catch (Exception e) {
			runLog.error("f["+socket.getLocalSocketAddress().toString()+"] >>> t["+remoteAddress.toString()+"] send udp message error.");
		}
	}
}
