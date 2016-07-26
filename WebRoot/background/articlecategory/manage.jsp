<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>栏目管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<style>
td a {
	margin-left: 5px;
	font-size: 1.2em;
}

.span_Pic {
	width: 100%;
	height: 100%;
	color: red;
}

.success {
	color: #5cb85c;
}

.success:focus,.success:hover {
	color: #449d44;
}

.danger {
	color: #d9534f;
}

.danger:focus,.danger:hover {
	color: #c9302c;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/background/articlecategory/resources/js/article.js"></script>
</head>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" onclick="insert()" class="btn btn-primary"
					data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus"></span>添加栏目
				</button>
			</div>
			<div class="main_table">
				<!-- Button trigger modal -->
				<table id="productTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/articlecategory_admin_show_list.action"
					data-cache="false" data-height="511" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-width="100">栏目名称</th>
							<th data-field="createDate" data-width="60">创建日期</th>
							<th data-field="metaKeywords" data-width="60">关键字</th>
							<th data-field="metaDescription" data-width="60">简介</th>
							<th data-field="orderList" data-width="60">排序</th>
							<th data-field="operate" data-align="center" data-width="80"
								data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:700px">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">添加栏目</h4>
						</div>
						<div class="modal-body">
							<form id="fm" method="post" enctype="multipart/form-data">
								<table id="modalTab" class="table table-bordered">
									<tr>
										<td>栏目名称:</td>
										<td><input type="text" id="name"
											name="articleCategory.name" class="form-control"></td>
									</tr>
									<tr>
										<td>栏目说明:</td>
										<td><input type="text" id="name"
											name="articleCategory.metaDescription" class="form-control"></td>
									</tr>
									<tr>
										<td>栏目关键字:</td>
										<td><input type="text" id="name"
											name="articleCategory.metaKeywords" class="form-control"></td>
									</tr>
									<tr>
										<td>排序:</td>
										<td><input type="text" id="name"
											name="articleCategory.orderList" class="form-control"></td>
									</tr>
								</table>
							</form>
						</div>
						<div class="modal-footer"></div>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>