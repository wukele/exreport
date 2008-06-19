<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!-- 整理中，暂不可用 -->
<html>
	<head>
		<title>My JSP '1.jsp' starting page</title>
		<script type="text/javascript" src="js/excel.js"></script>
		<script type="text/javascript">
  
  	var bean = {
  	<%
    String ss = org.qsoft.exreport.data.JsonReport.testa(1);
    out.println(ss);
     %>
  };
 
  var isdowndata = true; 
 
  var colsname = 'code,name,sum,lastmonth,lastyear,term';
  var serverurl ='localhost';
  var serverport = '8080';
  var contextpath = '/naf/xls';
  var templatename = 'new';
  var extensename = '.xls';
  var downservlet = "/naf/xls/5w.zip";
  //var downservlet = "/naf/ReportDataServlet?rows=50000&cols=" + colsname;
  
  var fileid = "";
  
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
		<object classid="clsid:17181852-F536-4B8D-8BA4-6CA8CD61D7AA"
			id="filehelper">
		</object>
	</body>
	<script type="text/javascript">
	  
	   var tmfile = downloadtemplate(); 
	  
	  window.setTimeout('createReport(tmfile);',500);
	  
	  //alert(11);
	  
  </script>
</html>

