package com.kcb.utiltest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.SpatialRelationUtil;
import com.kcb.app.json.model.LocationListJson;
import com.kcb.app.json.model.MapResultJson;






public class CTest {
	
	@Test
	public void  main(){
		String url = "http://api.map.baidu.com/geodata/v3/poi/list";
		Map<String, String> params = new HashMap<String, String>();
		params.put("geotable_id", "105955");
		params.put("ak", "X056jyXVHFNo5I1yxI5kAI27");
		params.put("userid", "11111111");
		url = getWholeUrl(url, params);
		HttpGet httppost = new HttpGet(url);	
		//httppost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		HttpClient httpclient = new DefaultHttpClient();
		try{
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			System.out.println(entity.getContentType());
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
			String res = br.readLine();
			LocationListJson llj= JSON.parseObject(res,LocationListJson.class);
			LatLng ll = new LatLng(2.3,4.5);
			SpatialRelationUtil sru = new SpatialRelationUtil();
			System.out.println(res);
			//JSONObject o = (JSONObject) JSONObject.fromObject(res).get("result");
			//Integer total = o.getInt("total");
		}
		
		httppost.abort();

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		httpclient.getConnectionManager().shutdown();
	}
	
	}
	
	public static void test1(){
		String url = "http://api.map.baidu.com/geodata/v3/poi/create";
		HttpPost httppost = new HttpPost(url);	
		//httppost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
		nvps.add(new BasicNameValuePair("geotype", "1"));  
		nvps.add(new BasicNameValuePair("name", "test111"));  
		nvps.add(new BasicNameValuePair("is_published", "0"));  
		nvps.add(new BasicNameValuePair("ak", "X056jyXVHFNo5I1yxI5kAI27"));
		try{
		httppost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			System.out.println(entity.getContentType());
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
			String res = br.readLine();
			System.out.println(res);
			//JSONObject o = (JSONObject) JSONObject.fromObject(res).get("result");
			//Integer total = o.getInt("total");
		}
		httppost.abort();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		httpclient.getConnectionManager().shutdown();
	}
	
	}
		
	public static void test2(){
String url = "http://api.map.baidu.com/geodata/v3/poi/create";
		
		HttpPost httppost = new HttpPost(url);	
		//httppost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		HttpClient httpclient = new DefaultHttpClient();
		
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
		
		nvps.add(new BasicNameValuePair("latitude", "116.413607"));  
		nvps.add(new BasicNameValuePair("longitude", "40.924033")); 
		nvps.add(new BasicNameValuePair("geotable_id", "105955"));  
		nvps.add(new BasicNameValuePair("coord_type", "1"));  
		nvps.add(new BasicNameValuePair("title", "test123"));  
		nvps.add(new BasicNameValuePair("ak", "X056jyXVHFNo5I1yxI5kAI27"));
		nvps.add(new BasicNameValuePair("userid", "11111111"));
		try{
		httppost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			System.out.println(entity.getContentType());
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
			String res = br.readLine();
			System.out.println(res);
			//JSONObject o = (JSONObject) JSONObject.fromObject(res).get("result");
			//Integer total = o.getInt("total");
		}
		
		httppost.abort();

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		httpclient.getConnectionManager().shutdown();
	}
	
	}
	
	public static String getWholeUrl(String url, Map<String, String> params){
        if(url == null){
            return null;
        }
        if(params == null){
            return url;
        }
        Set<Map.Entry<String, String>> set = params.entrySet();
        if(set.size() <= 0){
            return url;
        }
        url += "?";
        Iterator<Map.Entry<String, String>> it = set.iterator();
        if(it.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            String param = entry.getKey() + "=" + entry.getValue();
            url += param;
        }
        while(it.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            String param = entry.getKey() + "=" + entry.getValue();
            url += "&" + param;
        }
        return url;
    }
}