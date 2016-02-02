<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%
    out.println(session.getAttribute("FileUpload.Progress."+request.getParameter("sessionId").toString().trim()));
%>
