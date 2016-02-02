package com.wfs.common.jooq;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jooq.Condition;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.jooq.impl.TableImpl;

import com.wfs.common.ValidateMessage;
import com.wfs.common.engine.EngineHelper;
import com.wfs.engine.Context;

@SuppressWarnings({ //"unchecked", 
					"rawtypes" })
public class JooqHelper {
	
	private Condition condition;
	
	public JooqHelper(){
	}
	public JooqHelper getHelper() {
		return this;
	}
	
	public JooqHelper(Condition condition) {
		if (condition != null)
			this.condition = condition;
	}
	
	public Condition getCondition(){
		return this.condition;
	}
	
	public JooqHelper where(Condition condition){
		if (condition != null)
			this.condition = condition;
		return this;
	}
	
	public JooqHelper and(Condition condition){
		if (condition != null)
			this.condition = this.condition.and(condition);
		return this;
	}
	
	public JooqHelper or(Condition condition){
		if (condition != null)
			this.condition = this.condition.or(condition);
		return this;
	}
	
	public JooqHelper where(Condition condition,String validation,Object... values){
		if(validate(validation,values)){
			this.condition = condition;
		}
		return this;
	}
	
	public JooqHelper and(Condition condition,String validation,Object... values){
		if(validate(validation,values)){
			this.condition = this.condition.and(condition);
		}
		return this;
	}
	
	public JooqHelper or(Condition condition,String validation,Object... values){
		if(validate(validation,values)){
			this.condition = this.condition.or(condition);
		}
		return this;
	}
	
	private boolean validate(String validation,Object... values){
		List<String> vlist = Arrays.asList(validation.split(","));
		if(vlist.contains((Validation.NotNull.getValue()))){
			if(values[0]==null){
				return false;
			}
		}
		if(vlist.contains((Validation.NotEmpty.getValue()))){
			if(values[0]!=null){
				if(values[0] instanceof String && ((String)values[0]).trim().equals("")){
					return false;
				}
				if(values[0] instanceof List && ((List)values[0]).size()==0){
					return false;
				}
			}else{
				return false;
			}
		}
		if(vlist.contains(Validation.NeedEqual.getValue())){
			if(!values[0].equals(values[1])){
				return false;
			}
		}
		return true;
	}
	
	public static Field[] join(Field[] fieldarray1, Field... fieldarray2) {
		Field[] result = new Field[fieldarray1.length + fieldarray2.length];
		System.arraycopy(fieldarray1, 0, result, 0, fieldarray1.length);
		System.arraycopy(fieldarray2, 0, result, fieldarray1.length, fieldarray2.length);
		return result;
	}
	
	/**直接执行指定的sql
	 * 
	 * @param context
	 * @param sql
	 * @return
	 */
	public static  Result<Record> execSql(Context context, String sql){
		
		 ResultQuery<Record> resultQuery =context
					.getJc()
					.getDefaultClient()
					.getContext().resultQuery(sql);
		 
		 Result<Record> results = resultQuery.fetch();
		 
		 return results;
	}
	
	public static Record getRecordByid(Object table,String id){
		TableImpl<Record> tableImpl = (TableImpl<Record>)table;
		Field field = tableImpl.field("id");
		return getRecordByid(table,field.eq(id));
	}
	public static Record getRecordByid(Object table,String id,String idname){
		TableImpl<Record> tableImpl = (TableImpl<Record>)table;
		Field field = tableImpl.field(idname);
		return getRecordByid(table,field.eq(id));
	}
	public static<T> T getRecordByidInto(Object table,String id,Class<T> clas){
		TableImpl<Record> tableImpl = (TableImpl<Record>)table;
		Field field = tableImpl.field("id");
		Record record = getRecordByid(table,field.eq(id));
		if (record != null) return record.into(clas);
		return null;
	}
	
	public static Record getRecordByid(Context context,Object table,String id){
		TableImpl<Record> tableImpl = (TableImpl<Record>)table;
		Field field = tableImpl.field("id");
		return getRecordByid(context,table,field.eq(id));
	}

	public static Record getRecordByid(Object table,Condition cond){
		Context context = EngineHelper.getContext();
		return getRecordByid(context,table,cond);
	}
	
	public static Record getRecordByid(Context context,Object table,Condition cond){
		TableImpl<Record> tableImpl = (TableImpl<Record>)table;
		Record result = context.getJc().getDefaultClient().getContext()
				.select().from(tableImpl).where(cond).fetchOne();
		return result;
	}
	
	/**
	 * 验证表所有字段长度
	 * @param record
	 * @param table
	 * @return
	 */
	public static ValidateMessage validateRecord(Record record,TableImpl table){
		
		Field[] fields =  table.fields();
		for (int i = 0; i < fields.length; i++) {
			DataType dateType = fields[i].getDataType().getSQLDataType();
			if (dateType.hasLength()) {
				String value = (String) record.getValue(fields[i].getName());
				if (value != null && dateType.length() < value.length())
					return new ValidateMessage(false,"字段"+fields[i].getName() + ":无法保存"+value);
			}
		}
		return new ValidateMessage(true, "验证成功");
	}
	
	/**
	 * 验证表特定名字字段长度
	 * @param name
	 * @param length
	 * @param table
	 * @return
	 */
	public static ValidateMessage validateString(String value,Field field){
		if(value==null){
			return new ValidateMessage(false,"字段"+field.getName() + ":为空无法保存");
		}
		DataType dateType = field.getDataType().getSQLDataType();
			if (dateType.hasLength()) {
				if (dateType.length() < value.length())
					return new ValidateMessage(false,"字段"+field.getName() + ":无法保存");
			}
		return new ValidateMessage(true, "验证成功");
	}
	
	public static void main(String[] args) {
		System.out.println(new JooqHelper().validate("NotNull,NeedEqual", 1,1));
	}
	
}
