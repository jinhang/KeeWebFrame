package com.kcb.common.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import com.kcb.common.constant.Constants;
import com.kcb.common.session.DwrGetSession;

/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2013-5-7 下午5:02:09
 * 类说明
 */
public class ScriptSessionForDownloadOrderPageFilter implements ScriptSessionFilter {

	private String currentClientId=null;
	
	
	public ScriptSessionForDownloadOrderPageFilter(String currentClientId) {
		this.currentClientId=currentClientId;
	}
	
	
	@Override
	public boolean match(ScriptSession scriptsession) {
		try {
			if(scriptsession.getPage().equals("/kcb-1.0/main/downloadOrder.jsp")||scriptsession.getPage().equals("/kcb-1.0/client/system/system_shop.jsp")
				||scriptsession.getPage().equals("/kcb-1.0/client/system/staff_list.jsp")){
				String clientId = (String) scriptsession.getAttribute(Constants.SESSION_CURRENT_CLIENT_ID);
				//dwr session 共享问题
				if(clientId.compareToIgnoreCase(currentClientId)==0){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return false;
	}

}
