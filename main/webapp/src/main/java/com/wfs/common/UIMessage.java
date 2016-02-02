package com.wfs.common;

import java.util.ArrayList;
import java.util.List;

public class UIMessage {
	
	public Integer state;
	
	public String svalue;
	
	public String color;
	
	public boolean result;
	
	public List<String> value;
	
	public List context = new ArrayList();
	
	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

	public UIMessage(Integer state,String svalue,String color){
		this.state = state;
		this.svalue = svalue;
		this.color = color;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSvalue() {
		return svalue;
	}

	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public List getContext() {
		return context;
	}

	public void setContext(List context) {
		this.context = context;
	}

}
