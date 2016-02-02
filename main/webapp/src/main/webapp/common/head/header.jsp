<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" " http://www.w3.org/TR/html4/strict.dtd">
<script>
var contextPath = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="<%=basePath%>common/js/Constants.js"></script>
<!-- dhtmlx grid configuration -->
<!-- 新框架通用js￥ -->
<script type="text/javascript" src="<%=basePath%>/common/js/common_qyb.js"></script>

<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxForm/codebase/dhtmlxcommon.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/dhtmlxcommon.js"></script> --%>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/dhtmlxgrid.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/dhtmlxgridcell.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_drag.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_srnd.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/dhtmlxgrid.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_pgn_bricks.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/tip/jquery.jgrowl.css">
<link id="skin" rel="stylesheet" href="<%=basePath%>common/js/tip/Skins2/Green/jbox.css" />
<!-- dhtmlxgrid split功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_splt.js"></script>
<!-- dhtmlxgrid 分页功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/ext/dhtmlxgrid_pgn.js"></script>   

<!-- dhtmlxgrid counter功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_cntr.js"></script>

<!-- dhtmlx grid 过滤filter功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/ext/dhtmlxgrid_filter.js" ></script>

<!-- dhtmlx grid 链接link功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js" ></script>

<!-- calendar 功能  不是grid里面的calendar-->
<!-- Form组件中如果想使用calendar功能，使用dhtmlxCalendar -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/excells/dhtmlxgrid_excell_dhxcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">

<!-- tabbar标签工具条功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxTabbar/codebase/dhtmlxtabbar.css">

<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/ext/dhtmlxgrid_hmenu.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_hmenu.css">
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/ext/dhtmlxgrid_mcol.js"></script>

<!-- grid header rspan功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_rowspan.js"></script>

<!-- 下拉框combo功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxCombo/sources/dhtmlxcombo.js" ></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/excells/dhtmlxgrid_excell_combo.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxCombo/codebase/dhtmlxcombo.css">

<!-- dhtmlx windows -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/dhtmlxcontainer.js" ></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/dhtmlxwindows.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/dhtmlxwindows.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css">

<!-- dhtmlx 使用xmlB格式数据 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_nxml.js" ></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/ext/dhtmlxgrid_data.js"></script>

<!-- dwr 配置 -->
<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/util.js'></script>
<script type='text/javascript' src='<%=basePath%>common/js/dwr/sessionFilter.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/DwrGetSession.js'></script>
<script type='text/javascript' src='<%=basePath%>common/js/session.js'></script>
<!--<script type='text/javascript' src='<%=basePath%>dwr/interface/DwrGetGrid.js'></script>-->
<!-- dhtmlx grid wrapper code -->
<script type="text/javascript" src="<%=basePath%>common/js/dwr/dwrDhtmlx.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcbplugs/grid.js"></script>
<!-- jquery -->
<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/tip/jquery.jBox.src.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/tip/i18n/jquery.jBox-zh-CN.js"></script>


<script type="text/javascript" src="<%=basePath%>common/js/artDialog4.1.6/jquery.artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="<%=basePath%>common/js/artDialog4.1.6/plugins/iframeTools.js"></script>
<!-- 酷仓宝自定义工具类 -->
<script type="text/javascript" src="<%=basePath%>common/js/tools.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcbplugs/block.js"></script>
<!-- dojo -->
<script type='text/javascript' src='<%=basePath%>common/js/dojo/dojo.js'></script>
<!-- unknown -->
<script  type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_srnd.js"></script>

<!-- dhtmlxgrid to excel -->
<script  type="text/javascript" src="<%=basePath%>common/js/dhtmlxgrid_export.js"></script>

<!-- dhtmlxgrid Group功能 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/ext/dhtmlxgrid_group.js"></script>

<!--edit by wangchanglu { -->
<!--添加accordion -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_skyblue.css">
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxAccordion/codebase/dhtmlxaccordion.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxAccordion/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxAccordion/codebase/dhtmlxcontainer.js"></script>
<!--添加tree -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxTree/codebase/dhtmlxtree.css">
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
<!-- 添加Toolbar -->
<script src="<%=basePath%>common/js/kcb/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxToolbar/codebase/dhtmlxcommon.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css">

<!--添加layout -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxLayout/codebase/dhtmlxlayout.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css">
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxLayout/sources/dhtmlxlayout.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxLayout/codebase/dhtmlxcontainer.js" ></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxLayout/codebase/patterns/dhtmlxlayout_pattern4c.js" ></script>


<!-- 添加布局和皮肤 -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/theme/button/default.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/theme/moduleStyle.css">
<script type="text/javascript" src="<%=basePath%>common/theme/moduleLayout.js"></script>
<!-- } -->

<!-- 添加form -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css">
<script src="<%=basePath%>common/js/kcb/dhtmlxForm/codebase/dhtmlxform.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxForm/codebase/ext/dhtmlxform_item_calendar.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxForm/codebase/ext/dhtmlxform_item_combo.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxEditor/codebase/dhtmlxeditor.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxForm/codebase/ext/dhtmlxform_item_editor.js"></script>
<!-- form自定义 -->
<script type="text/javascript" src="<%=basePath%>common/js/dhtmlx/moduleForm.js"></script>
<script type="text/javascript" src="<%=basePath%>common/theme/moduleSearch.js"></script>

<!-- alert -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/theme/alert.css">
<script type="text/javascript" src="<%=basePath%>common/theme/alert.js"></script>

<!-- 添加window -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/dhtmlxwindows.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css">
<script src="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/dhtmlxcommon.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/dhtmlxcontainer.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>

<!-- 添加上传组件vault -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxVault/dhtmlxvault_std 1.6/codebase/dhtmlxvault.css" />
<script language="JavaScript" type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxVault/dhtmlxvault_std 1.6/codebase/dhtmlxvault.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxMenu/codebase/skins/dhtmlxmenu_dhx_skyblue.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/kcb/dhtmlxMenu/codebase/skins/dhtmlxmenu_dhx_black.css">
<script src="<%=basePath%>common/js/kcb/dhtmlxMenu/codebase/dhtmlxcommon.js"></script>
<script src="<%=basePath%>common/js/kcb/dhtmlxMenu/codebase/dhtmlxmenu.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxMenu/codebase/ext/dhtmlxmenu_ext.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxGrid/sources/ext/dhtmlxgrid_validation.js"></script>

<!-- 美元$符号变人民币￥ -->
<script type="text/javascript" src="<%=basePath%>/common/js/common.js"></script>

<!-- dhtmlx覆盖 -->
<script type="text/javascript" src="<%=basePath%>common/js/kcb/dhtmlxExtend.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/js/help.js"></script>
<!--  <script type='text/javascript' src='<%=basePath%>common/js/tip/jquery.jgrowl.js'></script>-->
<%
	String btns = request.getParameter("btns");
%>
<script type="text/javascript">
var btnsaa = "<%=btns%>";
showids = btnsaa.split(",");


var basePath = "<%=basePath%>";
window.dhx_globalImgPath="<%=basePath%>common/js/kcb/imgs/";


     String.prototype.trim=function(){
	　 return this.replace(/(^\s*)|(\s*$)/g, "");
	 }
	String.prototype.ltrim=function(){
	　 return this.replace(/(^\s*)/g,"");
	}
	String.prototype.rtrim=function(){
	　return this.replace(/(\s*$)/g,"");
	}

$(document).ready(function(){
	var btns = "<%=btns%>";
	if(btns != "null"){
		$(".butt[showid^='btn_']").hide();
		for(var i = 0;i<showids.length;i++){
			$(".butt[showid="+showids[i]+"]").show();
		} 
	}
});

(function (config) {
    config['lock'] = true;
    config['fixed'] = true;
    config['okVal'] = '确定';
    config['cancelVal'] = '取消';
    config["opacity"] = 0.5
    // [more..]
})(art.dialog.defaults);

function getSearchParam(){
	var param = {};
	
	var objs = $("#tabSearch .select");
	
	objs.each(function(){
		param[$(this).attr("n")] = $(this).attr("v");
	});
	
	return param;
}
function detectOS() {    
    var sUserAgent = navigator.userAgent;    
    var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");    
    var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");    
    if (isMac) return "Mac";    
    var isUnix = (navigator.platform == "X11") && !isWin && !isMac;    
    if (isUnix) return "Unix";    
    var isLinux = (String(navigator.platform).indexOf("Linux") > -1);    
    if (isLinux) return "Linux";    
    if (isWin) {    
    var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;    
    if (isWin2K) return "Win2000";    
    var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 ||   
    sUserAgent.indexOf("Windows XP") > -1;    
    if (isWinXP) return "WinXP";    
    var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;    
    if (isWin2003) return "Win2003";    
    var isWinVista= sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;    
    if (isWinVista) return "WinVista";    
    var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;    
    if (isWin7) return "Win7";    
    }    
    return "other";    
  } 
//处理字符串中含有的特殊字符,如 ">"、"<"等，如还有其他特殊字符，可自行添加处理
function handleChars(value){
	if(value == null || value == undefined){
		return "";
	}
	if(value.indexOf("&gt;") > -1){
		value = value.replace(new RegExp("&gt;","gm"),">");
	}
	if(value.indexOf("&lt;") > -1){
		value = value.replace(new RegExp("&lt;","gm"),"<");
	}
	return value;
}
</script>
<script type="text/javascript">
        /* jBox 全局设置 */
        var _jBoxConfig = {};
        _jBoxConfig.defaults = {
            top: '25%', /* 窗口离顶部的距离,可以是百分比或像素(如 '100px') */
        };
        $.jBox.setDefaults(_jBoxConfig);
</script>
<script type="text/javascript" src="http://a.tbcdn.cn/apps/isvportal/securesdk/securesdk.js" id="J_secure_sdk_script" data-appkey="21213047"></script>
