package com.kcb.app.demo;

import java.sql.Date;

import com.wfos.engine.annotation.Description;
import com.wfos.engine.wrapper.model.Msg;

/**
 *
 */
public class DwrCallWrapperApp {
	

	@Description("taskid,name,limit,first")
	public Msg getExpressByName(String taskid, String name,int limit,int first) {
		return null;
	}
	@Description("taskid,name,startdate,enddate")
	public Msg getRecordCountById(String taskid, String name, java.util.Date startdate, java.util.Date enddate) {
		return null;
	}
	@Description("taskid,name,password")
	public Msg checkUserLogin(String taskid, String name ,String password) {
		return null;
	}
	@Description("taskid,sendCode")
	public Msg fetchAddressBySendCode(String taskid, String sendCode) {
		return null;
	}
}