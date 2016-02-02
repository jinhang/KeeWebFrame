<%@page import="com.kcb.common.constant.Constants"%>
<%@ page contentType="image/jpeg" %>
<%@ page import="java.awt.*,java.awt.image.*" %>
<%@ page import="java.util.*,javax.imageio.*,java.util.List" %>
<%@ page import="com.kcb.common.login.RandomImage;" %>
<%
//设置页面不缓存
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    RandomImage ri = new RandomImage();
    List result = ri.createRandomImage();
    BufferedImage pic = (BufferedImage)result.get(0);
 // 将认证码RS存入SESSION中共享
     session.setAttribute(Constants.SESSION_RANDOM,result.get(1).toString());
// 输出生成后的验证码图像到页面
		ImageIO.write(pic, "JPEG", response.getOutputStream());
		out.clear();
		out = pageContext.pushBody();

%>