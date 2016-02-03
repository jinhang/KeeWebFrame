package com.kee.common.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import com.kee.common.constant.Constants;
import com.kee.common.session.DwrGetSession;

/**
 * @author jyl
 * @version 创建时间：2012-9-20 下午4:51:53
 * 类说明
 */
public class ScriptSessionForCurrentClientFilter implements ScriptSessionFilter {

	@Override
	public boolean match(ScriptSession scriptsession) {
		if(scriptsession.getPage().equals("/kcb-1.0/store/outstock/store_outstock_packing2.jsp")||scriptsession.getPage().equals("/kcb-1.0/store/outstock/store_outstock_packing_sqlite.jsp")){
			String clientId = (String) scriptsession.getAttribute(Constants.SESSION_CURRENT_CLIENT_ID);
			HttpSession session = DwrGetSession.getHttpSession();
			String currentClientId = (String)session.getAttribute(Constants.SESSION_CURRENT_CLIENT_ID);
			if(clientId.compareToIgnoreCase(currentClientId)==0){
				return true;
			}
		}
		return false;
	}

}
