<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试bootstrap-modal</title>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrapQ.js"></script>

<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-dialog.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-dialog.css" rel="stylesheet">

<script type="text/javascript">
 $(function(){
	// 普通tree
	 $('#treepanel').bstree({url:'${pageContext.request.contextPath}/back/power_treeGrid.action',
		 checkbox:true});
 });
</script>

</head>
<body>
<div style="width: 300px;">
<p id="treepanel"></p>
</div>
 
</body>
</html>