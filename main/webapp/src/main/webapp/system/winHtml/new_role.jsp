<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@include file="../../common/head/header.jsp"%>
<%   
	String clientid = request.getParameter("clientid");
%>
<script>
var clientid = '<%=clientid%>';
</script>
<style>
.u84_container {
    height: 132px;
    left: 500px;
    position: absolute;
    top: 478px;
    width: 420px;
}

#u84_img {
    height: 132px;
    left: 0;
    position: absolute;
    top: 0;
    width: 420px;
}
div {
    background-repeat: no-repeat;
}

.u87 {
    font-family: Arial;
    height: 14px;
    left: 13px;
    position: absolute;
    text-align: left;
    top: 25px;
    width: 90px;
    word-wrap: break-word;
}
.u88_container{
margin: 15px 0 0 0px;
}
.u91{margin: 13px 0 0 260px;}
</style>
<div class="u88_container" id="u88">
<ul class="dhtmlxForm"  name="myForm" oninit="doOnFormInit" >
	<li ftype="settings" position="label-left"  labelWidth="80" inputWidth="306"></li>
	<li ftype="input" name="name">角色名称</li>
</ul>

</div>

<div class="u91" id="u91">
<div id="u91_rtf">
	<ul class="dhtmlxForm" name="mybuttonform" oninit="dobuttonFormInit">
    	<li ftype="label" name="xx" labelWidth="128">&nbsp</li>
    	<li ftype="newcolumn"></li>
    	<li ftype="butt" name="save" label="saveButt" value="保存" command="save"></li>
    	<li ftype="newcolumn"></li>
    	<li ftype="butt" name="cancle" label="returnGoodsButt" value="关闭" command="cancle"></li>
	</ul>
</div>
</div>
<script type="text/javascript" src='../js/new_role.js'></script>