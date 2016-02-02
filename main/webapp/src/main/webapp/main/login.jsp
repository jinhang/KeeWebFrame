<%@page import="com.kcb.common.constant.Constants"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import ="java.net.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String basePath = request.getContextPath() + "/";
	int port = request.getServerPort();
	String ip = request.getServerName();
	String enable_transport_domain = Constants.ENABLE_TRANSPORT_DOMAIN;
	session.removeAttribute(Constants.SESSION_USER);
	InetAddress s = InetAddress.getLocalHost();
	System.out.println(request.getRemoteHost());
%>
<jsp:include page="../common/head/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="style/login.css">
<script type="text/javascript">

var isCookieChecked = false;
var ipAddress = '<%=ip%>';
var url = '<%=basePath%>';
var enableTransportDomain = '<%=enable_transport_domain%>';
var ipPort = '<%=port%>';
function getCookie (c_name) {
	if (document.cookie.length > 0) {
		var c_start = document.cookie.indexOf(c_name + "=")
    	if (c_start != -1) { 
        	c_start = c_start + c_name.length + 1;
        	var c_end = document.cookie.indexOf("^", c_start);
        	if (c_end == -1)
            	c_end = document.cookie.length;
        	return unescape(document.cookie.substring(c_start, c_end));
    	} 
  	}
	return "";
}

function setCookie (c_name, n_value, p_name, p_value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(n_value) + "^" + p_name + "=" + escape(p_value) + ((expiredays == null) ? "" : "^;expires=" + exdate.toGMTString());
}

function cleanCookie (c_name, p_name) {
	document.cookie = c_name + "=" + "^" + p_name + "=" + "^;expires=Thu, 01-Jan-70 00:00:01 GMT";
	isCookieChecked = false;
}

function checkCookie () {
    var username = getCookie('username');
    var password = getCookie('password');
    if (username != null && username != "" && password != null && password != "") {
    	document.getElementById("username").value = username;
        document.getElementById("password").value = password;
    	return true;
    }
}

function resetForm () {
	cleanCookie("username", "password");
	document.getElementById("logCheck").style.display = "";
	document.getElementById("keepPwdCh").style.display = "";
	document.getElementById("keepPwdLable").style.display = "";
	document.getElementById("username").value = "";
    document.getElementById("password").value = "";
}
</script>
    <script>
    //dwr.engine.setAsync(false);
    $(document).ready(function() {
    	$('#verCode')[0].value="";
    	
    	$('#logSubmitButton').click(function() {
    		//alert($('#username').val()+'a');
    		if($('#username').val()=='' || $('#password').val()=='')
    		{
    			$("#loginwarning").html("用户名或密码不能为空！");
    			return;
    		}
    				var remeberPwd;
    				if (true == isCookieChecked) {
//    					document.getElementById("verCode").value = "00";
    					remeberPwd = true;
    				}
    				else {
    					remeberPwd = false;
    				}
    				
    				var data = {
    					username:$('#username').val(),
    					password:$('#password').val(),
    					verCode:$('#verCode').val(),
    					remeberPwd:remeberPwd
    				}
    				$callTask("login.validate",data,function(msg){
    					if(msg.state == -1){
							 $("#loginwarning").html('验证码错误！');
							 resetValidateCode();
							 return;
						}
   						if(msg.state == -2){
							$("#loginwarning").html('用户名或者密码错误！');
							resetForm();
							resetValidateCode()
						}else {						
							if (true == document.getElementById("keepPassword").checked) {
								setCookie('username', $('#username').val(), 'password', $('#password').val(), 365);
							}
							if(enableTransportDomain == 'true'	
								||ipAddress.indexOf("127.0.0.1")>-1
								||ipAddress.indexOf("192.168")>-1
								||ipAddress.indexOf("localhost")>-1
								||ipAddress.indexOf("ewp.kucangbao.com")>-1
							){
								top.window.location.href = "index.jsp";
							}else{
								return null;
							}							
						}
    				});
        });
    });
    
    function resetValidateCode(){
    	dojo.byId('verCode').value="";
    	dojo.byId('logVerCodeImg').src='random.jsp?'+new Date().getTime();
    }
    function onKeyup(e){
    	if(e.keyCode == 13){
    		$('#logSubmitButton').click();
    	}
    }
    </script>
 </head>
<body>
	<div id="loginHeader">
		
	</div>
	<div id="loginContainer">
		<% if(ip.indexOf("gto.kucangbao.com")!=-1){ %>
			<div id="loginPanel_gto" class="fr">
		<%}else{ %>
			<div id="loginPanel" class="fr">
		<%} %>
		<div class="loginContent">
			<div class="filed">
				<label>用户名</label>
				<input id="username" type="text" name="username" />
			</div>
			<div class="filed">
				<label>密　码</label>
				<input id="password" type="password" name="password" />
			</div>
			<div id="logCheck" class="filed">
				<label>验证码</label>
				<input id="verCode" type="text" name="verCode" onkeypress="onKeyup(event);"/>
				<image  id="logVerCodeImg" class="verCodeImg" src="random.jsp">
				<a id="logVerCodeLink" class="logLink" onclick="javascript:resetValidateCode()"><span>看不清<br/>换一张</span></a>
			</div>	
			<div class="loginwarning" id="loginwarning"></div>	
			<div class="submit">
				<table width="200" border="0" cellspacing="0" cellpadding="0">
  					<tr>
  					<td><button id="logSubmitButton" class="submitButt" type="button">登录</button></td>
  					<td id="keepPwdCh"><input style="display:inline;float:left;" id="keepPassword" type="checkbox"/></td>
  					<td><span class="logLink" id = "keepPwdLable" onmouseover="this.style.cursor='default';" onclick="document.getElementById('keepPassword').checked=!(document.getElementById('keepPassword').checked);">记住密码<br/></span></td>
  					</tr>
  				</table>
			</div>
		</div>
		</div>
		<% if(ip.indexOf("gto.kucangbao.com")!=-1){ %>
			<div id="systemIntro_gto" class="fr"></div>
		<%}else{ %>
			<div id="systemIntro" class="fl">
			<div class="introduceWrap">
				<p class="introduce">我们一直致力于为客户打造一站式的用户体验，<br/>提供良好的解决方案。
				</p>
			</div>
			<div class="phoneWrap">
				<p class="phone">
				</p>
			</div>
		</div>
		<%} %>
		<div style="clear:both"></div>
	</div>
	
	<script type="text/javascript">
		if (true == checkCookie()) {
			isCookieChecked = true;
			document.getElementById("logCheck").style.display = "none";
			document.getElementById("keepPwdCh").style.display = "none";
			document.getElementById("keepPwdLable").style.display = "none";
		}
	</script>
</body>
</html>