<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/WEB-INF/classes/runqianReport4.tld" prefix="report" %>

<html>
<body leftMargin=0 topMargin=0 rightMargin=0 bottomMargin=0>

<script language=javascript>
	var _editorBorderLeft = "1px solid red";      //填报编辑框左边框
	var _editorBorderTop = "1px solid red";       //填报编辑框上边框
	var _editorBorderRight = "1px solid red";     //填报编辑框右边框
	var _editorBorderBottom = "1px solid red";    //填报编辑框下边框
	var _editingRowBackColor = "#d1f2fe";         //填报编辑行背景色，设为空值则不标记当前编辑行
	var _calendarMainBackColor = "#fa8072";       //填报下拉日历主面板色
	var _calendarWeekColor = "#FFFFFF";           //填报下拉日历周文字色
	var _calendarDayColor = "#000040";            //填报下拉日历日期文字色
	var _calendarDayBackColor = "#ffe4e1";        //填报下拉日历日期面板色
</script>



<report:html name="report1" reportFileName="123.raq"
	
	excelPageStyle="0"
	needScroll="yes"
	scrollWidth="100%"
	scrollHeight="400"
	
	selectText="yes"
	promptAfterSave="yes"
	funcBarLocation=""
	
	backAndRefresh="no"
/>
<div id=div1 style="width:100%;height:100%"></div>

<script language=javascript>
	document.body.style.overflow = "hidden";
	window.onresize = myResize;
	function myResize() {
		var scrolldiv = document.getElementById( "report1_scrollArea" );
		if( scrolldiv != null ) {
			var div1 = document.getElementById( "div1" );
			div1.style.display = "";
			var h = div1.offsetHeight;
			h -= getHeightX( document.body );
			var paramDiv = document.getElementById( "paramDiv" );
			if( paramDiv != null ) h -= paramDiv.offsetHeight;
			var functionBar = document.getElementById( "functionBar" );
			if( functionBar != null ) h -= functionBar.offsetHeight;
			if( ! document.all ) {
				h -= 3;
				scrolldiv.style.width = scrolldiv.offsetWidth - 4;
			}
			scrolldiv.style.height = h;
			_resizeScroll();
			div1.style.display = "none";
		}
	}
	myResize();
</script>

</body>
</html>
