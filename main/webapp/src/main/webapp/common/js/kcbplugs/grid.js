var kcbplugs = kcbplugs||{};
kcbplugs.grid = {};
//创建grid公用类封装
kcbplugs.grid.createGrid = function(options, mygrid) {
	var me = this;
	//记录前一页最后一个id
	mygrid.lastid = 0;
	
	
	if (typeof mygrid != 'object') {
		mygrid = new dhtmlXGridObject(mygrid);
	}
	
	//有几个列头(序号列 复选框列)
	mygrid.hassm = 0;
	
	if(options.sm){
		mygrid.hassm ++;
	}
	if(options.rn){
		mygrid.hassm ++;
	}
	
	var data;
	if(options.columns){//对象方式
		data = me.changeGridData(options,mygrid);
	}else{//字符串方式
		data = {
				headers:options[0],
				colids:options[1],
				widths:options[2],
				aligns:options[3],
				types:options[4],
				sortings:options[5]
		};
	}
	options.skin = options.skin||'dhx_skyblue';
	options.imgpath = options.imgpath||(basePath+ "common/js/kcb/dhtmlxGrid/codebase/imgs/");
	mygrid.setSkin(options.skin);
	if(options.validate){
		mygrid.enableValidation(true,true);
	}
	mygrid.setImagePath(options.imgpath);
	mygrid.setHeader(data.headers);
	mygrid.setColumnIds(data.colids);
	mygrid.setInitWidths(data.widths);
	mygrid.setColAlign(data.aligns);
	mygrid.setColTypes(data.types);
	mygrid.setColEditIcon(data.editIcons);
	mygrid.setColumnWidthId(options.columnWidthId);
	mygrid.setColumnSortDB(data.sortDBs);
	mygrid.setHdrOptions(data.options);
	if(!options.notsortint){
		mygrid.setColSorting(data.sortings);
	}
	mygrid.setColValidators(data.valids);
	
	//隐藏设定的列
	if(data.hcid&&data.hcid.length>0){
		for(var i in data.hcid){
			mygrid.setColumnHidden(data.hcid[i],true);
		}
	}
	
	//如果需要支持分页
	if(options.paging){
		var node = dojo.create('div',null, document.body);
		node.id = 'pagingArea';
		node.className="pagingRight";
		
		mygrid.enablePaging(true,50, 3, 'pagingArea',true);
		mygrid.setSkin("dhx_skyblue");
		mygrid.setPagingSkin("bricks");

	}
	
	//如果需要支持选择行选中复选框
	if(options.srcc&&options.sm){
		mygrid.attachEvent("onRowSelect", function(id,ind){
			me.checkedSelectedRow(mygrid,id,ind);
			$('#searchForm>.dhxlist_obj_dhx_skyblue>.dhxlist_base>.block_item_label_left').first().nextAll().css('display','none');
			$('#searchForm').removeClass('searchFormExpand');
			$('#toggleFormBtn').removeClass('toggleonFormButt').addClass('toggleFormButt');
			$('#tabSearch').hide();
		});
	}
	
	if(options.attachHeader){
		mygrid.attachHeader(options.attachHeader);
	}
	//price_grid.attachHeader(["#rspan", "#rspan", "Author1","Author2","Author3"]);
	if(!options.saveColumn){
		mygrid.init();
	}else{
		mygrid.saveColumnCustom(options.saveColumnId, data.headers, data.colids, data.types, data.widths,
				data.aligns, data.sortings, data.exluscolids, data.initfn);
	}

	mygrid.dbi = function(id){//databyid 根据rowid获取当前行数据
		if(!mygrid.dataSource){
			return {};
		}
		
		var _o = mygrid.dataSource[mygrid.getRowIndex(id)]||{};
		
		var _no = {};
		//$data.objExtendProp(_o,_no);
		//alert(_o);
		return Object.create(_o);
	}
	
	mygrid.dbis = function(ids){//databyids 根据一组rowid获取行数据数组
		if(ids==null || ids==""){
			return false;
		}
		var objarr = [];
		var idsarr = ids.split(",");
		for(var i = 0;i<idsarr.length;i++){
			objarr.push(mygrid.dbi(idsarr[i]));
		}
		
		return objarr;
	}
	
	//checkedobjectdata 获取选择行数据 mod 1:只能选择1行 2:最少选择一行
	mygrid.cod = function(t,msg){
		var cod_msg1 = msg||"请选择一条数据";
		var cod_msg2 = msg||"请至少选择一行数据";
		var mod = t || 1;
		var cid;
		if(options.sm){
			cid = mygrid.getCheckedRows(mygrid.getColIndexById("select"));
		}else{
			cid = mygrid.getSelectedRowId();
		}
		
		if(cid==null || cid=="" ||(mod==1 && cid.split(",").length>1 )){
			if(mod==1){
				$func.alert(cod_msg1);
			}else{
				$func.alert(cod_msg2);
			}
			
			return false;
		}
		
		var _objs = mygrid.dbis2(cid);
		
		if(mod == 1){
			return _objs[0];
		}
		
		return _objs;
	}
	
	mygrid.dbi2 = function(id){//根据id获取表格数据
		var _o = mygrid.dbi(id);
		var _cidarr = data.colids.split(",");
		
		for(var i = 0;i<_cidarr.length;i++){
			if(options.columns && (i-mygrid.hassm)>=0 && options.columns[(i-mygrid.hassm)].renderer){//如果有render转型 那不必改动
				continue;
			}
			_o[_cidarr[i]] = mygrid.cells2(mygrid.getRowIndex(id),i).getValue();
		}
		
		_o["@rowid"] = id;
		return _o;
	}
	
	mygrid.dbis2 = function(ids){//根据id获取表格数据 表格数据组
		if(ids==null || ids==""){
			return false;
		}
		var objarr = [];
		var idsarr = ids.split(",");
		for(var i = 0;i<idsarr.length;i++){
			objarr.push(mygrid.dbi2(idsarr[i]));
		}
		
		return objarr;
	}
	
	mygrid.dbis2a = function(){//获取所有数据
		var dbis2a_ids = mygrid.getAllRowIds();
		if(dbis2a_ids == null|| dbis2a_ids==""){
			return false;
		}
		
		
		return mygrid.dbis2(dbis2a_ids);
	}
	
	//getchangedata 获取被编辑过的列数据
	mygrid.gcd = function(){
		mygrid.editStop();
		var _cids = mygrid.getChangedRows(true);
		
		var _yd = mygrid.dbis(_cids);
		
		if(!_yd){
			$func.alert("无任何修改,不需保存");
		}
		
		if(!mygrid.iv(_cids)){
			return false;
		}
		
		
		var _gd = mygrid.dbis2(_cids);
		for(var i = 0;i<_gd.length;i++){
			var j = -1;
			for(var k in _gd[i]){
				if(k=="@rowid"){
					_yd[i][k] = _gd[i][k];
					continue;
				}
				j++;
				if(_gd[i][k]!=_yd[i][k]){//如果表格上的值和元数据的值不等 说明有改动
					if(options.columns && (j-mygrid.hassm)>=0 && options.columns[(j-mygrid.hassm)].renderer){//如果有render转型 那不必改动
						continue;
					}
					_yd[i][k] = _gd[i][k];
				}
			}
		}
		
		
		return _yd;
	}
	
	//根据给出的属性选择其中一行
	mygrid.srbp = function(colid,val){//select row by prop
		if(!colid||!val){
			return;
		}
		var ids = mygrid.getAllRowIds();
		var srbp_datas = mygrid.dbis2a();
		var flag = false;
		for(var i = 0;i<srbp_datas.length;i++){
			if(srbp_datas[i][colid]==val){
				mygrid.selectRowById(srbp_datas[i]['@rowid']);
				mygrid.callEvent("onRowSelect",[srbp_datas[i]['@rowid'],0]);
				flag = true;
			}
			
		}
		
		return flag;
	}
	
	//根据给出的属性加值对数据进行排序
	mygrid.sbv = function(map){//sort by value
		var sbv_yxidsarr = [];
		var sbv_rids = mygrid.getAllRowIds();
		var sbv_datas = mygrid.dbis2(sbv_rids);
		for(var key in map){
			for(var i = 0;i<sbv_datas.length;i++){
				var sbv_data = sbv_datas[i];
				if(sbv_data[key].indexOf(map[key])>=0){
					sbv_yxidsarr.push(sbv_data["@rowid"]);
				}
			}
		}
		
		sbv_yxidsarr = $data.disrept(sbv_yxidsarr);
		for(var i = 0;i<sbv_yxidsarr.length;i++){
			mygrid.moveRowTo(sbv_yxidsarr[i],sbv_rids[0],'move');
			mygrid.moveRowUp(sbv_yxidsarr[i]);
		}
		
		mygrid.selectRow(0);
		
	}
	
	mygrid.pd = function(pd,type,notclear){//parsedata
		if($.trim(pd)==""){
			return;
		}
		
		if(!mygrid.notclear){
			mygrid.clearAll();
		}
		
		var d = mygrid.parse(pd,"xmlB");
		this.rsetpdstr = pd;
		
		var pd = mygrid.px(d);
		
		if(pd.length>0){
			if(mygrid.dataSource){
				len = mygrid.dataSource.length;
				mygrid.dataSource.push(pd);
			}else{
				mygrid.dataSource = pd;
			}
		}
		
		mygrid.dorenderer();
	}
	
	mygrid.resetpd = function(){
		mygrid.pd(this.rsetpdstr);
	}
	
	//实现自定义渲染
	mygrid.dorenderer = function(){
		if(options.columns){
			for(var i = 0;i<options.columns.length;i++){
				var o = options.columns[i];
				var ids = mygrid.getAllRowIds();
				if(ids==null || ids==""){
					return;
				}
				var idsarr = ids.split(",");
				if(o.renderer){
					for(var j = 0;j<idsarr.length;j++){
						var v = mygrid.dbi(idsarr[j])[o.id];
						v = o.renderer(v);
						mygrid.cells(idsarr[j],i+mygrid.hassm).setValue(v);
					}
					
				}
				
			}
			
		}
	}
	
	//根据json对象生成表格数据
	mygrid.pj = function(json){
		var colids = data.colids;
		var colidsarr = colids.split(",");
		var pj_datas = [];
		for(var i = 0;i<json.length;i++){
			var pj_data = [];
			var jsonHasSelect = $data.mapHasKey(json[i],'select');
			
//			if(!jsonHasSelect&&$data.arrHasProp(colidsarr,'select')){
//				pj_data.push(0);
//			}
			
			for(var j = 0;j<colidsarr.length;j++){
				if(colidsarr[j]=='num_id'){
					pj_data.push(j+1);
					continue;
				}
				
				if(colidsarr[j]=='select'){
					if(json[i]['select']){
						pj_data.push(json[i]['select']);
					}else{
						pj_data.push(0);
					}
					
					continue;
				}
				
				if(json[i][colidsarr[j]]!=null && $func.typeOf(json[i][colidsarr[j]]) == 'Date'){
					var str = json[i][colidsarr[j]].getFullYear()+"-"
						+ (json[i][colidsarr[j]].getMonth()+1) +"-"+ json[i][colidsarr[j]].getDate();
					pj_data.push(str);
				}else{
					if(json[i][colidsarr[j]]!=null){
						pj_data.push(json[i][colidsarr[j]]);
					}else{
						pj_data.push("");
					}
				}
			}
			pj_datas.push(pj_data);
		}
		
		mygrid.pa(pj_datas);
		
		//mygrid.dorenderer();
	}
	
	//根据数据生成表格
	mygrid.pa = function(arr){
		if(!mygrid.notclear){
			mygrid.cd();
		}
		mygrid.parse(arr,'jsarray');
		
		var pa_idarr = data.colids.split(",");
		
		for(var i=0;i<arr.length;i++){
			var pa_data = {};
			for(var j = 0;j<arr[i].length;j++){
				pa_data[pa_idarr[j]] = arr[i][j];
			}
			
			if(mygrid.dataSource){
				mygrid.dataSource.push(pa_data);
			}else{
				mygrid.dataSource = [pa_data];
			}
		}
		
		mygrid.dorenderer();
		
	}
	
	mygrid.iv = function(ids,tip,excuval){//输入验证
		var _tip = tip||"存在不规范的数据";
		
		if(!options.validate){
			return true;
		}
		mygrid.editStop();
		
		if(!ids){
			ids = mygrid.getAllRowIds();
		}
		
		if(ids==null||ids==""){
			return true;
		}
		
		var idsarr = ids.split(",");
		var colids = data.colids;
		var colidsarr = colids.split(",");
		for(var i = 0+mygrid.hassm;i<colidsarr.length;i++){
			var colid = colidsarr[i];
			for(var j = 0;j<idsarr.length;j++){
				var id = idsarr[j];
				
				var rowind = mygrid.getRowIndex(id);
				var colind = mygrid.getColIndexById(colid);
				
				if(mygrid.cells2(rowind,colind).getValue()==excuval){
					continue;
				}
				
				if(options.attachHeaderValid){
					if(options.attachHeaderValid[i-mygrid.hassm]&&!mygrid.validateCell(id,colind)){
						$func.alert(_tip);
						mygrid.selectCell(rowind,colind);
						mygrid.editCell(true);
						//mygrid.cell.className+=" editable";
						//mygrid.cells4(mygrid.cell).edit();
						return false;
					}
				}else{
					if(options.columns[i-mygrid.hassm].valid&&!mygrid.validateCell(id,colind)){
						$func.alert(_tip);
//						mygrid.selectCell(rowind,colind);
//						mygrid.editCell(true);
						//mygrid.cell.className+=" editable";
						//mygrid.cells4(mygrid.cell).edit();
						return false;
					}
				}
			}
			
		}
		return true;
	}
	
	mygrid.cd = function(){//清空
		mygrid.clearAll();
		if(mygrid.dataSource){
			delete mygrid.dataSource;
		}
	}
	
	mygrid.px = function(doc){//解析xml
		var rows = doc.childNodes;
		var pdata = [];
		
		var rowarr = rows[0].childNodes;
		
		for(var i = 0;i<rowarr.length;i++){
			var row = rowarr[i];
			
			var nodes = row.childNodes;
			var d = {};
			for(var j = 0;j<nodes.length;j++){
				
				var node = nodes[j];
				
				d[node.tagName] = node.textContent;
			}
			
			pdata.push(d);
			
		}
		return pdata;
	}
	
	//选中当前第一行 select first row
	mygrid.sfr = function(clearGrids){
		var sf_rids = mygrid.getAllRowIds();
		if(sf_rids!=null && sf_rids!=""){
			var sf_ridsarr = sf_rids.split(",");
			mygrid.selectRowById(sf_ridsarr[0]);
			mygrid.callEvent("onRowSelect",[sf_ridsarr[0],0]);
		}else if(clearGrids){
			for(var i = 0;i<clearGrids.length>0;i++){
				if(clearGrids[i].cd){
					clearGrids[i].cd();
				}else{
					clearGrids[i].clearAll();
				}
			}
		}
		
	}
	
	//给单元格赋值 set select cell value
	mygrid.sscv = function(colid,value){
		var obj = mygrid.cod();
		
		if(!obj){
			return;
		}
		mygrid.cells(obj["@rowid"],mygrid.getColIndexById(colid)).setValue(value);
	}

	return mygrid;
}

//当选中行的时候 勾选复选框 需要放在onRowSelect事件中
kcbplugs.grid.checkedSelectedRow = function(mygrid, id, ind) {
	var me = this;
	var allids = mygrid.getAllRowIds();
	var allidsarr = allids.split(",");
	var ids = mygrid.getSelectedRowId();
	var idsarr = ids.split(",");
	
	if (ind == mygrid.getColIndexById("select")) {
		mygrid.cellById(id, mygrid.getColIndexById("select")).setValue(1);
		var selectRows = getCheckedRowsByGrid(mygrid, mygrid.getColIndexById('select'));
		for(var i=0;i<mygrid.hdr.rows[1].cells.length;i++){
			var html_v=mygrid.hdr.rows[1].cells[i].innerHTML;
			if(html_v.indexOf('checkbox')>0){
				var cell_checkBox = mygrid.hdr.rows[1].cells[i].getElementsByTagName("input")[0];
				cell_checkBox.checked=selectRows.length==mygrid.rowsCol.length;
				break;
			}
		}
		return idsarr;
	}else{
		for(var i=0;i<mygrid.hdr.rows[1].cells.length;i++){
			var html_v=mygrid.hdr.rows[1].cells[i].innerHTML;
			if(html_v.indexOf('checkbox')>0){
				var cell_checkBox = mygrid.hdr.rows[1].cells[i].getElementsByTagName("input")[0];
				cell_checkBox.checked=false;
				break;
			}
		}
	}
	for ( var i = 0; i < allidsarr.length; i++) {
		var c = 0;
		if (me.arrHasProp(idsarr, allidsarr[i])) {
			c = 1;
		}
		mygrid.cellById(allidsarr[i], mygrid.getColIndexById("select")).setValue(c);
	}
	return idsarr;

}

kcbplugs.grid.changeGridData = function(options,mygrid) {//创建数据转换
	var columns = [];

	if (options.columns) {
		columns = options.columns;
	}

	var headers = "", colids = "", widths = "", aligns = "", types = "", sortings = "", tips="",editIcons = "", option = "",sortDBs = "";
	var hcid = [];
	var valids = "";
	
	if(options.rn){
		headers+="序号,";
		colids+="num_id,";
		widths+="30,";
		aligns+="center,";
		types+="cntr,";
		sortings+="na,";
		valids+=",";
		tips+="0,";
		editIcons+="0,";
		option+="[]|";
		sortDBs+="0,";
	}
	
	if(options.sm){
		headers+=options.sm=="ch"?"#master_checkbox,":",";
		colids+="select,";
		widths+="50,";
		aligns+="center,";
		types+=options.sm+",";
		sortings+="int,";
		valids+=",";
		tips+="0,";
		editIcons+="0,";
		option+="[]|";
		sortDBs+="0,";
	}
	
	for (var i = 0 ; i < columns.length; i++) {
		var c = columns[i];
		if (c.id) {
			colids += c.id;
		} else {
			colids += "id" + i == 0 ? "" : i;
		}
		var t=0;
		if(c.tip!=undefined&&c.tip&&c.tip==true){
			t=1;
		}
		if (c.header) {
			headers +=t==1?("<span id='"+c.id+"_tip'>"+c.header+"<img src='loudou.png' /></span>"):c.header;
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
			sortings += "str";
		}
		if(c.isIcon!=undefined&&c.isIcon==false){
			editIcons +="0";
		}else{
			editIcons +="1"
		}
		if(c.options){
			option += c.options;
		}else{
			option +='[]';
		}
		if(c.sortdb!=undefined&&c.sortdb==true){
			sortDBs +="1";
		}else{
			sortDBs +="0"
		}
		if (c.hidden) {
			hcid.push(i+parseInt(mygrid.hassm,10));
		}
		
		if(options.validate){
			if(c.valid){
				if(c.valid=='Empty'||c.valid=='NotEmpty'){
					valids += c.valid;
				}else{
					valids += 'Valid'+c.valid;
				}
				
			} else {
				if(c.type && c.type=="int"){
					valids += 'ValidInteger'
				}
			}
			if (i != columns.length - 1) {
				valids += ",";
			}
			
		}

		if (i != columns.length - 1) {
			headers += ",";
			colids += ",";
			widths += ",";
			types += ",";
			sortings += ",";
			aligns += ",";
			tips += ",";
			editIcons += ",";
			option += "|";
			sortDBs +=","
		}
	}
	return {
		headers:headers,
		colids:colids,
		widths:widths,
		aligns:aligns,
		types:types,
		sortings:sortings,
		hcid:hcid,
		valids:valids,
		tips:tips,
		editIcons:editIcons,
		options:option,
		sortDBs:sortDBs
	};
}

//数组是否包含属性
kcbplugs.grid.arrHasProp = function(arr, prop) {
	var flag = false;
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i] == prop) {
			flag = true;
			break;
		}
	}
	return flag;
}

//自定义列持久化
dhtmlXGridObject.prototype.saveColumnCustom = function(id, headers,
		colids, types, widths, align, sorting,exluscolids, initfn) {

	var that = this;
	that.movedata = [];

	var hideColumnParam = {};
	var columnWidthParam = {};
	var columnWidthId = that.columnWidthId;
	var clientid;
	dwr.engine.setAsync(false);
	clientid=sessionObj["session_current_user_id"]; 
	// 获取表格自定义隐藏列的参数
	DwrCallWrapperSystem.queryInitParamsByPname("queryInitParamsByClientid",clientid, id, function(msg) {
		if (msg.lvalue[0])hideColumnParam = msg.lvalue[0];
	});
	if(columnWidthId&&columnWidthId!=""){
		DwrCallWrapperSystem.queryInitParamsByPname("queryInitParamsByClientid",clientid, columnWidthId, function(msg) {
			if (msg.lvalue[0])columnWidthParam = msg.lvalue[0];
		});
	}
	dwr.engine.setAsync(true);
	if (hideColumnParam["paramvalue"]) {
		var headersarr = headers.split(",");
		var colidsarr = colids.split(",");
		var alignsarr = align.split(",");
		var widthsarr = widths.split(",");
		var typesarr = types.split(",");
		var sortingarr = sorting.split(",");

		headers = "";
		colids = "";
		widths = "";
		align = "";
		types = "";
		sorting = "";
		
		var paramArr = hideColumnParam["paramvalue"].split(",");
		var columnWidths=columnWidthParam["paramvalue"]?columnWidthParam["paramvalue"].split(","):[];
		for ( var i = 0; i < paramArr.length; i++) {
			for ( var j = 0; j < colidsarr.length; j++) {
				if (paramArr[i] == colidsarr[j]) {
					if(headersarr[j]==exluscolids)
						continue;
					headers += headersarr[j] + ",";
					colids += colidsarr[j] + ",";
					widths += (columnWidths.length>0?columnWidths[j]:widthsarr[j]) + ",";
					align += alignsarr[j] + ",";
					types += typesarr[j] + ",";
					sorting += sortingarr[j] + ",";
					colidsarr[j] = "#";
				}
			}
		}
		if (colidsarr.length > 0) {
			for ( var j = 0; j < colidsarr.length; j++) {
				if (colidsarr[j] == "#") {
					continue;
				}
				if(headersarr[j]==exluscolids)
					 continue;
				headers += headersarr[j] + ",";
				colids += colidsarr[j] + ",";
				widths += widthsarr[j] + ",";
				align += alignsarr[j] + ",";
				types += typesarr[j] + ",";
				sorting += sortingarr[j] + ",";
			}
		}
		
		/*for ( var j = 0; j < colidsarr.length; j++) {
			if(headersarr[j]==exluscolids)continue;
			headers += headersarr[j] + ",";
			colids += colidsarr[j] + ",";
			widths += (columnWidths.length>0?columnWidths[j]:widthsarr[j]) + ",";
			align += alignsarr[j] + ",";
			types += typesarr[j] + ",";
			sorting += sortingarr[j] + ",";
		}*/

		headers = headers.substring(0, headers.length - 1);
		colids = colids.substring(0, colids.length - 1);
		widths = widths.substring(0, widths.length - 1);
		align = align.substring(0, align.length - 1);
		types = types.substring(0, types.length - 1);
		sorting = sorting.substring(0, sorting.length - 1);
	}else if(columnWidthParam["paramvalue"]){
		widths = columnWidthParam["paramvalue"];	
	}
	that.setImagePath(basePath + "common/js/kcb/dhtmlxGrid/codebase/imgs/");
	that.setDateFormat("%Y-%m-%d");
	that.setHeader(headers);
	that.setColumnIds(colids);
	that.setInitWidths(widths);
	that.setColAlign(align);
	that.setColTypes(types);
	if(sorting&&sorting!="")
	that.setColSorting(sorting);

	if (hideColumnParam["paramvalue"]) {
		// 一开始隐藏所有列
		var columns = colids.split(",");
		for ( var i = 0; i < columns.length; i++) {
			that.setColumnHidden(i, true);
		}
		var arr = hideColumnParam["paramvalue"].split(",");
		// 把需要显示的列还原回来
		for ( var i = 0; i < arr.length; i++) {
			if (arr[i] != "") {
				that.setColumnHidden(that.getColIndexById(arr[i]), false);
			}
		}
	} else {
		var columns = headers.split(",");
		for ( var i = 0; i < columns.length; i++) {
			if (columns[i].indexOf("|-|") > 0) {
				that.setColumnHidden(i, true);
			}
		}
	}
	that.init();
	that.enableColumnMove(true, undefined, function(id1, id2) {
		that.movedata.push([ id1, id2 ]);
	});
	// 设置菜单隐藏显示插件
	that.enableHeaderMenu({
		onMenuClose : function(map) {
			var cids = "";
			for ( var key in map) {
				if (key && map[key]) {
					cids += that.getColumnId(key) + ",";
				}
			}
			if (cids) {
				if (confirm("确定要保存该视图吗")) {
					hideColumnParam["paramvalue"] = cids;
					hideColumnParam["paramname"] = id;
					hideColumnParam["clientid"] = clientid;
					
					DwrCallWrapperSystem.saveInitParam("saveInitParam",
							hideColumnParam, function(msg) {
								hideColumnParam["id"] = msg.ovalue["id"];
								$func.alert("保存成功");
								if (that.movedata.length > 0) {
									window.location.reload();
								}
							});
				} else {
					hideColumnParam["paramvalue"] = cids;
				}
			}
		},
		onReset : function() {
			if (confirm("确定要还原吗")) {
				var exists_columnWidth=columnWidthId&&columnWidthId!=""?true:false;
				DwrCallWrapperSystem.delInitParam("delInitParam", clientid, id,function(msg) {
					if(exists_columnWidth)window.location.reload();
				});
				if(exists_columnWidth){
					DwrCallWrapperSystem.delInitParam("delInitParam",clientid,columnWidthId,function(msg){
						window.location.reload();
					});
				}
			}
		}
	});

	that.resetColumnMove = function() {
		if (that.movedata.length > 0) {
			for ( var i = that.movedata.length - 1; i >= 0; i--) {
				if (that.movedata[i][1] > that.movedata[i][0]) {
					that.moveColumn(that.movedata[i][1] - 1,
							that.movedata[i][0]);
				} else if (that.movedata[i][1] < that.movedata[i][0]) {
					that.moveColumn(that.movedata[i][1],
							that.movedata[i][0] + 1);
				}
			}
			that.movedata = [];
		}
	}

}


dhtmlXGridObject.prototype.saveColumnWidth = function() {
	var that = this;
	if(!that.columnWidthId||that.columnWidthId=="")return;
	var different=0;
	var newWidths="";
	for (var i = 0;i<that.cellWidthPX.length;i++){
		var w=parseInt(that.cellWidthPX[i]);
		if(w==0)that.cellWidthPX[i]=that.initCellWidth[i];
		newWidths+=that.cellWidthPX[i]+",";
		if(parseInt(that.initCellWidth[i])!=w)different++;	
	}
	if(different==0)return;
	var saveInitParam = {};
	dwr.engine.setAsync(false);
	clientid=sessionObj["session_current_user_id"]; 
	DwrCallWrapperSystem.queryInitParamsByPname("queryInitParamsByClientid",clientid, that.columnWidthId, function(msg) {
		if (msg.lvalue[0])saveInitParam = msg.lvalue[0];
	});
	newWidths=newWidths.substring(0,newWidths.length-1);
	saveInitParam["paramvalue"] = newWidths;
	saveInitParam["paramname"] = that.columnWidthId;
	saveInitParam["clientid"] = clientid;
	DwrCallWrapperSystem.saveInitParam("saveInitParam",saveInitParam, function(msg) {
			saveInitParam["id"] = msg.ovalue["id"];
			if (that.movedata.length > 0)window.location.reload();
	});
	dwr.engine.setAsync(true);
}

dhtmlXGridObject.prototype.initColumnWidth = function() {
	var that = this;
	if(!that.columnWidthId||that.columnWidthId=="")return;
	dwr.engine.setAsync(false);
	clientid=sessionObj["session_current_user_id"]; 
	DwrCallWrapperSystem.delInitParam("delInitParam",clientid,that.columnWidthId,function(msg){
		window.location.reload();
	});
	dwr.engine.setAsync(true);
}
