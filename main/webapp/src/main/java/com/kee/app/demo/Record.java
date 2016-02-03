package com.kee.app.demo;

import java.util.Date;
public class Record {
	
	private int id;
	//private String name;
	//ex.id as id,ex.sendcode as sendcode,ex.recordtime as recordtime,ex.province as province,ex.city as city,ex.district as district)
	private String sendcode;	
	private String recordtime;
	private String address;
	//private String city;
	//private String district;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSendcode() {
		return sendcode;
	}
	public void setSendcode(String sendcode) {
		this.sendcode = sendcode;
	}
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
