<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed>
</object>
var LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
/**
 * 
 */
function printColumns(grid,columns,rowid,css){
	
	var strStyleCSS="<link href='"+css+"' type='text/css' rel='stylesheet'>";
	var strFormHtml=strStyleCSS+"<body>"+document.getElementById("form1").innerHTML+"</body>";
	LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_外链样式风格");
	LODOP.ADD_PRINT_TEXT(50,50,260,39,"外链样式红色边框：");
	LODOP.ADD_PRINT_HTM(88,50,300,200,strFormHtml);
	LODOP.PREVIEW();
	 var colNum=grid.getColumnsNum();
	 var rows =  rowid.split(',');
	 var col  = columns.split(',');
	 var list = [];
	 for(i = 0;i < rows.length;i++ ){
		 var result = {};
		 for(j = 0;j < col.length;j++){
			 var index = grid.getColIndexById(col[j]);
			 result[col[j]] = grid.cellById(rows[i],index).getValue();
		 }
		 list[i] = result; 
	 }
	 return list;	
}