<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>left</title>
	</head>
	<body>
			<div class="left_top">
				<img class="left_top_"
					src="${pageContext.request.contextPath}/background/resources/images/san_left.png" />
				<span class="nav_name">角色管理</span>
				<img class="left_top_"
					src="${pageContext.request.contextPath}/background/resources/images/san_right.png" />
			</div>
			<img
				src="${pageContext.request.contextPath}/background/resources/images/fenge_.png" />
			<div class="left_down">
				<div class="nav_son">
					<a href="#" id="son1" class="nav_son_name"><span>首&nbsp;&nbsp;&nbsp;&nbsp;页</span>
					</a>
				</div>
				<div class="nav_son">
					<a href="${pageContext.request.contextPath}/back/role_showRole.action" id="son2" class="nav_son_name"><span>角色浏览</span></a>
				</div>				
			</div>

	</body>
</html>