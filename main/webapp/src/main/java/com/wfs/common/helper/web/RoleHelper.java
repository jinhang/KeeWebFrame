package com.wfs.common.helper.web;

import static com.wfs.model.tables.Role.ROLE;
import static com.wfs.model.tables.Permission.PERMISSION;
import static com.wfs.model.tables.Rolepermission.ROLEPERMISSION;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kcb.common.util.UUIDTool;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Role;
import com.wfs.model.tables.pojos.User;
import com.wfs.model.tables.records.RoleRecord;
import com.wfs.task.Msg;

public class RoleHelper {
	
	private static final Logger logger = Logger.getLogger(RoleHelper.class);
	
	public static Msg<List<Map<String, Object>>> getRole(Context context,String clientid){
		Msg<List<Map<String, Object>>> rmsg = new Msg<List<Map<String, Object>>>();
		DSLContext create = context.getJc().getDefaultClient().getContext();
		List<Map<String, Object>> lvalue = create.selectDistinct(ROLE.ROLEID.as("id"),
				ROLE.NAME,ROLE.TYPE).from(ROLE)
				.where(ROLE.CLIENTID.eq(clientid))
				.orderBy(ROLE.NAME.asc()).fetch().intoMaps();
		if(lvalue.size()>0){
			rmsg.setResult(true);
			rmsg.setValue(lvalue);
		}else{
			rmsg.setResult(false);
		}
		return rmsg;		
	}
	
	public static Msg<String> saveRole(Context context,Role role){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		RoleRecord record = create.newRecord(ROLE);
		try{
			record.from(role);
			record.setRoleid(UUIDTool.getUUID());			
			record.store();
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		msg.setValue(record.getRoleid());
		msg.setResult(true);
		return msg;		
	}
	
	public static Role getRoleById(Context context,String roleid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Role role = new Role();
		role = create.fetchOne(ROLE, ROLE.ROLEID.eq(roleid)).into(Role.class);
		return role;
	}
	
	
	public static Msg<List<String>> getPerByRoleid(Context context,String roleid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<List<String>> rmsg = new Msg<List<String>>();
		List<String> perids=create.select(PERMISSION.PERMISSIONID.as("id")).from(ROLEPERMISSION)
				.leftOuterJoin(PERMISSION).on(PERMISSION.PERMISSIONID.eq(ROLEPERMISSION.PERMISSIONID))
				.where(ROLEPERMISSION.ROLEID.eq(roleid))
				.fetch("id",String.class);
		rmsg.setResult(true);
		rmsg.setValue(perids);
		return rmsg;
	}
	
	public static Msg<String> updateRole(Context context,Role role){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		create.update(ROLE).set(ROLE.NAME,role.getName())
		.where(ROLE.ROLEID.eq(role.getRoleid())).execute();
		rmsg.setResult(true);
		return rmsg;
	}
	
	public static Msg<String> deletRole(Context context,String roleid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		try{
			create.delete(ROLE).where(ROLE.ROLEID.eq(roleid)).execute();
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}		
		return rmsg;
	}

}
