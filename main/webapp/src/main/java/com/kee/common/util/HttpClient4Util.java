package com.kee.common.util;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2013-6-28 下午3:42:46 httpclient4工具类
 */
public class HttpClient4Util {

	private static HttpClient4Util HTTP_CLIENT4_UTIL = null;

	/**
	 * 单例获取
	 * 
	 * @return
	 */
	public HttpClient4Util getInstanct() {
		if (HTTP_CLIENT4_UTIL == null) {
			HTTP_CLIENT4_UTIL = new HttpClient4Util();
		}
		return HTTP_CLIENT4_UTIL;
	}

	private static HttpClient httpclient = new DefaultHttpClient();
	private static Logger log = Logger.getLogger(HttpClient4Util.class);
	static {
		// 设置Cookie的兼容性
		httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		httpclient.getConnectionManager().closeIdleConnections(10, TimeUnit.SECONDS);

	}

	/**
	 * 调用请求接口
	 * 
	 * @param urlstr
	 * @return
	 */
	public static String get(String urlstr) {
		String html = null;
		try {
			html = get(urlstr, 0);
		} catch (Exception e) {
			log.error("urlstr="+urlstr);
			log.error(e);
			e.printStackTrace();
		}
		return html;
	}

	/**
	 * get 请求5次
	 * 
	 * @param urlstr
	 * @return html
	 * @throws Exception
	 */
	public static String get(String urlstr, int count) throws Exception {
		String html = null;
		System.out.println("第" + count + "次请求开始");
		log.info("第" + count + "次请求开始");
		if (count > 5) {
			log.error("alibaba throw http err");
			throw new Exception();// 这里可以抛出请求超时异常
		}
		URL url = new URL(urlstr);
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		HttpGet httpget = new HttpGet(uri);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity httpEntity = response.getEntity();
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null) {
			html = EntityUtils.toString(httpEntity, "utf-8");
			httpEntity.consumeContent();
		} else {
			httpEntity.consumeContent();
			html = get(urlstr, count + 1);
		}

		return html;
	}

	/**
	 * 获取文件
	 * 
	 * @param urlstr
	 * @param file
	 */
	public static void get(String urlstr, String file) {
		try {
			URL url = new URL(urlstr);
			URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
			HttpGet httpGet = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null) {
				byte[] b = EntityUtils.toByteArray(httpEntity);
				FileUtils.writeByteArrayToFile(new File(file), b);
			}
			httpEntity.consumeContent();
			// EntityUtils.consume(httpEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public String post(String url, Map<String, String> map) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			NameValuePair nvp = new BasicNameValuePair(key, map.get(key));
			nvps.add(nvp);
		}

		String html = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse response = httpclient.execute(httpPost);

			HttpEntity httpEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null) {
				html = EntityUtils.toString(httpEntity, "utf-8");
				// EntityUtils.consume(httpEntity);
				httpEntity.consumeContent();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}

}
