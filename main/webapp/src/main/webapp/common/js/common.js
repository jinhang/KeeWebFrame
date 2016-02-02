// 一些通用的js接口

function getSelectedRowsWithCols(grid, cols){
	 var rowid = grid.getSelectedRowId();
	 //$func.alert(rowid);
	 if(rowid == null|| rowid=="")
		 return null;
	 return getRowsObjWithCols(grid, rowid, cols);
}

function getAllRowsWithCols(grid, cols){
	 var rowid = grid.getAllRowIds();
	 //$func.alert(rowid);
	 if(rowid == null|| rowid=="")
		 return null;
	 return getRowsObjWithCols(grid, rowid, cols);
}

function getRowsObjWithCols(grid,rowid, cols){
	 var columns = cols.split(",");
	 //$func.alert(columns);
	 var rows =  rowid.split(',');
	 var col  = [];
	 var list = [];
	 for(j = 0;j < columns.length;j++){
		 col[j] = grid.getColumnId(columns[j]).replace('-','');
	 }	 
	 for(i = 0;i < rows.length;i++ ){
		 var result = {};
		 for(j = 0;j < columns.length;j++){
			 //$func.alert(grid.getColType(parseInt(columns[j])));
			 if (grid.getColType(parseInt(columns[j])) == 'dhxCalendar' || grid.getColType(parseInt(columns[j])) == 'dhxCalendarA'){
				 result[col[j]] = grid.cellById(rows[i],columns[j]).getDate();
			 } else {
				 result[col[j]] = grid.cellById(rows[i],columns[j]).getValue();
			 }
			 //$func.alert(result[col[j]]);
		 }
		 list[i] = result; 
	 }
	 return list;
}

function toTwoDimensionArray(objs){
	var result = [];
	var i, j;
	var ele = new Array(objs.length);
	for(i=0; i<objs.length; i++){
		ele[i] = [];
		j = 0;
		for(var attr in objs[i]){
			//$func.alert("name:" + attr + " value:" + objs[i][attr]);
			ele[i][j++] = objs[i][attr];
		}
		result[i] = ele[i];
		//$func.alert("i"+i+" "+result[i]);
	}
	return result;
}

// 设置gird的col列的样式为style
function setColumnsTextStyle(grid, col, style)
{
	var ids = grid.getAllRowIds(",");            // 获取grid所有的ID
	var rows = ids.split(",");
	for(var i=0; i<rows.length; i++){
		grid.setCellTextStyle(rows[i], col, style);
	}
}

//设置gird的指定行row指定col列的样式为style
function setColumnsTextStyleWithRows(grid, rids, col, style)
{
	var rows = rids.split(",");
	for(var i=0; i<rows.length; i++){
		grid.setCellTextStyle(rows[i], col, style);
	}
}


// 封装dhtmlx windows

// 修改dhtmlxgrid中price样式为人民币￥，而不是美元$
function alter_dollar_to_rmb()
{
	if(eXcell_price == undefined)
		return;
	eXcell_price.prototype.setValue = function(val){
	    if(isNaN(Number(val))){
	    	val = 0;
	    }
	     var color = "green";
	     if(val<0) color = "red";
	     this.setCValue("<span>￥</span><span style='padding-right:2px;color:"+color+";'>"+val+"</span>",val);
	 }; 
}
function StringToDate(str) {
	if (!str) {
  		return null;
  	}else if(typeof str == 'object'){
		return str;
	}
	var arys = new Array();
	try{
		arys = str.split('-');
	}catch(e){
	  return null;
	}
	if(arys.length != 3){
		return null;
	}
	var year = parseInt(arys[0], 10);
	var month = parseInt(arys[1], 10) - 1;
	var date = parseInt(arys[2], 10);
	return new Date(year, month, date);
}

/**
 * 获取min ~ max 之间的随机整数
 * @param Min
 * @param Max
 * @returns
 */
function GetRandomNum(Min,Max)
{   
	var Range = Max - Min;   
	var Rand = Math.random();   
	return(Min + Math.round(Rand * Range));   
} 

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

//用于与后台接口传map参数
function Map(){};
