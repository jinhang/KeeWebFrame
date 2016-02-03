package com.kee.app.json.model;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class LocationListJson {
	
	private String info;
	
	private Integer status;
	
	private Integer count;
	
	private List<PoisJson> datas;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<PoisJson> getDatas() {
		return datas;
	}

	public void setDatas(List<PoisJson> datas) {
		this.datas = datas;
	}
}
