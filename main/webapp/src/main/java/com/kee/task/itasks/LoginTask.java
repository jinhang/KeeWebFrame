package com.kee.task.itasks;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jooq.DSLContext;

import com.kee.common.constant.Constants;
import com.kee.common.session.DwrGetSession;
import com.kee.model.tables.pojos.Staff;
import com.kee.task.Msg;
import com.wfs.engine.Context;
import com.wfs.engine.annotation.Description;

import cn.kee.common.UIMessage;
import cn.kee.common.dhtmlx.DhtmlxTreeMapXml;
import cn.kee.common.helper.web.PermissionHelper;
import cn.kee.common.helper.web.StaffHelper;

public class LoginTask {
	
	@Description(value = "context,username,password,verCode,remeberPwd")
	public Msg<UIMessage> validate(Context context,String username, String password, String verCode, Boolean remeberPwd)
			throws Exception {
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<UIMessage> rmsg = new Msg<UIMessage>();
		UIMessage uimsg = new UIMessage(1,"登录成功","green");
		HttpSession session = context.getSession();
		String random = (String) session.getAttribute(Constants.SESSION_RANDOM);
		if (!remeberPwd) {
			if(verCode==null||random == null||(random.compareToIgnoreCase(verCode) != 0)){				
				rmsg.setValue(new UIMessage(-1,"验证码错误","red"));
			}
		}
		Msg<Staff> msg = StaffHelper.getStaffByUsername(context, username, password);
		if(msg.getResult()){
			setSession(context,msg.getValue());
			rmsg.setValue(uimsg);
		}else{
			rmsg.setValue(new UIMessage(-2,"用户名或者密码错误","red"));
		}
		return rmsg;
	}
	
	/**
	 * 设置session
	 * @param user
	 * @param memory
	 * @throws Exception
	 */
	private void setSession(Context context,Staff staff) throws Exception{
		if(staff == null){
			return;
		}
		HttpSession session = context.getSession();
		if(session == null){
			session = DwrGetSession.getHttpSession();
		}
		System.out.println(session);	
		System.out.println(session.getId());
		//当前操作的客户存入session中
		session.setAttribute(Constants.SESSION_CURRENT_CLIENT_ID, staff.getClientid());	
		//当前用户id
		session.setAttribute(Constants.SESSION_CURRENT_USER_ID, staff.getStaffid());
		session.setAttribute(Constants.SESSION_NICK, staff.getUsername());		
		session.setAttribute(Constants.SESSION_USER, staff);		
	}
	
	@Description(value = "context,staffid")
	public Msg<String> showPermission(Context context,String staffid)
			throws Exception {
		DSLContext create = context.getJc().getDefaultClient().getContext();
		Msg<String> rmsg = new Msg<String>();
		Msg<List<Map<String, Object>>> msg = PermissionHelper.getPermissionListByStaffid(context, staffid);
		if(msg.getResult()){
			rmsg = DhtmlxTreeMapXml.generateTreeMapXml(msg.getValue());
		}else{
			rmsg.setResult(false);
			rmsg.setValue("-1");
		}
		return rmsg;
	}

}
