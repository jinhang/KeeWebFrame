
dwr.engine.setAsync(false);
var user,nick;
DwrGetSession.getSessionList(['session_user','session_nick'],function(msg){
	if(msg[0] == null){
		$func.alert('请重新登录！');
		return;
	}
	user = msg[0].client;
	nick = msg[1];
})


function downloadFromTaobao() {
	parent.parent.downloadFromTaobao();
}



function doOnButtonFormInit() {
buttonForm.attachEvent("onChange", function (name, value){
	switch (name) {
		case 'forwardcompany':
			setForwardCompany();
			break;
	}
});


buttonForm.attachEvent("onButtonClick", function (name, value){
	switch (name) {
	case 'save':
		dwr.engine.setAsync(true);
		downloadFromTaobao();
		dwr.engine.setAsync(false);
		break;
	}
});

}
