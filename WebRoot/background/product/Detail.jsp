<%@page import="com.insurance.model.Product"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	
<%%>
	
</script>
<head>

<title>产品详情</title>

<meta name="viewport"
	content="width=device-width, height=device-height,initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<link rel="stylesheet" href="../resources/css/common.css">

<link rel="stylesheet"
	href="../resources/css/validationEngine.jquery.css">

<script type="text/javascript" src="../resources/js/jquery-1.10.1.js"></script>

<script src="../resources/js/hkdetail.js"></script>

<script src="../resources/js/common.js"></script>
<%
	//获取新闻信息集合，newList是从后台返回来的集合变量
	Product pro = (Product) request.getAttribute("product");
%>
</head>

<body>

	<div class="toolbar">


		<a href="javascript:history.back();" class="btn back pull-left">返回</a>

		产品详情 <a href="../index.html" class="btn pull-right">首页</a>

	</div>

	<div id="infotit" class="subTitle ">投保须知</div>

	<div id="infocont" class="subContent">
		<%=pro.getInsuranceNote()%>
	</div>

	<div id="statetit" class="subTitle ">投保声明书</div>

	<div id="statecont" class="subContent"></div>

	<div id="ruletit" class="subTitle ">投保规则</div>

	<div id="rulecont" class="subContent"></div>

	<div id="ruletit" class="subTitle ">理赔须知</div>

	<div id="rulecont" class="subContent"></div>

</body>



</html>