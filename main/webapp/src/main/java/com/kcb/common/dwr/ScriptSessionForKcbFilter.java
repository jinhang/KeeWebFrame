package com.kcb.common.dwr;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import com.kcb.common.session.DwrGetSession;

public class ScriptSessionForKcbFilter implements ScriptSessionFilter {

	@Override
	public boolean match(ScriptSession session) {

		try {
			String httpSessionId = (String) session.getAttribute("org.directwebremoting.ScriptSession.HttpSessionId");
			String dwrSessionId = DwrGetSession.getHttpSession().getId();
			if (httpSessionId != null && dwrSessionId != null && httpSessionId.compareToIgnoreCase(dwrSessionId) == 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return false;
	}

}
