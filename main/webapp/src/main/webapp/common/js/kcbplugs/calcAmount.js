var kcbplugs = kcbplugs || {};

/**
 * 计算快递运费的公共插件
 * 
 * 调用方式 var calc = new kcbplugs.calcAmount();
 */
kcbplugs.calcAmount = function(fcid) {
	this.fcid = fcid || "402881ec33c024850133c025918d0000";
	
	this.firstWeight = 1000;
	if("EMS6c79b831445448e1d5448b5528be7"==fcid){
		this.firstWeight = 500;
	}

	this.init.apply(this);
}

// 初始化方法
kcbplugs.calcAmount.prototype.init = function() {
	this.datas = {};

	var me = this;
	dwr.engine.setAsync(false);
	// 获取报价信息
	DwrCallWrapperForwardCompare.getClientForwardPriceTemp(
			't_getforwardpricetemp', function(msg) {
				var _d = msg.lvalue;

				for ( var i = 0; i < _d.length; i++) {
					if (_d[i].forwardcompanyid != me.fcid) {
						continue;
					}

					dwr.engine.setAsync(false);
					DwrCallWrapperForwardCompare
							.queryClientForwardPriceInfoByTempid(
									't_queryclientforwardpriceInfobytempid_j',
									_d[i].id, function(msg) {

										_d[i].infoList = msg.lvalue;

									});

					if (!me.datas[_d[i].forwardcompanyid]) {

						me.datas[_d[i].forwardcompanyid] = [ _d[i] ];

					} else {
						me.datas[_d[i].forwardcompanyid].push(_d[i]);
					}

				}
			});

}

/**
 * 开始计算 return -1:该快递公司报价单没有加载完毕
 */
kcbplugs.calcAmount.prototype.calc = function(weight, pro, city, country) {
	
	if(pro==null || $.trim(pro)==""){
		return -1;
	}
	
	console.log(this.datas,this.fcid);
	if (!this.datas[this.fcid]) {
		return -2;
	}

	var temps = this.datas[this.fcid];

	var infoList = [];

	// 如果城市不为空 首先根据城市查找促销或新价
	if (city != null) {
		for ( var i = 0; i < temps.length; i++) {
			if (infoList.length > 0) {
				break;
			}
			if (temps[i].priceType == 2 || temps[i].priceType == 3) {

				var il = temps[i].infoList;

				for ( var j = 0; j < il.length; j++) {
					var io = il[j];
					var sa = io.sendArea;

					city = city.substring(0, 2);

					if (sa.areaText.indexOf(city) >= 0) {
						infoList.push(io);
						break;
					}

				}

			}
		}
	}
	
	if(infoList.length==0){//通过省份查找促销和新价
		for ( var i = 0; i < temps.length; i++) {
			if (infoList.length > 0) {
				break;
			}
			
			if (temps[i].priceType == 2 || temps[i].priceType == 3) {
				var il = temps[i].infoList;

				for ( var j = 0; j < il.length; j++) {
					var io = il[j];
					var sa = io.sendArea;

					pro = pro.substring(0, 3);

					if (sa.areaText.indexOf(pro) >= 0) {
						infoList.push(io);
						break;
					}

				}
			}
		}
		
	}
	
	//通过城市找标准价
	if (infoList.length==0||city != null) {
		for ( var i = 0; i < temps.length; i++) {
			if (infoList.length > 0) {
				break;
			}
			if (temps[i].priceType == 1 || temps[i].priceType == 11) {

				var il = temps[i].infoList;

				for ( var j = 0; j < il.length; j++) {
					var io = il[j];
					var sa = io.sendArea;

					city = city.substring(0, 2);

					if (sa.areaText.indexOf(city) >= 0) {
						infoList.push(io);
						break;
					}

				}

			}
		}
	}
	
	if(infoList.length==0){//通过省份查找标准价
		for ( var i = 0; i < temps.length; i++) {
			if (infoList.length > 0) {
				break;
			}
			
			if (temps[i].priceType == 1 || temps[i].priceType == 11) {
				var il = temps[i].infoList;

				for ( var j = 0; j < il.length; j++) {
					var io = il[j];
					var sa = io.sendArea;

					pro = pro.substring(0, 3);

					if (sa.areaText.indexOf(pro) >= 0) {
						infoList.push(io);
						break;
					}

				}
			}
		}
		
	}
	
	if(infoList.length==0){
		return -3;
	}
	
	weight = weight*1000;
	
	var w1 = infoList[0].sendArea.weight;
	var w2 = infoList[0].sendArea.outWeight;
	
	var rates = this.getRate(w1,w2,weight,this.firstWeight);
	
	return rates;
	
}

kcbplugs.calcAmount.prototype.getRate = function(w1,w2,weight,firstWeight){
	var rates = 0;
	
	if(weight<=firstWeight){
		rates = w1;
	}else{
		var y = parseInt(weight/firstWeight-1);
		var m = parseInt(weight%firstWeight);
		
		if(m>0){
			y++;
		}
		
		rates = w1 + w2 * y;
	}
	
	return rates;
	
}