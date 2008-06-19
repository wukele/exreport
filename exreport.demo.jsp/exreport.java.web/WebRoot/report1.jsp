<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<html>
	<head>
		<title>My JSP '1.jsp' starting page</title>
		<script type="text/javascript" src="js/excel.js"></script>
		<script type="text/javascript">
  
  //所有数据的bean
  	var bean = {
  	<%
    String ss = org.qsoft.exreport.test.TestJson.testa(5000);
    out.println(ss);
     %>
  };
 
  var isdowndata = false;  //是否是下载数据文件方式
 
  var colsname = 'code,name,area,sum,lastmonth,lastyear,term'; // 需要处理的列名
  var serverurl ='http://localhost:8080';//服务器端ip
  //var serverport = '8080';   //端口
  var contextpath = '/exreport/xls'; //模板路径
  var templatename = 'report1';     //模板名称
  var extensename = '.xls';			//模板文件扩展名
  //var downservlet = "/naf/xls/5w.zip"; //数据文件路径
  //var downservlet = "/naf/ReportDataServlet?rows=50000&cols=" + colsname;
  
  var fileid = ""; //文件名附加字符串，可用于判断是否需要重新下载
  
  var reporttype = 1; // 报表类型, 0普通, 1 交叉表 
     
     
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

