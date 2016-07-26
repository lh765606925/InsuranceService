<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>产品详情管理</title>
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


<script type="text/javascript">
	$(function() {
		$("#dels").click(function() {
			$('#myModal').modal({
				show : true,
				backdrop : true,
			});
		});
	});
	function insert() {
		clearForm();
		$("#modalTab input").removeAttr("readOnly");
		$("#myModal").find('.modal-title').text('产品添加');
		modifyPic();
		$(".modal-footer")
				.html(
						'<button type="button" class="btn btn-primary" onclick="save()">保存</button>'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>');
	}

	function save() {
		var url = "back/productDetailinfo_insert.action";
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return true;
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#myModal').modal('hide');
					$("#productTable").bootstrapTable('refresh');
					showDlog("保存成功", "success");
				} else {
					showDlog("保存失败", "danger");
					return;
				}
			}
		});
	}

	function update() {
		var url = "back/productDetailinfo_update.action";
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return true;
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#myModal').modal('hide');
					$("#productTable").bootstrapTable('refresh');
					showDlog("保存成功", "success");
				} else {
					showDlog("保存失败", "danger");
					return;
				}
			}
		});
	}

	$("#fm").submit(function() {
		$.post(this.attr("action"), function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$("#productTable").bootstrapTable('refresh');
			}
		});
	});

	function nameFormatter(value, row, index) {
		if (value.length > 4) {
			return '<abbr title=' + value + '>' + value.substring(0, 4)
					+ '</abbr>';
		} else {
			return value;
		}
	}
	function insuranceliabilityFormatter(value, row, index) {
		if (value.length > 25) {
			return '<abbr title=' + value + '>' + value.substring(0, 25)
					+ '</abbr>';
		} else {
			return value;
		}
	}
	
	function operateFormatter(value, row, index) {
		return [
				'<a id="look" title="详细信息" class="success" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-user"></span>', '</a>',
				'<a id="save" title="修改" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-edit"></span>',
				'</a>',
				'<a id="remove" title="删除" class="danger">',
				'<span class="glyphicon glyphicon-trash"></span>', '</a>' ]
				.join('');
	}
	window.operateEvents = {
		'click #look' : function(e, value, row, index) {
			$("#myModal").find('.modal-title').text('产品信息');
			$("#td_sex")
					.html(
							'<input type="text" id="sex" name="salesman.sex" class="form-control">');
			$(".modal-footer")
					.html(
							'<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>');
			$("#modalTab input").attr("readOnly", true);
			fillForm(row);
			fillPic(row);
		},
		'click #save' : function(e, value, row, index) {
			$("#myModal").find('.modal-title').text('产品信息修改');
			$("#modalTab input").removeAttr("readOnly");
			$("#td_sex")
					.html(
							'<div class="panel panel-default" style="height: 34px;margin-bottom:0px;padding: 6px 12px;"> <div class="radio" style="margin-left: 5px;margin-top:0;margin-bottom:0;">'
									+ '<label><input type="radio" id="sex" name="salesman.sex" value="男" checked="checked">男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
									+ '<label><input type="radio" id="sex" name="salesman.sex" value="女">女</label></div></div>');
			$(".modal-footer")
					.html(
							'<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>'
									+ '<button type="button" class="btn btn-primary" onclick="update()">保存</button>');
			$("input:radio[value=" + row.sex + "]").attr("checked", "true");
			fillForm(row);
			modifyPic();
		},
		'click #remove' : function(e, value, row, index) {
			var dialog = BootstrapDialog.confirm('确认删除吗？', 'danger', function(
					result) {
				if (result) {
					$.post("productDetailinfo_delete.action", {
						ids : row.id
					}, function(result) {
						var result = eval('(' + result + ')');
						if (result.success) {
							dialog.close();
							$("#productTable").bootstrapTable('refresh');
						}
					});
				} else {
					dialog.close();
				}
			});
			dialog.setSize(BootstrapDialog.SIZE_SMALL);
		}
	};

	function clearForm() {
		$("input#amount").val("");
		$("input#name").val("");
		$("input#price").val("");
		$("input#createDate").val("");
		$("input#id").val("");
		$("input#product_id").val("");
		$("textarea#insuranceliability").val("");
		$("input#productType_id").val("");
	}
	function fillForm(row) {
		$("input#amount").val(row.amount);
		$("input#name").val(row.name);
		$("input#insuranceType_id").val(row.insuranceType_id);
		$("input#price").val(row.price);
		$("input#createDate").val(row.createDate);
		$("input#id").val(row.id);		
		$("input#product_id").val(row.product_id);
		$("textarea#insuranceliability").val(row.insuranceliability);
	}

	function modifyPic() {
	}

	function checkIsNull(str) {
		if (str == "" || str == null) {
			return true;
		} else {
			return false;
		}
	}

</script>
</head>
<body>
	<div class="center" >
		<div class="data_content" >
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
			<div class="main_table" >
				<!-- Button trigger modal -->
				<table id="productTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/productDetailinfo_findlist.action"
					data-cache="false" data-height="450" data-click-to-select="true"  style="height:750px">
					
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-width="60">产品名称</th>
							<th data-field="insuranceliability" data-formatter="insuranceliabilityFormatter" data-width="20">责任</th>
							<th data-field="price" data-width="20">价格</th>
							<th data-field="amount" data-width="10">保额</th>
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
							<h4 class="modal-title" id="myModalLabel">产品详情添加</h4>
						</div>
						<div class="modal-body">
							<form id="fm" method="post" enctype="multipart/form-data">
								<table id="modalTab" class="table table-bordered">
									<tr>
										<td rowspan="5" align="center"
											style="vertical-align: inherit;"><img
											src="${pageContext.request.contextPath}/background/resources/images/logo.png"
											style="height: 80px;width: 120px;" alt="..."
											class="img-rounded"></td>

										<td>产品名称:</td>
										<td><input type="text" id="name" name="productDetailinfo.name"
											class="form-control"></td>
										<td>接口ID:</td>
										<td><input type="text" id="product_id"
											name="productDetailinfo.product_id" class="form-control">
											<input type="text" id="createDate" name="productDetailinfo.createDate" style="display: none;">
											<input type="text" id="id" name="productDetailinfo.id" style="display: none;">
											</td>
									</tr>

									<tr>
										<td>责任</td>
										<td colspan="4"><textarea id="insuranceliability"
												name="productDetailinfo.insuranceliability" class="form-control" rows="5"></textarea>
										</td>

									</tr>
									<tr>
										<td>价格</td>
										<td><input type="text" id="price"
											name="productDetailinfo.price" class="form-control"></td>
										<td>保额</td>
										<td><input type="text" id="amount"
											name="productDetailinfo.amount" class="form-control"></td>
									
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