<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>版本管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/background/version/resources/css/style.css"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/background/version/resources/js/version.js"></script>
</head>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" onclick="insert()" class="btn btn-primary"
					data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus"></span>产品添加
				</button>
				&nbsp;&nbsp;
				<button type="button" id="dels" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>
			<div class="main_table">
				<!-- Button trigger modal -->
				<table id="productTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/version_admin_show_list.action"
					data-cache="false" data-height="450" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-width="100">公司名称</th>
							<th data-field="createDate" data-width="60">创建日期</th>
							<th data-field="intefaceName" data-width="60">接口</th>
							<th data-field="operate" data-align="center" data-width="80"
								data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:1000px">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">产品添加</h4>
						</div>
						<div class="modal-body">
							<form id="fm" method="post" enctype="multipart/form-data">
								<table id="modalTab" class="table table-bordered">
									<tr>
										<td rowspan="2" align="center"
											style="vertical-align: inherit;"><img
											src="${pageContext.request.contextPath}/background/resources/images/logo.png"
											style="height: 80px;width: 120px;" alt="..."
											class="img-rounded"></td>

										<td>产品名称:</td>
										<td><input type="text" id="name" name="product.name"
											class="form-control"></td>
										<td>接口ID:</td>
										<td><input type="text" id="insuranceType_id"
											name="product.insuranceType_id" class="form-control"></td>
									</tr>
									<tr>
										<td>起始价格（￥：单位元）</td>
										<td><input type="text" id="price" name="product.price"
											class="form-control"></td>
										<td>保额（￥：单位元）</td>
										<td><input type="text" id="price" name="product.amount"
											class="form-control"></td>
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