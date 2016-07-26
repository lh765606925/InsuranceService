<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>head</title>
<script type="text/javascript">
	function setDateTime() {
		var date = new Date();
		var day = date.getDay();
		var week;
		switch (day) {
		case 0:
			week = "星期日";
			break;
		case 1:
			week = "星期一";
			break;
		case 2:
			week = "星期二";
			break;
		case 3:
			week = "星期三";
			break;
		case 4:
			week = "星期四";
			break;
		case 5:
			week = "星期五";
			break;
		case 6:
			week = "星期六";
			break;
		}
		var today = date.getFullYear() + "年" + (date.getMonth() + 1) + "月"
				+ date.getDate() + "日  " + week + " " + date.getHours() + ":"
				+ date.getMinutes() + ":" + date.getSeconds();
		document.getElementById("today").innerHTML = today;
	}
	window.setInterval("setDateTime()", 1000);

	function logout() {
		var dialog = BootstrapDialog
				.confirm(
						'您确认退出系统吗？',
						'danger',
						function(result) {
							if (result) {
								window.location.href = "${pageContext.request.contextPath}/back/manager_logout.action";
							} else {
								dialog.close();
							}
						});
		dialog.setSize(BootstrapDialog.SIZE_SMALL);
	}
</script>
</head>
<body>
	<div class="head">
		<div class="head-top">
			<div class="h_logo">
				<img alt=""
					src="${pageContext.request.contextPath}/background/resources/images/logo.png">
			</div>
			<div class="h_nav_fenge">
				<img
					src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
			</div>
			<span class="h_title"><%=application.getInitParameter("project_name")%>后台管理系统</span>
			<div class="h_right">
				<span>欢迎管理员：</span><font color="red">${currentManager.userName
						}</font><font
					id="today" class="currentTime"></font>
			</div>
		</div>
	</div>
	<div class="head-down">
		<div class="h_nav">
			<a href="${pageContext.request.contextPath}/background/main.jsp"
				id="a1" class="h_nav_a"><span>主页管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		<div class="h_nav">
			<a
				href="${pageContext.request.contextPath}/background/main.jsp?leftPage=/background/common/power_left.jsp"
				id="a2" class="h_nav_a"><span>权限管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		<div class="h_nav">
			<a
				href="${pageContext.request.contextPath}/background/main.jsp?leftPage=/background/common/manager_left.jsp"
				id="a3" class="h_nav_a"><span>管理员管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		<div class="h_nav">
			<a
				href="${pageContext.request.contextPath}/background/main.jsp?leftPage=/background/common/role_left.jsp"
				id="a5" class="h_nav_a"><span>角色管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		<div class="h_nav">
			<a
				href="${pageContext.request.contextPath}/background/main.jsp?leftPage=/background/common/salesman_left.jsp"
				id="a10" class="h_nav_a"><span>会员管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		<div class="h_nav">
			<a
				href="${pageContext.request.contextPath}/background/main.jsp?leftPage=/background/product/left.jsp"
				id="a4" class="h_nav_a"><span>产品管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		<div class="h_nav">
			<a
				href="${pageContext.request.contextPath}/background/main.jsp?leftPage=/background/common/finance_left.jsp"
				id="a6" class="h_nav_a"><span>财务管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		<div class="h_nav">
			<a
				href="${pageContext.request.contextPath}/background/main.jsp?leftPage=/background/common/policymanage_left.jsp"
				id="a7" class="h_nav_a"><span>保单管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		
		<div class="h_nav">
			<a
				href="${pageContext.request.contextPath}/background/main.jsp?leftPage=/background/article/left.jsp"
				id="a8" class="h_nav_a"><span>资讯管理</span> </a>
		</div>
		<div class="h_nav_fenge">
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge.png" />
		</div>
		<div class="h_nav">
			<a href="javascript:logout()" id="a9" class="h_nav_a"><span>安全退出</span>
			</a>
		</div>
	</div>
</body>
</html>