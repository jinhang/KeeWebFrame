package com.kee.app.json.model;

public class IosMessageJson {
	
	private Aps aps;
	
	private LightAppCtrlKeys lightapp_ctrl_keys;
	
	private CustomContent custom_content;

	public Aps getAps() {
		return aps;
	}

	public void setAps(Aps aps) {
		this.aps = aps;
	}

	public LightAppCtrlKeys getLightapp_ctrl_keys() {
		return lightapp_ctrl_keys;
	}

	public void setLightapp_ctrl_keys(LightAppCtrlKeys lightapp_ctrl_keys) {
		this.lightapp_ctrl_keys = lightapp_ctrl_keys;
	}

	public CustomContent getCustom_content() {
		return custom_content;
	}

	public void setCustom_content(CustomContent custom_content) {
		this.custom_content = custom_content;
	}

}
