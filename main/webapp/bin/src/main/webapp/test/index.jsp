<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/head/header.jsp"%>
<HTML><HEAD><TITLE></TITLE>
<link rel="STYLESHEET" type="text/css" href="../common/js/kcb/dhtmlxGrid/codebase/dhtmlxgrid.css">
<link rel="stylesheet" type="text/css" href="../common/js/kcb/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css">
<link rel="STYLESHEET" type="text/css" href="../common/js/kcb/dhtmlxdhtmlx.css">
<link rel="STYLESHEET" type="text/css" href="../common/js/kcb/dhtmlxCombo/codebase/dhtmlxcombo.css">
<script src="../common/js/kcb/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
<script src="../common/js/kcb/dhtmlxCombo/codebase/dhtmlxcombo.js" type="text/javascript"></script>
<script src="../common/js/kcb/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
<script src="../common/js/kcb/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>    
<script src="../common/js/kcb/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_combo.js" type="text/javascript"></script>
<script src="../common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_srnd.js" type="text/javascript"></script>
<script src="../common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_nxml.js" type="text/javascript"></script>
<script  src="../common/js/kcb/dhtmlxGrid/codebase/ext/dhtmlxgrid_data.js"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/DwrCallWrapper.js'></script>
<script  src="../common/js/dwr/dwrDhtmlx.js"></script>
</HEAD>

<BODY>
<div id="gridbox" style="width:350px;height:250px;background-color:white;overflow:hidden"></div>
<div id="treeboxbox_tree" style="width:250px; height:218px;background-color:#f5f5f5;border :1px solid Silver;; overflow:auto;"></div>
<script type='text/javascript'>
	var mygrid = new dhtmlXGridObject('gridbox');
	mygrid.setImagePath("../common/js/kcb/imgs/");
	mygrid.setHeader("id,name");

	mygrid.setColumnIds("name,id");
	//mygrid.setInitWidths("100,250");
	mygrid.setColAlign("left,left");
	mygrid.setColTypes("ed,combo");
	mygrid.setColSorting("int,str");
	//$func.alert(mygrid.getCombo(1));

	mygrid.enableMultiselect(true);
	mygrid.enableSmartRendering(true);
	//mygrid.enableDistributedParsing(true,10,300);

	mygrid.enableMultiselect(true);
	mygrid.init();

		
	mygrid.setSkin("dhx_skyblue");
	

//	DwrCallWrapper.select_test("select_test",function (msg){
		//$func.alert(msg.svalue);
//		mygrid.parse(msg.svalue,"xml");

//	});
	var combo = mygrid.getColumnCombo(1);
	combo.disable(true);
	combo.loadXML('./status.xml');	
	function SaveRows(){
		var list = getChangedRows(mygrid);
		$func.alert(list[0].id);
		DwrCallWrapper.savePermission("save",list[0],function (msg){
			//$func.alert(msg.ivalue);
			//mygrid.parse(msg.svalue,"xmlB");
		}
		);
	}
	


	</script>	
</script>
<table width="375">
    <tr>
        <td>
            <li><a href="#" onclick=javascript:getChangedRows(mygrid);>得到增加和修改的行</a></li>
            <li><a href="#" onclick=javascript:getSelectedRows(mygrid);>得到选中的行</a></li>
            <li><a href="#" onclick=javascript:SaveRows();>保存信息</a></li>
        </td>
    </tr>
</table>
<script type="text/javascript" src="./index.js"></script>
</BODY>
</HTML>