var sessionObj = new Object();
if(window.top!=window.self){
	sessionObj = window.top.sessionObj;
}else{
	var names = [];
	DwrGetSession.getSessionNames(function(msg){
		names = msg;
		DwrGetSession.getSessionList(names,function(msg){
			for ( var i = 0; i < names.length; i++) {
				sessionObj[names[i]]=msg[i];
			}
		});
		
	});
	
}
