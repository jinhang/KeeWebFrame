<%@page import="com.kcb.common.constant.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../common/head/header.jsp"></jsp:include>
<%
	String basePath = request.getContextPath() + "/";
	request.getSession().setAttribute("session_isOpenLogin", false);
	String redirect_uri1 = Constants.REDIRECT_URI;
	String open_appkey1 = Constants.TOP_APPKEY;
	Boolean taobaoLogined;
	if(request.getSession().getAttribute("code")!=null){
		taobaoLogined = true;
	}else{
		taobaoLogined=false;
	}
	
	String buyUrl = Constants.KCB_BUY_URL;
%>
<script src="./style/dhtmlxlayout.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/js/help.js"></script>
<script type='text/javascript' src='<%=basePath%>common/js/dojo/dojo.js'></script>
<script>
var open_appkey = '<%=open_appkey1%>';
var redirect_uri = '<%=redirect_uri1%>';
var taobaoLogined = '<%=taobaoLogined%>';
var buyUrl = '<%=buyUrl%>';
var isOpenLogin = true;
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./style/mainInterface.css">
<style>
div.dhxlist_txt_label.align_left {
	color: #FFF
}

.dhx_tabcontent_zone {
	border: 1px solid #FFF;
	z-index: 2;
	background-color: #fff !important;
}
</style>
<title>酷仓宝</title>

<style type="text/css">
#out {
	width: 250px;
	height: 20px;
	background: #EEE;
}

#in {
	width: 10px;
	height: 20px;
	background: #778899;
	color: white;
	text-align: center;
}

#font_color {
	background: yellow;
	text-align: center;
	color: white;
}
</style>
</head>
<body id="kucangbao">
	<div id="container">
		<div id="header">
			<div id="headerWrap">
				<h1>
					<span class="logoName"></span>
				</h1>

				<div class="userInfo" style="float: right">
					<ul>
						<li class="last"><a href="javascript:kcbmain.addloginTab()">登录</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="footer">
		</div>
	</div>
	<iframe id="soundframe"></iframe>
	<script src="./style/mainInterface.js"></script>
	<noscript>你的浏览器不支持 JavaScript!</noscript>
</body>

<script type="text/javascript"
	src="<%=basePath%>/common/js/kcbplugs/msg.js"></script>
</html>