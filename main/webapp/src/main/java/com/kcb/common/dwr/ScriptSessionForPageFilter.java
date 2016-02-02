package com.kcb.common.dwr;

import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import com.kcb.common.constant.Constants;
import com.kcb.common.session.DwrGetSession;

/**
 * @author jyl
 * @version 创建时间：2012-9-20 下午4:51:53
 * 类说明
 */
public class ScriptSessionForPageFilter implements ScriptSessionFilter {

	@Override
	public boolean match(ScriptSession scriptsession) {
		if(scriptsession.getPage().equals("/kcb-1.0/store/outstock/store_outstock_packing2.jsp")||scriptsession.getPage().equals("/kcb-1.0/store/outstock/store_outstock_packing_sqlite.jsp")){
			return true;
		}
		return false;
	}

}
