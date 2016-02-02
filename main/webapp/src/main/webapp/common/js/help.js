var helps = {};

//创建grid公用类封装
helps.createGrid = function(options, mygrid) {
	//记录前一页最后一个id
	mygrid.lastid = 0;
	if (typeof mygrid != 'object') {
		mygrid = new dhtmlXGridObject(mygrid);
	}
	
	var data;
	if(options.columns){
		data = this.changeGridData(options);
	}else{
		data = {
				headers:options[0],
				colids:options[1],
				widths:options[2],
				aligns:options[3],
				types:options[4],
				sortings:options[5]
		};
	}
	if (options.skin) {
		mygrid.setSkin(options.skin);
	}
	
	mygrid.setHeader(data.headers);
	mygrid.setColumnIds(data.colids);
	mygrid.setInitWidths(data.widths);
	mygrid.setColAlign(data.aligns);
	mygrid.setColTypes(data.types);
	mygrid.setColSorting(data.sortings);
	
	//隐藏设定的列
	if(data.hcid&&data.hcid.length>0){
		for(var i in data.hcid){
			mygrid.setColumnHidden(data.hcid[i],true);
		}
	}
	
	//如果需要支持分页
	if(options.paging){
		var node = dojo.create('div',null, document.body);
		node.className="paging";
		mygrid.enablePaging(true,options.paging["ps"]||1, options.paging["pc"]||3, node , true);
		mygrid.setPagingSkin("bricks");
		
		mygrid.attachEvent("loadPagingData", function(first, limit){
			options.paging.pl(first+1, limit);
		});
		mygrid.attachEvent("onPageChanged", function(ind,fInd,lInd){
			mygrid.lastid = fInd;
		});

	}
	
	//如果需要支持选择行选中复选框
	if(options.srcc){
		mygrid.attachEvent("onRowSelect", function(id,ind){
			helps.checkedSelectedRow(mygrid,id,ind);
		});
	}
	
	mygrid.init();

	mygrid.dbi = function(id){//databyid 根据rowid获取当前行数据
		//console.log(mygrid.getRowIndex(id));
		//console.log(mygrid.cells(id,4).getValue());
	}
	
	mygrid.sids = function() {//selectedids选中的所有行编号
		return mygrid.dataSource;
	}
	
	mygrid.ads = function(){//alldatas
		var ids = mygrid.getSelectedRowId();
		
	}
	
	mygrid.pd = function(pd){//parsedata
		var len=0;
		if(mygrid.dataSource){
			len = mygrid.dataSource.length;
			for(var p in pd){
				mygrid.dataSource.push(p);
			}
		}else{
			mygrid.dataSource = [];
			mygrid.dataSource = pd;
		}
		
		
		var dsarr = [];
		var idsarr = data.colids.split(",");
		var hassm = 0;
		if(idsarr[0]=="select"){
			hassm = 1;
		}
		for(var j = 0;j<pd.length;j++){
			var ds = [];
			for(var i = 0;i<idsarr.length;i++){
				var t = pd[j][idsarr[i]];
				if(i>=hassm&&options.columns&&options.columns[i-hassm].renderer){
					t = options.columns[i-hassm].renderer.call(this,t);
				}
				ds.push(t);
			}
			dsarr.push(ds);
		}
		
		mygrid.parse(dsarr,function(){},"jsarray",len);
	}
	
	mygrid.cd = function(){
		mygrid.clearAll();
		if(mygrid.dataSource){
			mygrid.dataSource = [];
		}
	}

	return mygrid;
}

helps.changeGridData = function(options) {
	var columns = [];

	if (options.columns) {
		columns = options.columns;
	}

	var headers = "", colids = "", widths = "", aligns = "", types = "", sortings = "";
	var hcid = [];
	var hassm = 0;
	if(options.sm){
		headers+=options.sm=="ch"?"#master_checkbox,":",";
		colids+="select,";
		widths+="50,";
		aligns+="center,";
		types+=options.sm+",";
		sortings="int,";
		hassm++;
	}
	for (var i = 0 ; i < columns.length; i++) {
		var c = columns[i];
		if (c.id) {
			colids += c.id;
		} else {
			colids += "id" + i == 0 ? "" : i;
		}

		if (c.header) {
			headers += c.header;
		}

		if (c.width) {
			widths += c.width;
		} else {
			widths += "*";
		}

		if (c.align) {
			aligns += c.align;
		} else {
			aligns += "center";
		}

		if (c.type) {
			types += c.type;
		} else {
			types += "ro";
		}

		if (c.sorting) {
			sortings += c.sorting;
		} else {
			sortings += "string";
		}

		if (c.hidden) {
			hcid.push(i+hassm);
		}

		if (i != columns.length - 1) {
			headers += ",";
			colids += ",";
			widths += ",";
			types += ",";
			sortings += ",";
			aligns += ",";
		}

	}
	return {
		headers:headers,
		colids:colids,
		widths:widths,
		aligns:aligns,
		types:types,
		sortings:sortings,
		hcid:hcid
	};
}

// 当选中行的时候 勾选复选框 需要放在onRowSelect事件中
helps.checkedSelectedRow = function(mygrid, id, ind) {
	var allids = mygrid.getAllRowIds();
	var allidsarr = allids.split(",");
	var ids = mygrid.getSelectedRowId();
	var idsarr = ids.split(",");
	if (ind == mygrid.getColIndexById("select")) {
		mygrid.cellById(id, mygrid.getColIndexById("select")).setValue(1);
		return idsarr;
	}
	for ( var i = 0; i < allidsarr.length; i++) {
		var c = 0;
		if (this.arrHasProp(idsarr, allidsarr[i])) {

			c = 1;
		}
		mygrid.cellById(allidsarr[i], mygrid.getColIndexById("select"))
				.setValue(c);
	}

	return idsarr;

}

// 数组是否包含属性
helps.arrHasProp = function(arr, prop) {
	var falg = false;

	for ( var i = 0; i < arr.length; i++) {
		if (arr[i] == prop) {
			falg = true;
			break;
		}
	}

	return falg;
}

// 记住翻页编号
dhtmlXGridObject.prototype.saveCheckedAsPaging = function() {

	var that = this;
	that.pageDateMap = {};

	that.attachEvent("onBeforePageChanged", function(ind, count) {
		that.pageDateMap[ind] = that.getCheckedRows(that
				.getColIndexById("select"));
		return true;
	});

	that.attachEvent("onPageChanged", function(ind, find, lind) {
		var ids = that.pageDateMap[ind];
		if (ids && ids != "") {
			var idsarr = ids.split(",");
			var len;
			if (len = idsarr.length > 0) {
				for ( var i = 0; i < len; i++) {
					that.cellById(idsarr[i], that.getColIndexById("select"))
							.setValue(1);
				}
			}
		}

	});
}

// grid按空格选择下一行 keys:需要监听的code数组
dhtmlXGridObject.prototype.selectRowByKey = function(keys) {
	var keys = keys || [ 32 ];
	var mygrid = this;
	mygrid.attachEvent("onKeyPress", function(code, cFlag, sFlag) {
		if (helps.arrHasProp(keys, code)) {
			var allids = mygrid.getAllRowIds();
			var selectids = mygrid.getSelectedRowId();
			if (selectids != null) {
				var allarr = allids.split(",");
				var selectarr = selectids.split(",");
				var sid;

				for ( var i = 0; i < selectarr.length; i++) {
					mygrid.cellById(selectarr[i],
							mygrid.getColIndexById("select")).setValue(0);
				}
				for ( var i = 0; i < allarr.length; i++) {
					if (selectarr[selectarr.length - 1] == allarr[i]) {
						if (i == allarr.length - 1) {
							sid = allarr[0];
						} else {
							sid = allarr[i + 1];
						}
						mygrid.selectRowById(sid);
						mygrid.callEvent("onRowSelect", [ sid, 0 ]);
						break;
					}
				}

				mygrid.cellById(sid, mygrid.getColIndexById("select"))
						.setValue(1);
			}

		}
	});

}

helps.getDateTime = function() {
	var d = new Date();

	return d.getTime();
}

helps.getWindowHeight = function() {
	if ($.browser.msie) {
		return document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight
				: document.body.clientHeight;
	} else {
		return self.innerHeight;
	}
}

helps.getWindowWidth = function() {
	if ($.browser.msie) {
		return document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth
				: document.body.clientWidth;
	} else {
		return self.innerWidth;
	}
}

// 左下角提示层
helps.msgWinPlug = {
	win_id : 'kcb_msg_win',
	showMsgWin : function() {
		this.mainWin = this.mainWin || new dhtmlXWindows();
		if (!this.mainWin) {
			this.mainWin = new dhtmlXWindows();
		}

		var msg_win = this.mainWin.window(this.win_id);
		if (!msg_win) {
			this.objDiv = document.createElement("div");
			w = this.mainWin.createWindow(this.win_id,
					helps.getWindowWidth() - 300,
					helps.getWindowHeight() - 200, 300, 200);
			w.attachObject(this.objDiv);
		} else {
			w.show();
		}
		this.objDiv.innerHTML = this.getFormatMsg();
		w.setText("系统提示");
	},
	scanningMsgStack : function() {
		if (!this.buckMsgStackLen) {
			this.buckMsgStackLen = 0;
		}
		if (this.msgStack.length > this.buckMsgStackLen) {

			this.showMsgWin();
		}
		this.buckMsgStackLen = this.msgStack.length;
	},
	scanningFnStack : function() {
		if (this.fnStack.length > 0) {
			var result;
			for ( var i = 0; i < this.fnStack.length; i++) {
				result = this.fnStack[i].call();
				if (result) {
					this.addMsg(result);
				}
			}
			this.validShow();
		}
	},
	validShow : function() {
		if (this.msgStack.length > this.buckMsgStackLen) {
			this.showMsgWin();
		}
		this.buckMsgStackLen = this.msgStack.length;
	},
	addMsg : function(msg) {
		if (!this.msgStack) {
			this.msgStack = [];
		}
		var flag = true;
		if (msg && msg != "") {
			this.msgStack.push(msg);
		}

		this.scanningMsgStack();
	},
	addFn : function(fn) {
		var me = this;
		if (!this.fnStack) {
			this.fnStack = [];
			var call = function() {
				me.scanningFnStack()
			};
			this.timer = setInterval(call, 10000, this);
		}
		this.fnStack.push(fn);
	},
	getFormatMsg : function() {
		var str = "";
		for ( var i = this.msgStack.length - 1; i >= 0; i--) {

			str += "<p>" + this.msgStack[i] + "</p>";
		}
		return str;
	}
}

helps.loopFn = {
	fnStack : {},
	flag : 0,
	timer : null,
	addFn : function(fn, time, count, callback) {
		var me = this;
		fn.call(window);
		var me = this;
		var speed = time || 60000;
		var call = function() {
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
};