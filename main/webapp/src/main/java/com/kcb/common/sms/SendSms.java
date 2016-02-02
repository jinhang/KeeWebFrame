package com.kcb.common.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kcb.common.constant.Constants;
import com.kcb.common.date.DateUtil;

public class SendSms {
	
	/**
	 * 短信发送
	 * @param content
	 * @param mobile
	 * @return
	 */
	public static List<String> SendSms(String content, String mobile) {
		
		List<String> list = new ArrayList<String>();
		if (!content.equals("") && content.length() > 200) {
			int len = content.length();
			StringBuffer oneSms = new StringBuffer();
			for (int i = 0; i < len; i++) {
				if (oneSms.length() < 200) {
					oneSms.append(content.charAt(i));
				}
				else {
					oneSms.append("【E网配】");
					list.add(send(oneSms.toString(), mobile));
					list.add(oneSms.toString());
					oneSms.delete(0, 190);
				}
			}
			oneSms.append("【E网配】");
			list.add(send(oneSms.toString(), mobile));
			list.add(oneSms.toString());
		}
		else {
			content += "【E网配】";
			list.add(send(content, mobile));
			list.add(content);
		}
		return list;
	}
	
	/**
	 * 短信发送接口
	 * @param content
	 * @param mobile
	 * @return
	 */
	private static String send(String content, String mobile) {
		System.out.println(content);
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://smsapi.c123.cn/OpenPlatform/OpenApi?action=sendOnce");

		// 向StringBuffer追加用户名
		sb.append("&ac=1001@500981890001");
		try {
			// 向StringBuffer追加密码（密码采用MD5 32位 小写）
			sb.append("&authkey=E64F98D3DBDBBD814888EACBB074F7C9");
			sb.append("&cgid=1678");
			// 向StringBuffer追加手机号码
			// 向StringBuffer追加消息内容转URL标准码
			sb.append("&c=" + URLEncoder.encode(content,"UTF-8"));
			sb.append("&m=" + mobile);
			// 创建url对象
			URL url;
			url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));

			// 返回发送结果
			String inputline = in.readLine();
			in.close();
			// 返回结果为‘100’ 发送成功
			return inputline;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
 
}
