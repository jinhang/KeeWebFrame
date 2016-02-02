/*
 *  Author: zdarks
 *  Time: 02/03/2012
 *  Version: 1.0
 * */

// 创建采购单生成对话框
function create_purchase_list_win(dxwin, winid){           // dxwin为 对话框管理器,winid
	var purchase_list_win= {};
    purchase_list_win = dxwin.createWindow(winid, 80, 80, 1000, 600);
    purchase_list_win.setText("自动/手动生成采购单");
    purchase_list_win.attachObject(winid);
    purchase_list_win.hide();                                        // 隐藏对话框
    purchase_list_win.center();                                    // 居中
    //purchase_list_win.keepInViewport(true);          // 移动范围局限于视图内
    //purchase_list_win.denyResize();                           // 不允许改变大小
    //$func.alert(dxwin.window(winid));
    return purchase_list_win;
}

//商品选择对话框
function create_goods_list_win(dxwin, winid){
	var goods_list_win = {};
    goods_list_win = dxwin.createWindow(winid, 80, 80, 1000, 600);
    goods_list_win.setText("店铺商品选择");
    goods_list_win.attachObject(winid);
    //goods_list_win.center();                                  // 居中
     goods_list_win.hide();                                        // 隐藏对话框
    //goods_list_win.keepInViewport(true);          // 移动范围局限于视图内
    //$func.alert(dxwin.window(winid));
    return goods_list_win;
}

/**
 *弹出窗口的设置 
 */
//dhtmlxWindow
var dhxWins = new dhtmlXWindows();
dhxWins.setImagePath(basePath+"common/js/kcb/imgs/");

//创建dhtmlx window的方法
function createWin(winId,txt,htmlName){
	var win = dhxWins.createWindow(winId,($(window).width()-400)/2,($(window).height()-400)/2,700,500);
	win.setText(txt);
	//win.attachObject(divName);
	win.attachURL(htmlName);
	win.setModal(true);

	//win.attachEvent("onClose",function(){this.detachObject(divName);return true;})
	//win.maximize();
	return win;
}

/**
 * 获得弹出的某个窗口
 * @param winName
 * @returns
 */
function getPopWindow(winName){
	return dhxWins.window(winName);
}
