package com.kee.app.json.model;

public class Aps {
	
	private String alert;
	
	private Integer content_available;

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = "Message From Baidu Cloud Push-Service";
	}

	public Integer getContent_available() {
		return content_available;
	}

	public void setContent_available(Integer content_available) {
		this.content_available = 1;
	}

}
