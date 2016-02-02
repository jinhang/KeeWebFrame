<%@page import="com.kcb.common.constant.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:include page="../common/head/header.jsp"></jsp:include>
<%
	String basePath = request.getContextPath() + "/";
%>
<%
	com.kcb.model.Staff user = (com.kcb.model.Staff) request.getSession().getAttribute("session_user");
	String currentSessionStaffid  = (String) request.getSession().getAttribute(Constants.SESSION_CURRENT_USER_ID);
	String currentSessionWarehouseId = (String) request.getSession().getAttribute("session_current_warehouse_id");
	String currentSessionShopId = (String) request.getSession().getAttribute(Constants.SESSION_CURRENT_SHOP_ID);
	Integer warehouseCount = (Integer)request.getSession().getAttribute(Constants.SESSION_CURRENT_STAFF_WAREHOUSE_COUNT);
	Integer shopCount = (Integer)request.getSession().getAttribute(Constants.SESSION_CURRENT_STAFF_SHOP_COUNT);
%>
<script src="./style/dhtmlxlayout.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/js/help.js"></script>
<script type='text/javascript' src='<%=basePath%>common/js/dojo/dojo.js'></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="./style/mainInterface.css">
<style>
div.dhxlist_txt_label.align_left{color:#FFF}
.dhx_tabcontent_zone{
	border:1px solid #FFF;
	z-index:2;
	background-color: #fff !important;
}

</style>
        <title>酷仓宝</title>
<script>

	if (!window.indexedDB) {  
	    window.indexedDB = window.mozIndexedDB || window.webkitIndexedDB;  
	}  
	var request = indexedDB.open("db2",2);
	request.onsuccess = function(event) {   
		db = event.target.result; 
		var transaction = db.transaction(["outstock"],"readwrite"); 
		var objectStore = transaction.objectStore("outstock");
		var request = objectStore.clear();
		request.onsuccess = function(event) {   
			 console.log("清除成功！");
		}
	};
</script>

</html>
