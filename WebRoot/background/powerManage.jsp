<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限管理</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- jquery-ui-bootstrap  -->
<style>
</style>

<script type="text/javascript">
	//类似于confirm的功能
	//说明callback是如果要选择是，要执行的方法
	function queren(text, callback) {
		$("#spanmessage").text(text);
		//	       $("#spanmessage").html(text);
		$("#message").dialog({
			title : "系统提示",
			modal : true,
			resizable : false,
			buttons : {
				"是" : function() {
					callback.call();//方法回调
					$(this).dialog("close");
				},
				"否" : function() {
					$(this).dialog("close");
				}
			}
		});
	}

	//只是提示信息alert
	function message(text) {
		$("#spanmessage").text(text);
		$("#message").dialog({
			title : "系统提示",
			modal : true,
			buttons : {
				"确定" : function() {
					$(this).dialog("close");
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="center">
		<div class="data_content">
			<div class="main_table" style="width: 100%;height: 100%">
				<iframe
					src="${pageContext.request.contextPath}/background/menuManage.jsp"
					width="100%" height="100%" frameborder=0 allowtransparency="true"
					style="background-color=transparent" scrolling="auto"></iframe>

			</div>
		</div>
	</div>
</body>
</html>