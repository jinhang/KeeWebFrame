package cn.kee.common.helper.web;

import static cn.kee.model.tables.Permission.PERMISSION;
import static cn.kee.model.tables.Rolepermission.ROLEPERMISSION;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;

import com.kee.common.util.UUIDTool;
import com.kee.model.tables.pojos.Role;
import com.kee.model.tables.records.RolepermissionRecord;
import com.kee.task.Msg;
import com.wfs.engine.Context;

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
