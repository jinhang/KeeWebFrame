package com.kcb.common.date;

import java.util.Date;

/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2013-7-11 上午11:12:42 类说明
 */
public class IsOverTimeUtil {

	/**
	 * 
	 * @param loginTime
	 *            上次登陆时间
	 * @param expires_in
	 *            单位毫秒
	 * @return
	 */
	public static boolean isOverTime(Date loginTime, Long expires_in) {
		try {
			if (loginTime != null && expires_in != null) {
				long overTime = loginTime.getTime() + expires_in * 1000;
				if (overTime >= System.currentTimeMillis()) {
					return true;// 未超时
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;// 超时或未登陆
	}
}
