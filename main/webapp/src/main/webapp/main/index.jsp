<%@page import="com.kcb.common.constant.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kcb.common.util.RemoteClient"%>
<jsp:include page="../common/head/header.jsp"></jsp:include>
<%
	String basePath = request.getContextPath() + "/";
%>
<%
 	com.wfs.model.tables.pojos.Staff user = (com.wfs.model.tables.pojos.Staff) request.getSession().getAttribute("session_user");
	String currentSessionStaffid  = (String) request.getSession().getAttribute(Constants.SESSION_CURRENT_USER_ID);
	String currentSessionWarehouseId = (String) request.getSession().getAttribute("session_current_warehouse_id");
	String name=user.getName()!=null&&!user.getName().equals("")?user.getName():user.getUsername();
%>
<script src="./style/dhtmlxlayout.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/js/help.js"></script>
<script type='text/javascript' src='<%=basePath%>common/js/dojo/dojo.js'></script>
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
<title>E网配</title>
<script>
	var myself="<%=user.getName()%>";
	var isOpenLogin =false;
	function cleanCookie (c_name, p_name) {
		document.cookie = c_name + "=" + "^" + p_name + "=" + "^expires=Thu, 01-Jan-70 00:00:01 GMT";
		isCookieChecked = false;
		cleanSession();
	}
	function cleanSession(){
		DwrGetSession.cleanSession('session_current_client_id', function(msg) {
		});
	}
	
	var warehouseCount = 1;
	var shopCount = 0;
	if(window.top!=window.self){
		 parent.window.location.href = "index.jsp";
	}
	</script>
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

#showtip {
	background: url(../images/xzddb.png) no-repeat;
	width: 221px;
	height: 157px;
	position: fixed;
	top: 24px;
	z-index: 999;
	padding: 12px 0 0 14px;
	display: none;
}

#close_btn{
	cursor: pointer;
	width: 20px;
	height: 20px;
	background-image:url("./style/imgs/close.png");
	
}

#download{
color: #006699 !important;
border-radius: 5px; 
background: #FFFFFF;
}

#download:hover {
text-decoration: underline !important;
}
</style>
</head>
<body id="kucangbao">
	<div id="container">
		<div id="header">
			<div id="headerWrap">				
				<div class="userInfo" style="float: right;">
					<ul>
						<li ftype="label" name="msg"><a id="msg"></a></li>
						<li><a id="userName"><%=name%></a></li>
						<li class="last"><a id="userLogOut" href="../main/login.jsp"
							onclick="cleanCookie('username', 'password');">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="master" style="display: none;"></div>
	</div>
	<iframe id="soundframe"></iframe>
	<script src="./style/mainInterface.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/index.js"></script>
	<noscript>你的浏览器不支持 JavaScript!</noscript>
</body>
<script type="text/javascript"
	src="<%=basePath%>/common/js/kcbplugs/msg.js"></script>
<script type="text/javascript">

        var Sys = {};

        var ua = navigator.userAgent.toLowerCase();

        var s;

        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :

        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :

        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :

        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :

        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

        if(!Sys.ie && !Sys.firefox && !Sys.chrome){ 		
            	$("#showabout").html("<p  style='line-height:32px;font-size:16px;font-weight:600;'>为保证良好的操作体验，请使用火狐(firefox)、谷歌(chrome)、IE8、IE9或IE10,我们正在努力支持更多的浏览器。<br><a href='http://download.firefox.com.cn/releases/webins3.0/official/zh-CN/Firefox-latest.exe'>下载firefox</a></p>");	
//            	showVersion();
        } 
    	
    </script>
	
</html>
