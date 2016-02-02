<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath() + "/";

	String soundfile = request.getParameter("soundfile");
%>
<title>入库贴条码</title>


</head>
<body>
    <script>
		var MSIE=navigator.userAgent.indexOf("MSIE");  
	　　var NETS=navigator.userAgent.indexOf("Netscape");  
	　　var OPER=navigator.userAgent.indexOf("Opera");  
	　　if((MSIE>-1) || (OPER>-1)) {  
	　　document.write('<BGSOUND VOLUME=100 SRC="<%=basePath%>sound/<%=soundfile%>" mce_SRC="<%=basePath%>sound/<%=soundfile%>" LOOP=1>');  
	　　} else {  
	　　document.write('<video style="display:none;" src="<%=basePath%>sound/<%=soundfile%>" controls="controls" autoplay="true">');  
	　　document.write('您的浏览器不支持 video 标签。');
		document.write('</video>');
		
	　　}
	</script>
	</body>
</html>