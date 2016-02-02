<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/WEB-INF/classes/runqianReport4.tld" prefix="report" %>

<html>
<body leftMargin=0 topMargin=0 rightMargin=0 bottomMargin=0>

<script language=javascript>
	var _editorBorderLeft = "1px solid red";      //��༭����߿�
	var _editorBorderTop = "1px solid red";       //��༭���ϱ߿�
	var _editorBorderRight = "1px solid red";     //��༭���ұ߿�
	var _editorBorderBottom = "1px solid red";    //��༭���±߿�
	var _editingRowBackColor = "#d1f2fe";         //��༭�б���ɫ����Ϊ��ֵ�򲻱�ǵ�ǰ�༭��
	var _calendarMainBackColor = "#fa8072";       //��������������ɫ
	var _calendarWeekColor = "#FFFFFF";           //���������������ɫ
	var _calendarDayColor = "#000040";            //�����������������ɫ
	var _calendarDayBackColor = "#ffe4e1";        //����������������ɫ
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
