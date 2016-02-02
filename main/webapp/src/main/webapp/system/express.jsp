<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/head/header.jsp" %>
<TITLE>物流</TITLE>
</HEAD>

<BODY>
<div id="topContainer" class="clearfix">
	<div>
	    <a class="butt" onclick="doAdd();"><span class="refreshButt">新增</span></a>
		<a class="butt" onclick="doDelete();"><span class="refreshButt">删除</span></a>
		<a class="butt" onclick="doSave();"><span class="refreshButt">保存</span></a>
		<a class="butt2" onclick="setTemplate();"><span class="refreshButt">设置面单模版</span></a>
		<a class="butt" onclick="deRefresh();"><span class="refreshButt">刷新</span></a>
	</div>
</div>
<div id="forward_company_grid"></div>

<script type="text/javascript" src='./js/express.js'></script>
</BODY>
</HTML>