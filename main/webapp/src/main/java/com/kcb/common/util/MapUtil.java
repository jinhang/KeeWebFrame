package com.kcb.common.util;

import java.util.Map;

public class MapUtil {
	public static String getString(String key,Map _mParam){
		if(_mParam == null||!_mParam.containsKey(key)){
			return null;
		}else{
			Object o = _mParam.get(key);
			if(o == null){
				return null;
			}else{
				return o.toString();
			}
		}
	}
	
	public static int getInt(String key,Map _mParam) throws Exception{
		if(_mParam == null||!_mParam.containsKey(key)){
			throw new Exception();
		}else{
			Object o = _mParam.get(key);
			if(o == null){
				throw new Exception();
			}else{
				return Integer.parseInt(o.toString());
			}
		}
	}
	
	public static Integer getInteger(String key,Map _mParam) throws Exception{
		if(_mParam == null||!_mParam.containsKey(key)){
			return null;
		}else{
			Object o = _mParam.get(key);
			if(o == null){
				return null;
			}else{
				return Integer.parseInt(o.toString());
			}
		}
	}
	
}
