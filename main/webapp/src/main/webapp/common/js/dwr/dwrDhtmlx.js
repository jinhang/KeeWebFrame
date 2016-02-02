/**
 * 封装Dhtmlx与Dwr的接口
 * @param grid
 * @returns
 */

/**
 * 得到变化的Row对象，包括增加的行
 */
function getChangedRows(grid){
	 var rowid =grid.getChangedRows(false);
	 if(rowid == null || rowid=="")
		 return null;
	 return getRowsObj(grid,rowid);
}

/**
 * 得到所有的行
 */
function getAllRows(grid){
	var rowid = grid.getAllRowIds();
	if(rowid == null||rowid=="")
		 return null;
	return getRowsObj(grid,rowid);
}

/**
 * 
 * @param grid
 * @param colNum---checkbox所在列数,由0开始计数
 * @returns
 */
function getCheckedRowsByGrid(grid,colNum){
	 var rowid = grid.getCheckedRows(colNum);
	 if(rowid==null||rowid=="")
		 return null;
	 return getRowsObj(grid,rowid);
}


function deleteCheckedRowsByGrid(grid,colNum){
	var rowid = grid.getCheckedRows(colNum);
	if(rowid==null||rowid=="") return null;
	var rowIdArr = rowid.split(',');
	
	for(var i=0;i<rowIdArr.length;i++){
		grid.deleteRow(rowIdArr[i]);
	}
}

/**
 * 
 * @param grid
 * @returns
 */
function getSelectedRows(grid){
	 var rowid = grid.getSelectedRowId();
	 if(rowid == null||rowid=="")
		 return null;
	 return getRowsObj(grid,rowid);
}

function getSelectedCheckRows(grid) {
	var cind = grid.getColIndexById("select");
	var rowids = grid.getCheckedRows(cind);
	if(rowids == null || rowids == "") {
		return null;
	}
	var rowidList = rowids.split(",");
	var allList = [];
	for(var i = 0; i < rowidList.length; i++) {
		var temp = getRowsObj(grid, rowidList[i]);
		allList = allList.concat(temp);
	}
	return allList;
}

function getSelectRowObj(grid){
	var rowid = grid.getSelectedRowId();
	if(!rowid){
		return null;
	}
	return getRowsObj(grid,rowid);
}


function getCheckedColumnValue(grid,column) {
	var rowids = grid.getCheckedRows(0);
	if(rowids == null || rowids == "") {
		return null;
	}
	var rowidList = rowids.split(",");
	var allList = [];
	var index = grid.getColIndexById(column);
	for(var i = 0; i < rowidList.length; i++) {
		var rowId = rowidList[i];
		var temp = grid.cellById(rowId, index).getValue();
		allList.push(temp);
	}
	return allList;
}


function getRowsObj(grid,rowid){
	 var colNum=grid.getColumnsNum();
	 var rows =  rowid.split(',');
	 var col  = [];
	 var list = [];
	 for(j = 0;j < colNum;j++){
		 col[j] = grid.getColumnId(j).replace('-','');
	 }	 
	 for(i = 0;i < rows.length;i++ ){
		 var result = {};
		 for(j = 0;j < colNum;j++){
			 if (grid.getColType(j) == 'dhxCalendar' || grid.getColType(j) == 'dhxCalendarA'){
				 result[col[j]] = grid.cellById(rows[i],j).getDate();
			 } else {
				 result[col[j]] = grid.cellById(rows[i],j).getValue();
				 if(col[j] == 'id'&&result[col[j]]==''){
					 result[col[j]] = null;
				 }
			 }
		 }
		 list[i] = result; 
	 }
	 return list;
}


function getRowArray(grid,rowid){
	 var colNum=grid.getColumnsNum();
	 var rows =  rowid.split(',');
	 var col  = [];
	 for(j = 0;j < colNum;j++){
		 col[j] = grid.getColumnId(j).replace('-','');
	 }	 
	 var result = [];
	 for(i = 0;i < rows.length;i++ ){
		 for(j = 0;j < colNum;j++){
			 if (grid.getColType(j) == 'dhxCalendar' || grid.getColType(j) == 'dhxCalendarA'){
				 result[j] = grid.cellById(rows[i],j).getDate();
			 } else {
				 result[j] = grid.cellById(rows[i],j).getValue();
				 if(col[j] == 'id'&&result[col[j]]==''){
					 result[j] = null;
				 }
			 }
		 }
	 }
	 return result;
}



function getRowIndexByValue(grid,row_name,row_value){
	var index = -1;
	var num =  grid.getRowsNum();
	var c_index = grid.getColIndexById(row_name);
	if(c_index<0){
		return index;
	}
	for(var i=0;i<num;i++){
		var value = grid.cells2(i,c_index).getValue();
		if(value ==  row_value){
			return i;
		}
	}
	return index;
}


function setRowsObj(grid,obj,rowid,row_name){
	var index = -1;
	if(rowid){
		index = grid.getRowIndex(rowid);
	}else{
		index = getRowIndexByValue(grid,row_name,obj[row_name]);
	}
	if(index == -1){
		return;
	}
	var colNum=grid.getColumnsNum();
	 for(j = 0;j < colNum;j++){
		 var col = grid.getColumnId(j).replace('-','');
		 if(typeof(obj[col])!='undefined')
			 grid.cells2(index,j).setValue(obj[col]);
	 }	 
	 return;
}



function getSelectedRowObj(grid){
	 var colNum=grid.getColumnsNum();
	var rowId = grid.getSelectedRowId();
	if(rowId==null||rowId==""){
		return;
	}
	 var col  = [];
	 for(var j = 0;j < colNum;j++){
		 col[j] = grid.getColumnId(j).replace('-','');
	 }	 
		 var result = {};
		 for(var j = 0;j < colNum;j++){
			 if (grid.getColType(j) == 'dhxCalendar' || grid.getColType(j) == 'dhxCalendarA'){
				 result[col[j]] = grid.cellById(rowId,j).getDate();
			 } else {
				 result[col[j]] = grid.cellById(rowId,j).getValue();
			 }
		 }
	 return result;
}
/**
 * 
 */
function getColumns(grid,columns,rowid){
	 var colNum=grid.getColumnsNum();
	 var rows =  rowid.split(',');
	 var col  = columns.split(',');
	 var list = [];
	 for(i = 0;i < rows.length;i++ ){
		 var result = {};
		 for(j = 0;j < col.length;j++){
			 var index = grid.getColIndexById(col[j]);
			 result[col[j]] = grid.cellById(rows[i],index).getValue();
		 }
		 list[i] = result; 
	 }
	 return list;	
}
/**
 * 
 */
function getColumnList(grid,columnid,rowid){
	 var rows =  rowid.split(',');
	 var list = [];
	 for(i = 0;i < rows.length;i++ ){
		 var index = grid.getColIndexById(columnid);
		 list[i] = grid.cellById(rows[i],index).getValue();
	 }
	 return list;	
}

function getTreePermission(id,tree){
	//判断是否为开放平台登录首页
	if(top.openIndex==true){
		$func.alert('您无权打开此菜单');
		return;
	}else{
		var index = tree.getIndexById(id)
		if(index == null){
			$func.alert('您无权打开此菜单');
			return;
		}
		kcbmain.create_tab(id, tree);
	}
}

function isHavePermission(id,tree){
	var index = tree.getIndexById(id)
	if(index == null){
		return false;
	}
	return true;
}


function getOneRowObj(grid,rowid){
	 var colNum=grid.getColumnsNum();
	 var col  = [];
	 for(j = 0;j < colNum;j++){
		 col[j] = grid.getColumnId(j).replace('-','');
	 }	 
	 var result = {};
	 for(j = 0;j < colNum;j++){
		 if (grid.getColType(j) == 'dhxCalendar' || grid.getColType(j) == 'dhxCalendarA'){
			 result[col[j]] = grid.cellById(rowid,j).getDate();
		 } else {
			 result[col[j]] = grid.cellById(rowid,j).getValue();
			 if(col[j] == 'id'&&result[col[j]]==''){
				 result[col[j]] = null;
			 }
		 }
	 }
	 return result;
}
