//v.3.0 build 110713

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/
/**
*	@desc: enable pop up menu which allows hidding/showing columns
*	@edition: Professional
*	@type: public
*/
dhtmlXGridObject.prototype.enableHeaderMenu=function(options)
{
	var that=this;
	this.options = options;
	this.attachEvent("onInit",function(){
    	this.hdr.oncontextmenu = function(e){ return that._doHContClick(e||window.event); };   
		{
			this.startColResizeA=this.startColResize;
			this.startColResize=function(e){
					if (e.button==2 || (_isMacOS&&e.ctrlKey))
						return this._doHContClick(e)
					return this.startColResizeA(e);
			}
		}
		this._chm_ooc=this.obj.onclick;
		this._chm_hoc=this.hdr.onclick;
		this.hdr.onclick=function(e){
			if (e && ( e.button==2  || (_isMacOS&&e.ctrlKey))) return false;
			that._showHContext(false);	
			return that._chm_hoc.apply(this,arguments)
		}
		this.obj.onclick=function(){
			that._showHContext(false);	
			return that._chm_ooc.apply(this,arguments)
		}
	    	
	});
	dhtmlxEvent(document.body,"click",function(){
		if (that._hContext){
			that._showHContext(false);
		}
			
	})
	if (this.hdr.rows.length) this.callEvent("onInit",[]);
	this.enableHeaderMenu=function(){};
}

dhtmlXGridObject.prototype._doHContClick=function(ev)
{
	function mouseCoords(ev){
            if(ev.pageX || ev.pageY){
                return {x:ev.pageX, y:ev.pageY};
            }
            var d  =  ((_isIE)&&(document.compatMode != "BackCompat"))?document.documentElement:document.body;
            return {
                x:ev.clientX + d.scrollLeft - d.clientLeft,
                y:ev.clientY + d.scrollTop  - d.clientTop
            };
        }

		this._createHContext();
		var coords = mouseCoords(ev);
		this._showHContext(true,coords.x,coords.y);
        ev[_isIE?"srcElement":"target"].oncontextmenu = function(e){ (e||event).cancelBubble=true; return false; };
		
		ev.cancelBubble=true;
		if (ev.preventDefault) ev.preventDefault();
    	return false;
}

dhtmlXGridObject.prototype._createHContext=function()
{
	if (this._hContext) return this._hContext;
	var d = document.createElement("DIV");
	d.oncontextmenu = function(e){ (e||event).cancelBubble=true; return false; };
	d.onclick=function(e){
		(e||event).cancelBubble=true;
		return true;
		}
	d.className="dhx_header_cmenu";
	//d.style.width=d.style.height="5px";
	d.style.display="none";

	var a=[];
	var i=0;
	if (this._fake)
		i=this._fake._cCount;	
	
	var true_ind=i;
	for (var i; i<this.hdr.rows[1].cells.length; i++){
		if(this.hdr.rows[1].cells[i].innerHTML&&this.hdr.rows[1].cells[i].innerHTML!="" && this.hdr.rows[1].cells[i].innerHTML.indexOf("|-|")>0){
			if(c){
				true_ind+=(c.colSpan||1);
			}else{
				true_ind+=1;
			}
			continue;
		}
		var c=this.hdr.rows[1].cells[i];
		
		//如果是复选框 不需要做隐藏操作 孙东辉
		var _dis = "block";
		if(this.hdr.rows[1].cells[i].innerHTML.indexOf("<input")>=0){
			_dis = "none";
		}
		
		if (c.firstChild && c.firstChild.tagName=="DIV") var val=c.firstChild.innerHTML;
		else var val = c.innerHTML;
	 	val = val.replace(/<[^>]*>/gi,"");
	 	//在指定位置改变样式-加横线、间距 林时探
	 	if(i==11 || i==23)
	 		a.push("<div style='background: none repeat scroll 0 0 #F6F5F5;border-bottom:1px #c9d8e3 solid;line-height: 18px;padding: 1px 0 5px 10px;display:"+_dis+"' class='dhx_header_cmenu_item'><input type='checkbox' column='"+true_ind+"' len='"+(c.colSpan||1)+"' checked='true' />&nbsp;"+val+"</div>");
	 	else 
	 	{	
	 		if(i==12 || i==24)
	 			a.push("<div style='background: none repeat scroll 0 0 #F6F5F5;line-height: 18px;padding: 3px 0 0px 10px;display:"+_dis+"' class='dhx_header_cmenu_item'><input type='checkbox' column='"+true_ind+"' len='"+(c.colSpan||1)+"' checked='true' />&nbsp;"+val+"</div>");
	 		else
	 			a.push("<div style='background: none repeat scroll 0 0 #F6F5F5;line-height: 18px;padding: 0 0 0 10px;display:"+_dis+"' class='dhx_header_cmenu_item'><input type='checkbox' column='"+true_ind+"' len='"+(c.colSpan||1)+"' checked='true' />&nbsp;"+val+"</div>");
	 	}
	 	true_ind+=(c.colSpan||1);
	}
	//最后一个加底部边距，放保存、还原按钮
	a[a.length-1] = a[a.length-1].replace("padding: 0 0 0 10px;","padding: 0 0 40px 10px;");
	a.push("<div class='dhx_header_cmenu_item' id='_savecloumns' style='position:fixed;top:586px;'><input type='button' value='保存'/><input type='button' value='还原'/></div>");
	
	d.innerHTML=a.join("");
	
	var that=this;	
	var f=function(){
    	var c=this.getAttribute("column");
    	if (!this.checked && !that._checkLast(c)) return this.checked=true;
    	if (that._realfake) that=that._fake;
    	for (var i=0; i<this.getAttribute("len"); i++)
			that.setColumnHidden((c*1+i*1),!this.checked);
		if(this.checked && that.getColWidth(c)==0) 
			that.adjustColumnSize(c);
	}
	
	var t = function(){
		that._hContext.style.display = "none";
		
		//将当前的选择情况回调给调用方
		var headermap = {};
		for (var i=0; i<that._hContext.childNodes.length; i++){
			var c=that._hContext.childNodes[i].firstChild;
			var col=c.getAttribute("column");
			var checked = c.checked;
			headermap[col] = checked;
		}

		if(typeof that.options.onMenuClose == "function"){
			that.options.onMenuClose(headermap);
		}
	}
	var p = function(){
		that._hContext.style.display = "none";
		
		//将当前的选择情况回调给调用方
		var headermap = {};
		for (var i=0; i<that._hContext.childNodes.length; i++){
			var c=that._hContext.childNodes[i].firstChild;
			var col=c.getAttribute("column");
			var checked = c.checked;
			headermap[col] = checked;
		}

		if(typeof that.options.onReset == "function"){
			that.options.onReset(headermap);
		}
	}
	
	
	
	
	for (var i=0; i<d.childNodes.length; i++){
		if(i!=d.childNodes.length-1){
			d.childNodes[i].firstChild.onclick=f;
		}else{
			d.childNodes[i].firstChild.onclick=t;
			d.childNodes[i].lastChild.onclick=p;
		}
	}
		
	
	document.body.insertBefore(d,document.body.firstChild);
	this._hContext=d;
	
	d.style.position="absolute";
	d.style.zIndex=999;
	//d.style.width='auto'
	//d.style.height='auto'
	//设置滚动
	d.style.display='block';
	d.style.overflow="auto";
	d.style.height="458px";
	d.style.width="130px";
	d.style.padding="0 0 50px 0";
}
dhtmlXGridObject.prototype._checkLast=function(ind){
	for (var i=0; i < this._cCount; i++)
		if ((!this._hrrar || !this._hrrar[i])&&(i!=ind))
			return true;
	return false;
}
dhtmlXGridObject.prototype._updateHContext=function()
{
	for (var i=0; i<this._hContext.childNodes.length; i++){
		var c=this._hContext.childNodes[i].firstChild;
		var col=c.getAttribute("column");
		if (this.isColumnHidden(col) || (this.getColWidth(col)==0))
			c.checked=false;
	}
}

dhtmlXGridObject.prototype._showHContext=function(mode,x,y)
{
	if (mode && this.enableColumnMove) {
		 this._hContext.parentNode.removeChild(this._hContext);
		 this._hContext=null;
	}
    this._createHContext();
	this._hContext.style.display=(mode?'block':'none');
	if (mode){
		this._updateHContext(true);
		this._hContext.style.left=x+"px";
		this._hContext.style.top=y+"px";
		//获取menu顶部距离
		document.getElementById("_savecloumns").style.top = (484+y)+"px";
	}
	
	
}
//(c)dhtmlx ltd. www.dhtmlx.com
