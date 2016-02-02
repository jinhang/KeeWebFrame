<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/head/header.jsp" %>
<TITLE>角色权限</TITLE>
</head>
<style>
.dhx_toolbar_base_dhx_skyblue.in_layoutcell
{
	background-image:none !important;
	padding:5px 0 0 0;
}
.dhx_toolbar_base_dhx_skyblue
{
	background-image:none;
}
.dhx_toolbar_base_dhx_skyblue div.dhx_toolbar_btn{
	margin-top:0px;
	padding:0;
}
.dhx_toolbar_base_dhx_skyblue div.dhx_toolbar_btn div{
background: url("./img/add1.png") no-repeat scroll left top transparent;
padding:6px 16px;height:14px;
}
.dhx_toolbar_base_dhx_skyblue div.dhx_toolbar_btn div:hover{
background: url("./img/add1.png");
background-position:0px 52px;
}
.dhx_toolbar_base_dhx_skyblue div.dhx_toolbar_btn div:active{
background: url("./img/add1.png");
background-position:0px 26px;
}
.dhx_toolbar_base_dhx_skyblue div.dhx_toolbar_btn.over{
	background:none;
	border:none;margin-right: 1px;
    margin-top: 0px;
    padding: 0;
}
.dhx_toolbar_base_dhx_skyblue div.dhx_toolbar_btn.pres{
	background:none;
	border:none;margin-right: 1px;
    margin-top: 0px;
    padding:0;
}
.dhx_toolbar_base_dhx_skyblue{
	height:37px;
}
</style>
<body>
<div id="role_right" style=" height:100%;overflow-y:scroll"></div>

<script type="text/javascript" src='./js/role.js'></script>
<script>
$(document).ready(function(){
	  $(".dhx_toolbar_btn div").eq(3).css("background",'url("./img/butt12.png")');
	  $(".dhx_toolbar_btn div").eq(3).hover(
			function(){
				$(this).css("background",'url("./img/butt12.png")');
				$(this).css("background-position",'0 52px');
			},
			function(){
				$(this).css("background",'url("./img/butt12.png")');
				$(this).css("background-position",'0 0');
			}
	  );
});
</script>
</body>
</html>