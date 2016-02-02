//设置光标位置
var setCursorPos = function(pos) {
	try {
		var el = $("#myViewForm1 :input")[0];
		if (el.createTextRange) {
			var rng = el.createTextRange(); //新建textRange对象   
			rng.moveStart('character', pos); //更改rng对象的开始位置   
			rng.collapse(true); //光标移动到范围结尾   
			rng.select();//选中   
			el.focus();
		} else if (el.setSelectionRange) {
			el.focus(); //先聚集 
			el.setSelectionRange(pos, pos); //设光标 
		}
	} catch (e) {
		// TODO: handle exception
	}

}
dwr.engine.setAsync(false);
var staff = sessionObj["session_user"];
var userid = sessionObj["session_current_user_id"];