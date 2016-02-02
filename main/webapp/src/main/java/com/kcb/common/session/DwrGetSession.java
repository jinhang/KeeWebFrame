package com.kcb.common.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.json.JsonUtil;
import org.directwebremoting.json.parse.JsonParseException;



//import com.kcb.common.constant.Constants;
import com.kcb.common.constant.Constants;
import com.kcb.common.server.ActiveUserListener;
import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;
import com.wfs.engine.Context;
import com.wfs.engine.common.EngineHelper;

public class DwrGetSession extends Tasks {
	private Logger log = Logger.getLogger(this.getClass());

	// 获取单个session
	public static Object getSession(String name) {
		WebContext context = WebContextFactory.get();
		if (context == null) {
			return null;
		}
		HttpSession session = context.getSession();
		return session.getAttribute(name);
	}

	// 获取多个session
	public static List<Object> getSessionList(List<String> name_list) {
		WebContext context = WebContextFactory.get();
		HttpSession session = context.getSession();
		List<Object> list = new ArrayList<Object>();
		for (String name : name_list) {
			list.add(session.getAttribute(name));
		}
		return list;
	}

	/**
	 * @author tsw
	 * Using getAttributeNames instead of getValueNames.Because it is deprecated!
	 * @return
	 */
	public static List<String> getSessionNames() {
		WebContext context = WebContextFactory.get();
		HttpSession session = context.getSession();
		
		List<String> list = new ArrayList<String>();
		Enumeration<String> sessionNames = session.getAttributeNames();
		while(sessionNames.hasMoreElements()) {
			list.add(sessionNames.nextElement());
		}
		return list;
	}

	// 获取HttpSessin对象
	public static HttpSession getHttpSession() {
		WebContext context = WebContextFactory.get();
		HttpSession session = context.getSession();
		return session;
	}

	// 设置单个session
	public static void setSession(String name, Object o) {
		// System.out.println(name+":"+o);
		WebContext context = WebContextFactory.get();
		// System.out.println(context);
		HttpSession session = context.getSession();
		// System.out.println(session);
		session.setAttribute(name, o);
	}

	/**
	 * 清空session中的变量
	 * @param name
	 */
	public static void cleanSession(String name) {
		WebContext context = WebContextFactory.get();
		HttpSession session = context.getSession();
		session.removeAttribute(name);
	}

	/**
	 * @author tsw
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 */
	public static String getAllSessionList() throws IOException, JsonParseException {
		WebContext context = WebContextFactory.get();
		HttpSession session = context.getSession();
		JSONObject jo = new JSONObject();
		
		Enumeration<String> sessionNames = session.getAttributeNames();
		while(sessionNames.hasMoreElements()) {
			String key = sessionNames.nextElement();
			Object value = session.getAttribute(key);
			String v = JsonUtil.toJson(value);
			jo.put(key, v);
		}
		return jo.toString();
	}

	// 设置单个session
	public static void setSessionString(String name, String o) {
		setSession(name, o);
	}

	// 验证
	@SuppressWarnings("unchecked")
	public Msg validateSession(@SuppressWarnings("rawtypes") Map _mParam) {
		Msg msg = new Msg();
		HttpSession session = null;
		try {
			session = getHttpSession();
			Enumeration<String> name_list = session.getAttributeNames();
			while (name_list.hasMoreElements()) {
				String name = name_list.nextElement();
				Object o = session.getAttribute(name);
				if (_mParam.containsKey(o)) {
					continue;
				}
				_mParam.put(name, o);
			}
		} catch (Exception e) {
			log.debug("非dwr调用！");
		}
		if (session == null) {
			return msg;
		}
		// msg.setWillreturn(true);
		// log.info("session过期！");
		return msg;
	}

	
	@Override
	public Msg execute(@SuppressWarnings("rawtypes") Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg) throws Exception {
		Msg msg = new Msg();

		msg = validateSession(_mParam);

		return msg;
	}

	// 通过sessionMap获取登陆系统的session的user
	public static List<Object> getSessionMap() {
		List<Object> list = new ArrayList<Object>();
		Map<String, HttpSession> sessionMap = ActiveUserListener.sessionMaps;
		HttpSession session;
		Set<String> keys = sessionMap.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			session = sessionMap.get(key);
			list.add(session.getAttribute(Constants.SESSION_USER));
		}
		System.out.println(list);
		return list;

	}
}
