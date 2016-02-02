<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/head/header.jsp"%>
<%   
 String setType = request.getParameter("setType");  //setType ==1 KCB设置快递公司模板   ==0 或空  客户设置快递模板
 String updateTemplateId = request.getParameter("updateTemplateId");
 String forwardId = request.getParameter("forwardId");
 String fromPage = request.getParameter("fromPage");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>/common/js/kcb/dhtmlxCombo/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/js/kcb/dhtmlxCombo/codebase/dhtmlxcombo.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/js/kcb/dhtmlxCombo/codebase/ext/dhtmlxcombo_extra.js"></script>
<link rel="stylesheet" type="text/css" href="./css/dhtmlxcombo.css">
<!-- dropdownCheckList相关文件 -->
<link rel="stylesheet" type="text/css" href="./css/jquery-ui-1.8.13.custom.css">
<link rel="stylesheet" type="text/css" href="./css/ui.dropdownchecklist.themeroller.css">
<script type="text/javascript" src="./js/jquery-ui-1.8.13.custom.min.js"></script>
<script type="text/javascript" src="./js/ui.dropdownchecklist-1.4-min.js"></script>

<script type='text/javascript'  src='<%=basePath%>/common/js/arabiatochinese.js'></script>
<script language="javascript" src="<%=basePath%>/common/js/print/LodopFuncs.js"></script>
    <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
        <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="<%=basePath%>resource/lodop/install_lodop32.exe"></embed>
    </object>
    <script>
    var fromPage='<%=fromPage%>'; 
    var setType=<%=setType%>; 
    var updateTemplateId = '<%=updateTemplateId%>';
    var forwardId = '<%=forwardId%>';
    try{
    	if(document.documentMode!="undefined"){
			if(document.documentMode==10){
				document.write('<link href="./css/ie10style.css" rel="stylesheet" type="text/css">');
			}
    	}
    }catch(e){}
    
</script>
<style>
.nav_link{
	line-height:22px !important;
	
}
.dhxlist_img{
	margin:5px 0 0 0;
}
.dhx_combo_box{width:90px !important;height:26px !important;}
.dhx_combo_list {width:138px !important;height:222px !important;}
ul.optioncell{margin-bottom:5px; }
ul.optioncell li{display:block; float:left; margin-bottom:5px;}
#print_option{width: 820px; height:75px; position: absolute; z-index: 100011; 
margin-left: 165px; background:white; top:-54px; left:-82px; border:1px solid black;
 overflow:auto; padding:3px 5px; display:none; }
</style>
</head>
<body >
	
<table  style="width:100%;height:97%" class="height_t" align="center">
<tr  style="height:30Px">
<td>  
<div id="myViewForm" style="width:100%">
    <ul class="dhtmlxForm" name="myViewForm" oninit="doOnFormInit" >
   <li ftype="settings"></li>
        <li ftype="block" name="data" >
        
        <% if(!"1".equals(setType)){   %>
                    <ul>
                    	<li ftype="hidden" name="isopen" > isopen:</li>
                         <li ftype="newcolumn">
                        <li ftype="hidden" name="templateid" > id:</li>
                        <li ftype="newcolumn">
                        <li ftype="hidden" name="clientid" > 所属客户</li>  
                        <li ftype="newcolumn">
                        <%if("3".equals(setType)){%>
                        <li ftype="select" name="transport" style="width:120px">物流公司:</li>
                        <li ftype="newcolumn"></li>
                        <li ftype="select" name="templates" style="width:130px">&lt;span style="color: red;"&gt;*&lt;/span&gt;</li>
                        <li ftype="newcolumn"></li>
                        <%} %>
                        <li ftype="input" name="templatename" style="width:100px">模板名称&lt;span style="color: red;"&gt;*&lt;/span&gt;</li>
                        <li ftype="newcolumn"></li>
                        <li ftype="select" name="printer" style="width:120px">默认打印机:</li>              
                    </ul>    
                <% } %>    
                
        <% if("1".equals(setType)){   %>
                    <ul>
                     	<li ftype="select" name="templates" style="width:130px;height:27px;margin-left:15px;"></li>
                     	<li ftype="newcolumn">
                     	<li ftype="input" name="templatename" style="width:120px;">模版名称&lt;span style="color: red;"&gt;*&lt;/span&gt;</li>
                     	<li ftype="newcolumn">
                     	<li ftype="checkbox"  name="isDefault" id="isDefault" value="0" style="height:27px;">设为默认模版</li>
                        <li ftype="hidden" name="forwardcompanyid" > id:</li>
                        <li ftype="newcolumn">
                        <li ftype="hidden"  name="templateimgurl" style="width:145px">背景图片:</li>
                    </ul>    
                <% } %>                                                                  
       </li>
    </ul>
 </div>
</td>
</tr>

<tr>
<td>
<div style="height:37px;margin-top:5px">
<div style="position:absolute;margin-left:15px;margin-top:5px;z-index:100000">
<!-- <select id="select_checkbox" multiple="multiple">
			  <optgroup label=" ">
	            <option>=寄件人姓名</option>
	            <option>=寄件人单位名称</option>
	            <option>=寄件人详细地址</option>
	            <option>=寄件人电话</option>
	          </optgroup>
	          <optgroup label=" ">
					<option>=收件人姓名</option>
					<option>=收件人单位名称</option>
					<option>=收件人详细地址</option>
					<option>=收件人手机</option>
					<option>=收件人电话</option>
				</optgroup>
	            <optgroup label=" ">
					<option>=所属省</option>
					<option>=所属市</option>
					<option>=所属区县</option>
					<option>=内件数量</option>
					<option>=年</option>
					<option>=月</option>
					<option>=日</option>
				</optgroup>
			    <optgroup label=" ">
					<option>=申通大头笔</option>
					<option>=买家留言</option>
					<option>=卖家留言</option>
					<option>=订单编号</option>
					<option>=宝贝信息</option>
					<option>=宝贝信息2</option>
					<option>=宝贝+规格+数量</option>
					<option>=宝贝+数量</option>
					<option>=规格+数量</option>
					<option>=本次打印流水号</option>
				</optgroup>
				<optgroup label=" ">
				<option>=买家昵称</option>
				<option>=卖家昵称</option>
				</optgroup>
	        </select>   -->
<div id="combo_zone2" style="width:200px; height:30px;"></div>
<div id="optionButton" style="position:absolute; top:-15px; width:85px; height:40px; ">
	        <a class="butt" style="position:absolute; top:15px; width:80px;height:26px;">
			<span class="optionButt"  onmouseover="addPrintOption()";>打印选项</span>
		</a></div>
						<div id="print_option" style="display:none;">
							<ul class="optioncell">						
								<li><input type="checkbox" id="cbox_no0" class="cbox_option"
									value="=寄件人姓名" />=寄件人姓名&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no1" class="cbox_option"
									value="=卖家昵称" />=卖家昵称&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no2" class="cbox_option"
									value="=店铺名称" />=店铺名称&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no3" class="cbox_option"
									value="=寄件人详细地址" />=寄件人详细地址&nbsp</li>
								<li><input type="checkbox" id="cbox_no4" class="cbox_option"
									value="=寄件人电话" />=寄件人电话&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no5" class="cbox_option"
									value="=收件人姓名" />=收件人姓名&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no6" class="cbox_option"
									value="=买家昵称" />=买家昵称&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								</ul>
								<ul class="optioncell">
								<li><input type="checkbox" id="cbox_no7" class="cbox_option"
									value="=收件人详细地址" />=收件人详细地址&nbsp</li>
								<li><input type="checkbox" id="cbox_no8" class="cbox_option"
									value="=收件人手机" />=收件人手机&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no9" class="cbox_option"
									value="=收件人电话" />=收件人电话&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no10" class="cbox_option"
									value="=所属省" />=所属省&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no11" class="cbox_option"
									value="=所属市" />=所属市&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no12" class="cbox_option"
									value="=所属区县" />=所属区县&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no13" class="cbox_option"
									value="=邮政编码" />=邮政编码&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no14" class="cbox_option"
									value="=年" />=年&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								</ul>
								<ul class="optioncell">								
								<li><input type="checkbox" id="cbox_no15" class="cbox_option"
									value="=月" />=月&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no16" class="cbox_option"
									value="=日" />=日&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no17" class="cbox_option"
									value="=订单编号" />=订单编号&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no18" class="cbox_option"
									value="=买家留言" />=买家留言&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no19" class="cbox_option"
									value="=卖家留言" />=卖家留言&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no20" class="cbox_option"
									value="=宝贝+规格+数量" />=宝贝+规格+数量&nbsp</li>
								<li><input type="checkbox" id="cbox_no21" class="cbox_option"
									value="=宝贝+数量" />=宝贝+数量&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								</ul>
								<ul class="optioncell">
								<li><input type="checkbox" id="cbox_no22" class="cbox_option"
									value="=规格+数量" />=规格+数量&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no23" class="cbox_option"
									value="=内件数量" />=内件数量&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no24" class="cbox_option"
									value="=条码" />=条码&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no25" class="cbox_option"
									value="=全称+规格+数量" />=全称+规格+数量&nbsp</li>
								<li><input type="checkbox" id="cbox_no27" class="cbox_option"
									value="=申通大头笔" />=申通大头笔&nbsp&nbsp&nbsp&nbsp&nbsp</li>
								<li><input type="checkbox" id="cbox_no28" class="cbox_option"
									value="=本次打印流水号" />=本次打印流水号</li>
						</div>
						</div>
		<a class="butt" style="margin-left:200px;margin-top:5px"><span class="saveButt" onclick="addPrintText();">增加文本</span></a>
		<a class="butt" style="margin-top:5px"><span class="saveButt" onclick="deleteSelectText();">删除</span></a>
		<a class="butt" style="margin-top:5px"><span class="saveButt" onclick="printview();">预览</span></a>
		<input type="checkbox"  id ="cbox_needsendcode" value="1" /><span id='span_needsendcode'>启用热敏模式</span>
</div>
</td>
<tr>

<tr>
<td >
  <% if("1".equals(setType)){   %>
<div id="vaultDiv" style="height:105px" ></div> 
  <% } else { %>  
<div id="vaultDiv" style="display:none;height:105px" ></div> 
<% }  %> 
</td>
</tr>
<tr style="width:100%;height:95%">
<td style="width:100%;height:90%" class="height_t"><div  style="width:100%;height:100%">
    <object id="LODOP2" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=100% height=95%> 
      <param name="Caption" value="内嵌显示区域">
      <param name="Border" value="1">
      <param name="Color" value="#C0C0C0">
      <embed id="LODOP_EM2" TYPE="application/x-print-lodop" width=100% height=95% PLUGINSPAGE="install_lodop.exe">
    </object>
    </div>
</td>
</tr>
<tr  style="height:10Px;"  align="right">
<td>
<a id="purchase_list_pay" class="butt2"   onclick="save();"  ><span class="saveButt">保存</span></a>
<% if(!"1".equals(setType)){   %>
<!-- <a id="purchase_list_pay" class="butt"   onclick="newAsSave();"  ><span class="saveButt">另存为新模板</span></a> -->
<% } %>
<a id="purchase_list_pay" class="butt"   onclick="cancel();"  ><span class="saveButt">取消</span></a>
</td>
</tr>

</table>

<!--判断浏览器-->
<script type="text/javascript">

        var Sys = {};

        var ua = navigator.userAgent.toLowerCase();

        var s;

        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :

        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :

        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :

        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :

        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

        if(!Sys.ie && !Sys.firefox){ 		
            $("#combo_zone2").hide();	
        } 
        else{
        	$("#optionButton").hide();
        }
        </script>
        
<script>
try{
	if(document.documentMode==8 || document.documentMode==9){
		$(".height_t").css("height","89%");
	}
}catch(e){
	
}
</script>
<script type="text/javascript" src="./js/template_view.js"></script>
<div id="zjdyDiv"></div>

</body>
</html>