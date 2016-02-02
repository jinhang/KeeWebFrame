package com.kcb.common.util;

/**
 * 防止从数据库中取到null值，导致一些异常
 * @author alfred
 *
 */
public class NullHandler {

	public static String nullGet(Object obj){
		return obj==null?"":obj.toString();
	}
	
	public static boolean isNull(Object obj){
		return obj==null?true:false;
	}
}
