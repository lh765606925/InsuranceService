<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>文章管理</title>
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

<link rel="stylesheet" href="../../bootstrap/css/bootstrap.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/background/article/resources/css/style.css"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/background/article/resources/js/article.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-suggest.js"></script>
</head>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" onclick="insert()" class="btn btn-primary"
					data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus"></span>添加文章
				</button>
			</div>
			<div class="main_table">
				<!-- Button trigger modal -->
				<table id="productTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/article_admin_show_list.action"
					data-cache="false" data-height="525" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="title" data-width="150" data-formatter="titleFormatter">标题</th>
							<th data-field="articleCategory" data-width="40"data-formatter="articlecategoryFormatter">栏目</th>
							<th data-field="isTop" data-width="20">置顶</th>
							<th data-field="isRecommend" data-width="20">推荐</th>
							<th data-field="createDate" data-width="60">创建时间</th>
							<th data-field="operate" data-align="center" data-width="60"
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
							<h4 class="modal-title" id="myModalLabel">添加文章</h4>
						</div>
						<div class="modal-body">
							<form id="fm" method="post" enctype="multipart/form-data">
								<table id="modalTab" class="table table-bordered">
									<tr>
										<td>文章标题:</td>
										<td><input type="text" id="name" name="article.title"
											class="form-control"></td>
									</tr>
									<tr>
										<th>是否推荐:</th>
										<td><label><input type="radio"
												name="article.isRecommend" value="true" />是</label> <label><input
												type="radio" name="article.isRecommend" value="false" />否</label></td>
									</tr>
									<tr>
										<th>是否置顶:</th>
										<td><label><input type="radio"
												name="article.isTop" id="isTop" value="true" />是</label> <label><input
												type="radio" name="article.isTop" id="noTop" value="false" />否</label>
										</td>
									</tr>

									<tr>
										<th>文章栏目:</th>
										<td>
											<!-- btn-group start-->
											<div class="col-lg-6">
												<div class="input-group">
													<input type="text" class="form-control"
														id="articlecategory">
													<div class="input-group-btn">
														<button type="button"
															class="btn btn-default dropdown-toggle"
															data-toggle="dropdown">
															<span class="caret"></span>
														</button>
														<ul class="dropdown-menu dropdown-menu-left" role="menu">
														</ul>
													</div>
												</div>
												<!-- /btn-group -->
											</div> <input id="articleCategory_id"
											name="article.articleCategory_id" style="display: none;">
										</td>
									</tr>

									<tr>
										<th>文章图片:</th>
										<td><span id="head_Pic"></span></td>
									</tr>
									<tr>
										<td>文章内容</td>
										<td colspan="4"><textarea id="content"
												name="article.content" class="form-control" rows="12"></textarea>
										</td>

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

	<script type="text/javascript">
		$("#articlecategory").bsSuggest({
			url : "back/articlecategory_admin_show_jsp_list.action",
			effectiveFields : ["name","id" ],
			searchFields : [],
			//indexKey : 2, // 每组数据的第几个数据，作为input输入框的内容
			effectiveFieldsAlias : {
				name : "栏目名称",
				id:"ID"
			},
			showHeader : true,
			idField : "id",
			keyField : "name",
			listAlign : "left",
			listStyle : {
				"left": "-306px",
				"padding-top" : 0,
				"max-height" : "375px",
				"max-width" : "800px",
				"overflow" : "auto",
				"width" : "auto",
				"transition" : "0.5s",
				"-webkit-transition" : "0.5s",
				"-moz-transition" : "0.5s",
				"-o-transition" : "0.5s"
			},
		}).on('onDataRequestSuccess', function(e, result) {
			console.log('onDataRequestSuccess: ', result);
		}).on('onSetSelectValue', function(e, keyword) {
			$("input#articleCategory_id").val(keyword.id);
			//添加产品行
			console.log('onSetSelectValue: ', keyword);
		}).on('onUnsetSelectValue', function(e) {
			console.log("onUnsetSelectValue");
		});
	</script>
</body>
</html>