<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8 ">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>管理员登陆系统</title>
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/background/resources/css/style.css"
	type="text/css" media="screen">
</head>
<script type="text/javascript">
	function checkForm() {
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("passWord").value;
		if (userName == null || userName == "") {
			document.getElementById("error").innerHTML = "用户名不能为空！";
			return false;
		}
		if (password == null || password == "") {
			document.getElementById("error").innerHTML = "密码不能为空！";
			return false;
		}
		return true;
	}
</script>
<body id="login_page">
	<div id="login-container">
		<div id="login-title">
			<img alt=""
				src="${pageContext.request.contextPath}/background/resources/images/logojnb.png">
			<!-- End #login-title -->
		</div>
		<div id="login">
			<div id="login-content">
				<form
					action="${pageContext.request.contextPath}/back/manager_login.action"
					method="post" onsubmit="return checkForm()">
					<input class="" type="text" id="userName" name="manager.userName"
						value="${manager.userName}"> <br />
					<div class="clear"></div>
					<input class="" type="password" id="passWord"
						name="manager.passWord" value="${manager.passWord}"> <br />
					<div class="clear"></div>
					<input id="remember" name="remember" type="checkbox" value="" /> 
					<span id="remember_text">记住密码</span> 
						<input id="loginbutton"
						class="input_out" type="submit"
						onmousemove="this.className='input_move'"
						onmouseout="this.className='input_out'" value="登录"> <span
						id="error">${error}</span> <span id="welcome">欢迎使用<%=application.getInitParameter("project_name")%>后台!</span>
				</form>
			</div>
			<!-- End #login-container -->
		</div>
	</div>

</body>
</html>