var kcbplugs = kcbplugs||{};

kcbplugs.block = function(datas,parentid){
	
	this._datas = datas;
	
	this._parentid = parentid;
	
	this._dom = $("<div id='mainBlock'></div>")
	
	$("#"+parentid).append(this._dom);
	
	this.init.apply(this);
	
}

/**
 * 初始化方法
 */
kcbplugs.block.prototype.init = function(){
	
	var me = this;
	
	me._dom.empty();
	
	//去重复
	me.clearDatas();
	
	for(var i = 0;i<this._datas.length;i++){
		if(this._datas[i]!=null && this._datas[i]!=""){
			var atag = $("<a class='bTag select'><span>"+this._datas[i]+"</span></a>")
			me._dom.append(atag);
			
			atag.on("click",function(){
				me.remove(this);
			});
		}
	}
}

/**
 * 删除块
 */
kcbplugs.block.prototype.remove = function(dom){
	
	var index = $data.arrHasProp(this._datas,$(dom).children().html());
	
	if(index){
		delete this._datas[index-1];
	}
	
	$(dom).remove();
	
}

/**
 * 新增块
 */
kcbplugs.block.prototype.add = function(value){
	var me = this;
	
	me._datas.push(value);
	
	
	me.init();
}

/**
 * 清理数据(去重复)
 */
kcbplugs.block.prototype.clearDatas = function(){
	var me = this;
	var dto = [];
	
	for(var i = 0;i<me._datas.length;i++){
		if(me._datas[i]!=null&&me._datas[i]!=""){
			
			if($.inArray(me._datas[i],dto)<0){
				dto.push(me._datas[i]);
			}
			
		}
	}
	
	me._datas = dto;

	return dto;
}
