package com.wfs.common.helper.web;

import static com.wfs.model.tables.Permission.PERMISSION;
import static com.wfs.model.tables.Rolepermission.ROLEPERMISSION;
import static com.wfs.model.tables.Staffpermission.STAFFPERMISSION;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Staff;
import com.wfs.task.Msg;

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
