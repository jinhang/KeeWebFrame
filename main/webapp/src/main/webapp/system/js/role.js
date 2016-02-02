dwr.engine.setAsync(false);
var disRid;
//var staff, clientid, client_type;
var tusername;
var clientid = sessionObj["session_current_client_id"];
var tusername = sessionObj["session_nick"];
var pageObj = {
	type : "1100",
	width : [ 300 ],
	htmlObj : [ "role_right" ]
};

var dhxWins = new dhtmlXWindows();
dhxWins.setImagePath(basePath + "common/js/kcb/imgs/");
var layoutContainer = create_layout(pageObj);
var role_grid = layoutContainer.grid[0];
layoutContainer.layout.items[0].showHeader();
layoutContainer.layout.items[0].setText("角色列表");
layoutContainer.layout.items[1].showHeader();
layoutContainer.layout.items[1].setText("功能模块");
var role_toolbar = layoutContainer.layout.items[0].attachToolbar();
role_toolbar.setIconsPath("./img/");
role_toolbar.addButton("add", 1, "新增", "");
role_toolbar.addButton("save", 2, "保存", "");
role_toolbar.addButton("delete", 3, "删除", "");
//role_toolbar.addButton("set", 4, "设置个数", "");
role_toolbar.attachEvent("onClick", doOnClickRole);

function role_grid_init() {
	role_grid.setImagePath(basePath + "common/js/kcb/dhtmlxGrid/codebase/imgs/");
	role_grid.setSkin("dhx_skyblue");
	role_grid.setHeader("ID,角色列表,类型");
	// role_grid.setNoHeader(true);
	role_grid.setColumnIds("id,name,type");
	role_grid.setColAlign("left,center,left");
	role_grid.setColTypes("ro,ed,combo");
	role_grid.setColSorting("str,str,str");
	role_grid.setInitWidths("10,*,*");
	role_grid.enableMultiselect(true);
	role_grid.setColumnHidden(0, true);
	role_grid.setColumnHidden(2, true);
//	role_grid.setEditableColumn(['name']);
	role_grid.attachEvent("onRowSelect", getDetailPermission);
	role_grid.init();
	role_grid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		if (stage == 2) {
			if (!nValue) {
				$func.alert('角色权限不能为空！', 'red');
				role_grid.cells(rId, cInd).setValue(oValue);
			}
			var ids = role_grid.getAllRowIds().split(',');
			for (var i=0;i < ids.length;i++) {
				var id = ids[i];
				if(id == (rId+'')){
					continue;
				}else {
					if (nValue == role_grid.cells(id, cInd).getValue()) {
						$func.alert('角色权限已存在！', 'red');
						role_grid.cells(rId, cInd).setValue(oValue);
					}
				}
			}
		}
	});
}
/**
 * 处理第二行como能否被选中方法
 * @param rid
 * @param cInd
 * @returns {Boolean}
 */
function canEdit(rid,cInd){
	if(cInd!=2) return true;
	else{
		//if(disRid==undefined) return false;
		for(var i=0;i<disRid.length;i++){
			if(rid==disRid[i]) return false;
		}
	}
	
	return true;
}

function doOnClickRole(id) {
	switch (id) {
	case 'add':
		addRole();
		break;
	case 'delete':
		deleteRole();
		break;
	case 'save':
		saveRole();
		break;
	}
}
var type;
function addRole() {
	if(!dhxWins){
		dhxWins = new dhtmlXWindows();
	}
	w1 = dhxWins.createWindow("w1", 20, 30, 430, 162);
    w1.center();
    w1.setModal(true);
    w1.setText("新增角色");
    w1.denyResize();
    w1.button("minmax1").hide();
    w1.attachURL("./winHtml/new_role.jsp?clientid="+clientid);
}

function saveRole() {
	role_grid.editStop();
	var rowid = role_grid.getSelectedId();
	if (rowid == null) {
		$func.alert("请至少选择一行");
		return;
	}
	
	var roleid = role_grid.cellById(rowid, 0).getValue();
	var rolename = role_grid.cellById(rowid, 1).getValue();
	type = role_grid.cells(rowid, 2).getCellCombo().getSelectedText();
	var role= {
		name : rolename,
		clientid : clientid,
		type : 1,
		roleid : roleid
	};
	
	var permissionidslist = [];
	var c = 0;
	if (rolename == '') {
		$func.alert('不能插入空的角色');
		return;
	}
	permissionidslist = getAllSelectedPermissionId();
	// $func.alert("您为这个角色分配了"+permissionidslist.length+"个权限！");
	
	var data = {
		role:role,
		permissionids:permissionidslist,
	}
	$callTask("system.updateOrSaveRole",data,function(msg){		
		$func.alert("权限分配成功！");
		role_grid.cellById(rowid, 0).setValue(msg.svalue);
		role_grid.selectRowById(rowid, true);
	});
}
function getSelectedPermissionId(form) {
	var list = [];
	var index = 0;
	var itemList = form.getItemsList();
	for ( var i = 0; i < itemList.length; i += 2) {
		var isChecked = form.isItemChecked(itemList[i]);
		if (isChecked) {
			list[index] = form.getItemValue(itemList[i]);
			index++;
		}
	}
	return list;
}
function getAllSelectedPermissionId() {
	var idList = [];
	var index = 0;
	var list = tree.getAllCheckedBranches();
	var as = list.split(",");
	dwr.engine.setAsync(false);
	for ( var j = 0; j < as.length; j++) {
		idList[index] = as[j];
		index++;
	}
	dwr.engine.setAsync(true);
	return idList;
}

/*
 * 得到当前选中角色的详细权限
 */
function getDetailPermission() {

	// all_check(false);
	var list = tree.getAllCheckedBranches();
	var as = list.split(",");
	for ( var j = 0; j < as.length; j++) {
		tree.setCheck(as[j], 0);
	}
	var roleid = role_grid.cellById(role_grid.getSelectedRowId(), 0).getValue();
	if (roleid == '' || roleid == null) {
		return;
	}
	
	var data = {
		roleid:roleid
	}
	$callTask("system.getPerByRoleid",data,function(msg){
		var parentlist = tree.getAllChildless();
		var p_nodes = parentlist.split(",");
		var list = msg.value;
		for (var k = 0; k < list.length; k++) {
			for ( var i = 0; i < p_nodes.length; i++)
				if (list[k] == p_nodes[i])
					tree.setCheck(list[k], 1);
		}
	});
};

function deleteRole() {
//	var id = role_grid.getSelectedId();
//	var rolename=[];
//	if (id == null) {
//		$func.alert("请至少选择一个角色进行删除");
//		return;
//	}
//	var idArr = id.split(',');
//	for(var i = 0;i<idArr.length;i++){
//		var roleid = role_grid.cellById(idArr[i],role_grid.getColIndexById("id")).getValue();
//		rolename[i] = role_grid.cellById(idArr[i],role_grid.getColIndexById("name")).getValue();
//		if(roleid=="c5809a569e704f14a48289698db1b85b" //标准版
//				|| roleid=="42c2e4bb9cb64bf887bc999558dcaa7f" //豪华版
//				|| roleid=="4c3175154ce34a25b3c79c1f6246030d" //企业版
//				|| roleid=="8c93cfb310854a3a8854420c2375dd55" //仓储版
//				|| roleid=="fc1afc53027049fca97fd241daa130a5" //酷仓宝
//				|| roleid=="b4af67ac40b348069cf93b202933e59a" //仓储客户管理员	
//			){
//			$func.alert("存在固定角色，不能删除！请重新选择。");
//			return;
//		}
//	}
//	$func.confirm("确定要删除选择的角色吗？", function() {
//		var ids = id.split(',');
//		roleidlist = [];
//		rolelist = [];
//		for (i = 0; i < ids.length; i++) {
//			var newid = role_grid.cellById(ids[i], 0).getValue();
//			if (newid == null || newid == '') {
//				role_grid.deleteRow(ids[i]);
//				return;
//			}
//			var role = {
//				id : newid
//			};
//			rolelist[i] = role;
//			roleidlist[i] = newid;
//		}
//		DwrCallServiceBasic.delRole("dele_role", rolelist, roleidlist,
//				function(msg) {
//				for (i = 0; i < ids.length; i++) {
//					//角色权限操作日志
//					var operationlog=new Object;
//					operationlog.clientnewid=clientid;
//					operationlog.username=tusername;
//					operationlog.action='新增/删除角色';
//					operationlog.menu='角色权限';
//					operationlog.type=1;//申通管理员
//					operationlog.remarks='角色名：'+rolename[i];
//					DwrCallServiceBasic.saveOperationLog(operationlog,function(msg){});
//				}
//			
//					var parentlist = tree.getAllChildless();
//					var p_nodes = parentlist.split(",");
//					for ( var i = 0; i < p_nodes.length; i++) {
//						tree.setCheck(p_nodes[i], 0);
//					}
//				});
//		for (i = 0; i < ids.length; i++) {
//			role_grid.deleteRow(ids[i]);
//		}
//		role_grid.clearChangedState();
//	});
};

role_grid_init();
load_role_grid();

function load_role_grid() {	
	var data = {
		clientid:clientid
	}
	$callTask("system.getClientRole",data,function(msg){
		role_grid.clearAll();
		role_grid.parse(msg.xmlB, "xmlB");
	});
}

// 创建树
tree = new dhtmlXTreeObject("role_right", "100%", "100%", 0);
tree.setSkin('dhx_skyblue');
tree.setImagePath(basePath
		+ "common/js/kcb/dhtmlxTree/codebase/imgs/csh_bluebooks/");
tree.enableTreeLines(false);
tree.enableCheckBoxes(1);
tree.enableThreeStateCheckboxes(true);
tree.enableTreeImages(0);

dwr.engine.setAsync(false);

tree.attachEvent("onClick", function(id) {
	tree.openItem(id);
});

//if (staff.issysuser == true && tusername=='admin') {
	showAllRoleMenu(clientid);
//} else {
//	showRoleMenu(clientid);
//}
function showAllRoleMenu(clientid) {// 主页上是staff.id来选择leftmenu;本公司时，显示所有权限
	
	var data = {
		clientid:clientid
	}
	$callTask("system.showPermission",data,function(msg){
		tree.loadXMLString(msg);
	});
}
//function showRoleMenu(clientid) {// 主页上是staff.id来选择leftmenu
//	DwrCallWrapperBasic.showRoleMenu('show_role_permission', clientid,
//			function(msg) {
//				tree.loadXMLString(msg.svalue);
//			});
//	// 样式
//	var tree_item_list = tree.getAllItemsWithKids();
//	var tree_ids = tree_item_list.split(',');
//	tree.setItemTopOffset(tree_ids[0], "20px");
//	var tree_index = 0;
//	while (tree_index < tree_ids.length) {
//		tree
//				.setItemStyle(
//						tree_ids[tree_index],
//						"font-size:11pt !important;color:#333333 !important;height:18px !important;line-height:20px !important;background-repeat: repeat !important;");
//		tree_index++;
//	}
//	tree_item_list = tree.getAllChildless();
//	tree_ids = tree_item_list.split(',');
//	tree_index = 0;
//	while (tree_index < tree_ids.length) {
//		tree
//				.setItemStyle(
//						tree_ids[tree_index],
//						"font-size:10pt !important;color:#333333 !important;height:18px !important;line-height:20px !important;background-repeat: repeat !important;");
//		tree_index++;
//	}
//}