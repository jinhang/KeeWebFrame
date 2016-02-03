package com.kee.common.login;

import java.net.InetSocketAddress;

import com.kee.common.util.UDPEncoder;

public class UDPMessage {
	private InetSocketAddress address;
	private UDPEncoder encoder;
	private String msg;
	
	public UDPMessage(InetSocketAddress address,UDPEncoder encoder,String msg) {
		this.address=address;
		this.encoder=encoder;
		this.msg=msg;
	}
	
	public InetSocketAddress getAddress() {
		return address;
	}
	public void setAddress(InetSocketAddress address) {
		this.address = address;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UDPEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(UDPEncoder encoder) {
		this.encoder = encoder;
	}
	
}
