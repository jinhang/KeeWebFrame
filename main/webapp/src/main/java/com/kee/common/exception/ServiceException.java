package com.kee.common.exception;

import com.kee.common.dwr.JavaCallJsByDwr;

/**
 * session过期异常
 * @author jyl
 * @version 创建时间：2012-10-24 下午2:48:56
 * 类说明
 */
public class ServiceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3065811671679209716L;

	private String msg;
	
	private String stack;
	
	public ServiceException(String msg) {
		this.msg = msg;
	}
	
	public ServiceException(String msg,String stack) {
		this.stack = stack;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}
	
}
