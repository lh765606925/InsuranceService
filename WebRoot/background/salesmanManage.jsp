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
		$("#dels").click(
				function(e, value, row, index) {
					$saleTable = $('#saleTable').bootstrapTable();
					var selects = $saleTable.bootstrapTable('getSelections');
					var ids = $.map(selects, function(row) {
						return row.sid;
					});
					var dialog = BootstrapDialog.confirm('确认删除吗？', 'danger',
							function(result) {
								if (result) {
									$.post("salesman_delete.action?ids=[" + ids
											+ "]", ids, function(result) {
										var result = eval('(' + result + ')');
										if (result.success) {
											dialog.close();
											$("#saleTable").bootstrapTable(
													'refresh');
										}
									});
								} else {
									dialog.close();
								}
							});
					dialog.setSize(BootstrapDialog.SIZE_SMALL);
				});
	});

	function insert() {
		clearForm();
		$("#modalTab input").removeAttr("readOnly");
		$("#myModal").find('.modal-title').text('会员添加');
		modifyPic();
		$(".modal-footer")
				.html(
						'<button type="button" class="btn btn-primary" onclick="save()">保存</button>'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>');
	}

	function save() {
		var url = "back/salesman_insert.action";
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return true;
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#myModal').modal('hide');
					$("#saleTable").bootstrapTable('refresh');
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
				$("#saleTable").bootstrapTable('refresh');
			}
		});
	});

	function emailFormatter(value, row, index) {
		if (value.length > 16) {
			return '<abbr title='+value+'>' + value.substring(0, 16)
					+ '</abbr>';
		} else {
			return value;
		}
	}
	function nameFormatter(value, row, index) {
		if (value.length > 4) {
			return '<abbr title='+value+'>' + value.substring(0, 4) + '</abbr>';
		} else {
			return value;
		}
	}
	function idNumFormatter(value, row, index) {
		if (value.length > 10) {
			return '<abbr title='+value+'>' + value.substring(0, 10)
					+ '</abbr>';
		} else {
			return value;
		}
	}
	function nowAddrFormatter(value, row, index) {
		if (value.length > 10) {
			return '<abbr title='+value+'>' + value.substring(0, 10)
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
			$("#myModal").find('.modal-title').text('会员信息');
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
			$("#myModal").find('.modal-title').text('会员修改');
			$("#modalTab input").removeAttr("readOnly");
			$("#td_sex")
					.html(
							'<div class="panel panel-default" style="height: 34px;margin-bottom:0px;padding: 6px 12px;"> <div class="radio" style="margin-left: 5px;margin-top:0;margin-bottom:0;">'
									+ '<label><input type="radio" id="sex" name="salesman.sex" value="男" checked="checked">男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
									+ '<label><input type="radio" id="sex" name="salesman.sex" value="女">女</label></div></div>');
			$(".modal-footer")
					.html(
							'<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>'
									+ '<button type="button" class="btn btn-primary">保存</button>');
			$("input:radio[value=" + row.sex + "]").attr("checked", "true");
			fillForm(row);
			modifyPic();
		},
		'click #remove' : function(e, value, row, index) {
			var dialog = BootstrapDialog.confirm('确认删除吗？', 'danger', function(
					result) {
				if (result) {
					$.post("salesman_delete.action", {
						ids : row.sid
					}, function(result) {
						var result = eval('(' + result + ')');
						if (result.success) {
							dialog.close();
							$("#saleTable").bootstrapTable('refresh');
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
		$("input#invate").val("");
		$("input#name").val("");
		$("input#realName").val("");
		$("input#sex").val("");
		$("input#phone").val("");
		$("input#email").val("");
		$("input#nowAddr").val("");
		$("input#workAddr").val("");
		$("input#bank").val("");
		$("input#bankUser").val("");
		$("input#bankNum").val("");
		$("input#idNum").val("");
		$("input#congYe").val("");
		$("input#zhanYe").val("");
		$("span#idPic1").html(
				"<input type='file' class='btn btn-default' name='idPic'>");
		$("span#idPic2").html(
				"<input type='file' class='btn btn-default' name='idPic'>");
		$("input#congye").val("");
		$("span#cyPic1").html(
				"<input type='file' class='btn btn-default' name='cyPic'>");
		$("span#cyPic2").html(
				"<input type='file' class='btn btn-default' name='cyPic'>");
		$("input#zhanye").val("");
		$("span#zyPic1").html(
				"<input type='file' class='btn btn-default' name='zyPic'>");
		$("span#zyPic2").html(
				"<input type='file' class='btn btn-default' name='zyPic'>");
	}
	function fillForm(row) {
		$("input#invate").val(row.invate);
		$("input#name").val(row.name);
		$("input#realName").val(row.realName);
		$("input#sex").val(row.sex);
		$("input#phone").val(row.phone);
		$("input#email").val(row.email);
		$("input#nowAddr").val(row.nowAddr);
		$("input#workAddr").val(row.workAddr);
		$("input#bank").val(row.bank);
		$("input#bankUser").val(row.bankUser);
		$("input#bankNum").val(row.bankNum);
		$("input#idNum").val(row.idNum);
		$("input#congYe").val(row.congYe);
		$("input#zhanYe").val(row.zhanYe);
	}
	function fillPic(row) {
		$("span#idPic1")
				.html(
						'<img alt="照片未找到！请重新上传！" src="'+row.idPic1+'" class="span_Pic">');
		$("span#idPic2")
				.html(
						'<img alt="照片未找到！请重新上传！" src="'+row.idPic2+'" class="span_Pic">');
		$("span#cyPic1")
				.html(
						'<img alt="照片未找到！请重新上传！" src="'+row.cyPic1+'" class="span_Pic">');
		$("span#cyPic2")
				.html(
						'<img alt="照片未找到！请重新上传！" src="'+row.cyPic2+'" class="span_Pic">');
		$("span#zyPic1")
				.html(
						'<img alt="照片未找到！请重新上传！" src="'+row.zyPic1+'" class="span_Pic">');
		$("span#zyPic2")
				.html(
						'<img alt="照片未找到！请重新上传！" src="'+row.zyPic2+'" class="span_Pic">');
	}

	function modifyPic() {
		$("span#idPic1").html(
				"<input type='file' class='btn btn-default' name='idPic'>");
		$("span#idPic2").html(
				"<input type='file' class='btn btn-default' name='idPic'>");
		$("input#congye").val("");
		$("span#cyPic1").html(
				"<input type='file' class='btn btn-default' name='cyPic'>");
		$("span#cyPic2").html(
				"<input type='file' class='btn btn-default' name='cyPic'>");
		$("input#zhanye").val("");
		$("span#zyPic1").html(
				"<input type='file' class='btn btn-default' name='zyPic'>");
		$("span#zyPic2").html(
				"<input type='file' class='btn btn-default' name='zyPic'>");
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
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" onclick="insert()" class="btn btn-primary"
					data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus"></span> 会员添加
				</button>
				&nbsp;&nbsp;
				<button type="button" id="dels" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>
			<div class="main_table">
				<!-- Button trigger modal -->
				<table id="saleTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/salesman_list.action"
					data-cache="false" data-height="526" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="sid" data-width="50">ID</th>
							<th data-field="realName" data-width="80"
								data-formatter="nameFormatter">姓名</th>
							<th data-field="sex" data-align="center" data-width="50">性别</th>
							<th data-field="phone" data-width="100">手机号</th>
							<th data-field="money" data-width="50">余额</th>
							<th data-field="email" data-width="150"
								data-formatter="emailFormatter">邮箱</th>
							<th data-field="idNum" data-width="100"
								data-formatter="idNumFormatter">身份证号</th>
							<th data-field="nowAddr" data-formatter="nowAddrFormatter">现住址</th>
							<th data-field="regtime">注册时间</th>
							<th data-field="status" data-width="80">状态</th>
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
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">
							<form id="fm" method="post" enctype="multipart/form-data">
								<table id="modalTab" class="table table-bordered">
									<tr>
										<td rowspan="6" align="center"
											style="vertical-align: inherit;"><img
											src="${pageContext.request.contextPath}/background/resources/images/logo.png"
											style="height: 120px;width: 120px;" alt="..."
											class="img-rounded"></td>
										<td>邀请人：</td>
										<td><input type="text" id="invate" name="salesman.invate"
											class="form-control"></td>
									</tr>
									<tr>
										<td>昵称:</td>
										<td><input type="text" id="name" name="salesman.name"
											class="form-control"></td>
									</tr>
									<tr>
										<td>真实姓名:</td>
										<td><input type="text" id="realName"
											name="salesman.realName" class="form-control"></td>

									</tr>
									<tr>
										<td>性别：</td>
										<td><div id="td_sex">
												<div class="panel panel-default"
													style="height: 34px;margin-bottom:0px;padding: 6px 12px;">
													<div class="radio"
														style="margin-left: 5px;margin-top:0;margin-bottom:0;">
														<label><input type="radio" id="sex"
															name="salesman.sex" value="男" checked="checked">男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label><input type="radio" id="sex"
															name="salesman.sex" value="女">女</label>
													</div>
												</div>
											</div></td>
									</tr>
									<tr>
										<td>电话：</td>
										<td><input type="text" id="phone" name="salesman.phone"
											class="form-control"></td>
									</tr>
									<tr>
										<td>邮箱：</td>
										<td><input type="text" id="email" name="salesman.email"
											class="form-control"></td>
									</tr>
									<tr>
										<td>现住址：</td>
										<td colspan="2"><input type="text" id="nowAddr"
											name="salesman.nowAddr" class="form-control"></td>
									</tr>
									<tr>
										<td>工作地址：</td>
										<td colspan="2"><input type="text" id="workAddr"
											name="salesman.workAddr" class="form-control"></td>
									</tr>
									<tr>
										<td>开户银行：</td>
										<td colspan="2"><input type="text" id="bank"
											name="salesman.bank" class="form-control"></td>
									</tr>
									<tr>
										<td>开户名：</td>
										<td colspan="2"><input type="text" id="bankUser"
											name="salesman.bankUser" class="form-control"></td>
									</tr>
									<tr>
										<td>银行帐号：</td>
										<td colspan="2"><input type="text" id="bankNum"
											name="salesman.bankNum" class="form-control"></td>
									</tr>
									<tr>
										<td>港澳通行证号：</td>
										<td colspan="2"><input type="text" id="gangAo"
											name="salesman.gangAo" class="form-control"></td>
									</tr>
									<tr>
										<td>身份证号：</td>
										<td colspan="2"><input type="text" id="idNum"
											name="salesman.idNum" class="form-control"></td>
									</tr>
									<tr>
										<td rowspan="2" style="vertical-align: inherit;">身份证件照片</td>
										<td colspan="3" height="200px"><span id="idPic1"></span></td>
									</tr>
									<tr>
										<td colspan="3" height="200px"><span id="idPic2"></span></td>
									</tr>
									<tr>
										<td>从业证号：</td>
										<td colspan="2"><input type="text" id="congYe"
											class="form-control" name="salesman.congYe"></td>
									</tr>
									<tr>
										<td rowspan="2" style="vertical-align: inherit;">从业证照片</td>
										<td colspan="3" height="200px"><span id="cyPic1"></span></td>
									</tr>
									<tr>
										<td colspan="3" height="200px"><span id="cyPic2"></span></td>
									</tr>
									<tr>
										<td>展业证号：</td>
										<td colspan="2"><input type="text" id="zhanYe"
											class="form-control" name="salesman.zhanYe"></td>
									</tr>
									<tr>
										<td rowspan="2" style="vertical-align: inherit;">展业证照片</td>
										<td colspan="3" height="200px"><span id="zyPic1"></span></td>
									</tr>
									<tr>
										<td colspan="3" height="200px"><span id="zyPic2"></span></td>
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