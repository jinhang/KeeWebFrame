package com.wfs.common;

import java.util.List;
import java.util.Map;

public class GridMessage {
	
	public int first;
	public int limit;
	public int total;
	public String xmlB;
	public List<Map<String, Object>> lvalue;
	
	public int getFirst() {
		return first;
	}
	public GridMessage setFirst(int first) {
		this.first = first;
		return this;
	}
	public int getLimit() {
		return limit;
	}
	public GridMessage setLimit(int limit) {
		this.limit = limit;
		return this;
	}
	public int getTotal() {
		return total;
	}
	public GridMessage setTotal(int total) {
		this.total = total;
		return this;
	}
	public String getXmlB() {
		return xmlB;
	}
	public GridMessage setXmlB(String xmlB) {
		this.xmlB = xmlB;
		return this;
	}

	public List<Map<String, Object>> getLvalue() {
		return lvalue;
	}

	public void setLvalue(List<Map<String, Object>> lvalue) {
		this.lvalue = lvalue;
	}

}
