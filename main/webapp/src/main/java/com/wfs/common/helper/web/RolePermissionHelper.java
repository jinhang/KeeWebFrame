package com.wfs.common.helper.web;

import static com.wfs.model.tables.Permission.PERMISSION;
import static com.wfs.model.tables.Rolepermission.ROLEPERMISSION;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kcb.common.util.UUIDTool;
import com.wfs.engine.Context;
import com.wfs.model.tables.pojos.Role;
import com.wfs.model.tables.records.RolepermissionRecord;
import com.wfs.task.Msg;

public class RolePermissionHelper {
	
	private static final Logger logger = Logger.getLogger(RolePermissionHelper.class);
	
	public static Msg<String> saveRolePerList(Context context,List<String> permissionids,String roleid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> msg = new Msg<String>();
		
		try{
			List<RolepermissionRecord> rpl = new ArrayList<RolepermissionRecord>();
			for(int i=0;i<permissionids.size();i++){
				RolepermissionRecord record = create.newRecord(ROLEPERMISSION);
				record.setRolepermissionid(UUIDTool.getUUID());		
				record.setRoleid(roleid);
				record.setPermissionid(permissionids.get(i));
				rpl.add(record);
			}
			create.batchStore(rpl).execute();
			msg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			msg.setResult(false);
			return msg;
		}
		return msg;		
	}
	
	public static Msg<String> deletPermission(Context context,String roleid){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		try{
			create.delete(ROLEPERMISSION).where(ROLEPERMISSION.ROLEID.eq(roleid)).execute();
			rmsg.setResult(true);
		}catch(Exception e){
			e.printStackTrace();
			rmsg.setResult(false);
		}		
		return rmsg;
	}

}
