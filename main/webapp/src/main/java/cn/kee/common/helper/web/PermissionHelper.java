package cn.kee.common.helper.web;

import static cn.kee.model.tables.Permission.PERMISSION;
import static cn.kee.model.tables.Rolepermission.ROLEPERMISSION;
import static cn.kee.model.tables.Staffpermission.STAFFPERMISSION;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.model.tables.pojos.Staff;
import com.kee.task.Msg;
import com.wfs.engine.Context;

public class PermissionHelper {
	
	private static final Logger logger = Logger.getLogger(PermissionHelper.class);
	
	public static Msg<List<Map<String, Object>>> getPermissionListByStaffid(Context context,String staffid){
		Msg<List<Map<String, Object>>> rmsg = new Msg<List<Map<String, Object>>>();
		DSLContext create = context.getJc().getDefaultClient().getContext();
		List<Map<String, Object>> lvalue = create.selectDistinct(PERMISSION.PERMISSIONID.as("id"),
				PERMISSION.PARENTID,PERMISSION.NAME.as("text"),PERMISSION.ACTION,
				PERMISSION.TYPE,PERMISSION.ORDERBY,PERMISSION.LEVEL)
				.from(ROLEPERMISSION)
				.leftOuterJoin(STAFFPERMISSION).on(STAFFPERMISSION.ROLEID.eq(ROLEPERMISSION.ROLEID))
				.leftOuterJoin(PERMISSION).on(PERMISSION.PERMISSIONID.eq(ROLEPERMISSION.PERMISSIONID))
				.where(STAFFPERMISSION.STAFFID.eq(staffid))
				.orderBy(PERMISSION.ORDERBY.asc()).fetch().intoMaps();
		if(lvalue.size()>0){
			rmsg.setResult(true);
			rmsg.setValue(lvalue);
		}else{
			rmsg.setResult(false);
		}
		return rmsg;		
	}
	
	public static Msg<List<Map<String, Object>>> getPermissionList(Context context){
		Msg<List<Map<String, Object>>> rmsg = new Msg<List<Map<String, Object>>>();
		DSLContext create = context.getJc().getDefaultClient().getContext();
		List<Map<String, Object>> lvalue = create.selectDistinct(PERMISSION.PERMISSIONID.as("id"),
				PERMISSION.PARENTID,PERMISSION.NAME.as("text"),PERMISSION.ACTION,
				PERMISSION.TYPE,PERMISSION.ORDERBY,PERMISSION.LEVEL)
				.from(PERMISSION).orderBy(PERMISSION.ORDERBY.asc()).fetch().intoMaps();
		if(lvalue.size()>0){
			rmsg.setResult(true);
			rmsg.setValue(lvalue);
		}else{
			rmsg.setResult(false);
		}
		return rmsg;		
	}

}
