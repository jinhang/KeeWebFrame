package cn.kee.common.dhtmlx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.kee.common.date.DateUtil;
import com.kee.common.toxml.JavaBeanToXml;
import com.kee.engine.wrapper.model.Msg;
import com.wfos.engine.transfer.Tasks;

public class DhtmlxDhxComplete extends Tasks{
	public Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 * @param _list
	 * @param _xmlpath
	 * @return
	 */
	public Msg generateGridToXml(List _list,String _xmlpath){
		URL path = null;
		Msg msg = new Msg();
		Rows rows = new Rows();
		rows.setRow(_list);
		if (_xmlpath != null)
			path = getClass().getResource(_xmlpath);
		try{
			String xmlString = JavaBeanToXml.JavaBeanToXml(rows,path);
			log.debug(xmlString);
			msg.setSvalue(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @param _list
	 * @param _xmlpath
	 * @return
	 */
	public Msg generateGridCellXml(List _list, Map _mParam){
		Msg msg = new Msg();
		StringBuffer buf = new StringBuffer();
		PagingHeader(buf,_list.size(), _mParam);
		for (Iterator iterator = _list.iterator(); iterator.hasNext();) {
			buf.append("<row>");
			HashMap map = (HashMap) iterator.next();
			for (Iterator iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String key = (String) iterator2.next();
				Object temp =  map.get(key);
				buf.append("<cell>"+processTemp(temp)+"</cell>");
			}
			buf.append("</row>");
		}
		buf.append("</rows>");
		log.debug(buf.toString());
		msg.setSvalue(buf.toString());
		return msg;
	}	
	
	/**
	 * 处理临时对象
	 * @param temp
	 * @return
	 */
	public String processTemp(Object temp) {
		if (temp == null) {	
			return "";
		} else {
			//枚举类型时返回值
			if(temp instanceof Enum){
				return String.valueOf(((Enum)temp).ordinal());
			}
			//时间类型时要改变显示类型
			if (Date.class.isInstance(temp)){
				Date obj = (Date) temp;
				if(temp instanceof java.sql.Date){
					return DateUtil.getDateStr(DateUtil.formart3,obj);
				}else if(obj.getHours() == 0 && obj.getMinutes() == 0&& obj.getSeconds()==0){
					return DateUtil.getDateStr(DateUtil.formart3,obj);
				}else{
					return DateUtil.getDateStr(DateUtil.formart9, obj);
				}
				
			
			}
			return temp.toString();
		}
		
	}


	/**
	 * 
	 * @param list
	 * @return
	 */
	public Msg generateGridXml(List list, Map _mParam){
		if(list!=null&&list.size() > 0){
			Object obj = list.get(0);
			if (HashMap.class.isInstance(obj)){
				return generateGridMapXml(list,null,_mParam);
			}
		}
		return generateGridToXml(list,null);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public Msg generateGridXmlByColmun(List list,Object[] colmun, Map _mParam){
		if(list.size() > 0){
			Object obj = list.get(0);
			if (HashMap.class.isInstance(obj)){
				return generateGridMapXml(list,colmun, _mParam);
			}
		}
		return generateGridToXml(list,null);
	}
	
	/**
	 *若要为每一行指定id，需要在map显示的指名一项key为rowId即可 
	 *rowId用处是方便前台根据主键来删除或者修改grid
	 * @param list
	 * @return
	 */
	public Msg generateGridMapXml(List _list,Object[] colmun, Map _mParam){
		Msg msg = new Msg();
		StringBuffer buf1 = new StringBuffer();
		Map<String,String> colmun_map = new HashMap<String, String>();
		if(colmun!=null){
			for (Object obj : colmun) {
				colmun_map.put(obj.toString(), obj.toString());
			}
		}
		PagingHeader(buf1,_list.size(), _mParam);
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
			for (Iterator iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String key = (String) iterator2.next();
				//不生成该项
				if(key.equals("rowId"))
					continue;
				if(colmun_map.containsKey(key)){
					buf2.append(" "+key+"=\""+map.get(key)+"\"");
				}
				Object temp =  map.get(key);
				//buf.append("<userdata name=\""+key+"\">"+temp.toString()+"</userdata>");
				buf3.append("<"+key+"><![CDATA["+processTemp(temp)+"]]></"+key+">");
			}
			buf2.append(">");
			buf3.append("</row>");
			buf1.append(buf2).append(buf3);
		}
		buf1.append("</rows>");
		//log.info(buf1.toString());
		msg.setSvalue(buf1.toString());
		return msg;
	}
	
	
	public void PagingHeader(StringBuffer buf1, int size, Map _mParam){
		buf1.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><rows");
		int first = 0;
		if(_mParam.containsKey("first")){
			try{
				first = Integer.parseInt(_mParam.get("first").toString());
			}
			catch (Exception e) {
			}
			buf1.append(" pos=\""+first+"\" ");
		}
		if(_mParam.containsKey("limit")){
			int limit = -1;
			try{
				limit = Integer.parseInt(_mParam.get("limit").toString());
			}
			catch (Exception e) {
			}
			if(size<limit){
				limit = size;
			}
			if(limit != -1){
				buf1.append(" total_count=\""+(first+limit)+"\" ");
			}
		}
		buf1.append(">");
	}
	
	/**
	 *若要为每一行指定id，需要在map显示的指名一项key为rowId即可 
	 *rowId用处是方便前台根据主键来删除或者修改grid
	 * @param list
	 * @return
	 * @throws IOException 
	 */
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
				buf3.append("<"+key+"><![CDATA["+processTemp(temp)+"]]></"+key+">");
			}
			buf3.append("</row>");
			buf1.append(buf2).append(buf3);
		}
		buf1.append("</rows>");
		//log.info(buf1.toString());
		msg.setSvalue(buf1.toString());
		return msg;
	}	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public Msg generateTreeList(List<ITree> list){
		Tree root = new Tree();
		root.setId("0");
		root.setText("root");
		HashMap<String, Tree> tree = new HashMap<String, Tree>();
		if (list.size() > 0 ){
			for (int i = 0; i < list.size(); i++) {
				ITree itree = list.get(i);
				String id = itree.getId();
				String parentid = itree.getParentid();
				Tree pnode = tree.get(parentid);
				Tree temp = new Tree();
				temp.setId(id);
				temp.setText(itree.getName());
				temp.setUserdata(itree);
				if (pnode != null){
					pnode.getItem().add(temp);
				} else {
					root.getItem().add(temp);
				}
				tree.put(id, temp);
			}
		}
		Msg msg = new Msg();
		msg.setOvalue(root);
		return msg;
	}
	
	/**
	 * 
	 * @param list
	 * @param _xmlpath
	 * @return
	 */
	public Msg generateTreeMapXml(List list){
		Msg msg = new Msg();
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
		generateTreeXml(value,buf);
		//buf.append("</tree>");
		log.debug(buf.toString());
		msg.setSvalue(buf.toString());
		return msg;
	}
	
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
	
	/**
	 * 
	 * @param list
	 * @param _xmlpath
	 * @return
	 */
	public Msg generateTreeXml(List<ITree> list,String _xmlpath){
		Msg msg = generateTreeList(list);
		URL path = null;
		if (_xmlpath != null){
			path = getClass().getResource(_xmlpath);
		} else {
			path = getClass().getResource("/xml/tree.xml");
		}
		Tree tree = (Tree) msg.getOvalue();
		try{
			String xmlString = JavaBeanToXml.JavaBeanToXml(msg.getOvalue(),path);
			log.debug(xmlString);
			msg.setSvalue(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return msg;
	}	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public Msg generateTreeXml(List<ITree> list){
		return generateTreeXml(list,null);
	}
	
	/**
	 * 
	 * @param _list
	 * @return
	 */
	public Msg generateStringListMsg(List _list){
		Msg msg = new Msg();
		List<List<String>> list = new ArrayList<List<String>>();
		for (Iterator iterator = _list.iterator(); iterator.hasNext();) {
			Object[] array = (Object[]) iterator.next();
			List<String> tmplist = new ArrayList<String>();
			for (int i = 0; i < array.length; i++) {
				Object temp =  array[i];
				tmplist.add(processTemp(temp));
			}
			list.add(tmplist);
		}
		msg.setLvalue(list);
		return msg;
	}
	
	/**
	 * 指定可以隐藏的列。
	 * @param list
	 * @return
	 */
	public Msg generateGridXml(List list,String colmuns, Map _mParam){
		if(list.size() > 0){
			Object obj = list.get(0);
			if (HashMap.class.isInstance(obj)){
				if (colmuns != null && colmuns.length() > 0)
					return generateGridMapXml(list,colmuns.split(","), _mParam);
				return generateGridMapXml(list,null, _mParam);
			}
		}
		return generateGridToXml(list,null);
	}
	
	
	/**
	 *若要为每一行指定id，需要在map显示的指名一项key为rowId即可 
	 *rowId用处是方便前台根据主键来删除或者修改grid
	 * @param list
	 * @return
	 */
	public Msg generateGridMapXml(List _list, Map _mParam){
		Msg msg = new Msg();
		StringBuffer buf = new StringBuffer();
		PagingHeader(buf,_list.size(), _mParam);
		for (Iterator iterator = _list.iterator(); iterator.hasNext();) {
			HashMap map = (HashMap) iterator.next();
			//设定rowId
			if(map.containsKey("rowId")){
				buf.append("<row id=\""+map.get("rowId")+"\">");
			}else{
				buf.append("<row>");
			}
			for (Iterator iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String key = (String) iterator2.next();
				//不生成该项
				if(key.equals("rowId"))
					continue;
				Object temp =  map.get(key);
				//buf.append("<userdata name=\""+key+"\">"+temp.toString()+"</userdata>");
				buf.append("<"+key+"><![CDATA["+processTemp(temp)+"]]></"+key+">");
			}
			buf.append("</row>");
		}
		buf.append("</rows>");
		log.debug(buf.toString());
		msg.setSvalue(buf.toString());
		return msg;
	}

	public Msg generateDhxComplete(List _list){
		Msg msg = new Msg();
		StringBuffer buf = new StringBuffer();
		buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><complete>");
		for (Iterator iterator = _list.iterator(); iterator.hasNext();) {
			HashMap map = (HashMap) iterator.next();
			buf.append("<option value=\""+map.get("id")+"\">"+map.get("value")+"</option>");
		}
		buf.append("</complete>");
		log.debug(buf.toString());
		msg.setSvalue(buf.toString());
		return msg;
	}
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		List _list = _oMsg.getLvalue();
		msg = generateDhxComplete(_list);
		
		return msg;
	}
	
	
	
}
