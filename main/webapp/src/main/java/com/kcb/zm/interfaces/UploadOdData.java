package com.kcb.zm.interfaces;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.kcb.common.util.XmlCreateAndChangeString;
import com.kcb.zm.impl.ChangeObjectToString;
import com.wfs.model.tables.pojos.Logisticsdata;
import com.wfs.task.Msg;

import org.jdom.Document;
import org.jdom.Element;

public class UploadOdData {
	
	public static Msg<List<String>> OdData(List<Logisticsdata> datas,String scantype){
		Msg<List<String>> rmsg = new Msg<List<String>>();
		List<String> emsg = new ArrayList();
		try{
			XmlCreateAndChangeString xcacs =  new XmlCreateAndChangeString();
			String url="http://60.191.250.42:8011/Api.aspx";
			HttpClient hc = new HttpClient();
			Document document;
			Element element;
			Element xml=new Element("XML");
			document= new Document(xml);
			element=xcacs.CreateElement("ScanType",scantype);
			xml.addContent(element);
			for(int i=0;i<datas.size();i++){
				element=xcacs.CreateElement("Row",ChangeObjectToString.ChangeDataToString(datas.get(i),i+1));
				xml.addContent(element);
			}
			PostMethod method = new PostMethod(url);
			method.setRequestBody(URLEncoder.encode(xcacs.OutputToString(document),"UTF-8"));
			hc.executeMethod(method);
			System.out.println(method.getStatusLine());
		    //打印返回的信息
		    System.out.println(method.getResponseBodyAsString());
		    //释放连接
		    method.releaseConnection();
		    if(method.getResponseBodyAsString().equals("<XML>00</XML>")){
		    	rmsg.setResult(true);
		    }else{
		    	if(method.getResponseBodyAsString().equals("<XML>01|全部失败</XML>")){
			    	rmsg.setResult(false);
			    	for(int i=0;i<datas.size();i++){
			    		emsg.add(datas.get(i).getNum());
			    	}
			    	rmsg.setValue(emsg);
			    }else{
			    	rmsg.setResult(false);
			    	//解析XML
				  	Document doc =xcacs.readStringXML(method.getResponseBodyAsString());
				  	Element responseroot = doc.getRootElement();
				  	List node = responseroot.getChildren();
				  	for(int j=0;j<node.size();j++){
				  		Element row = (Element) node.get(j);
				  		String[] rowinfo = row.getText().split("|");
				  		emsg.add(rowinfo[0]);
				  	}
				  	rmsg.setValue(emsg);
			    }
		    }		    
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}		
		return rmsg;
	}

}
