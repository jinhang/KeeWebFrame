/**
 * Js工具类
 */
var kcbtools = {};

/**
 * 浏览器信息
 */
kcbtools.browser = {
	// 获取浏览器高度
	getWindowHeight : function() {
		if ($.browser.msie) {
			return document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight
					: document.body.clientHeight;
		} else {
			return self.innerHeight;
		}
	},
	// 获取浏览器宽度
	getWindowWidth : function() {
		if ($.browser.msie) {
			return document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth
					: document.body.clientWidth;
		} else {
			return self.innerWidth;
		}
	},
	// 浏览器是否为IE
	isIE : function() {
		return /MSIE/.test(window.navigator.userAgent);
	}
};

/**
 * 时间处理
 */
kcbtools.time = {
	// 计算两个时间之间的天数
	dayMinus : function(day1, day2) {
		return Math.floor((day2 - day1) / (1000 * 60 * 60 * 24));
	},
	strToDate : function(str) {// 字符串转时间 针对 '-'
		var dateArr = str.split("-");
		
		if(dateArr.length<=1){
			return null;
		}
		
		if (dateArr[1].length == 1) {
			dateArr[1] = "0" + dateArr[1];
		}

		if (dateArr[2].length == 1) {
			dateArr[2] = "0" + dateArr[2];
		}

		str = dateArr.join("-");

		return new Date(str);
	}
};

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

/**
 * 字符串处理
 */
kcbtools.string = {
	// 字符串是否为空
	strIsEmpty : function(str) {
		return str == null || !str || typeof str == undefined || str == '';
	},
	// 特殊字符转义
	convchar : function(str) {
		if (str != '') {
			str = str.replace(/</g, '&lt;');
			str = str.replace(/%3C/g, '&lt;');
			str = str.replace(/>/g, '&gt;');
			str = str.replace(/%3E/g, '&gt;');
			str = str.replace(/'/g, '&#39;');
			str = str.replace(/"/g, '&quot;');
		}
		return str;
	}
};

/**
 * 数据处理
 */
kcbtools.data = {
	// 数组是否包含属性
	arrHasProp : function(arr, prop) {
		var falg = false;

		for ( var i = 0; i < arr.length; i++) {
			if (arr[i] == prop) {
				return i+1;
				falg = true;
				break;
			}
		}

		return falg;
	},
	// map是否包含key
	mapHasKey : function(map, key) {
		var flag = false;
		for ( var k in map) {
			if (k == key) {
				flag = true;
				break;
			}

		}
		return flag;
	},
	mapGetKeyByVal : function(map,val){
		var result = null;
		for (var k in map){
			if(map[k]==val){
				result =  k;
				break;
			}
		}
		
		return result;
	},
	// 数组去重复
	disrept : function(arr) {
		if (arr.length > 1) {
			var newarr = [];
			for ( var i = 0; i < arr.length; i++) {
				if (!this.arrHasProp(newarr, arr[i])) {
					newarr.push(arr[i]);
				}
			}
			arr = newarr;
		}

		return arr;
	},
	objExtendProp : function(oldobj, newobj) {
		for ( var k in oldobj) {
			newobj[k] = oldobj[k];
		}
	}
};
kcbtools.date = {
		//获取当前日期
		getCurrentDate:function(){
			var newDate= new Date();
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			return year+"-"+month+"-"+day;
		},
		//获取当前日期时间
		getCurrentDateTime:function(){
			var newDate= new Date();
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			var hours = newDate.getHours();
			if(hours<10) hours="0"+hours;
			var minutes = newDate.getMinutes();
			if(minutes<10) minutes="0"+minutes;
			var seconds = newDate.getSeconds();
			if(seconds<10) seconds="0"+seconds;
			return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		},
		//获取当前日期的开始时间
		getCurrentDate0Time:function(){
			var newDate= new Date();
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			var hours = newDate.getHours();
			if(hours<10) hours="0"+hours;
			var minutes = newDate.getMinutes();
			if(minutes<10) minutes="0"+minutes;
			var seconds = newDate.getSeconds();
			if(seconds<10) seconds="0"+seconds;
			return year+"-"+month+"-"+day+" 00:00:00";
		},
		//获取当前日期的前一天
		getPreDayDateTime:function(){
			var newDate= new Date();
			newDate= new Date(newDate.getTime() - 1*24*60*60*1000);
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			var hours = newDate.getHours();
			if(hours<10) hours="0"+hours;
			var minutes = newDate.getMinutes();
			if(minutes<10) minutes="0"+minutes;
			var seconds = newDate.getSeconds();
			if(seconds<10) seconds="0"+seconds;
			return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		},
		//获取当前日期的后一天
		getNextDayDateTime:function(){
			var newDate= new Date();
			newDate= new Date(newDate.getTime() + 1*24*60*60*1000);
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			var hours = newDate.getHours();
			if(hours<10) hours="0"+hours;
			var minutes = newDate.getMinutes();
			if(minutes<10) minutes="0"+minutes;
			var seconds = newDate.getSeconds();
			if(seconds<10) seconds="0"+seconds;
			return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		},
		//获取传入日期月份的第一天，传入格式：2012-01-01 11:11:11
		getFirstDayDateTime:function(dateTime){
			var newDate= new Date(dateTime.replace(/-/g,"/"));
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			return year+"-"+month+"-01 00:00:00";
		},
		//获取传入日期月份的最后一天，传入格式：2012-01-01 11:11:11
		getLastDayDateTime:function(dateTime){
			var newDate= new Date(dateTime.replace(/-/g,"/"));
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1; 
			if(month<10) month="0"+month;
			var myDate = new Date(year,month,0); 
			var day = myDate.getDate();
			if(day<10) day="0"+day;
			return year+"-"+month+"-"+day+" 23:59:59";
		},
		//传入格式：2012-01-01 11:11:11
		addNDay:function(dateTime,n){
			var newDate= new Date(dateTime.replace(/-/g,"/"));
			newDate= new Date(newDate.getTime() + n*24*60*60*1000);
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			var hours = newDate.getHours();
			if(hours<10) hours="0"+hours;
			var minutes = newDate.getMinutes();
			if(minutes<10) minutes="0"+minutes;
			var seconds = newDate.getSeconds();
			if(seconds<10) seconds="0"+seconds;
			return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		},
		//传入格式：2012-01-01 11:11:11
		minusNDay:function(dateTime,n){
			var newDate= new Date(dateTime.replace(/-/g,"/"));
			newDate= new Date(newDate.getTime() - n*24*60*60*1000);
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			var hours = newDate.getHours();
			if(hours<10) hours="0"+hours;
			var minutes = newDate.getMinutes();
			if(minutes<10) minutes="0"+minutes;
			var seconds = newDate.getSeconds();
			if(seconds<10) seconds="0"+seconds;
			return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		},
		minusNDays:function(dateTime,n){
			var newDate= new Date(dateTime.replace(/-/g,"/"));
			newDate= new Date(newDate.getTime() - n*24*60*60*1000);
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			return year+"-"+month+"-"+day;
		},
		
		validate:function(dateTime){
			if(dateTime==null) return true;
			var date = null;
			if(typeof(dateTime) == "string"){
				date = new Date(dateTime.replace(/-/g,"/"));
			}else{
				date = dateTime;
			}
			var flag = !(date.toString()=="Invalid Date");
//			console.log(date+"----"+flag)
			return flag;
		},
		formatDate:function(date){
			if(date==null) return null;
			var newDate= date
			if(typeof(date)=='string'){
				newDate= new Date(date.replace(/-/g,"/"));
			}
			var year = newDate.getFullYear();
			var month = newDate.getMonth()+1;
			if(month<10) month="0"+month;
			var day = newDate.getDate();
			if(day<10) day="0"+day;
			var hours = newDate.getHours();
			if(hours<10) hours="0"+hours;
			var minutes = newDate.getMinutes();
			if(minutes<10) minutes="0"+minutes;
			var seconds = newDate.getSeconds();
			if(seconds<10) seconds="0"+seconds;
			var result = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
//			console.log(result);
			return result;
		},
		//字符串类型转日期类型,传入格式：2012-01-01 11:11:11
		strToDate:function (dateStr){   
			   if(dateStr==null) return null;
			   try{
				  var newDate= new Date(dateStr.replace(/-/g,"/")); 
				  return newDate;
			   }catch (e) {
				// TODO: handle exception
				  return null;
			   }
		},
		//验证时间间隔是否在day内
		AminusB:function(date1,date2,day){
			var d1 = this.strToDate(this.minusNDay(date1,day));
			var d2 = this.strToDate(date2);
			if(d1<=d2) return true;
			else return false;
		}
		
	}
/**
 * 功能封装
 */
kcbtools.func = {
	// 函数定时器 调addFn添加需要执行的函数
	loopFn : {
		fnStack : {},
		flag : 0,
		timer : null,
		// fn:执行函数 time:多少时间执行一次 count:执行多少时间停止,callback:停止以后回调
		addFn : function(fn, time, count, callback) {
			return false;
			var me = this;
			fn.call(window);
			var me = this;
			var speed = time || 60000;
			var call = function() {
				dwr.engine.setAsync(false);
				fn.call(window);
			}
			if (!count) {
				setInterval(call, speed);
			} else {
				this.flag++;
				var timer = setInterval(call, speed);
				this.fnStack["fn" + this.flag] = [ timer, count, 0, callback ];
				if (!this.timer) {
					var tocall = function() {
						me.scan();
					}
					this.timer = setInterval(tocall, 1000);
				}
			}

		},
		scan : function() {
			var i = 0;
			for ( var key in this.fnStack) {
				i++;
				this.fnStack[key][2] = this.fnStack[key][2] + 1000;
				if (parseInt(this.fnStack[key][2], 10) >= parseInt(
						this.fnStack[key][1], 10)) {
					clearInterval(this.fnStack[key][0]);
					this.fnStack[key][3]();
					delete this.fnStack[key];
				}
			}
			if (i == 0) {
				clearInterval(this.timer);
				this.timer = null;
			}
		}
	},
	// 公用alert提示方法
	alertf : function(text, style, callback) {
		if(!text.content){
			if(typeof text=='string'){
				if(!style){
					var errorKey = ["不","错","选","少","没","失败"];
					for(var i = 0;i<errorKey.length;i++){
						if(text.indexOf(errorKey[i])>=0){
							style = "red";
							break;
						}
					}
				}
				
				window.top.kcbmsg.addMsg({
					text:text,
					speed:50,
					delay:3000,
					style:style
				});
				
			}else{
				window.top.kcbmsg.addMsg(text);
			}
			
		}else{
			return art.dialog(text);
		}

	},
	alert : function(text, style, callback,state,config){
		if(!config){
			config = {
			    timeout:2000
			};
		}
		config.width = 370;
		config.top=62;
		
		if(!state){
			state = 'none';
		}
		var p = parent;
		while(p != p.parent){
			p = p.parent;
		}
		if (!text.content) {
			if (typeof text == 'string') {
				if (!style) {
					var errorKey = [ "不", "错", "选", "少", "没", "失败" ];
					for ( var i = 0; i < errorKey.length; i++) {
						if (text.indexOf(errorKey[i]) >= 0) {
							style = "red";
							break;
						}
					}
				}
			}
			if (style == "red") {
				config.error = true;
			}
			p.$.jBox.tip(text, state,config);
			if(typeof style=="function"){
				style();
			}
		//	return p.$.jGrowl(text,config);
		} else {
			return art.dialog(text);
		}
//		window.top.kcbmsg.setMsgStack([]);
//		window.top.kcbmsg.setAnimateStop();
//		this.alertf(text, style, callback);
	},
	fixMsg  : function(text) {
		var config = {
				timeout:0
		};
		this.alert(text, null, null,'loading', config);
	},
	fixCancel : function(text){
		console.log("关闭弹出框开始执行...");
		var p = parent;
		while(p != p.parent){
			p = p.parent;
		}
		try{
			if(text){
				this.fixMsg(text);
			}else{
				this.fixMsg("执行完成！");
			}
			
			p.$.jBox.closeTip();
			
			setTimeout(function(){
				var count = 0;
				while(p.$.jBox.FadeBoxCount>0&&count<5){
					setTimeout(function(){
						p.$.jBox.closeTip();
					},200);
					count++;
				}
			},200);
			
			console.log("关闭弹出框执行完成！");
		}catch (e) {
			// TODO: handle exception
		}
	},
	// 公用confirm提示方法
	confirm : function(options, callback) {
		if (typeof options == 'object') {
			if (!options.cancel) {
				options.cancel = function() {
				}
			}
			// console.log(typeof (options.content));
			if (typeof (options.content) == 'object') {
				// console.log(options.content);
				options.content = options.content.join('<br/>');

				if (options.content != "") {
					options.content += ",";
				}

				options.content += "确定要执行吗?";
			}

			if (!options.icon) {
				options.icon = "question";
			}
			//art.dialog(options);
			
			var title = options.title || "操作提示";
			
			var okVal = options.okVal || "确定";
			var cancelVal = options.cancelValue || "取消";
			
			var buttons = {};
			buttons[okVal] = true;
			buttons[cancelVal] = false;
			
			jBox.confirm(options.content, title, function (v, h, f) {
				
				if(v)
				options.ok(v,h,f);
				
				return true;
			}, { id:'confirmid', showScrolling: false, buttons: buttons });
		} else if (typeof options == 'string') {

//			art.dialog({
//				content : options,
//				icon : 'question',
//				ok : function() {
//
//					callback();
//				},
//				cancel : function() {
//
//				},
//			});
			
			jBox.confirm(options, "操作提示", function (v, h, f) {
				if(v)
					callback(v,h,f);
				
				return true;
			}, { id:'confirmid', showScrolling: false, buttons: { '确定': true, '取消': false } });

		}

	},
	//播放声音
	playSound : function(soundfile) {
		// console.log('<iframe src="' + basePath
		// + 'sound/sound.jsp"></iframe>');
		// //return;
		// document.write('<iframe src="' + basePath
		// + 'sound/sound.jsp"></iframe>');
		
		try{
			var player = new Audio("../../sound/"+soundfile);
			player.play();
			return;
		}catch(e){
			window.top.document.getElementById("soundframe").src = basePath
			+ 'sound/sound.jsp?soundfile=' + soundfile;
		}
		
	},
	//获取对象数据类型
	typeOf : function(o) {
		var t,c,n;
		
		if(o===null){
			return "null";
		}
		
		if(o!=o){
			return "nan";
		}
		
		if((t=typeof o)!== "object") return t;
		
		if((c=Object.prototype.toString.call(o).slice(8,-1))!=="Object")return c;
		
		if(o.constructor && typeof o.constructor === "function" && (n=o.constructor.getName())) return n;
	
		return "Object";
	},
	icon : function(name){
		
		return "<span class='tableIcon "+name+"'></span>";
	},
	progressStart : function(){
		var me = kcbtools;
		
		if(me.pi>=98){
			me.pi = null;
			
			return true;
		}
		
		if(!me.pi){
			me.pi = 1;
			
			me.pt = $(".downText").html().replace("(1%)","").replace("(99%)","").replace("(100%)","");;
		}
		
		$(".downText").html(me.pt+"("+me.pi+"%)");
		
		if(me.pi!=98){
			me.pi++;
		}
		
		setTimeout($func.progressStart,100+(me.pi*10));
	},
	progressStop : function(layout){
		var me = kcbtools;
		me.pi = 100;
		
		$(".downText").html("上传完成");
		
		var call = function(){
			layout.progressOff();
		}
		
		setTimeout(call,500);
	},
	progressText : function(text){
		$(".downText").html(text);
	}
};

String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.LTrim = function() {
	return this.replace(/(^\s*)/g, "");
}
String.prototype.RTrim = function() {
	return this.replace(/(\s*$)/g, "");
}

function updateLoadingMsg(text){
	console.log($("iframe"));
	$("iframe").each(function(){
		if(this.src.indexOf('store_outstock_handle.jsp')>0){
			
			$(this).contents().find(".downText").html(text);
		}
	});
	
}

// 工具函数简称 $data就可以访问kcbtools.data下面的工具类
var $func = kcbtools.func, $string = kcbtools.string, $time = kcbtools.time,$date = kcbtools.date, $data = kcbtools.data, $browser = kcbtools.browser;