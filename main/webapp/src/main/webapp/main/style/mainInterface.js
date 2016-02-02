// ///////////////////////////
// Dhtmlx Components declaration
var kcbmain = new Object();
var isHaveShop = null;
kcbmain.skin = "dhx_skyblue";
kcbmain.treeImgPath = basePath + "main/style/imgs/navIco/";
// kcbmain.tabbarImgPath = "../common/js/kcb/imgs/";
// 路径最后一定要加斜杠
kcbmain.tabbarImgPath = basePath + "common/js/kcb/dhtmlxTabbar/codebase/imgs/";
kcbmain.tabbarSkin = "dhx_blue";
kcbmain.tabNumLimit = 10;
kcbmain.pages = {};
kcbmain.windows = {};
kcbmain.winImgPath = basePath + "common/js/kcb/dhtmlxWindows/codebase/imgs/";
var isinit;// client是否已经初始化过，1表示已初始化
var staffid = sessionObj["session_current_user_id"];
var systemtt;
var contentPath;
kcbmain.create_window = function(id, viewPortId, type, message, tittle, width,
		height) {
	if (!kcbmain.windows[id])
		var dhxWins = new dhtmlXWindows();
	dhxWins.attachViewportTo(viewPortId);
	dhxWins.setImagePath(kcbmain.winImgPath);
	var win = dhxWins.createWindow("win", 0, 0, width ? 200 : width,
			height ? 200 : height);
	win.setText(tittle);
	win.setModal(true);
	win.keepInViewport(true);
	win.center();
	win.bringToTop();
	// win.centerOnScreen();
	switch (type) {
	case 1:
		win.attachHTMLString(message);
		break;
	case 2:
		win.attachURL(message);
		break;
	case 3:
		win.attachObject(message);
	}
}

kcbmain.create_tab = function(id, treeId, paramArray) {
	var tabWidth = 120;
	var tabNum = 0;
	// 当打开标签数大于9个的时候 关闭第一个
	var firstid = null;
	for ( var i in kcbmain.tabbar._tabs) {
		if (tabNum == 1) {
			firstid = i;
		}
		if (i == id) {
			continue;
		}
		tabNum++;
	}

	if (firstid != null && tabNum > 8) {
		kcbmain.tabbar.removeTab(firstid);
		delete kcbmain.pages[firstid];
	}

	if (tabNum - 1 > kcbmain.tabNumLimit) {
		var message = "打开菜单数不能大于10，请手动关闭一些标签";
		kcbmain.create_window(id, kcbmain.tabbar.getActiveTab(), 1, message,
				"操作提示", "auto", 400);
		return;
	} else if (!kcbmain.pages[id]) {

		switch (treeId.getUserData(id, "type")) {
		case "1":
			contentPath = ".." + treeId.getUserData(id, "action");
			contentPath = contentPath.replace(/#/g, '&');
			break;
		}
		kcbmain.tabbar.enableTabCloseButton(true);
		var welltitle = "首页";
		if (id == 0) {
			contentPath = "welcomehf.jsp?picdir=sykdt.png";
			
			kcbmain.tabbar.enableTabCloseButton(false);
			if(systemtt != 'C' && systemtt != 'BT'){
				kcbmain.tabbar.addTab(id, welltitle, tabWidth);
			}
			kcbmain.tabbar.enableTabCloseButton(true);
		} else {
			kcbmain.tabbar.addTab(id, treeId.getItemText(id), tabWidth);
		}
		// 修正所有tab的位置
		$(".dhx_tab_element").each(function(index) {
			$(this).css('left', 110 * index + 'px !important');

		});
		// 鼠标移上去更换关闭图标
		$("div.dhx_tab_element img")
				.hover(
						function() {
							$(this).attr('src',kcbmain.tabbarImgPath + 'dhx_blue/close_hover.png');
						},
						function() {
							$(this).attr('src',kcbmain.tabbarImgPath + 'dhx_blue/close.png');
						});
		if (paramArray && paramArray.length != 0) {
			for ( var i = 0; i < paramArray.length; i++) {
				var param = paramArray[i];
				if (contentPath.indexOf('?') == -1) {
					contentPath += '?' + param.name + '=' + param.value;
				} else {
					contentPath += '&' + param.name + '=' + param.value;
				}

			}
		}
		if (treeId.getUserData(id, "buttons")) {

			if (contentPath.indexOf('?') == -1) {
				contentPath += '?';
			} else {
				contentPath += '&';
			}

			contentPath += "btns=" + treeId.getUserData(id, "buttons");

		}
		kcbmain.tabbar.setContentHref(id, contentPath);
		var win = kcbmain.tabbar.cells(id);
		kcbmain.tabbar.setTabActive(id);
		kcbmain.pages[id] = win;
		// 保存位于tabbar最右边的tab id
		kcbmain.pages.lastid = id;
	} else{
		kcbmain.tabbar.setTabActive(id);
	}
}

kcbmain.init = function() {
	// mainLayout
	kcbmain.layout = new dhtmlXLayoutObject(document.body, "2U", kcbmain.skin);
	// dhxLayout.setSkin(activeSkin);
	kcbmain.layout.cont.obj._offsetTop = 30;
	// top margin
	// dhxLayout.cont.obj._offsetLeft = 1; // left margin
	kcbmain.layout.cont.obj._offsetHeight = -31;
	// bottom margin
	// dhxLayout.cont.obj._offsetWidth = -4; // right margin
	kcbmain.layout.setSizes();

	// 使用API添加头和尾
	// kcbmain.layout.attachHeader("header");
	// kcbmain.layout.attachFooter("footer");

	kcbmain.layout.items[0].setWidth(120);

	// 隐藏版块头部
	kcbmain.layout.forEachItem(function(item) {
		item.hideHeader();
	});
	/*
	 * //暂时去掉快捷入口模块 kcbmain.layout.items[0].setHeight(150);
	 * kcbmain.layout.items[0].setWidth(160);
	 * kcbmain.layout.setEffect("resize",false);
	 * kcbmain.layout.items[0].fixSize(true, true);
	 * 
	 * kcbmain.layout.items[0].setText("快捷入口"); kcbmain.tree1 =
	 * kcbmain.layout.items[0].attachTree(0);
	 * kcbmain.tree1.setSkin(kcbmain.skin);
	 * kcbmain.tree1.setImagePath(kcbmain.treeImgPath);
	 * kcbmain.tree1.setIconSize(18, 18); //隐藏图标和线
	 * kcbmain.tree1.enableTreeLines(false);
	 * //kcbmain.tree1.enableTreeImages(false);
	 * 
	 * kcbmain.tree1.loadXML("../main/test_tree1.xml");
	 * kcbmain.tree1.attachEvent("onClick", function(id) {
	 * kcbmain.create_tab(id, kcbmain.tree1); return true; });
	 */

	$("div.dhxcont_global_content_area").first().addClass("main-navMenu");
	$("div.dhxcont_global_content_area")[0].children[0].style.height = ($("div.dhxcont_global_content_area")[0].children[0].clientHeight - 39)
			+ "px";
	kcbmain.tree2 = kcbmain.layout.items[0].attachTree(0);
	kcbmain.tree2.setSkin(kcbmain.skin);
	kcbmain.tree2.setImagePath(kcbmain.treeImgPath);
	kcbmain.tree2.setIconSize(18, 18);
	kcbmain.tree2.enableTreeLines(false);
	// kcbmain.tree2.loadXML("../main/navMenuTree.xml");
	// /*

	function showLeftMenu() {
		var data = {
			staffid:staffid
		}
		$callTask("login.showPermission",data,function(msg){
			kcbmain.tree2.loadXMLString(msg);
		});
	}
	showLeftMenu();

	
	kcbmain.tree2.attachEvent("onClick", function(id) {
		kcbmain.tree2.openItem(id);
		var $menuItemSelected, $menuItemSelPrev;
		$("td.standartTreeRow").removeClass("navMenuSelected");
		$menuItemSelected = $("span.selectedTreeRow").parents(
				"td.standartTreeRow");
		$menuItemSelected.parents("table").addClass("w");
		$menuItemSelected.addClass("navMenuSelected");
		var level2 = kcbmain.tree2.getLevel(id);
		if (level2 == 2)
			kcbmain.create_tab(id, kcbmain.tree2);
		return true;
	});
	
	// 去一级菜单的蓝边仍然有问题
	/*
	 * $menuItemSelPrev.find(".standartTreeImage:first").click(function(){
	 * if($menuItemSelected.css('display') == 'none')
	 * $menuItemSelPrev.css("border-bottom-color","transparent"); else
	 * $menuItemSelPrev.css("border-bottom-color","#a6c1de"); });
	 */
	kcbmain.tabbar = kcbmain.layout.items[1].attachTabbar();
	kcbmain.tabbar.setHrefMode("iframes-on-demand");
	kcbmain.tabbar.setSkin(kcbmain.tabbarSkin);
	kcbmain.tabbar.setImagePath(kcbmain.tabbarImgPath);
	// kcbmain.tabbar.enableAutoReSize(true);
	// kcbmain.tabbar.enableTabCloseButton(true);
	kcbmain.tabbar.enableScroll(true);
	kcbmain.tabbar.setMargin("0");
	kcbmain.tabbar.setOffset("0");

	kcbmain.tabbar.attachEvent("onTabClose", function(id) {
		if(id == '2342342342354'){
			kcbmain.pages[id].getFrame().contentWindow.mygrid.saveColumnWidth();
		}
		// kcbmain.tabbar.removeTab(id,true);
		var tabNum = 0;
		for ( var i in kcbmain.pages)
			tabNum++;
		// tabbar只剩一个tab时此tab不能关闭，注：此功能发生修改，只剩最后一个tab也可以关闭

		if (id == kcbmain.pages.lastid) {
			kcbmain.tabbar.goToPrevTab();
			kcbmain.pages.lastid = kcbmain.tabbar.getActiveTab();
		} else {
			kcbmain.tabbar.goToNextTab();
		}
		kcbmain.tabbar.removeTab(id);
		delete kcbmain.pages[id];

		$(".dhx_tab_element").each(function(index) {
			$(this).css('left', 110 * index + 'px !important');
		});
	});
	// XML加载完成时修改tabbar样式
	kcbmain.tabbar.attachEvent("onXLE", function(id) {
		$(".dhx_tab_element").first().addClass("tabbar-firstTab");
		// $(".dhx_tablist_line").first().remove();
		// $(".dhx_tab_element>div").addClass("tabbar-delTabBg");
		// $(".dhx_tab_element>img").addClass("tabbar-closeImg");
	});
	// 修正所有tab的位置
	$("div.dhx_tablist_zone").click(function() {
		$(".dhx_tab_element").each(function(index) {
			$(this).css('left', 110 * index + 'px !important');
		});
	});

	// XML加载完成时修改tree样式
	kcbmain.tree2.attachEvent("onXLE", function(tree, id) {

		$(".containerTableStyle>table").css('margin', '2px 0 50px');// 修改导航有时候被遮盖
		/*
		 * var $menuItem = $(".containerTableStyle>table>tbody>tr>td>table");
		 * $menuItem.each(function(){
		 * $(this).find("tr:first").addClass("navMenu-level1").nextAll("tr").addClass("navMenu-level2");
		 * $(this).find("tr:first").find("td:eq(2)").css("display","none");
		 * $(this).find("tr:gt(0)").find(".standartTreeImage:first").css("display","none");
		 * });
		 */
		var $menuItem = $(".containerTableStyle>table>tbody>tr>td>table");
		$menuItem.each(function() {
			$(this).find("tr:first").addClass("navMenu-level1").nextAll("tr")
					.addClass("navMenu-level2");
			$(this).find("tr:first").find("td:eq(2)").css("display", "none");
			$(this).find("tr:gt(0)").find(".standartTreeImage").css("display",
					"none").end().find("td:gt(0)").find("td:eq(1)").css(
					"display", "none");// 隐藏IE下navMenu-level2内第二个TD
		});
		kcbmain.treeMouseEvent();// 鼠标hover事件
		dwr.engine.setAsync(true);
		dwr.engine.setActiveReverseAjax(true);
		kcbmain.tree2.openAllItems(id);	
	})

	// 添加鼠标事件
	kcbmain.treeMouseEvent = function() {
		// 设置第一层菜单鼠标移上去的样式
		$("td .standartTreeRow").mouseover(function() {
			$(this).css("background-position", "4px -34px");
		});
		// 设置第一层菜单鼠标移走后的样式
		$("td .standartTreeRow").mouseout(function() {
			$(this).css("background-position", "0 30px");
		});
	}
}
$(document).ready(
		function() {
				kcbmain.init();
				kcbmain.create_tab(0, kcbmain.tree2);
		});

/**
 * Tree消息初始化
 * 
 * @returns
 */
//function InitTreeMsg() {
//	// 测试用例
//	kcbmain.treeAddMsg("出库列表", 10, 0);// type=0为绿色
//	kcbmain.treeAddMsg("库存查询", 369, 1);// type=1为红色
//
//	// 在这里添加初始化内容
//
//}

// 添加登录tab
kcbmain.addloginTab = function() {
	kcbmain.tabbar.removeTab(250);
	kcbmain.tabbar.addTab(250, "登录", 110);
	kcbmain.tabbar.setTabActive(250);
	kcbmain.tabbar.setContentHref(250,
			"https://oauth.taobao.com/authorize?response_type=code&client_id="
					+ open_appkey + "&redirect_uri=" + redirect_uri + "");
}
//添加购买tab
kcbmain.addBuyTab = function() {
	kcbmain.tabbar.removeTab(250);
	kcbmain.tabbar.addTab(250, "登录", 110);
	kcbmain.tabbar.setTabActive(250);
	kcbmain.tabbar.setContentHref(250, buyUrl);
}

function clickNodeBatch(id,batchid){
	var params = null;
	if(batchid != undefined && batchid != null){
		params = [];
		params.push({"name" : "batchid","value" : batchid});
	}
	kcbmain.tree2.openItem(id);
	var $menuItemSelected, $menuItemSelPrev;
	$("td.standartTreeRow").removeClass("navMenuSelected");
	$menuItemSelected = $("span.selectedTreeRow").parents("td.standartTreeRow");
	$menuItemSelected.parents("table").addClass("w");
	$menuItemSelected.addClass("navMenuSelected");
	var level2 = kcbmain.tree2.getLevel(id);
	if (level2 == 2){
		kcbmain.create_tab(id, kcbmain.tree2,params);
	}
	if(kcbmain.pages[id] && batchid != undefined && batchid != null){
		var tabWin = kcbmain.pages[id].getFrame().contentWindow;
		tabWin.clickBatchidLoad(1,batchid);
	}
}
function clickNode(id,status){
	
		var params = null;
		if(status != undefined && status != null){
			params = [];
			params.push({"name" : "orderStatus","value" : status});
		}
		kcbmain.tree2.openItem(id);
		var $menuItemSelected, $menuItemSelPrev;
		$("td.standartTreeRow").removeClass("navMenuSelected");
		$menuItemSelected = $("span.selectedTreeRow").parents("td.standartTreeRow");
		$menuItemSelected.parents("table").addClass("w");
		$menuItemSelected.addClass("navMenuSelected");
		var level2 = kcbmain.tree2.getLevel(id);
		// alert(level2);
		if (level2 == 2){
			kcbmain.create_tab(id, kcbmain.tree2,params);
		}
	if(kcbmain.pages[id] && status != undefined && status != null){
		var tabWin = kcbmain.pages[id].getFrame().contentWindow;
		tabWin.clickNumLoad(1,status);
	}
}