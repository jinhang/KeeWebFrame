package com.kcb.common.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.wfos.engine.common.WebConfig;
import com.wfos.engine.wrapper.model.Msg;

/**
 * @author jyl
 * @version 创建时间：2013-8-15 下午1:54:20
 * 类说明
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String webRootPath;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		DwrCallWrapperBasic basic = (DwrCallWrapperBasic) WebConfig.getBean("DwrCallWrapperBasic");
//		webRootPath = getServletContext().getRealPath("");
//		String taskid = req.getParameter("taskid");
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//		HttpSession session = req.getSession();
//		Msg msg = basic.getUserForClient(taskid, username, password, "1111", true,session);
//		System.out.println(req.getParameter("taskid"));
//		resp.setContentType("text/plain;charset=utf-8");
//		resp.setCharacterEncoding("utf-8");
//		String json = "{state:";
//		if(msg.ovalue==null){
//			json+="0,shopface:0,staffid:\""+session.getAttribute("session_current_user_id")+"\"}";
//		}else{
//			json+="1,staffid:\""+session.getAttribute("session_current_user_id")+"\",shopface:";
//			DwrCallWrapperOutstock dcw = (DwrCallWrapperOutstock) WebConfig.getBean("DwrCallWrapperOutstock");
//			msg = dcw.hasPermissionForClient("t_judge_permission_by_permissionid","40288zzzz41b22f601341b4749c11134",session);
//			Integer count = Integer.valueOf(String.valueOf(msg.getLvalue().get(0)));
//			Client c =  (Client) session.getAttribute("session_client");
//			if(count>0){
//				msg = dcw.getShopfaceIDForClient("t_get_shopface_by_department",session);
//				if(msg.getLvalue().size()>0){
//					Client client = (Client) msg.getLvalue().get(0);
//					json+="1,\"staff_clientid\":\""+c.getId()+"\",\"weight_current_clientid\":\""+client.getId()+"\",\"companyID\":\""+c.getForwardcompanyid()+"\"}";
//				}else{
//					json+="1,\"staff_clientid\":\""+c.getId()+"\",\"weight_current_clientid\":\""+null+"\",\"companyID\":\""+c.getForwardcompanyid()+"\"}";
//				}
//			}else{
//				json+="0,\"staff_clientid\":\""+c.getId()+"\",\"companyID\":\""+c.getForwardcompanyid()+"\"}";
//			}
//		} 
//		PrintWriter writer=SyncClientInfoServlet.createGzipPw(req,resp);
//		writer.write(json);
//		writer.close();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		super.service(arg0, arg1);
	} 
	
}
