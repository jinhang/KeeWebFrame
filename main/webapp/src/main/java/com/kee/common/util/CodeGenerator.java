package com.kee.common.util;

import java.text.NumberFormat;
import java.util.Date;

import com.kee.common.date.DateUtil;

public class CodeGenerator {
	
	public static synchronized String generator() throws InterruptedException{
		Thread.sleep(1);
		String ms = System.currentTimeMillis()+"";
		ms = ms.substring(ms.length() - 3);
		return DateUtil.getDateStr(DateUtil.formart8, new Date())+ms;
	}
	
	
	public static String goodCodeGenerator(){
		long b=(long) (Math.random()*10000000000000L);
		NumberFormat format =  NumberFormat.getInstance();
		format.setMinimumIntegerDigits(13);
		format.setMaximumIntegerDigits(13);
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(0);
		format.setGroupingUsed(false);
		return format.format(b);
	}
	
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
