package com.kcb.app.json.model;

import java.util.List;

public class AroundLocation {
	
	private String info;
	
	private Integer status;
	
	private Integer count;
	
	private List<Datas>  datas;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Datas> getDatas() {
		return datas;
	}

	public void setDatas(List<Datas> datas) {
		this.datas = datas;
	}
	
	

}
