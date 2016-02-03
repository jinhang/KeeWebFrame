package com.kee.app.json.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MapResultJson {
	
	private Integer status;
	
	private String messsage;
	
	private JSONArray results;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public JSONArray getResults() {
		return results;
	}

	public void setResults(JSONArray results) {
		this.results = results;
	}

}
