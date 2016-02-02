function setHTML(shops,div){
	var html = "<table style='width:100%'>";
	for(var i = 0; i < shops.length; i++){
		var name = shops[i].name;
		if(i % 2 == 0){
			html += "<tr>";
		}
		html += "<td>" + name + "</td>";
		if((i + 1) % 2 == 0){
			html += "</tr>";
		}
	}
	html += "</table>";
	if(shops.length == 1){
		div.css({"width" : "110px"});
	}else if(shops.length > 1){
		div.css({"width" : "220px"});
	}
	div.html(html);
}
function setShopCount(status) {
	DwrCallWrapperSystem.getShopCountByStatus("t_get_shop_count_by_status",
			sessionObj["session_current_client_id"], status, function(msg) {
				if (status == 1) {
					$("#online_shop").html(msg.ivalue+"个店铺");
					setHTML(msg.lvalue,$("#loadShop"));
				} else if (status == 0) {
					$("#offline_shop").html(msg.ivalue+"个店铺");
					setHTML(msg.lvalue,$("#unloadShop"));
				}
			});
}

function setOrderCount(status) {
	var refundStatus = null;
	var isprint2 = null;
	var islock = null;
	var orderdeal = null;
	if (status == 'WAIT_SELLER_AGREE') {
		refundStatus = 'WAIT_SELLER_AGREE';
	} else {
		isprint2 = status;
		if(status == 1){
			orderdeal = '0';
		}
		islock = '0';
	}
	DwrCallServerOutstock.getOutstocksCount(null, null, null, "@cid", null, null, null, null, [ 0,
			1, 2, 3, 4, 5 ], null, null, null, null, null, null, 0, 0, null, null, null, null,
			null, null, null, null, null, null, null, islock, null, null, isprint2, null, null, null,
			orderdeal, null, 1, null, null, null, null, null, null, null, null, null, null, null, null,
			null, refundStatus, null, null, null, function(msg) {
				if (status == 0) {
					$("#to_print").html(msg.lvalue[0]);
				} else if (status == 1) {
					$("#to_lanch").html(msg.lvalue[0]);
				} else if (status == "WAIT_SELLER_AGREE") {
					$("#in_refund").html(msg.lvalue[0]);
				}
			});
}
function setCount() {
	dwr.engine.setAsync(true);
	setShopCount(0);
	setShopCount(1);

	setOrderCount(0);
	setOrderCount(1);
	setOrderCount("WAIT_SELLER_AGREE");
	dwr.engine.setAsync(false);
}
function showMore(noticeId) {
	var dhxWins = new dhtmlXWindows();
	noticeWin = dhxWins.createWindow("noticeWin", ($(window).width() - 900) / 2, ($(window)
			.height() - 700) / 2, 900, 700);
	noticeWin.setText("系统通知");
	noticeWin.denyResize();
	noticeWin.button("minmax1").hide();
	noticeWin.button("park").hide();
	noticeWin.attachURL("systemNotice.jsp?noticeId=" + noticeId);
}

function downloadOrder() {
	DwrCallWrapperSystem.isClientInit('isHaveShop', sessionObj["session_current_user_id"],
			function(msg) {
				if (msg.lvalue.length > 0) {
					var dhxWins = new dhtmlXWindows();
					w1 = dhxWins.createWindow("w1", ($(window).width() - 900) / 2, ($(window)
							.height() - 600) / 2, 900, 557);
					w1.setText("下载订单");
					w1.denyResize();
					w1.center();
					w1.button("minmax1").hide();
					w1.button("park").show();
					w1.attachEvent("onParkUp", function(w1) {
						w1.setDimension(240, 10);
						w1.denyMove();
						w1.setPosition(200, ($(window).height() - 40));
						w1.button("close").hide();

					});
					w1.attachEvent("onParkDown", function(w1) {
						w1.setDimension(900, 557);
						w1.center();
						w1.allowMove();
						w1.button("close").show();
					});
					w1.attachURL("downloadOrder.jsp");
				} else {
					$func.alert('您没有店铺，无法下载订单！');
					return;
				}
			});
}
