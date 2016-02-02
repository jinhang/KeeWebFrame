// 设置DWR数据异步处理
dwr.engine.setAsync(false);
var dhxWins = new dhtmlXWindows();
var pageObj = {type:"0100",height:[40,,]}
var layoutContainer = create_layout(pageObj);
var forward_company_grid = layoutContainer.grid[0];
var clientId=sessionObj["session_current_client_id"];

layoutContainer.layout.items[0].setText("物流公司");


function forward_company_grid_init(){
	
	forward_company_grid.setImagePath(basePath+"common/js/kcb/dhtmlxGrid/codebase/imgs/");
	forward_company_grid.setHeader("id,简称,编号,名称,类型,备注,排序号");
	forward_company_grid.setColumnIds("id,shortname,code,name,type,memo,sortno");
	forward_company_grid.setColumnHidden(forward_company_grid.getColIndexById("id"),true);
	forward_company_grid.setColumnHidden(forward_company_grid.getColIndexById("type"),true);
	forward_company_grid.setColumnHidden(forward_company_grid.getColIndexById("memo"),true);
	forward_company_grid.setInitWidths("*,*,*,*,*,*,*");
	forward_company_grid.setColAlign("right,center,center,center,center,center,center");
	forward_company_grid.setColTypes("ed,ed,ed,ed,ed,ed,ed");	
	forward_company_grid.setColSorting("str,str,str,str,str,str,int");

	forward_company_grid.enableMultiselect(true);
	forward_company_grid.init();
	forward_company_grid.setSkin("dhx_skyblue");
	
//	forward_company_grid.getColumnCombo(forward_company_grid.getColIndexById("type")).loadXML("./status/forward_company_type.xml");

}

function getForwardCompany(){
	
	var data = {
	}
	$callTask("system.getExpress",data,function(msg){
		forward_company_grid.parse(msg.xmlB,"xml");
		
	});
//	DwrCallWrapperBasic.getForwardCompany("select_forwardcompany",function (msg){
//		forward_company_grid.parse(msg.svalue,"xml");
//	}
//	);
}

function doAdd(){
	var newid =forward_company_grid.uid(); 
	forward_company_grid.addRow(newid,['','','','','','','','',0,'','','','','','',0,0,0,'',999]); 
}
function doDelete(){
	var fcs = getSelectedRows(forward_company_grid);         // 选择多行信息
	if(fcs == null){                                       // 没有选择任一行 
		$func.alert("请至少选择一行！");
		return;
	}
	for(var i=0; i<fcs.length; i++){
		if(fcs[i].id == ""||fcs[i].id == null){
			forward_company_grid.deleteSelectedRows();
			return;
		}
	}
	$func.confirm("确定要删除所选的物流公司吗？",function(){
		DwrCallServiceBasic.operateForwardCompanys("delete_forwardcompany", fcs, function (msg){
			forward_company_grid.deleteSelectedRows();
	    }); 
	});
}
function doSave(){
	
	var fcs = getSelectedRows(forward_company_grid);         // 选择多行信息
	if(fcs == null){                                       // 没有选择任一行 
		$func.alert("请至少选择一行！");
		return;
	}
	for(var i=0; i<fcs.length; i++){
		if(fcs[i].id == ""){
			fcs[i].id = null;
		}
	}
	// 保存
    DwrCallServiceBasic.operateForwardCompanys("save_forwardcompany", fcs, function (msg){
    	var fs = msg.lvalue;
    	$func.alert("保存成功!");
    	for(var i=0; i<fcs.length; i++){
    		if(fcs[i].id == null||fcs[i].id==""){
    			forward_company_grid.cells(forward_company_grid.getSelectedRowId(),0).setValue(fs[i].id);
    		}
    	}
    });
}
function deRefresh(){
	forward_company_grid.clearAll();
	getForwardCompany();
}

//快递面单
var w1;
function setTemplate(){

	var fcs = getSelectedRows(forward_company_grid);         // 选择多行信息
	if(fcs == null){                                       // 没有选择任一行 
		$func.alert("请至少选择一行！");
		return;
	}
	dhxWins.setImagePath(basePath + "/common/js/kcb/imgs/");
	w1 = dhxWins.createWindow("w1", 10, 10, 800, 500);
	w1.maximize();
	w1.setText("统一的快递打印模板");
	w1.attachURL(basePath+"system/template_view.jsp?setType=1&forwardId="+fcs[0].id);
}



forward_company_grid_init();
getForwardCompany();