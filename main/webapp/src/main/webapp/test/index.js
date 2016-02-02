//dwr.engine.setAsync(false);

	alert("here");
	var data = {
		name:"test"
	}
	
	$callTask("order.OrderTest",data,function(msg){
		
		alert(msg);
    });