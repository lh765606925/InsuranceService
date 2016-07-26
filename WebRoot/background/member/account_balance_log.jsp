<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>账户日志管理</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/background/resources/css/common/base-style.css" />
<script
	src="${pageContext.request.contextPath}/background/resources/js/base-js.js"></script>
<script
	src="${pageContext.request.contextPath}/background/resources/js/member/account_balance_log.js"></script>
</head>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" >
					<span class="glyphicon "></span>会员余额日志
				</button>
			</div>
			<div class="main_table">
				<!-- Button trigger modal -->
				<table id="productTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/accountbalancelog_list.action"
					data-cache="false" data-height="512" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true" data-width="20"></th>
							<th data-field="sid" data-width="50">SID</th>
							<th data-field="salesman" data-width="80"
								data-formatter="nameFormatter">用户名称</th>
							<th data-field="createDate" data-width="150">时间</th>
							<th data-field="type" data-align="center" data-width="50" data-formatter="typeFormatter">日志类型</th>
							<th data-field="operator" data-width="80">操作员</th>
							<th data-field="amount" data-width="80">金额</th>
							<th data-field="info" data-width="100">日志信息</th>
							<th data-field="operate" data-align="center" data-width="80"
								data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>

		</div>
	</div>

</body>

</html>