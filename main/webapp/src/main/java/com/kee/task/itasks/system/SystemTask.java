package com.kee.task.itasks.system;

import java.util.List;
import java.util.Map;

import org.jooq.DSLContext;

import com.kee.model.tables.pojos.Role;
import com.kee.task.Msg;
import com.wfs.engine.Context;
import com.wfs.engine.annotation.Description;
import com.wfs.engine.annotation.TA;
import com.wfs.engine.annotation.TransactionManager;

import cn.kee.common.CommonHelper;
import cn.kee.common.GridMessage;
import cn.kee.common.UIMessage;
import cn.kee.common.dhtmlx.DhtmlxTreeMapXml;
import cn.kee.common.helper.web.ForwardCompanyHelper;
import cn.kee.common.helper.web.PermissionHelper;
import cn.kee.common.helper.web.RoleHelper;
import cn.kee.common.helper.web.RolePermissionHelper;

public class SystemTask {
	
	@Description(value = "context,clientid")
	public Msg<GridMessage> getClientRole(Context context,String clientid)
			throws Exception {
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<GridMessage> rmsg = new Msg<GridMessage>();
		Msg<List<Map<String, Object>>> msg = RoleHelper.getRole(context, clientid);
		Integer num = msg.getValue().size();
		rmsg.setValue(CommonHelper.generateGridMessage(msg.getValue(),0,num, num));
		return rmsg;
	}
	
	@Description(value = "context,clientid")
	public Msg<String> showPermission(Context context,String clientid)
			throws Exception {
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		Msg<List<Map<String, Object>>> msg = PermissionHelper.getPermissionList(context);
		if(msg.getResult()){
			rmsg = DhtmlxTreeMapXml.generateTreeMapXml(msg.getValue());
		}else{
			rmsg.setResult(false);
			rmsg.setValue("-1");
		}
		return rmsg;
	}
	
	@Description(value = "context,role")
	public Msg<UIMessage> saveRole(Context context,Role role){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage um = new UIMessage(1,"保存成功","green");
		Msg<String> msg=RoleHelper.saveRole(context, role);
		if(!msg.getResult()){
			um.setState(-1);
			um.setSvalue("保存失败");
			um.setColor("red");
		}
		rmsg.setValue(um);
		return rmsg;
	}
	
	@Description(value = "context,roleid")
	public Msg<UIMessage> getPerByRoleid(Context context,String roleid){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage um = new UIMessage(1,"","green");
		Msg<List<String>> msg=RoleHelper.getPerByRoleid(context, roleid);
		um.setValue(msg.getValue());
		rmsg.setValue(um);
		return rmsg;
	}
	
	@TransactionManager(value = TA.JC)
	@Description(value = "context,role,permissionids")
	public Msg<UIMessage> updateOrSaveRole(Context context,Role role,List<String> permissionids){
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage um = new UIMessage(1,"","green");
		try{
			if(role.getRoleid()!=null && !role.getRoleid().equals("")){
				RolePermissionHelper.deletPermission(context, role.getRoleid());
				RoleHelper.updateRole(context, role);
				Msg<String> msg = RolePermissionHelper.saveRolePerList(context, permissionids, role.getRoleid());
				if(msg.getResult()){
					rmsg.setRollback(false);
					um.setResult(true);
					um.setSvalue(role.getRoleid());
					rmsg.setValue(um);
				}else{
					rmsg.setRollback(true);
					um.setResult(false);					
					um.setSvalue(role.getRoleid());
					rmsg.setValue(um);
				}
			}else{
				rmsg.setRollback(true);
				um.setResult(false);					
				um.setSvalue(role.getRoleid());
				rmsg.setValue(um);
			}
		}catch(Exception e){
			rmsg.setRollback(false);
			um.setResult(true);
			rmsg.setValue(um);
		}		
		return rmsg;
	}
	
	@Description(value = "context")
	public Msg<GridMessage> getExpress(Context context){
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<GridMessage> rmsg = new Msg<GridMessage>();
		Msg<List<Map<String, Object>>> msg = ForwardCompanyHelper.getExpress(context);
		Integer total = msg.getState();
		rmsg.setValue(CommonHelper.generateXmlGridMessage(msg.getValue(),0,total, total));
		return rmsg;
	}
}
