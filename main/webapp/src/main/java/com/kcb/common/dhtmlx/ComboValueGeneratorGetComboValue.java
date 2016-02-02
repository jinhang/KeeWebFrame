package com.kcb.common.dhtmlx;

import java.util.Map;

import org.apache.log4j.Logger;

import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;

/**
 * 生成前台下拉框数据
 *
 */

public class ComboValueGeneratorGetComboValue extends Tasks{
	private Logger logger = Logger.getLogger(this.getClass());
/**
 * 	
 * @param enumType--枚举类型类名，如果没有package名，默认在com.kcb.model.enums包下
 * @return
 */
	public Msg getComboValue(String enumType){
		Msg msg=new Msg();
		msg.svalue="";
		
		try {
			String className =enumType.indexOf(".")>0?enumType:"com.kcb.model.enums."+enumType;
			Class clz = Class.forName(className);
			
			if(clz.isEnum()){
				StringBuilder sb = new StringBuilder("[");
				Object[] objs = clz.getEnumConstants();
				for(Object obj : objs){
					Enum e = (Enum)obj;
					sb.append("['"+e.ordinal()+"','"+e.toString()+"'],");
				}
				sb.append("]");
				if(objs.length>0){
					msg.svalue = sb.toString();
				}
				logger.info("获取枚举类型["+className+"]的前台数据："+msg.svalue);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("未找到枚举类型["+enumType+"]!");
		} 		
//		msg.svalue="[['0','哈哈'],['1','呵呵']]";
		finally{
		return msg;
		}
	}
	
	
	
	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		
		String enumType = (String) _mParam.get("enumType");
		msg = getComboValue(enumType);

		return msg;
	}

}
