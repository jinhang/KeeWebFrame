var pagingStyle;
function enablePaging(page,grid,layoutBase,pagingId){
	if(page.paging == true){
		if (false) {
			var bottom = dojo.create('div', null, layoutBase);
			var pagingNode = document.createElement('div');
			var memoNode = document.createElement('div');
			memoNode.className = 'memo';
			var memo = document.createElement('p');
			var text = document.createTextNode('注：该页面所有商品与淘宝网同步，无法进行删除操作；如需删除，可前往');
			memo.appendChild(text);
			var link = document.createElement('a');
			link.className = 'linkText';
			link.target = '_blank';
			link.href = "http://www.taobao.com";
			var linkText = document.createTextNode('淘宝店铺');
			link.appendChild(linkText);
			memo.appendChild(link);
			text = document.createTextNode('，进行修改删除');
			memo.appendChild(text);
			pagingNode.id = 'pagingNode';
			pagingNode.className = 'pagingLeft';
			bottom.appendChild(pagingNode);
			bottom.appendChild(memoNode);
			memoNode.appendChild(memo);
			grid.enablePaging(true,page.size||50, 5, "pagingNode" , true);
			grid.setSkin("dhx_skyblue");
			grid.setPagingSkin("bricks");
		}
		else {
			var node = dojo.create('div',null, layoutBase);
			node.id = 'pagingArea';
			node.className="pagingRight";
			if (pagingId) {
				node.id = pagingId;
			}
			grid.enablePaging(true,page.size||50, 5, node.id , true);
			grid.setSkin("dhx_skyblue");
			grid.setPagingSkin("bricks");
		}
	}
}


function tabbar_init(type, tabbar, objId, objName,objPath) {
	var tabbarImgPath = basePath+"common/js/kcb/dhtmlxTabbar/codebase/imgs/";
	var tabbarSkin = "dhx_blue";
	var tabWidth = "108px";
	// var tabbar=new dhtmlXTabBar("plist_tabbar","top");
	tabbar.setSkin(tabbarSkin);
	tabbar.setImagePath(tabbarImgPath);
	tabbar.addTab(objId, objName, tabWidth);
	switch(type) {
		case "1":
			return tabbar.cells(objId).attachGrid();
		case "2":
			tabbar.setContentHref(objId, objPath);
			return objId;
	}
}

// dhxWins需在调用create_window前创建，为了避免窗口重复打开的情况
function create_window(dhxWins, id, type, message, tittle, width, height) {
	if(dhxWins.isWindows(id)) {
		// dhxWins.enableAutoViewport(false);
		// dhxWins.attachViewportTo(viewPortId);
		var win = dhxWins.createWindow("win", 0, 0, width ? width : 400, height ? height : 400);
		win.setText(tittle);
		// win.denyResize();
		// win.button("close").hide();.
		// win.button("minmax1").disable();
		// win.button("minmax1").hide();
		// win.button("park").hide();
		// win.button("minmax2").show();
		// win.button("dock").show();
		// win.button("stick").show();
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
	} else
		dhxWins.window(id).show();
}

// type由三位组成，第一位表示附加jsp页面中obj的数量，第二位表示要创建grid的数量，第三位表示要创建tabbar的数量

//参数用对象page
//page={type:string;height:Array;tabName:Array;tabPath:Array;}
function create_layout(page, style, container, pagingId) {
	pagingStyle = style;
	var layout = null;
	var layoutSkin = "dhx_skyblue";
	var layoutBottomMargin = 10;
	var layoutTopMargin = 2;
	//layout最底部的下边距，为0时隐藏layout会有问题
	var layoutContainer = new Object();
	layoutContainer.grid = new Array();
	layoutContainer.tree = new Array();
	layoutContainer.content = new Array();
	// dhxLayout.setSkin(activeSkin);
	// layout.cont.obj._offsetTop = 30; // top margin
	// dhxLayout.cont.obj._offsetLeft = 4; // left margin
	// layout.cont.obj._offsetHeight = -50; // bottom margin
	// dhxLayout.cont.obj._offsetWidth = -4; // right margin
	// layout.setSizes();
	// if(document.body){
		// alert("body还没有开始加载");
		// return;
	// }
	var layoutBase= document.body;
	if (container != null)
		layoutBase = container;
	//if(page.height[0] !== undefined) layoutTopMargin = page.height[0];
	if(page.hasOwnProperty("height") && page.height[2] !== undefined) layoutBottomMargin = page.height[2];
	if(page.hasOwnProperty("height") && page.height[0] !== undefined) layoutTopMargin = page.height[0];
	
	
	switch (page.type) {
		case "0100":
			layout = new dhtmlXLayoutObject(layoutBase, "1C", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase, pagingId);
			layoutContainer.layout = layout;
			break;
		case "1100"://新增一个布局左边grid右边html
			layout = new dhtmlXLayoutObject(layoutBase, "2U", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			layout.items[1].attachObject(page.htmlObj[0]);

			layoutContainer.layout = layout;
			break;
		case "1-120"://新增一个布局左边grid右边grid
			layout = new dhtmlXLayoutObject(layoutBase, "2U", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			layoutContainer.grid[1] = layout.items[1].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			//layout.items[1].attachObject(page.htmlObj[0]);
			layoutContainer.layout = layout;
			break;
		case "0200":
			layout = new dhtmlXLayoutObject(layoutBase, "2E", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase, pagingId);
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layoutContainer.grid[1] = layout.items[1].attachGrid();

			layoutContainer.layout = layout;
			break;
		case "02-100":
			layout = new dhtmlXLayoutObject(layoutBase, "2U", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			layoutContainer.grid[1] = layout.items[1].attachGrid();

			layoutContainer.layout = layout;
			break;
		case "02-200":
			layout = new dhtmlXLayoutObject(layoutBase, "2U", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layout.items[0].attachObject(page.htmlObj[0]);
			layout.items[1].attachObject(page.htmlObj[1]);

			layoutContainer.layout = layout;
			break;
		case "1200":
			layout = new dhtmlXLayoutObject(layoutBase, "3L", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[2].setHeight(page.height[1]);
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			layoutContainer.grid[1] = layout.items[1].attachGrid();
			layout.items[2].attachObject(page.htmlObj[0]);
			
			layoutContainer.layout = layout;
			
			break;
		case "1300":
			layout = new dhtmlXLayoutObject(layoutBase, "4C", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[2].setHeight(page.height[1]);
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			layoutContainer.grid[1] = layout.items[1].attachGrid();
			layoutContainer.grid[2] = layout.items[2].attachGrid();
			layoutContainer.grid[3] = layout.items[3].attachGrid();
			
			layoutContainer.layout = layout;
			
			break;
		case "0300":
			layout = new dhtmlXLayoutObject(layoutBase, "3T", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layoutContainer.grid[1] = layout.items[1].attachGrid();
			layoutContainer.grid[2] = layout.items[2].attachGrid();
			layoutContainer.layout = layout;
			break;
		case "0010":
			layout = new dhtmlXLayoutObject(layoutBase, "1C",layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.tabbar = layout.items[0].attachTabbar();
			for(var i = 0; page.tabName && i < page.tabName.length; i++) {
				layoutContainer.grid[i] = tabbar_init("1", layoutContainer.tabbar, "tab" + i, page.tabName[i]);
			}
			if(page.tabName) {
				layoutContainer.tabbar.setTabActive("tab0");
			}

			layoutContainer.layout = layout;
			break;
		case "0020":
			layout = new dhtmlXLayoutObject(layoutBase, "1C",layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.tabbar = layout.items[0].attachTabbar();
			layoutContainer.tabbar.setHrefMode("iframes-on-demand");
			for(var i = 0; page.tabName && i < page.tabName.length; i++) {
				tabbar_init("2", layoutContainer.tabbar, "tab" + i, page.tabName[i],page.tabPath[i]);
			}
			if(page.tabName) {
				layoutContainer.tabbar.setTabActive("tab0");
			}
			
			layoutContainer.layout = layout;
			break;
		case "0110":
			layout = new dhtmlXLayoutObject(layoutBase, "2E", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layoutContainer.tabbar = layout.items[1].attachTabbar();
			layout.items[1].setHeight(200);
			for(var i = 0; page.tabName && i < page.tabName.length; i++) {
				layoutContainer.grid[i + 1] = tabbar_init("1", layoutContainer.tabbar, "tab" + i, page.tabName[i]);
			}
			if(page.tabName) {
				layoutContainer.tabbar.setTabActive("tab0");
			}
			layoutContainer.layout = layout;
			break;
		case "0111":
			layout = new dhtmlXLayoutObject(layoutBase, "2E", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layoutContainer.tabbar = layout.items[1].attachTabbar();
			layout.items[1].setHeight(300);
			layoutContainer.layout = layout;
			break;
		case "1110":
			layout = new dhtmlXLayoutObject(layoutBase, "3T", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[1].setWidth(page.width[0]);
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			layout.items[1].attachObject(page.htmlObj[0]);
			layoutContainer.tabbar = layout.items[2].attachTabbar();
			for(var i = 0; page.tabName && i < page.tabName.length; i++) {
				layoutContainer.grid[i + 1] = tabbar_init("1", layoutContainer.tabbar, "tab" + i, page.tabName[i]);
			}

			if(page.tabName) {
				layoutContainer.tabbar.setTabActive("tab0");
			}
			
			layoutContainer.layout = layout;
			break;
		case "1200":
			layout = new dhtmlXLayoutObject(layoutBase, "3T", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layout.items[1].attachObject(page.htmlObj[0]);
			layoutContainer.grid[1] = layout.items[2].attachGrid();

			layoutContainer.layout = layout;
			break;
		case "1211":
			layout = new dhtmlXLayoutObject(layoutBase, "3w", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layout.items[1].attachObject(page.htmlObj[0]);
			layout.items[2].attachObject(page.htmlObj[1]);
			
			layoutContainer.layout = layout;
			break;
		case "1212":
			layout = new dhtmlXLayoutObject(layoutBase, "2u", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes(); 
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layout.items[0].attachObject(page.htmlObj[0]);
			layout.items[1].attachObject(page.htmlObj[1]);
			
			layoutContainer.layout = layout;
			break;
		case "0101":
			layout = new dhtmlXLayoutObject(layoutBase, "2U", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.tree[0] = layout.items[0].attachTree(0);
			layoutContainer.grid[0] = layout.items[1].attachGrid();
			
			layoutContainer.layout = layout;
			break;
		case "0303"://左边object 右边 tree
			layout = new dhtmlXLayoutObject(layoutBase, "2U", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.tree[0] = layout.items[1].attachTree(0);
			layoutContainer.layout = layout;
			break;
			
		case "1101":
			layout = new dhtmlXLayoutObject(layoutBase, "3L", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[2].setHeight(page.height[1]);
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.tree[0] = layout.items[0].attachTree(0);
			layoutContainer.grid[0] = layout.items[1].attachGrid();
			layout.items[2].attachObject(page.htmlObj[0]);
			
			layoutContainer.layout = layout;
			break;
			
			
		case "1102":
			layout = new dhtmlXLayoutObject(layoutBase, "3L", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[2].setHeight(page.height[1]);
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.tree[0] = layout.items[0].attachTree(0);
			layoutContainer.grid[0] = layout.items[1].attachGrid();
			layoutContainer.grid[1] = layout.items[2].attachGrid();
			layoutContainer.layout = layout;
			break;
			
		case "1103":
			layout = new dhtmlXLayoutObject(layoutBase, "3L", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.tree[0] = layout.items[0].attachTree(0);
			layout.items[1].attachObject(page.htmlObj[0]);
			layoutContainer.grid[0] = layout.items[2].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase, pagingId);
			layoutContainer.layout = layout;
			break;	
			
		case "1203":
			layout = new dhtmlXLayoutObject(layoutBase, "3J", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[0].setHeight(page.height[1]);
			layout.items[0].attachObject(page.htmlObj[0]);
			layoutContainer.tree[0] = layout.items[1].attachTree(0);
			layoutContainer.grid[0] = layout.items[2].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase, pagingId);
			layoutContainer.layout = layout;
			break;		
			
		case "1111":
			layout = new dhtmlXLayoutObject(layoutBase, "2U", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			if(page.hasOwnProperty("width") && page.width[0]) layout.items[0].setWidth(page.width[0]);
			layoutContainer.grid[0] = layout.items[0].attachTree(0);
			layout.items[1].attachObject(page.htmlObj[0]);

			layoutContainer.layout = layout;
			break;
			
		case "0112":
			layout = new dhtmlXLayoutObject(layoutBase, "2E", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layoutContainer.tabbar = layout.items[1].attachTabbar();
			layoutContainer.tabbar.setHrefMode("iframes-on-demand");
			for(var i = 0; page.tabName && i < page.tabName.length; i++) {
				if(i == 0){
					layoutContainer.content[i] = tabbar_init("2", layoutContainer.tabbar, "tab" + i, page.tabName[i],page.tabPath[i]);
				}else{
					layoutContainer.grid[i] = tabbar_init("1", layoutContainer.tabbar, "tab" + i, page.tabName[i]);
				}
			}		

			if(page.tabName) {
				layoutContainer.tabbar.setTabActive("tab0");
			}
			layoutContainer.layout = layout;
			break;
			
		case "0113":
			layout = new dhtmlXLayoutObject(layoutBase, "2E", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layoutContainer.tabbar = layout.items[1].attachTabbar();
			layoutContainer.tabbar.setHrefMode("iframes-on-demand");
			for(var i = 0; page.tabName && i < page.tabName.length; i++) {
					layoutContainer.grid[i+1] = tabbar_init("1", layoutContainer.tabbar, "tab" + i, page.tabName[i]);
			}
			if(page.tabName) {
				layoutContainer.tabbar.setTabActive("tab0");
			}
			layoutContainer.layout = layout;
			break;
		case "0114":
			layout = new dhtmlXLayoutObject(layoutBase, "2E", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layoutContainer.tabbar = layout.items[1].attachTabbar();
			layout.items[1].setHeight(280);
			for(var i = 0; page.tabName && i < page.tabName.length; i++) {
				layoutContainer.grid[i + 1] = tabbar_init("1", layoutContainer.tabbar, "tab" + i, page.tabName[i]);
			}
			if(page.tabName) {
				layoutContainer.tabbar.setTabActive("tab0");
			}
			layoutContainer.layout = layout;
			break;
		case "0201":
			layout = new dhtmlXLayoutObject(layoutBase, "2E", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			layoutContainer.grid[1] = layout.items[1].attachGrid();
			enablePaging(page, layoutContainer.grid[1], layoutBase, pagingId);
			layoutContainer.layout = layout;
			break;
			
		case "003001":
			layout = new dhtmlXLayoutObject(layoutBase, "3T", layoutSkin);
			layout.cont.obj._offsetTop = layoutTopMargin;
			// top margin
			layout.cont.obj._offsetHeight = -(layout.cont.obj._offsetTop + layoutBottomMargin);
			layout.setSizes();
			layoutContainer.grid[0] = layout.items[0].attachGrid();
			enablePaging(page, layoutContainer.grid[0], layoutBase);
			//if(page.hasOwnProperty("height") && page.height[1]) layout.items[1].setHeight(page.height[1]);
			
			layoutContainer.tabbar = layout.items[1].attachTabbar();
			layout.items[1].setHeight(280);
			layoutContainer.grid[1] = tabbar_init("1", layoutContainer.tabbar, "tab0", page.tabName[0]);
			layoutContainer.tabbar.setTabActive("tab0");
			layoutContainer.tabbar = layout.items[2].attachTabbar();
			layout.items[2].setHeight(280);
			layoutContainer.grid[2] = tabbar_init("1", layoutContainer.tabbar, "tab1", page.tabName[1]);
			layoutContainer.tabbar.setTabActive("tab1");
			layoutContainer.layout = layout;
			break;
		default:
			alert("不支持此种布局");
	}

	//默认隐藏所有header
	layout.forEachItem(function(item) {
		item.hideHeader();
	});
	//设置布局的各边距
	layout.cont.obj._offsetLeft = 3; // left margin
	layout.cont.obj._offsetWidth = -6; // right margin
	layout.setSizes();
	//layout.cont.obj._offsetTop = -50; // top margin
	//layout.cont.obj._offsetHeight = -100; // bottom margin

	var gridNum = layoutContainer.grid.length;
	for(var i = 0;i<gridNum;i++){
		layoutContainer.grid[i].attachEvent("onXLE", function() {
		  $gridHeader =  $('.gridbox table.hdr tr');
		  $gridHeader.find('td div.filter').parent('td').addClass('grayRowCell').parent('tr').addClass('grayRow'); 
		});
	}
	
	return layoutContainer;
}