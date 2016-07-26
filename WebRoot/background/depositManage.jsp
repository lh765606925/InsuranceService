<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员管理</title>

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
		var url = "back/product_insert.action";
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
		var url = "back/product_update.action";
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

	function emailFormatter(value, row, index) {
		if (value.length > 16) {
			return '<abbr title=' + value + '>' + value.substring(0, 16)
					+ '</abbr>';
		} else {
			return value;
		}
	}
	function nameFormatter(value, row, index) {
		if (value.length > 4) {
			return '<abbr title=' + value + '>' + value.substring(0, 4)
					+ '</abbr>';
		} else {
			return value;
		}
	}
	function idNumFormatter(value, row, index) {
		if (value.length > 10) {
			return '<abbr title=' + value + '>' + value.substring(0, 10)
					+ '</abbr>';
		} else {
			return value;
		}
	}
	function nowAddrFormatter(value, row, index) {
		if (value.length > 10) {
			return '<abbr title=' + value + '>' + value.substring(0, 10)
					+ '</abbr>';
		} else {
			return value;
		}
	}

	function operateFormatter(value, row, index) {
		return [
				'<a id="look" title="详细信息" class="success" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-user"></span>',
				'</a>',
				'<a id="save" title="修改" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-edit"></span>', '</a>',
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
					$.post("product_delete.action", {
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
		$("input#id").val("");
		$("input#name").val("");
		$("input#price").val("");
		$("input#insuranceNote").val("");
		$("input#productType_id").val("");
		$("input#insuranceDeclaration").val("");
		$("input#insuranceRules").val("");
		$("input#claimNote").val("");
		$("span#idPic2").html(
				"<input type='file' class='btn btn-default' name='idPic'>");
	}
	function fillForm(row) {
		$("input#id").val(row.id);
		$("input#name").val(row.name);
		$("input#price").val(row.price);
		$("input#insuranceNote").val(row.insuranceNote);
		$("input#productType_id").val(row.productType_id);
		$("input#insuranceDeclaration").val(row.insuranceDeclaration);
		$("input#insuranceRules").val(row.insuranceRules);
		$("input#claimNote").val(row.claimNote);
	}
	function fillPic(row) {
		$("span#idPic1")
				.html(
						'<img alt="照片未找到！请重新上传！" src="' + row.idPic1
					+ '" class="span_Pic">');
		$("span#idPic2")
				.html(
						'<img alt="照片未找到！请重新上传！" src="' + row.productImgList
					+ '" class="span_Pic">');
	}

	function modifyPic() {
		$("span#idPic2").html(
				"<input type='file' class='btn btn-default' name='idPic'>");
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
<script type="text/javascript" src="/background/resources/js/finance.js"></script>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" onclick="insert()" class="btn btn-primary"
					data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus"></span>充值
				</button>
				&nbsp;&nbsp;
				<button type="button" id="dels" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>
			<div class="main_table">
				<!-- Button trigger modal -->
				<table id="financeTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/deposit_list.action"
					data-cache="false" data-height="526" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-width="80">用户名</th>
							<th data-field="credit" data-width="20">充值金额</th>
							<th data-field="createDate" data-width="40"
								data-formatter="createDateFormatter">充值日期</th>
							<th data-field="depositType" data-width="20">充值方式</th>
							<th data-field="operate" data-align="center" data-width="80"
								data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">充值</h4>
						</div>
						<div class="modal-body">
							<form id="fm" method="post" enctype="multipart/form-data">
								<table id="modalTab" class="table table-bordered">
									<tr>
										<td rowspan="3" align="center"
											style="vertical-align: inherit;"><img
											src="${pageContext.request.contextPath}/background/resources/images/logo.png"
											style="height: 120px;width: 120px;" alt="..."
											class="img-rounded"></td>
										
										<td>充值金额:</td>
										<td><input type="text" id="invate" name="salesman.invate"
											class="form-control"></td>
									</tr>
									<tr>
										<td>充值方式:</td>
										<!-- <td><input type="text" id="name" name="salesman.name"
											class="form-control"></td> -->
										<td><input type="text" id="name" name="salesman.name"
											class="form-control"></td>
									</tr>
									<tr>
										<td>会员ID：</td>
										<td><input type="text" id="realName"
											name="salesman.realName" class="form-control"></td>

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