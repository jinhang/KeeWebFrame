package com.kcb.common.filter;

/*   
 使用Filter进行用户信息验证等操作是个不错的选择。   
 这是LoginControlFilter.java   
 初学者注意，这里用到的是javax.servlet.*，不包含在j2sdk里，需要从j2ee sdk中，或者$tomcat_home/lib/ 或者$resin_home/lib/下找到那个jar包。   
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Array;

import com.kcb.common.constant.Constants;
import com.kcb.common.server.ActiveUserListener;
import com.wfs.model.tables.pojos.Staff;

public class LoginControlFilter extends HttpServlet implements javax.servlet.Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		int index = url.lastIndexOf(".dwr");
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();

		if (isLoginedByOtherUser(session)) {
			System.out.println("该用户在其他地方登录！");
			session.removeAttribute(Constants.SESSION_USER);
			if (index != -1) {
				res.setStatus(Constants.LOGINED_BY_OTHER_USER);
			} else {
				res.sendRedirect(basePath + "/main/login.jsp");
				return;
			}
		} else if (ValidateUrl(url)) {
			chain.doFilter(request, response);
		} else if (session.getAttribute(Constants.SESSION_USER) == null) {
			System.out.println("用户未登陆！");
			if (index != -1) {
				res.setStatus(1000);
			} else {
				res.sendRedirect(basePath + "/main/login.jsp");
				return;
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	private boolean ValidateUrl(String url) {
		List<String> url_list = new ArrayList();
		url_list.add("main/login.jsp");
		url_list.add("/random.jsp");
		url_list.add("/DwrCallWrapperBasic.getUser.dwr");
		url_list.add(".xsd");
		url_list.add("/callback");
		url_list.add("/bc");
		url_list.add("getSessionNames.dwr");
		url_list.add("getSessionList.dwr");
		// url_list.add("/kcb-1.0/");
		for (String string : url_list) {
			int index = url.lastIndexOf(string);
			if (index != -1) {
				return true;
			}
		}
		url_list = new ArrayList();
		url_list.add("__System.");
		for (String string : url_list) {
			int index = url.indexOf(string);
			if (index != -1) {
				return true;
			}
		}
		return false;
	}

	public boolean openValidateUrl(String url) {
		if (url.endsWith("header.jsp"))
			return true;
		if (url.endsWith("openIndex.jsp"))
			return true;
		if (url.endsWith("welcome.jsp"))
			return true;
		if (url.endsWith(".dwr"))
			return true;
		if (url.endsWith(".js"))
			return true;
		if (url.endsWith("main/index.jsp"))
			return false;
		if (url.endsWith(".jsp"))
			return true;
		return false;
	}

	private boolean isLoginedByOtherUser(HttpSession session) {
		Staff user = (Staff) session.getAttribute(Constants.SESSION_USER);
		String sessionid = session.getId();
		if (user != null) {
			String logined_sessionid = ActiveUserListener.getActiveUser(user.getStaffid());
			if (logined_sessionid != null && logined_sessionid.compareToIgnoreCase(sessionid) != 0) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig filterConfig) {
		System.out.println("OnlineFilter initialized.");
	}

	public void destroy() {
		System.out.println("OnlineFilter destroied");
	}

}
