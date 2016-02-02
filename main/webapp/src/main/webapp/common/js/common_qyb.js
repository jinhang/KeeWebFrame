
Date.prototype.toJSON = function () { 
	//return this.getFullYear()+'-'+(this.getMonth()+1)+'-' +this.getDay()+' ' + this.getHours()+':'+this.getMinutes()+':'+this.getSeconds();
	return this.toUTCString();
}
//同步
function $callTask(taskhandle,dataObj,successFunc){
	var data =  JSON.stringify(dataObj);
	$.ajax({
	    type: "POST",
	    contentType: 'application/json',
	    url: contextPath+"/engine/excute?taskid="+taskhandle,
	    data:data,
	    async: false,
	    success: successFunc,
	    error: function(e){
	    	alert('Error: ' + e);
	    }
	});
}
function $callTaskAsync(taskhandle,dataObj,successFunc){
var data =  JSON.stringify(dataObj);
$.ajax({
    type: "POST",
    contentType: 'application/json',
    url: contextPath+"/engine/excute?taskid="+taskhandle,
    data:data,
    success: successFunc,
    async: true,
    error: function(e){
    	alert('Error: ' + e);
    }
});
}

/**
//标识对应的值 从后台获取
//获取后台枚举数据的方式 
 $codeObj.枚举名.属性 或   $codeObj[枚举名][属性]
 demo:
1 $codeObj.FilterType 返回一个对象 
	for(var attrx in object){//循环遍历对象的属性 
		alert(attrx);
		} 
2 $codeObj.FilterType.BCTJ
3 $codeObj.FilterType["BCTJ"]
4 $codeObj["FilterType"]["BCTJ"]
**/
//var $codeObj;
//if(window.top!=window.self){
//	$codeObj = window.top.$codeObj;
//}else{
//$callTask("commontask.getCodeToMap",{}, function(msg) {
//	$codeObj=msg;	
//});
//}
