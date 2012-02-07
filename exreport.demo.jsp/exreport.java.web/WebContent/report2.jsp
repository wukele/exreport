<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<html>
	<head>
		<title>行一级，列二级交叉报表</title>
		<script type="text/javascript" src="js/excel.js"></script>
		<script type="text/javascript">
  
  //所有数据的bean
  	var bean = {
  		"META_DATA_ARRAYS":[
  		"EXCEL_DATA_LENGTH",
  		"unit",
  		"area",
  		"owner",
  		"number",
  		"REPORT_DTO_FIELDS_KEY",
  		"HIDDEN_FIELDS",
  		"industry",
  		"date"
  		],
  		"EXCEL_DATA_LENGTH":5000,
  		"unit":"万元",
  		"area":"beijing",
  		"owner":"国有",
  		"DTO_DATA_ARRAYS":[
  		],
  		"number":111111111,
  		"REPORT_DTO_FIELDS_KEY":"code,name,area,sum,lastmonth,lastyear,term",
  		"HIDDEN_FIELDS":"lastmonth,lastyear",
  		"industry":"金融",
  		"date":"2012-2-7 16:45:06"
  	};
 
  var isdowndata = true;  //是否是下载数据文件方式
  var downservlet = "/exreport/data/data.zip"; //数据文件路径,动态数据可以通过servlet获取数据拼装成csv格式输出
  //var downservlet = "/naf/ReportDataServlet?rows=50000&cols=" + colsname;
  
  var colsname = 'code,name,area,sum,lastmonth,lastyear,term'; // 需要处理的列名
  var serverurl ='http://localhost:8080';//服务器端ip
  //var serverport = '8080';   //端口
  var contextpath = '/exreport/xls'; //模板路径
  var templatename = 'report2';     //模板名称
  var extensename = '.xls';			//模板文件扩展名
  
  var fileid = "";

var reporttype = 1; // 报表类型, 0普通, 1 交叉表 
var reportdatazip = true; //是否是zip格式数据
	     
     
      function   document.onkeydown(){   
         if( window.event.ctrlKey && window.event.shiftKey && window.event.keyCode==39)
			{
				executeMacro("hidecols");
			}
		 else if( window.event.ctrlKey && window.event.shiftKey && window.event.keyCode==37)
			{
				executeMacro("showcols");
			}
  }
  </script>

		<script language="vbscript" src="vbs/array.vbs"></script>


	</head>

	<body>
		<input type="button" value="保存报表" name="save"
			onclick="save('c:\\abcd.xls')">
			
		<input type="button" value="导出报表" name="export" class="BUTTON"
			onclick="output()">
		<input type="button" value="打印报表" name="print" class="BUTTON"
			onclick="print()">
		<input type="button" value="打印预览" name="preview" class="BUTTON"
			onclick="preview()">
		<input type="button" value="报表转置" name="revers" class="BUTTON"
			onclick="revers()">
		<input type="button" value="报表放大" name="zoomin" class="BUTTON"
			onclick="zoomin()">
		<input type="button" value="报表缩小" name="zoomout" class="BUTTON"
			onclick="zoomout()">
		<input type="button" value=" 关  闭 " name="wclose" class="BUTTON"
			onclick="window.close();">

		<object classid="clsid:00460182-9E5E-11d5-B7C8-B8269041DD57"
			id="oframe" width="100%" height="100%">
			<PARAM NAME="_ExtentX" VALUE="23283">
			<PARAM NAME="_ExtentX" VALUE="23283">
			<PARAM NAME="_ExtentY" VALUE="13891">
			<PARAM NAME="BorderColor" VALUE="-2147483632">
			<PARAM NAME="BackColor" VALUE="-2147483643">
			<PARAM NAME="ForeColor" VALUE="-2147483640">
			<PARAM NAME="TitlebarColor" VALUE="-2147483635">
			<PARAM NAME="TitlebarTextColor" VALUE="-2147483634">
			<PARAM NAME="BorderStyle" VALUE="1">
			<PARAM NAME="Titlebar" VALUE="0">
			<PARAM NAME="Toolbars" VALUE="0">
			<PARAM NAME="Menubar" VALUE="0">
		</OBJECT>
	</body>
	<script type="text/javascript">
	  
	   var tmfile = downloadtemplate(); //下载模板
	  
	  window.setTimeout('createReport(tmfile);',500); //创建报表
	  
  </script>
</html>

