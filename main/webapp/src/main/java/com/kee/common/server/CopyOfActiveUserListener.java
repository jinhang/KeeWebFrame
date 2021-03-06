package com.kee.common.server;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;

import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;

import com.kee.common.constant.Constants;

public class CopyOfActiveUserListener implements HttpSessionListener {
	private static int sessionCount = 0;
	private static Map<String,HttpSession> sessionMaps = new HashMap<String,HttpSession>(); // 存放session的集合类
	public static Map<String,ScriptSession> scriptSessions = new HashMap<String,ScriptSession>();// 存放scriptsession的集合类
	private static Map<String, String> userSessionMaps = new HashMap<String, String>();

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		String sessionId = session.getId();
		System.out.println("Create a session:" + sessionId);
		sessionMaps.put(sessionId, session);
		sessionCount++;
		//ScriptSession监听
		initScriptSessionListener();
	}
	

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		sessionCount--;
		String sessionId = arg0.getSession().getId();
		Object userid = arg0.getSession().getAttribute(Constants.SESSION_CURRENT_USER_ID);
		if(userid != null){
			removeActiveUser(userid.toString());
		}
		sessionMaps.remove(sessionId);// 利用会话ID标示特定会话
		System.out.println("Destroy a session:" + sessionId+";session count"+sessionCount);
	}
	
	
	public static String getActiveUser(String userid) {
		return userSessionMaps.get(userid);
	}


	public static void putActiveUser(String userid,String sessionid) {
		userSessionMaps.put(userid, sessionid);
	}


	public static void removeActiveUser(String userid) {
		userSessionMaps.remove(userid);
	}	
	
	public static HttpSession getSessionByUserid(String userid){
		String sessionid = getActiveUser(userid);
		if(sessionid == null){
			return null;
		}
		return sessionMaps.get(sessionid);
	}

	public static int getSessionCount() {
		return sessionCount;
	}

	public static Map getSessionMaps() {
		return sessionMaps;
	}
	
	public void initScriptSessionListener(){
//		System.out.println("--------监听方法执行-------");
		Container container = ServerContextFactory.get().getContainer();  
        ScriptSessionManager manager = container.getBean(ScriptSessionManager.class); 
        ScriptSessionListener listener = new KcbScriptSessionListener();
        manager.addScriptSessionListener(listener);  
	}
	
	/**
	 * ScriptSession监听内部类 
	 * @author jyl
	 *
	 */
	public class KcbScriptSessionListener implements ScriptSessionListener{

		@Override
		public void sessionCreated(ScriptSessionEvent sse) {
			// TODO Auto-generated method stub.
			 ScriptSession scriptSession = sse.getSession();
			 HttpSession session = WebContextFactory.get().getSession(); 
			 if(scriptSession.getPage().equals("/kcb-1.0/main/")){
				 Object object = session.getAttribute(Constants.SESSION_CURRENT_CLIENT_ID); 
	             String clientId=null;
	             if(object!=null){
	            	 clientId = object.toString();
	             }
	             scriptSession.setAttribute(Constants.SESSION_CURRENT_CLIENT_ID, clientId);  
	             if(clientId!=null){
					 if(scriptSessions.get(session.getId())!=null&&scriptSessions.get(session.getId())!=scriptSession){
						 // 销毁scriptSession
						 scriptSessions.get(session.getId()).invalidate();
					 }
	            	 scriptSessions.put(session.getId(), scriptSession);
	             }
			 }else{
				 // 销毁scriptSession
				 scriptSession.invalidate();
			 }
		}

		@Override
		public void sessionDestroyed(ScriptSessionEvent sse) {
			
		}
		
	}
}