<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%   
	String picdir = request.getParameter("picdir");
%>

<style type="text/css">
body {
	background-attachment: fixed;
	background-color: #fff !important;
	background-repeat:no-repeat;
	background-position:center;
	background-image:url(style/imgs/theme/index3.jpg);
}
</style>

<title>Insert title here</title>
</head>
<body>
</body>
<script>
	var gpicdir = "<%=picdir%>";
	document.body.style.backgroundImage='url(style/imgs/theme/'+gpicdir+')';
</script>
</html>