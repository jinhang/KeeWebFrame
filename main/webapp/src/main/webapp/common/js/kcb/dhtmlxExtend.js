/**
 * grid方法新增,实现全选当前页面
 */
dhtmlXGridObject.prototype.forEachRowA=function(custom_code){
		var tmp = this.rowsBuffer;
		for (var a =0; a<this.rowsBuffer.length; a++){
			if(a<(this.currentPage-1)*this.rowsBufferOutSize||a>this.currentPage*this.rowsBufferOutSize-1){
				continue;
			}
			if (this.rowsBuffer[a])
				custom_code.call(this, this.render_row(a).idd);
		}
}
/**
 * grid分页方法重写，翻页时取消全部勾选
 */
dhtmlXGridObject.prototype.changePage = function(pageNum){ 
	if (arguments.length==0) pageNum=this.currentPage||0;
	pageNum=parseInt(pageNum);
	pageNum=Math.max(1,Math.min(pageNum,Math.ceil(this.total/this.rowsBufferOutSize)));
	if(!this.callEvent("onBeforePageChanged",[this.currentPage,pageNum]))
		return;
	this.checkAll(0);
	$(".hdrcell>input").attr("checked",false);
	this.currentPage = parseInt(pageNum);
	if(this.clickedPageSet.indexOf(","+this.currentPage+",")<0){
		//加载新页面数据
		var pageSize = this.rowsBufferOutSize;
		var pageStart = (this.currentPage-1)*pageSize;
		this.callEvent("loadPagingData",[pageStart,pageSize] );
		this.clickedPageSet = this.clickedPageSet+this.currentPage+",";
	}
	
	this._reset_view();
	this._fixAlterCss();		
	this.callEvent("onPageChanged",this.getStateOfView());
	
}

/**
 * 设置选中限制
 */
dhtmlXGridObject.prototype.setLimitSelected = function(){
	limit = parseInt(this.myselect.options[this.myselect.selectedIndex].value);
	this.rowsBufferOutSize = limit;
	this.clickedPageSet = ",1,";
}

/**
 * 返回选中的每页显示行数
 */
dhtmlXGridObject.prototype.getPageRowLimit = function(){
	return this.rowsBufferOutSize;
}
dhtmlXGridObject.prototype.setPageRowLimit = function(limit){
	this.rowsBufferOutSize = limit;
}
dhtmlXGridObject.prototype.getCurrentPage = function(){
	return this.currentPage;
}
/**
 * 获取页面中pageBox个数
 */
dhtmlXGridObject.prototype.getPageBoxNum = function(){
	return this.pagesInGroup;
}

/**
 * 设置页面中pageBox个数
 */
dhtmlXGridObject.prototype.setPageBoxNum = function(num){
	this.pagesInGroup = num;
}

/**
*	 分页按钮覆盖，加入分页行数选择功能
*/
dhtmlXGridObject.prototype._pgn_bricks = function(page, start, end){
		//set class names depending on grid skin
		var tmp = (this.skin_name||"").split("_")[1];
		var sfx="";
		if(tmp=="light" || tmp=="modern" || tmp=="skyblue")
			sfx = "_"+tmp;
		this.pagerElAr = new Array();
		this.pagerElAr["pagerCont"] = document.createElement("DIV");
		this.pagerElAr["pagerBord"] = document.createElement("DIV");
		this.pagerElAr["pagerLine"] = document.createElement("DIV");
		this.pagerElAr["pagerBox"] = document.createElement("DIV");
		this.pagerElAr["pagerInfo"] = document.createElement("DIV");
		this.pagerElAr["pagerInfoBox"] = document.createElement("DIV");
		var se = (this.globalBox||this.objBox);
		this.pagerElAr["pagerCont"].style.width = se.clientWidth+"px";
		this.pagerElAr["pagerCont"].style.overflow = "hidden";
		this.pagerElAr["pagerCont"].style.clear = "both";
		this.pagerElAr["pagerBord"].className = "dhx_pbox"+sfx;
		this.pagerElAr["pagerLine"].className = "dhx_pline"+sfx;
		this.pagerElAr["pagerBox"].style.clear = "both";
		this.pagerElAr["pagerInfo"].className = "dhx_pager_info"+sfx;
		
		//create structure
		this.pagerElAr["pagerCont"].appendChild(this.pagerElAr["pagerBord"]);
		this.pagerElAr["pagerCont"].appendChild(this.pagerElAr["pagerLine"]);
		this.pagerElAr["pagerCont"].appendChild(this.pagerElAr["pagerInfo"]);
		this.pagerElAr["pagerLine"].appendChild(this.pagerElAr["pagerBox"]);
		this.pagerElAr["pagerInfo"].appendChild(this.pagerElAr["pagerInfoBox"]);
		this._pgn_parentObj.innerHTML = "";
		this._pgn_parentObj.appendChild(this.pagerElAr["pagerCont"]);
			
				
			
		
		if(this.total>0){
			var lineWidth = 20;
			var lineWidthInc = 22;
			
			var pageCont = document.createElement("DIV"); 
			pageCont.className = "dhx_page"+sfx;
			dhtmlXGridObject.prototype.myselect = document.createElement("select");
			var self = this;
			
			this.myselect.onchange = function(){
				var limit = this.options[this.options.selectedIndex].value;
				var oldPageNum=Math.ceil(self.total/self.rowsBufferOutSize);
				self.setLimitSelected();
				switch (parseInt(limit)) { 
			    case 50: 
			    	if(50<self.getTotal()||oldPageNum!=1){
						self.clearAll();
						self.callEvent("loadPagingData",[0,50] );
						self.changePage(1);
					} 
			        break; 
			    case 100: 
			    	if(100<self.getTotal()||oldPageNum!=1){
						self.clearAll();
						self.callEvent("loadPagingData",[0,100] );
						self.changePage(1);
					}
			        break; 
			    case 300: 
			    	if(300<self.getTotal()||oldPageNum!=1){
						self.clearAll();
						self.callEvent("loadPagingData",[0,300] );
						self.changePage(1);
					}
			    	break; 
			    case 500: 
			    	if(500<self.getTotal()||oldPageNum!=1){
						self.clearAll();
						self.callEvent("loadPagingData",[0,500] );
						self.changePage(1);
					}
			        break; 
				}
			}
			
			var option1 = new Option(50,50);
			this.myselect.options.add(option1);
			
			var option2 = new Option(100,100);
			this.myselect.options.add(option2);
			
			var option3 = new Option(300,300);
			this.myselect.options.add(option3);
			
			var option4 = new Option(500,500);
			this.myselect.options.add(option4); 
			
			if(this.rowsBufferOutSize==50||this.rowsBufferOutSize==100||this.rowsBufferOutSize==300||this.rowsBufferOutSize==500){
				this.myselect.value = this.rowsBufferOutSize;
			}
			
			pageCont.appendChild(this.myselect);
			this.pagerElAr["pagerBox"].appendChild(pageCont);
			lineWidth +=50;
			
			//create left arrow if needed
			if(page>this.pagesInGroup){
				var pageCont = document.createElement("DIV");
				var pageBox = document.createElement("DIV");
				pageCont.className = "dhx_page"+sfx;
				pageBox.innerHTML = "&larr;";
				pageCont.appendChild(pageBox);
				this.pagerElAr["pagerBox"].appendChild(pageCont);
				var self = this;
				pageCont.pgnum = (Math.ceil(page/this.pagesInGroup)-1)*this.pagesInGroup;
				pageCont.onclick = function(){
					self.changePage(this.pgnum);
					self.callEvent("onClickPageNo");
				}
				lineWidth +=lineWidthInc;
			}
			
			//create pages
			for(var i=1;i<=this.pagesInGroup;i++){
				var pageCont = document.createElement("DIV");
				var pageBox = document.createElement("DIV");
				pageCont.className = "dhx_page"+sfx;
				pageNumber = ((Math.ceil(page/this.pagesInGroup)-1)*this.pagesInGroup)+i;
				if(pageNumber>Math.ceil(this.total/this.rowsBufferOutSize))
					break;
				pageBox.innerHTML = pageNumber;
				pageCont.appendChild(pageBox);
				if(page==pageNumber){
					pageCont.className += " dhx_page_active"+sfx;
					pageBox.className = "dhx_page_active"+sfx;
				}else{
					var self = this;
					pageCont.pgnum = pageNumber;
					pageCont.onclick = function(){
						self.changePage(this.pgnum);
						self.callEvent("onClickPageNo");
					}
				}
				lineWidth +=(parseInt(lineWidthInc/3)*pageNumber.toString().length)+15;
				pageBox.style.width = (parseInt(lineWidthInc/3)*pageNumber.toString().length)+8+"px";
				this.pagerElAr["pagerBox"].appendChild(pageCont);
			}
			
			//create right arrow if needed
			if(Math.ceil(page/this.pagesInGroup)*this.pagesInGroup<Math.ceil(this.total/this.rowsBufferOutSize)){
				var pageCont = document.createElement("DIV");
				var pageBox = document.createElement("DIV");
				pageCont.className = "dhx_page"+sfx;
				pageBox.innerHTML = "&rarr;";
				pageCont.appendChild(pageBox);
				this.pagerElAr["pagerBox"].appendChild(pageCont);
				var self = this;
				pageCont.pgnum = (Math.ceil(page/this.pagesInGroup)*this.pagesInGroup)+1;
				pageCont.onclick = function(){
					var pageSize = self.rowsBufferOutSize;
					var pageStart = (this.pgnum-1)*pageSize;
					var pageEnd = pageSize*5+1;
					if((pageStart+pageEnd)>self.getRowsNum()&&self.getRowsNum()==(pageStart+pageEnd-pageSize*5)){
						self.callEvent("loadPagingData",[pageStart,pageEnd] );
					}
					self.changePage(this.pgnum);
					self.callEvent("onClickPageNo");
				}
				lineWidth +=lineWidthInc;
			}
			
			this.pagerElAr["pagerLine"].style.width = lineWidth+"px";
		}
		
		//create page info
		if(this.total>0 && this.showRecInfo)
			this.pagerElAr["pagerInfoBox"].innerHTML = this.i18n.paging.records+(start+1)+this.i18n.paging.to+end+this.i18n.paging.of +this.total;
		else if(this.total==0){
			this.pagerElAr["pagerLine"].parentNode.removeChild(this.pagerElAr["pagerLine"]);
			this.pagerElAr["pagerInfoBox"].innerHTML = this.i18n.paging.notfound;
		}
		//add whitespaces where necessary
		this.pagerElAr["pagerBox"].appendChild(document.createElement("SPAN")).innerHTML = "&nbsp;";
		this.pagerElAr["pagerBord"].appendChild(document.createElement("SPAN")).innerHTML = "&nbsp;";
		this.pagerElAr["pagerCont"].appendChild(document.createElement("SPAN")).innerHTML = "&nbsp;";
		this.callEvent("onPaging",[]);			
}

dhtmlXGridObject.prototype.resetClickedPageSet = function(){
	this.clearAll();
	this.clickedPageSet = ",";
	//this.setTotal(0);
	this.changePage(this.currentPage);
}

/**
 * 覆盖
 */
dhtmlXGridObject.prototype.enablePaging = function(fl,pageSize,pagesInGrp,parentObj,showRecInfo,recInfoParentObj){
		this._pgn_parentObj = typeof(parentObj)=="string" ? document.getElementById(parentObj) : parentObj;
	this._pgn_recInfoParentObj = typeof(recInfoParentObj)=="string" ? document.getElementById(recInfoParentObj) : recInfoParentObj;
	this.total = 0;
	this.pagingOn = fl;
	this.showRecInfo = showRecInfo;
	this.rowsBufferOutSize = 50;
	this.currentPage = 1;
	this.pagesInGroup = parseInt(8);
	this.setPagingSkin("default");
	this.clickedPageSet = ",";
}

/**
 * 设置总条数
 */
dhtmlXGridObject.prototype.setTotal = function(total){
	this.total = total;
	this._page_skin_update();
	this._init_pgn_events();
//	this.changePage(1);
//	this.callEvent("onClickPageNo");
	this.clickedPageSet = ",1,";
}

/**
 * 设置总条数
 */
dhtmlXGridObject.prototype.getTotal = function(){
	return this.total;
}