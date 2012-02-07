<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

<html>
	<head>
		<title>简单交叉报表</title>
	</head>

	<body>
		<div id="logo" width="100%" height="100%">
			<img src="images/blue-loading.gif"></img><span id="tip">正在加载页面...</span>
		</div>
		<div id="content" width="100%" height="100%" style="display: block">
			<iframe id="report" FRAMEBORDER="0" width="100%" height="100%" src="report1.jsp">
		</div>
		<script type="text/javascript">
		//document.frames["report"].src = "";
		//alert(1);
  	</script>
	</body>

</html>

