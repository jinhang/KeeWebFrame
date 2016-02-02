package com.kcb.common.util;

import java.util.Random;

/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2013-6-25 上午9:53:07
 * 类说明
 */
public class NumberUtil {
	public static boolean isInt(String str) {
		int temp = 0;
		try {
			temp = Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean isDouble(String str) {
		double temp = 0;
		try {
			temp = Double.parseDouble(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static String getRandomNum(int scale){
		String random = "";
		Random r = new Random();
		while(random.length() < scale){
			random = random + r.nextInt(10);
		}
		return random;
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomNum(6));
	}
}
