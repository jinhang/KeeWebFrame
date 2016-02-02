var kcbplugs = kcbplugs || {};
kcbplugs.msg = {};

// 弹出层封装类
kcbplugs.msg.msgManager = function() {
	var msgStack = [];

	// 构建div弹出层
	var divDom = $("<div></div>").appendTo(document.body);
	divDom.css("top", 60);

	// 当前是否显示
	var isshow = false;

	// 鼠标是否移入
	var ishand = false;
	divDom.bind("mouseover", function() {
		ishand = true;
	}).bind("mouseout", function() {
		ishand = false;
	});

	return {
		setTop : function(_top) {
			divDom.css("top", _top);
		},
		getMsg : function() {
			return msgStack;
		},
		addMsg : function(msg) {
			msgStack.push(msg);
			//console.log(msgStack);
			if (!isshow) {
				this.showDiv();
			}
		},
		finishMsg : function(msg){
			msgStack=[null];
			this.addMsg(msg);
		},
		setMsgStack : function(msgStack){
			msgStack = msgStack;
		},
		setAnimateStop : function(){
			divDom.stop();
		},
		showDiv : function() {
			var me = this;
			if (msgStack.length > 0) {
				isshow = true;

				var msgObj = msgStack[0];

				divDom.removeClass();
				divDom.addClass("message");

				var speed = 2000;
				var delay = 3000;

				if (typeof (msgObj) == 'string') {
					divDom.html(msgObj);
				} else {
					divDom.html(msgObj.text || "");
					divDom.addClass(msgObj.style || "");
					speed = msgObj.speed || 2000;
					delay = msgObj.delay || 3000;
				}

				var _width = parseInt(divDom.css("width").replace("px"));
				var _left = ($browser.getWindowWidth() - _width) / 2;
				divDom.css("left", _left);

				divDom.fadeIn(speed).delay(delay).fadeOut(1, function() {
					if (!ishand) {
						divDom.slideUp(speed, function() {
							msgStack.shift();
							me.showDiv();
						});
					} else {
						me.showDiv();
					}
				});
			} else {
				isshow = false;
			}
		},
		initDivDom : function(v){
			divDom.removeClass();
			divDom.addClass("message");
			divDom.html(v);
			var _width = parseInt(divDom.css("width").replace("px"));
			var _left = ($browser.getWindowWidth() - _width) / 2;
			divDom.css("left", _left);
		},
		fixMsg : function(v){
			this.initDivDom(v);
			divDom.show();
		},
		fixCancel : function(){
			divDom.hide();
		}
	}
}

var kcbmsg = kcbplugs.msg.msgManager();

//for(var i = 0;i<10;i++){
//	kcbmsg.fixMsg("消息"+i);
//}
//kcbmsg.finishMsg("结束");

