package cn.kee.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kee.common.dhtmlx.DhtmlxDhxComplete;
import cn.kee.common.jooq.CodeEnum;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommonHelper{
	

	private static Map<String,Map<String,String>> codeEnumMapss=null;
	
	/**
	 * 用于把数据封装成前台组件可方便显示的结构
	 * @param lvalue   result.intoMaps()
	 * @return  Map<String, Object>
	 */
	public static Map<String, Object> getMapByList(List<Map<String, Object>> lvalue){
		
		Map<String,String> pm = new HashMap<String,String>();
		
		com.kee.engine.wrapper.model.Msg m = new DhtmlxDhxComplete()
				.generateGridMapXml(lvalue, pm);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("svalue", m.getSvalue());
		return resultMap;
	}
	
	public static void putXmlB(Map<String, Object> rmap,String key,List<Map<String, Object>> lvalue){
		Map pm = new HashMap();
		String xmlB = new DhtmlxDhxComplete().generateGridMapXml(lvalue, pm).getSvalue();
		rmap.put(key, xmlB);
	}
	
	public static void putXmlB(Map<String, Object> rmap,String key,List<Map<String, Object>> lvalue,int first,int limit){
		Map pm = new HashMap();
		pm.put("first", first);
		pm.put("limit", limit);
		String xmlB = new DhtmlxDhxComplete().generateGridMapXml(lvalue, pm).getSvalue();
		rmap.put(key, xmlB);
	}
	
	/**
	 * 把所有枚举类动态生成map 用于传在前端使用
	 */
	public static Map getCodeEnumToMap() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		if(codeEnumMapss!=null)return codeEnumMapss;
		
		codeEnumMapss=new  HashMap<String,Map<String,String>>();
		Class[] classes=CodeEnum.class.getClasses();
			for(int i=0;i<classes.length;i++)
			{  
				Class classx=classes[i];	
				Method[] methods = classx.getDeclaredMethods();
			        if(classx.isEnum()){
						Map<String,String>  maps=new  HashMap<String,String>();
						codeEnumMapss.put(classx.getSimpleName(), maps);
			            List<?> enumConstants = Arrays.asList(classx.getEnumConstants());
			            enumConstants = Arrays.asList(classx.getEnumConstants());
			            for(Object enu : enumConstants){
			                for(Method method : methods){
			                    if(method.getName().startsWith("getValue")){
			                        maps.put(enu.toString(), method.invoke(enu).toString());
			                    }
			                    if(method.getName().startsWith("getKey")){
			                        maps.put(method.invoke(enu).toString(), classx.getMethod("getValue").invoke(enu).toString());
			                    }
			                }
			            }
			        }
			}
			return codeEnumMapss;
	}
	
	public static GridMessage generateGridMessage(List<Map<String, Object>> lvalue,int first,int limit,int total){
		
		GridMessage grid = new GridMessage();
		grid.setFirst(first);
		grid.setLimit(limit);
		grid.setTotal(total);
		Map pm = new HashMap();
		pm.put("first", first);
		pm.put("limit", limit);
		String xmlB = new DhtmlxDhxComplete().generateGridMapXml(lvalue, pm).getSvalue();
		grid.setXmlB(xmlB);
		return grid;
		//rmap.put(key, xmlB);
	}
	
	public static GridMessage generateXmlGridMessage(List<Map<String, Object>> lvalue,int first,int limit,int total){
		
		GridMessage grid = new GridMessage();
		grid.setFirst(first);
		grid.setLimit(limit);
		grid.setTotal(total);
		Map pm = new HashMap();
		String xmlB = new DhtmlxDhxComplete().generateGridCellXml(lvalue, pm).getSvalue();
		grid.setXmlB(xmlB);
		return grid;
		//rmap.put(key, xmlB);
	}
	
}