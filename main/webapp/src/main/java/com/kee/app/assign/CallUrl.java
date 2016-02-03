package com.kee.app.assign;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alibaba.fastjson.JSON;
import com.baidu.mapapi.model.LatLng;
import com.kee.app.json.model.AroundLocation;
import com.kee.app.json.model.Datas;
import com.kee.app.json.model.LocationListJson;
import com.kee.app.json.model.PoisJson;
import com.kee.common.constant.Constants;
import com.kee.task.Msg;


public class CallUrl {
	
	public static Msg<List<LatLng>> call(String staffid){
		Msg<List<LatLng>> rmsg = new Msg<List<LatLng>>();
		List<LatLng> lls = new ArrayList();
		AroundLocation mrj = new AroundLocation();
		try{			
			HttpClient hc = new HttpClient();
//			String urlPath=Constants.HF_url;
			String urlPath="http://yuntuapi.amap.com/datamanage/data/list?";
			urlPath +="tableid=558d1470e4b0b297165d1168";
			urlPath +="&filter=user_id:"+staffid;
			urlPath +="&sortrule=sequence:1";
			urlPath +="&key="+Constants.EWP_SERVER_AK;
//			urlPath +="&key=25284b478f1da94d318d0f7d8bd8af87";
			System.out.println(urlPath);
			HttpMethod method = new GetMethod(urlPath);
			hc.executeMethod(method);
		     //打印服务器返回的状态
		    System.out.println(method.getStatusLine());
		    //打印返回的信息
		    System.out.println(method.getResponseBodyAsString().replaceAll("_", "map"));
		    //释放连接
		    method.releaseConnection();
		    //解析JSON
		    mrj=JSON.parseObject(method.getResponseBodyAsString().replaceAll("_", "map"),AroundLocation.class);
		    List<Datas> pjs = mrj.getDatas();
		    if(pjs!=null){
		    	for(int i=0;i<pjs.size();i++){
		    		String[] location =pjs.get(i).getMaplocation().split(",");
			    	lls.add(new LatLng(Double.valueOf(location[1]),Double.valueOf(location[0])));		    	
			    }
		    	rmsg.setResult(true);
				rmsg.setValue(lls);
		    }else{
		    	rmsg.setResult(false);
		    }		   
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			return rmsg;
		}
		return rmsg;
	}
	
	
	public static Msg<List<Datas>> callAround(String lat,String lng){
		Msg<List<Datas>> rmsg = new Msg<List<Datas>>();
		List<LatLng> lls = new ArrayList();
		AroundLocation mrj = new AroundLocation();
		try{			
			HttpClient hc = new HttpClient();
//			String urlPath=Constants.HF_url;
			String urlPath="http://yuntuapi.amap.com/datasearch/around?";
			urlPath +="tableid=558d1470e4b0b297165d1168";
			urlPath +="&sortrule=user_id:1";
			urlPath +="&center="+lng+","+lat;
			urlPath +="&radius="+Constants.EWP_AROUND_RADIUS;
//			urlPath +="&radius=5000";
			urlPath +="&key="+Constants.EWP_SERVER_AK;
//			urlPath +="&key=25284b478f1da94d318d0f7d8bd8af87";
			System.out.println(urlPath);
			HttpMethod method = new GetMethod(urlPath);
			hc.executeMethod(method);
		     //打印服务器返回的状态
		    System.out.println(method.getStatusLine());
		    //打印返回的信息
		    System.out.println(method.getResponseBodyAsString().replaceAll("_", "map"));
		    //释放连接
		    method.releaseConnection();
		    //解析JSON
		    mrj=JSON.parseObject(method.getResponseBodyAsString().replaceAll("_", "map"),AroundLocation.class);
		    List<Datas> pjs = mrj.getDatas();
		    if(pjs!=null && pjs.size()>0){
		    	rmsg.setResult(true);
		    	rmsg.setValue(pjs);
		    }else{
		    	rmsg.setResult(false);
		    }		   
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
			return rmsg;
		}
		return rmsg;
	}
}
