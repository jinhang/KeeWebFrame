package cn.kee.common.jooq;

import java.util.List;

import org.jooq.Condition;

public class Expression {
	
	public static Condition NotNull(Object value,Condition condition){
		if (value != null&&!value.equals("")) return condition;
		return null;
	}
	
	public static Condition NotEmpty(Object value,Condition condition){
		
		if(value != null){
			if(value instanceof String && ((String)value).trim().length() > 0){
				return condition;
			}
			if(value instanceof List && ((List)value).size() > 0){
				return condition;
			}
		}
		return null;
		
	}
	
	
	public static Condition Eq(Object value1, Object value2,Condition condition){
		if(value1==null ||value2==null){
			return null;
		}else{
			if (value1 == value2 || value1.equals(value2)) return condition;
		}
		return null;
	}
	
}
