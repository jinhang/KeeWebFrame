/**
 * Title: Msg.java
 * Description:
 * 
 * @Copyright: Copyright (c) 2000-2009. All rights reserved.
 * @Company: KingYi杭州金安易计算机技术有限公司
 */
package com.wfos.engine.wrapper.model;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.directwebremoting.io.FileTransfer;


/**
 *
 */
public class Msg implements Serializable {
	//int类型返回值
	public int ivalue;
	//boolean类型返回值
	public boolean bvalue;
	//String类型返回值
	public String svalue;
	//List类型返回值
	public List lvalue = new ArrayList();
	//Object类型返回值
	public Object ovalue;
	//Map类型返回值
	public Map<String,Object> mvalue;
	//Set类型返回值
	public Set tvalue;
	//返回类型标识
	public int state = 1;
	public double dvalue;
	public BigDecimal bdvalue;
	public long lgvalue;
	public Object[] cvalue;
	public boolean willreturn = false;
	public String breakto = null;
	private FileTransfer filevalue = null;
	private InputStream isValue = null;
	private byte[] byvalue;
	
	public byte[] getByvalue() {
		return byvalue;
	}
	public void setByvalue(byte[] byvalue) {
		this.byvalue = byvalue;
	}
	public boolean isWillreturn() {
		return willreturn;
	}
	public void setWillreturn(boolean willreturn) {
		this.willreturn = willreturn;
	}
	public String getBreakto() {
		return breakto;
	}
	public void setBreakto(String breakto) {
		this.breakto = breakto;
	}
	public Object[] getCvalue() {
		return cvalue;
	}
	public void setCvalue(Object[] cvalue) {
		this.cvalue = cvalue;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getIvalue() {
		return ivalue;
	}
	public void setIvalue(int ivalue) {
		this.ivalue = ivalue;
	}
	public boolean isBvalue() {
		return bvalue;
	}
	public void setBvalue(boolean bvalue) {
		this.bvalue = bvalue;
	}
	public String getSvalue() {
		return svalue;
	}
	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}
	public List getLvalue() {
		return lvalue;
	}
	public void setLvalue(List lvalue) {
		this.lvalue = lvalue;
	}
	public Object getOvalue() {
		return ovalue;
	}
	public void setOvalue(Object ovalue) {
		this.ovalue = ovalue;
	}
	public Map<String, Object> getMvalue() {
		return mvalue;
	}
	public void setMvalue(Map<String, Object> mvalue) {
		this.mvalue = mvalue;
	}
	public Set getTvalue() {
		return tvalue;
	}
	public void setTvalue(Set tvalue) {
		this.tvalue = tvalue;
	}
	public double getDvalue() {
		return dvalue;
	}
	public void setDvalue(double dvalue) {
		this.dvalue = dvalue;
	}
	public BigDecimal getBdvalue() {
		return bdvalue;
	}
	public void setBdvalue(BigDecimal bdvalue) {
		this.bdvalue = bdvalue;
	}
	public long getLgvalue() {
		return lgvalue;
	}
	public void setLgvalue(long lgvalue) {
		this.lgvalue = lgvalue;
	}
	public FileTransfer getFilevalue() {
		return filevalue;
	}
	public void setFilevalue(FileTransfer filevalue) {
		this.filevalue = filevalue;
	}
	public InputStream getIsValue() {
		return isValue;
	}
	public void setIsValue(InputStream isValue) {
		this.isValue = isValue;
	}
	
}
