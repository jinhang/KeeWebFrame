function myalert(msg,title,type,call){
	if(!title){
		title = "信息提示：";
	}
	//
	if(typeof type == "undefined"){
		type = 2;
	}
	if(!(typeof(type) == "number")){
		msg = type;
		type = 2;
	}
	switch(type){
	case 1:
		$('div.alertBox1').remove();
		
		$('body').append('<div id="" class="alertBox1 "><span class="box1Text" id="box1Text"></span><a class="boxCloseButt"></a></div>');
		$('.alertBox1 .box1Text').html(msg);
		document.getElementById("box1Text").onclick = function () {
			call();
		};
		//5秒自动隐藏
		$('div.alertBox1').delay(5000).hide(0);
		break;
	case 2:
		var dhxWins = new dhtmlXWindows();
		dhxWins.attachViewportTo(document.body);
		dhxWins.setImagePath(basePath+"common/js/kcb/dhtmlxWindows/codebase/imgs/");
		var win = dhxWins.createWindow("win", 0, 0, 300,200);
		win.setText(title?title:"操作确认");
		win.button("minmax1").hide();
		win.button("park").hide();
		win.setModal(true);
		win.keepInViewport(true);
		win.center();
		win.bringToTop();
		var alertBox2Msg = '<div class="alertBox2"><p class="box2Text"></p><div class="box2Butt"><a id="alertBoxOk">确定</a><a id="alertBoxCancel">取消</a></div></div>';
		win.attachHTMLString(alertBox2Msg);
		$('.alertBox2 .box2Text').html(msg);
		
		break;
	default:
		alert("alert类型有误");
		break;
	} 
	
	$('a.boxCloseButt').click(function(){
		$('div.alertBox1').remove();
	});
	
	
	$('#alertBoxOk').click(function(){
		win.close();
	});
	$('#alertBoxCancel').click(function(){
		win.close();
	});
}
