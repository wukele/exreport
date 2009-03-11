<%@ Page Language="C#" AutoEventWireup="true"  CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <title>行一级，列二级交叉报表</title>

    <script type="text/javascript">
  
  //所有数据的bean
  	var bean = 
  	<%=exreport.demo.TestReport.test(5000)%>
  ;
 
  var isdowndata = false;  //是否是下载数据文件方式
 
  var colsname = 'code,name,area,sum,lastmonth,lastyear,term'; // 需要处理的列名
  var serverurl ='http://localhost:<%=Request.Url.Port%>';//服务器端ip
  //var serverport = '8080';   //端口
  var contextpath = '/exreport/xls'; //模板路径
  var templatename = 'report1';     //模板名称
  //alert(templatename);
  var extensename = '.xls';			//模板文件扩展名
  //var downservlet = "/naf/xls/5w.zip"; //数据文件路径 ---- 用于大数据量(MB级以上数据)，暂不提供
  //var downservlet = "/naf/ReportDataServlet?rows=50000&cols=" + colsname;
  
  var fileid = ""; //文件名附加字符串，可用于判断是否需要重新下载
  
  var reporttype = 1; // 报表类型, 0普通, 1 交叉表 
     
     
//      function   document.onkeydown(){   
//         if( window.event.ctrlKey && window.event.shiftKey && window.event.keyCode==39)
//			{
//				executeMacro("hidecols");
//			}
//		 else if( window.event.ctrlKey && window.event.shiftKey && window.event.keyCode==37)
//			{
//				executeMacro("showcols");
//			}
//  }
    </script>

    <script type="text/javascript" src="js/excel.js"></script>

    <script type="text/vbscript" language="vbscript" src="vbs/array.vbs"></script>

</head>
<body>
    <form id="form1" runat="server">
        <div>
            <input type="button" value="保存报表" name="save" onclick="save('c:\\abcd.xls')">
            <input type="button" value="导出报表" name="export" class="BUTTON" onclick="output()">
            <input type="button" value="打印报表" name="print" class="BUTTON" onclick="print()">
            <input type="button" value="打印预览" name="preview" class="BUTTON" onclick="preview()">
            <input type="button" value="报表转置" name="revers" class="BUTTON" onclick="revers()">
            <input type="button" value="报表放大" name="zoomin" class="BUTTON" onclick="zoomin()">
            <input type="button" value="报表缩小" name="zoomout" class="BUTTON" onclick="zoomout()">
            <input type="button" value=" 关  闭 " name="wclose" class="BUTTON" onclick="window.close();">
            <object classid="clsid:00460182-9E5E-11d5-B7C8-B8269041DD57" id="oframe" width="100%"  height="100%">
                <param name="_ExtentX" value="23283">
                <param name="_ExtentX" value="23283">
                <param name="_ExtentY" value="13891">
                <param name="BorderColor" value="-2147483632">
                <param name="BackColor" value="-2147483643">
                <param name="ForeColor" value="-2147483640">
                <param name="TitlebarColor" value="-2147483635">
                <param name="TitlebarTextColor" value="-2147483634">
                <param name="BorderStyle" value="1">
                <param name="Titlebar" value="0">
                <param name="Toolbars" value="0">
                <param name="Menubar" value="0">
            </object>
        </div>
    </form>
</body>

<script type="text/javascript">
	  
	   var tmfile = downloadtemplate(); //下载模板
	  
	   window.setTimeout('createReport(tmfile);',500); //创建报表
	  
</script>

</html>
