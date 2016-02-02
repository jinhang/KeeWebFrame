package com.kcb.common.dhtmlx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;
import com.wfs.common.dhtmlx.DhtmlxDhxComplete;

public class DhtmlxGridHeadXml extends Tasks{
	
	public Msg generateGridHeadXml(List _list,String xml) throws IOException{
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		builder.setFeature("http://apache.org/xml/features/xinclude", true);
		Element root = null;
		Element element = null;
		CharBuffer charbuf = CharBuffer.allocate(20000);
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(xml),"utf-8"));
		String stemp;
		StringBuffer buf1 = new StringBuffer();
		while((stemp = reader.readLine()) != null){
			stemp	= stemp.replaceAll("</rows>", "").trim();
			buf1.append(stemp);
		}
		reader.close();
		Msg msg = new Msg();
		for (Iterator iterator = _list.iterator(); iterator.hasNext();) {
			HashMap map = (HashMap) iterator.next();
			StringBuffer buf2 = new StringBuffer();
			StringBuffer buf3 = new StringBuffer();
			//设定rowId
			if(map.containsKey("rowId")){
				buf2.append("<row id=\""+map.get("rowId")+"\"");
			}else{
				buf2.append("<row");
			}
			buf2.append(">");
			for (Iterator iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String key = (String) iterator2.next();
				Object temp =  map.get(key);
				//buf.append("<userdata name=\""+key+"\">"+temp.toString()+"</userdata>");
				buf3.append("<"+key+"><![CDATA["+new DhtmlxDhxComplete().processTemp(temp)+"]]></"+key+">");
			}
			buf3.append("</row>");
			buf1.append(buf2).append(buf3);
		}
		buf1.append("</rows>");
		//log.info(buf1.toString());
		msg.setSvalue(buf1.toString());
		return msg;
	}	
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		List _list = _oMsg.getLvalue();
		String xml = "/xml/client/purchase/suppliergridhead.xml";
		msg = generateGridHeadXml(_list, xml);
		
		return msg;
	}
}
