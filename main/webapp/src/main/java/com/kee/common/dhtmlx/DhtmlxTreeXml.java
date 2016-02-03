package com.kee.common.dhtmlx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Tasks;

public class DhtmlxTreeXml extends Tasks{
	
	public void generateTreeXml(HashMap hash,StringBuffer buf){
		if (hash.get("id") != null && hash.get("id").equals("0")){
			buf.append("<tree id=\""+hash.get("id")+"\" text=\""+hash.get("text")+"\" im0=\""+hash.get("img")+"\">");
		} else buf.append("<item id=\""+hash.get("id")+"\" text=\""+hash.get("text")+"\" im0=\""+hash.get("img")+"\">");
		ArrayList list =  null;
		for (Iterator iterator = hash.keySet().iterator(); iterator.hasNext();) {
			Object type = (Object) iterator.next();
			if (type.equals("key_child")) { 
				list = (ArrayList)hash.get(type);
			} else {
				Object temp = hash.get(type);
				if (temp != null){
					buf.append("<userdata name=\""+type.toString()+"\">");
					buf.append(hash.get(type).toString() + "</userdata>");
				}
			}
		}
		if (list != null){
			for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
				HashMap object = (HashMap) iterator2.next();
				generateTreeXml(object,buf);
			}
		}	
		if (hash.get("id") != null && hash.get("id").equals("0")){
			buf.append("</tree>");
		} else 
		buf.append("</item>");
	}
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		return msg;
	}
}
