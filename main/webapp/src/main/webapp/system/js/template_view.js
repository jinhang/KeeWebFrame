dwr.engine.setAsync(false);
var pattern = /=[\u4e00-\u9fa5\+]+/g;
var checkCombo=new dhtmlXCombo("combo_zone2","alfa3",200, 'checkbox');
checkCombo.setDefaultImage("../common/images/book.gif");
checkCombo.attachEvent("onCheck", function(value, state){			
	addPrintItem(state,value);		
	return true;
});
checkCombo.addOption([['=寄件人姓名','=寄件人姓名'],['=卖家昵称','=卖家昵称'],['=店铺名称','=店铺名称'],['=寄件人详细地址','=寄件人详细地址'],['=寄件人电话','=寄件人电话']]);
checkCombo.addOption([['=收件人姓名','=收件人姓名'],['=买家昵称','=买家昵称'],['=收件人详细地址','=收件人详细地址'],['=收件人手机','=收件人手机'],['=收件人电话','=收件人电话']]);
checkCombo.addOption([['=所属省','=所属省'],['=所属市','=所属市'],['=所属区县','=所属区县'],['=邮政编码','=邮政编码'],['=年','=年'],['=月','=月'],['=日','=日']]);
checkCombo.addOption([['=订单编号','=订单编号'],['=买家留言','=买家留言'],['=卖家留言','=卖家留言'],['=快递单号','=快递单号']]);
checkCombo.addOption([['=宝贝+规格+数量','=宝贝+规格+数量'],['=宝贝+数量','=宝贝+数量'],['=规格+数量','=规格+数量']]);
checkCombo.addOption([['=编码+全称+数量','=编码+全称+数量']]);
checkCombo.addOption([['=编码+条码+数量','=编码+条码+数量']]);
checkCombo.addOption([['=简称+数量','=简称+数量']]);
checkCombo.addOption([['=内件数量','=内件数量'],['=条码','=条码']]);
checkCombo.addOption([['=全称+规格+数量','=全称+规格+数量'],['=规格+数量','=规格+数量'],['=大头笔','=大头笔'],['=本次打印流水号','=本次打印流水号'],['=订单重量','=订单重量'],['=订单时间','=订单时间']]);
checkCombo.addOption([['=订单金额','=订单金额'],['=付款金额','=付款金额']]);

var a='tab';
$(".dhx_combo_img").attr("src","img/"+a+".jpg");
$(".dhx_combo_img").css("width","90px");
$(".dhx_combo_img").css("height","26px");
    var LODOP; // 声明为全局变量
    var userTemplate; // 用户自定义的打印模板
     var _name="";
     var myViewForm = new dhtmlXForm("myViewForm");
     //var checkCombo=new dhtmlXCombo("combo_zone2","alfa2",200, 'checkbox');
     var clientid= sessionObj["session_current_client_id"];
     var session_user_id=sessionObj["session_current_user_id"];
//     var current_shop_id=sessionObj["session_current_shop_id"];
//	 var client_systemtype=sessionObj["session_current_client_systemtype"]; 
    
    	$("#cbox_batchprint").hide();
		$("#span_batchprint").hide();	
			
     var arrOption = $(".cbox_option");
     vault=new dhtmlXVaultObject();
     vault.setImagePath(basePath+"common/js/kcb/dhtmlxVault/dhtmlxvault_std 1.6/codebase/imgs/");
     vault.setServerHandlers( basePath+"system/upload-template/UploadHandler.jsp",
    		 basePath+"system/upload-template/GetInfoHandler.jsp",
    		 basePath+"system/upload-template/GetIdHandler.jsp");
     vault.onFileUploaded = function(file) {
    	 var imgUrl= "resource/kuaidi_template/"+file.name;
         LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+basePath+imgUrl+"' />");
         myViewForm.setItemValue("templateimgurl",imgUrl);
     };
     vault.create("vaultDiv");
     $(document).ready(function() {
	for ( var i = 0; i < arrOption.length; i++) {
		$("#cbox_no" + i).click(function() {
			var value = $(this).val();
			var state = $(this).is(":checked");
			addPrintItem(state, value);
		});
	}
});
     
    function DisplayDesign() {
        changeTransport();
    };

    function addPrintOption() {
		$("#print_option").show();		
		$("#print_option").hover(function() {
			$("#print_option").show();
		},function(){
			$("#print_option").hide();
		});
		$("#optionButton").hover(function() {
			$("#print_option").show();
		},function(){
			$("#print_option").hide();
		});
		if (window.screen.width <= 1024) {
			$("#print_option").width(600);
			if(setType==3)
				{
				$("#print_option").height(95);
				$("#print_option").css("top","-80px")
				}
		}
};

function disappearOption(){
	$("#print_option").hide();
}
    function CreatePage() {
        LODOP=getLodop(document.getElementById('LODOP2'),document.getElementById('LODOP_EM2')); 
        LODOP.PRINT_INITA(4,10,869,478,"打印初始化");
       // LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='/kcb-1.0/resource/kuaidi_template/STO.jpg' />");
        LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
        LODOP.ADD_PRINT_TEXT(110,480,118,30,"=收件人姓名");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(141,472,294,30,"=收件人单位名称");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(177,467,302,60,"=收件人详细地址");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(341,287,90,27,"=内件数量");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(242,640,130,30,"=收件人电话");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(394,53,51,25,"=年");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(392,108,39,25,"=月");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(393,146,37,24,"=日");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(244,505,134,26,"=收件人手机");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(106,123,115,30,"=寄件人姓名");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(142,110,268,30,"=寄件人单位名称");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.ADD_PRINT_TEXT(173,108,273,65,"=寄件人详细地址");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);
        LODOP.ADD_PRINT_TEXT(241,143,107,30,"=寄件人电话");
        LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
        LODOP.SET_PRINT_STYLEA(0,"Bold",1);

        LODOP.SET_SHOW_MODE("DESIGN_IN_BROWSE",1);
        LODOP.SET_SHOW_MODE("HIDE_QBUTTIN_PREVIEW",1);
        LODOP.PRINT_DESIGN();

    }

    // 把用户选择的模板添加到
    function loadOneTemplate(template) {
        LODOP=getLodop(document.getElementById('LODOP2'),document.getElementById('LODOP_EM2')); 
        eval(template);
        LODOP.SET_SHOW_MODE("DESIGN_IN_BROWSE",1);        
        LODOP.SET_SHOW_MODE("HIDE_PBUTTIN_SETUP",1);
        LODOP.SET_SHOW_MODE("HIDE_ABUTTIN_SETUP",1);
        LODOP.SET_SHOW_MODE("HIDE_VBUTTIN_SETUP",1);
        LODOP.PRINT_DESIGN();
    }
    
    function loadData(){
    		viewPageUint();
    		if(setType!=1){
    			if(setType == 3){
    				transprots();
    			}
//    			shops();
    			printer();						//加载系统打印机
    		}
    	    view();
            myViewForm.setItemValue("clientid",clientid);
    }
  
    function transprots(){    	
    	var transports = myViewForm.getSelect("transport");
    	var data = {
    		clientid : clientid
		}
		$callTask("express.getExpress",data,function(msg){		
			for ( var i = 0; i < msg.context.length; i++) {
				var option=document.createElement("option");
	    		option.text	= msg.context[i].name;
	    		option.value = msg.context[i].id;
	    		transports.options.add(option);
			}
		});
    }
    
    // 加载系统打印机
    function printer(){
    	var printerSelect = myViewForm.getSelect("printer");
	    printerSelect.options.length=0;
    	var option=document.createElement("option");
		option.text	= "默认";
		option.value = "-1";
		printerSelect.options.add(option);
		LODOP = getLodop(document.getElementById('LODOP'), document.getElementById('LODOP_EM'));
    	var iPrinterCount = LODOP.GET_PRINTER_COUNT();
    	for ( var i = 0; i < iPrinterCount; i++) {
    		option=document.createElement("option");
    		option.text	= LODOP.GET_PRINTER_NAME(i);
    		option.value = LODOP.GET_PRINTER_NAME(i);
    		printerSelect.options.add(option);
    	}
    }
    
    function templateparsms(){
    	var templateparamsCombo = myViewForm.getSelect("templateparams");
    	var transprotidx="";
    	DwrCallWrapperOutstock.getTemplateParams("getTemplateParams",transprotidx,function(msg){
    	var templateparams=msg.lvalue;
    	for(var i=0;i<templateparams.length;i++){
    		var option=document.createElement("option");
    		option.text	= templateparams[i].name;
    		option.value = templateparams[i].id;
    		try
    		{
    			if(templateparamsCombo==null){
    				templateparamsCombo = myViewForm.getSelect("templateparams");
    			}
    			templateparamsCombo.options.add(option);
    		}
    		catch(e)
    		{
    		}
    	}
    	});
    }
    
  //界面的初始化
    function viewPageUint(){
    	myViewForm.setFormData({
    		clientid:"",
    		templateid:"",				
    		shopid:"",			
    		transport:"",	
    		templatename:"",	
    		sortno:10,
    		templateimgurl:""
    	});		
    }   
    
    var uImgUrl = "";
    var aImgUrl = "";
    
    function setTemplates(transportId){
    	var templateId = null;
    	var templates = myViewForm.getSelect("templates");
    	templates.options.length = 0;
		var selectedIndex = 0;
		var data = {
			tId : null,
			transportId : transportId
		}
		$callTask("express.getSysTemplate",data,function(msg){
				templateId = msg.context[0].id;
				for(var k = 0;k < msg.context.length;k++){
					var option = document.createElement("option");
					option.value = msg.context[k].id;
					option.text = msg.context[k].name;
					templates.options.add(option);
					if(msg.context[k].isDefault == 1){
						selectedIndex = k;
						templateId = msg.context[k].id;
						myViewForm.checkItem("isDefault");
					}
				}
				if(templates.options[selectedIndex]){
					templates.options[selectedIndex].setAttribute("selected", "true");
					myViewForm.setItemValue("templatename",templates.options[selectedIndex].text);
				}
			}
		);
		return templateId;
    }
   
    function setTemplate(transportId){
    	var templateId = setTemplates(transportId);
    	var data = {
    		tId : templateId,
    		transportId : null
    	}
    	$callTask("express.getSysTemplate",data,function(msg){
				if(msg.context.length>0){
					myViewForm.setItemValue("templatename",msg.context[0].name);
					aImgUrl = msg.context[0].image;
    				printSelect(msg.context[0].value.match(pattern));//显示到打印选项Checkbox上
    				printOption(msg.context[0].value.match(pattern));
		        	loadOneTemplate(msg.context[0].value);
		        	if(msg.context[0].needsendcode==1){
		            	document.getElementById("cbox_needsendcode").checked=true;      	
		            }else{
		            	document.getElementById("cbox_needsendcode").checked=false; 
		            }
				}
			}
		);
    }

    function view(){
    	if(setType == 1){//管理员设置快递模版
    		myViewForm.setItemValue("forwardcompanyid",forwardId);
    		try{
    			var templateId = setTemplates(forwardId);
    			var data = {
    				tId : null,
    				transportId : templateId
    			}
    			$callTask("express.getSysTemplate",data,function(msg){		
    					var tp = msg.context[0];
    					if(tp != null){	
    						if(tp.image != null && tp.image != "" && tp.image != "null")
    							myViewForm.setItemValue("templateimgurl",tp.image);     			
    						if(tp.value == "null" || tp.value == null || tp.value==""){
    							CreatePage();
    						}else{
    							loadOneTemplate(tp.value);
    						}
    					}	 			
    				}
    			);			    		
    		}catch(e){} 
    	}else if(setType == 2){//修改客户快递模版
    		myViewForm.setItemValue("templateid",updateTemplateId);
    		DwrCallWrapperOutstock.getTemplateByIdMap("getTemplateByIdMap",updateTemplateId, 
    			function(msg) {			
    				uImgUrl = msg.ovalue.templateImgUrl;
    				var string = msg.ovalue.templatevalue;
    				var array = string.match(pattern);
    				printSelect(array);//显示到打印选项Checkbox上
    				printOption(array);
    				edit(msg.ovalue);
    		});			
    	}else if(setType == 3){//客户新增快递模版
    	     //  DisplayDesign();
    		var transportId = myViewForm.getItemValue("transport");
    		setTemplate(transportId);
    	}
    }
    
    function edit(templateObj){
        myViewForm.setFormData({
      		templateid:updateTemplateId,
      		shopid:templateObj.shopid,	
      		templatename:templateObj.templatename,			
      		sortno:templateObj.sortno,			
      		isdefault:templateObj.isdefault,
      		printer:templateObj.printer,
      		isopen:templateObj.isopen
        }); 
        if(templateObj.needsendcode==1){
        	document.getElementById("cbox_needsendcode").checked=true;      	
        }        
       loadOneTemplate(templateObj.templatevalue);
    }
      
    
    function doOnFormInit(){
    	if (typeof(myViewForm) == 'object'){
    		moduleSearchInit();
    		myViewForm.attachEvent("onButtonClick", function(name, command) {
    			if(command == 'addTemplateParams')	addTemplateParams();
    			if(command == 'addPrintText')	addPrintText();
    			if(command == 'deleteSelectText')	deleteSelectText();
    			if(command == 'setTemplateBgPic')	setTemplateBgPic();
    		});
    	
    		myViewForm.attachEvent("onChange", function(id, value) {
    			if(id == 'transport'){
    				var transportId = myViewForm.getItemValue("transport");
					setTemplate(transportId);
    			}else if(id == 'templates'){
    				var tId = myViewForm.getItemValue("templates");
    				var data = {
    					tId : tId,
    					transportId : null
    				}
    				$callTask("express.getSysTemplate",data,function(msg){
    					if(msg.context.length > 0){
							myViewForm.setItemValue("templatename",msg.context[0].name);
							if(setType == 1){
								myViewForm.setItemValue("templateimgurl",msg.context[0].image);
								if(msg.context[0].isDefault == 1){
									myViewForm.checkItem("isDefault");
								}else{
									myViewForm.uncheckItem("isDefault");
								}
								if(msg.context[0].needsendcode==1){
					            	document.getElementById("cbox_needsendcode").checked=true;      	
					            }else{
					            	document.getElementById("cbox_needsendcode").checked=false; 
					            }
								loadOneTemplate(msg.context[0].value);
    						//LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+basePath+msg.context[0].image+"' />");
							}else if(setType == 3){
								var templateId = myViewForm.getItemValue('templates');
			    				var data = {
			    					tId : null,
			    					transportId : templateId
			    				}
			    				$callTask("express.getSysTemplate",data,function(msg){								
										if(msg.context.length>0){
											aImgUrl = msg.context[0].image;
											myViewForm.setItemValue("templatename",msg.context[0].name);
											if(msg.context[0].needsendcode==1){
								            	document.getElementById("cbox_needsendcode").checked=true;      	
								            }else{
								            	document.getElementById("cbox_needsendcode").checked=false; 
								            }
								        	loadOneTemplate(msg.context[0].value);
										}
									});
							}
						}
    					
    				});   				
    			}
    		});    	
    	}   	
    		$("select[name=printer]").width(120);
    	 loadData(); //初始载入数据
    }	
   
    function addPrintText(){
    	LODOP.ADD_PRINT_TEXT(100,100,200,30,"新增文本");
      //  LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
      //  LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    }
    
    function deleteSelectText(){
    	//LODOP.GET_VALUE ("ItemIsDeleted", "selected");
    	
    	//取消selectCheckbox中的相应勾选
    	var content = LODOP.GET_VALUE ("ItemContent", "Selected");
    	$("input[value='"+content+"']").removeAttr("checked");
    	
    	LODOP.SET_PRINT_STYLEA("selected","Deleted","true");
    }
    
    function addTemplateParams(){
        var templateparamsCombo = myViewForm.getSelect("templateparams");
    	var paramsStr = templateparamsCombo.options[templateparamsCombo.selectedIndex].text;
    	LODOP.ADD_PRINT_TEXT(100,100,200,30,paramsStr);
      //  LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
      //  LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    }
    
    
  //保存方法
    function SaveTemplate(){
        var templateName = $.trim(myViewForm.getItemValue("templatename"));  // 模板名 
        if(templateName == ""){
        	$func.alert("未填写模板名称！","red");
        	return;
        }
        
        var needsendcode=0;
 	    if (document.getElementById("cbox_needsendcode").checked) {
 		   needsendcode=1;
 	    }		
        LODOP=getLodop(document.getElementById('LODOP2'),document.getElementById('LODOP_EM2'));
        var templateValue = LODOP.GET_VALUE("ProgramCodes",0);
        var template = {
               id : null,
               clientid : session_user_id,
               templatevalue : LODOP.GET_VALUE("ProgramCodes",0),
               name : templateName,
               initastr : templateValue.split(";")[0]+";",
               pagewidth : LODOP.GET_VALUE("PrintInitWidth",1),
               pageheight : LODOP.GET_VALUE("PrintInitHeight",1),
//               sortno : myViewForm.getItemValue("sortno"),
               forwardcompanyid : myViewForm.getItemValue("transport"),
               printer : myViewForm.getItemValue("printer"),
               isdefault : 0,
               templateImgUrl : aImgUrl,
               isopen:1,
               needsendcode : needsendcode
            };
        	var data ={
        		expresstemp:template 
        	 }
        	 $callTask("express.updateSysTemp",data,function(msg){
        		 $func.alert(msg.svalue);
        	 });
    }
    
    
    function updateTemplate() {
        var templateName=$.trim(myViewForm.getItemValue("templatename")); 
    	if(templateName == ""){
    		$func.alert("未填写模板名称！","red");
    		return;
    	}
   	    var needsendcode=0;
 	    if (document.getElementById("cbox_needsendcode").checked) {
 		   needsendcode=1;
 	    }		    
        LODOP=getLodop(document.getElementById('LODOP2'),document.getElementById('LODOP_EM2'));
        var templateValue = LODOP.GET_VALUE("ProgramCodes",0);
        var template = {
            id : updateTemplateId,
            templatevalue : templateValue,
            name : templateName,
            clientid : session_user_id,
            initastr : templateValue.split(";")[0]+";",
            pagewidth : LODOP.GET_VALUE("PrintInitWidth",1),
            pageheight : LODOP.GET_VALUE("PrintInitHeight",1),
//            sortno : myViewForm.getItemValue("sortno"),
            forwardcompanyid : forwardId,
            printer : myViewForm.getItemValue("printer"),
            isdefault : myViewForm.getItemValue("isdefault"),
            templateImgUrl : uImgUrl,
            needsendcode : needsendcode,
            isopen: myViewForm.getItemValue('isopen')
        };
        var data ={
        	expresstemp:template 
        }
        $callTask("express.updateExpressTemp",data,function(msg){        	
        	if(msg.state==1){				
				parent.loadData();
    			parent.closeWwindow();
			}
        	$func.alert(msg.svalue);
        });
    }
    
    function saveSysTemplate(){
    	var name = $.trim(myViewForm.getItemValue("templatename"));
    	 if(name == ""){
         	$func.alert("请输入模版名称","red");
         	return;
         }
    	 var id = myViewForm.getItemValue("templates");
    	 var isDefault = 0;
    	 if (myViewForm.isItemChecked("isDefault")) {
        	isDefault = 1;
    	 }
    	 
    	var needsendcode=0;
     	if (document.getElementById("cbox_needsendcode").checked) {
     		needsendcode=1;
     	}
    	 var template = {
    		id : id,
    		name : name,
    		value : LODOP.GET_VALUE("ProgramCodes",0),
    		width : LODOP.GET_VALUE("PrintInitWidth",1)+"px",
    	 	height : LODOP.GET_VALUE("PrintInitHeight",1)+"px",
    	 	image : myViewForm.getItemValue("templateimgurl"),
    	 	forwardcompanyid : forwardId,
    	 	isdefault : isDefault,
    	 	needsendcode : needsendcode
    	 };
    	 var data ={
    		systemp:template 
    	 }
    	 $callTask("express.updateSysTemp",data,function(msg){
    		 $func.alert(msg.svalue);
    	 });
    }
    function save(){
    	if(setType == 1){
            saveSysTemplate();
    	}else if(setType == 3){
    		SaveTemplate();
    	}else if(setType == 2){
    		updateTemplate();
    	}
    }
    
  function  newAsSave(){
	  SaveTemplate();
  }

    function changeTransport(){
        var transportid=myViewForm.getItemValue("transport");
		DwrCallWrapperOutstock.getTransportByIdMap("getTransportByIdMap",transportid, function(msg) {	
    		var obj=msg.ovalue;
    		
    		//暂存已有变量----------------------
    		var string = obj.templatevalue;
    		var array = string.match(pattern);
        	printSelect(array);//显示到selectCheckbox上
        	printOption(array);
    		//----------------------------------
    		
	    	if(obj!=null && obj.templateimgurl!=null && obj.templateimgurl!="" ){
	               LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+basePath+obj.templateimgurl+"' />");
	    	}else{
	    		changeTransport();
	    	}
    	});	
       
    }
    
    function setTemplateBgPic(){
       // document.getElementById("vaultDiv").style.display="none";
    	if(document.getElementById("vaultDiv").style.display=="none"){
    		document.getElementById("vaultDiv").style.display="";
    		//myViewForm.setItemValue("setTemplateBgPic","打开背景图设置");
    	}else{
    		document.getElementById("vaultDiv").style.display="none";
    		//myViewForm.setItemValue("setTemplateBgPic","关闭背景图设置");
    	}
    }
    
    function printSelect(array){
    	if(array==null)return;
    	for(var k=0;k<checkCombo.optionsArr.length;k++){
    	for(var i=0;i<array.length;i++){
    		if(checkCombo.optionsArr[k].value==array[i]){
    			checkCombo.setCheckedByValue(checkCombo.optionsArr[k].value);
    	 	}
    	 }    
       }
    }
    
    function printOption(array) {
    	if (array == null)
    		return;
    	for ( var k = 0; k < arrOption.length; k++) {
    		for ( var i = 0; i < array.length; i++) {
    			if (arrOption[k].value == array[i]) {
    				$(".cbox_option:eq(" + k + ")").attr('checked', true);
    			}
    		}
    	}
    }
    
    function addPrintItem(state,value){		
			if(state==true){//增加变量
 				if(value == '=快递单号'){
 					LODOP.ADD_PRINT_BARCODE(40,100,200,50, "code39", value);
 				}else{
 					LODOP.ADD_PRINT_TEXT(100,100,200,30,value);
 				}
 			}
 			else{//删除变量
 				var valuex = value;				
 				for(var i = 0;i<100;i++){
 			    	var content = LODOP.GET_VALUE ("ItemContent", i);
 			    	if(content==valuex){
 			    		LODOP.SET_PRINT_STYLEA(i,"Deleted","true");
 			    		break;
 			    	}
 				}
 			}
    }
    
    function cancel(){
    	if(fromPage=='ywp'){
			parent.window.closeWindow();
		}else{
			parent.window.closeWwindow();
		}
    	
    }
    
    
    function printview(){
            LODOP=getLodop(document.getElementById('LODOP2'),document.getElementById('LODOP_EM2')); 
            var LODOPx=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
            var template = LODOP.GET_VALUE("ProgramCodes",0);
            var name = myViewForm.getItemValue("templatename");  // 模板名 
            var shopId =myViewForm.getItemValue("shopid");
            var initastr=template.split(";")[0]+";";;
            var pagewidth=LODOP.GET_VALUE("PrintInitWidth",1);
            var pageheight=LODOP.GET_VALUE("PrintInitHeight",1);
            LODOPx.NewPage();// 强制分页
    		var template2=demoData(initastr,template, 1,pagewidth,pageheight);
    		eval(template2);
    		pagewidth = pagewidth + "px";
    		pageheight =pageheight + "px";
    		LODOPx.SET_PRINT_PAGESIZE(1,pagewidth,pageheight, "");		
    		//LODOPx.SET_SHOW_MODE("HIDE_PBUTTIN_PREVIEW", true);// 隐藏打印按钮
    		//LODOPx.SET_SHOW_MODE("HIDE_SBUTTIN_PREVIEW", true);// 隐藏设置按钮
    		LODOPx.PREVIEW();
    }
   
  
  function demoData(initastr,template,  isneedinitastr,pagewidth, pageheight) {
    	template = template.replace(new RegExp("=收件人姓名", "gm"), "张三丰");
    	template = template.replace(new RegExp("=收件人手机", "gm"), "13858182349");
    	template = template.replace(new RegExp("=收件人电话", "gm"), "0571-87995756");
    	template = template.replace(new RegExp("=收件人详细地址", "gm"), "浙江省杭州市西湖区天目山路327号合生国贸中心7栋八楼A单元");
    	template = template.replace(new RegExp("=收件人单位名称", "gm"), "杭州酷仓宝");
    	template = template.replace(new RegExp("=买家昵称", "gm"), "杭州酷仓宝");
     	template = template.replace(new RegExp("=内件数量", "gm"), 3);
     	template = template.replace(new RegExp("=寄件人单位名称", "gm"), "你的店铺名称");
     	template = template.replace(new RegExp("=卖家昵称", "gm"), "你的店铺名称");
     	template = template.replace(new RegExp("=寄件人姓名", "gm"), "店掌柜");
    	template = template.replace(new RegExp("=寄件人详细地址", "gm"), "浙江义乌苏溪镇306号");
    	template = template.replace(new RegExp("=寄件人电话", "gm"), "13845232234");
    	template = template.replace(new RegExp("=所属省", "gm"), "浙江");
    	template = template.replace(new RegExp("=所属市", "gm"), "杭州");
    	template = template.replace(new RegExp("=所属区县", "gm"), "西湖区");
    	template = template.replace(new RegExp("=邮政编码", "gm"), "168888");
    	var myDate = new Date();
    	template = template.replace(new RegExp("=年", "gm"),  myDate.getFullYear());
    	template = template.replace(new RegExp("=月", "gm"),  myDate.getMonth() + 1);
    	template = template.replace(new RegExp("=日", "gm"),  myDate.getDate());
    	template = template.replace("=编码+全称+数量", "【3351726881、013351726881、1】【3251726882、AW013351726882、2】");
    	template = template.replace("=编码+条码+数量", "【3351726881、013351726881、1】【3251726882、AW013351726882、2】");
    	template = template.replace(new RegExp("=义乌申通大头笔", "gm"), "浙江杭州西湖区");
    	template = template.replace(new RegExp("=申通大头笔", "gm"), "浙江杭州西湖区");
    	template = template.replace(new RegExp("=大头笔", "gm"), "浙江杭州西湖区");
    	template = template.replace(new RegExp("=买家留言", "gm"), "请给我发红色");
    	template = template.replace(new RegExp("=卖家留言", "gm"), "发申通");
    	template = template.replace(new RegExp("=订单编号", "gm"), "335172688136408");
    	template = template.replace(new RegExp("=宝贝简称", "gm"), "背包01-运动裤AW");
    	template = template.replace(new RegExp("=宝贝条码", "gm"), "3351726881|3251726882");
    	template = template.replace(new RegExp("=商家编码", "gm"), "3351726881|3251726882");
    	template = template.replace("=宝贝+规格+数量", "背包013351726881、红色XL、1\\n运动裤AW013351726882、蓝色XL、2");
    	template = template.replace("=简称+数量", "背包、1\\n运动裤、2");
    	template = template.replace(new RegExp("=宝贝信息2", "gm"), "背包013351726881、颜色:红色;尺码:XL、数量:1\\n运动裤AW013351726882、颜色:蓝色;尺码:XL、数量:2");
    	template = template.replace(new RegExp("=宝贝信息", "gm"), "【背包013351726881、红色XL、1】【运动裤AW013351726882、蓝色XL、2】");
    	template = template.replace("=宝贝+数量", "背包013351726881、1\\n运动裤AW013351726882、2");
    	template = template.replace(new RegExp("=打印日流水号", "gm"), 1);
    	template = template.replace(new RegExp("=本次打印流水号", "gm"), 1);
    	template = template.replace(new RegExp("=快递单号", "gm"), "568388234567");
    	template = template.replace(new RegExp("=订单金额", "gm"), "23.56");
    	
    	if (isneedinitastr == 1) {
    		template = template.replace(initastr, "");
    	} else {
    		pagewidth = pagewidth + "px";
    		pageheight = pageheight + "px";
    		template = template + "LODOP.SET_PRINT_PAGESIZE(1,'" + pagewidth+ "','" + pageheight + "','');";
    	}
    	template = template.replace(new RegExp("LODOP.", "gm"), "LODOPx.");
    	return template;
    }
    


  function zjdy() {
		var zjdyDivHtml = '';
		zjdyDivHtml += "<object ";
		zjdyDivHtml += "classid=\"clsid:2105C259-1E0C-4534-8141-A753534CB4CA\""
		zjdyDivHtml += " width=\"0\" height=\"0\" id=\"LODOP\">";
		zjdyDivHtml += "<embed id=\"LODOP_EM\" type=\"application/x-print-lodop\" width=0 height=0 pluginspage=\"<%=basePath%>resource/lodop/install_lodop32.exe\"></embed>";
		zjdyDivHtml += "</object>";
		if(dojo.byId("zjdyDiv")!=null)
		dojo.byId("zjdyDiv").innerHTML = zjdyDivHtml;
	}
  zjdy();