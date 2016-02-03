package com.kee.common.dwr;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import com.kee.common.constant.Constants;
import com.kee.common.session.DwrGetSession;

/**
 * @author jyl
 * @version 创建时间：2012-9-20 下午4:51:53
 * 类说明
 */
public class ScriptSessionForClientFilter implements ScriptSessionFilter {

	private Logger log = Logger.getLogger(this.getClass());
	
	String targetClientid = null;
	
	@Override
	public boolean match(ScriptSession scriptsession) {
		log.info("begin ScriptSessionForClientFilter match="+scriptsession.getId());
		if(scriptsession.getPage().equals("/kcb-1.0/main/")){
		if (targetClientid == null) {
			String clientId = (String) scriptsession.getAttribute(Constants.SESSION_CURRENT_CLIENT_ID);
			log.info("match---- scriptsession.getid:"+scriptsession.getId());
			log.info("match---- scriptsession.getAttribute(Constants.SESSION_CURRENT_CLIENT_ID):"+clientId);
			HttpSession session = DwrGetSession.getHttpSession();
			String currentClientId = (String)session.getAttribute(Constants.SESSION_CURRENT_CLIENT_ID);
			if(clientId.compareToIgnoreCase(currentClientId)==0){
				return true;
			}
			return false;
		}
		else {
			log.info("match: targetClientid="+targetClientid);
			String clientId = (String) scriptsession.getAttribute(Constants.SESSION_CURRENT_CLIENT_ID);
			if (clientId == null) {
				return false;
			}
			else {
				if(clientId.compareToIgnoreCase(targetClientid)==0){
					return true;
				}
				return false;
			}
		}
		}
		else
			return false;
	}

	public ScriptSessionForClientFilter(String tc) {
		// TODO Auto-generated constructor stub
		targetClientid = tc;
	}
	
	public ScriptSessionForClientFilter() {
		log.info("begin ScriptSessionForClientFilter");
		// TODO Auto-generated constructor stub
	}
}
