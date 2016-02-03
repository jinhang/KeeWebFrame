package com.kee.app.json.model;

public class AndroidMessageJson {
	
	private String title;	
	
	private String description;
	
	private Integer notification_builder_id;
	
	private Integer notification_basic_style;
	
	private LightAppCtrlKeys lightapp_ctrl_keys;
	
	private Integer open_type;
	
	private Integer net_support;
	
	private CustomContent custom_content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = "message";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = "Message From Baidu Cloud Push-Service";
	}

	public Integer getNotification_builder_id() {
		return notification_builder_id;
	}

	public void setNotification_builder_id(Integer notification_builder_id) {
		this.notification_builder_id = 0;
	}

	public Integer getNotification_basic_style() {
		return notification_basic_style;
	}

	public void setNotification_basic_style(Integer notification_basic_style) {
		this.notification_basic_style = 7;
	}

	public LightAppCtrlKeys getLightapp_ctrl_keys() {
		return lightapp_ctrl_keys;
	}

	public void setLightapp_ctrl_keys(LightAppCtrlKeys lightapp_ctrl_keys) {
		this.lightapp_ctrl_keys = lightapp_ctrl_keys;
	}

	public Integer getOpen_type() {
		return open_type;
	}

	public void setOpen_type(Integer open_type) {
		this.open_type = 3;
	}

	public Integer getNet_support() {
		return net_support;
	}

	public void setNet_support(Integer net_support) {
		this.net_support = 1;
	}

	public CustomContent getCustom_content() {
		return custom_content;
	}

	public void setCustom_content(CustomContent custom_content) {
		this.custom_content = custom_content;
	}
}
