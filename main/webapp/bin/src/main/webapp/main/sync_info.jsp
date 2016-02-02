<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/head/header.jsp"%>

<html>
<title>初始化流程</title>

<!-- DWR访问接口 -->
<script type="text/javascript" src="<%=basePath%>/dwr/interface/DwrCallWrapperPurchase.js"></script>
<script type="text/javascript" src="<%=basePath%>/dwr/interface/DwrCallServicePurchase.js"></script>
<script type="text/javascript" src="<%=basePath%>/dwr/interface/DwrCallWrapperSystem.js"></script>
<script type="text/javascript" src="<%=basePath%>/dwr/interface/DwrCallServiceSystem.js"></script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/DwrCallWrapperOrder.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/DwrCallServiceOrder.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/DwrCallWrapperTaobao.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/DwrCallServerOutstock.js'></script>

<script type="text/javascript" src="./js/common.js"></script>
</head>

<body>
<div id="winVP" style="position:absolute; height:600px; width:100%">
		<div id="topContainer" class="clearfix">
			<div id="controlPanel">
				<ul class="dhtmlxForm" name="buttonForm" oninit="doOnButtonFormInit">
					<li ftype="butt" name="save" command="save" label="saveButt" value="同步信息"></li>	
				</ul>
			</div>
			</div>
			</div>
<script type="text/javascript" src="./style/sync_info.js"></script>
</body>
</html>









