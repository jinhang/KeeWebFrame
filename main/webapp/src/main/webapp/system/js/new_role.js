var role_grid = parent.role_grid;
function doOnFormInit(){
	$(".dhxlist_base").eq(1).css("margin-left","20px");
	$(".dhxlist_txt_label").css("width","70px");
}
var tusername = sessionObj["session_nick"];

function dobuttonFormInit(){
	mybuttonform.attachEvent("onButtonClick", function(name, command) {
		switch (command) {
			case 'save':
				doSave();
				break;
			case 'cancle':
				doClose();
				break;
		}
	});
}

function doSave(){
	var role={};
	role.name=myForm.getItemValue("name");
	role.type=1;
	role.clientid=clientid;
	console.log(role.name)
	if (!role.name) {
		$func.alert('角色名称不可为空！', 'red')
		return;
	}
	
	if (role.name.Trim()=="") {
		$func.alert('角色名称不可为空！', 'red')
		return;
	}
	
	if(role.name.length>25){
		$func.alert('角色名称太长，请重新填写！', 'red')
		return;
	}
	role.name=role.name.Trim();
	var ids = role_grid.getAllRowIds('.').split('.');
    if(ids[0] !=null && ids[0]!=""){
		for (var i in ids) {
			if (role.name == role_grid.cells(ids[i], 1).getValue()) {
				$func.alert('角色权限已存在！', 'red');
				return;
			}
		}
	}
    
    var data = {
    	role:role
    }
    $callTask("system.saveRole",data,function(msg){
    	$func.alert("添加成功！");
		parent.load_role_grid();
		parent.w1.close();
    });
}

function doClose(){
	parent.w1.close();
}