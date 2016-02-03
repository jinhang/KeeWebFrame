package cn.kee.common.dhtmlx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kee.task.Msg;

public class DhtmlxTreeMapXml {
	
	/**
	 * 
	 * @param list
	 * @param _xmlpath
	 * @return
	 */
	public static Msg<String> generateTreeMapXml(List list){
		Msg<String> msg = new Msg<String>();
		HashMap<String, HashMap> tree = new HashMap<String, HashMap>();
		HashMap<String, Object>  value = new HashMap<String, Object>();
		value.put("id", "0");
		value.put("text", "root");
		value.put("key_child", new ArrayList());
		if (list.size() > 0 ){
			for (int i = 0; i < list.size(); i++) {
				HashMap itree = (HashMap)list.get(i);
				String id =  itree.get("id").toString();
				String parentid = (String)itree.get("parentid");
				HashMap pnode = tree.get(parentid);
				if(itree.get("level")!=null&&"3".equals(itree.get("level").toString())){
					System.out.println(itree.get("text"));
					if(pnode.get("buttons") == null){
						pnode.put("buttons", itree.get("id"));
					}else{
						pnode.put("buttons", pnode.get("buttons")+","+itree.get("id"));
					}
				}else if (pnode != null){
					if (pnode.get("key_child") == null){
						pnode.put("key_child", new ArrayList());
					}
					((ArrayList)pnode.get("key_child")).add(itree);
				} else {
					((ArrayList)value.get("key_child")).add(itree);
				}
				tree.put(id, itree);
			}
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		new DhtmlxDhxComplete().generateTreeXml(value,buf);
		//buf.append("</tree>");
		new DhtmlxDhxComplete().log.debug(buf.toString());
		msg.setResult(true);
		msg.setValue(buf.toString());
		return msg;
	}

}
