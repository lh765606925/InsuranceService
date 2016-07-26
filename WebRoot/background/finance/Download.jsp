<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>财务管理</title>

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
</head>
<script type="text/javascript">
	$(function() {
		$("#dels").click(function() {
			$('#myModal').modal({
				show : true,
				backdrop : true,
			});
		});
	});

	/* 业务员添加 */
	function insert() {
		clearForm();
		$("#modalTab input").removeAttr("readOnly");
		$("#myModal").find('.modal-title').text('业务员添加');
		modifyPic();
		$(".modal-footer")
				.html(
						'<button type="button" class="btn btn-primary" onclick="save()">保存</button>'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>');
	}
	/* 充值 */
	function deposit() {
		clearForm();
		$("#modalTab input").removeAttr("readOnly");
		$("#myModal").find('.modal-title').text('充值');
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
			return '<abbr title=' + value + '>' + value.substring(0, 16)
					+ '</abbr>';
		} else {
			return value;
		}
	}

	function nameFormatter(value, row, index) {
		return '<a title=' + value + ' href='+row.link+'>' + value + '</a>';
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
	function createDateFormatter(value, row, index) {
		if (value.length > 10) {
			return '<abbr title=' + value + '>' + value.substring(0, 10)
					+ '</abbr>';
		} else {
			return value;
		}
	}
	/**
	 * 操作
	 */
	function operateFormatter(value, row, index) {
		return [
				'<a id="look" title="详细信息" class="success" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-user"></span>',
				'</a>',
				'<a id="save" title="充值" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-edit"></span>', '</a>',
				'<a id="remove" title="删除" class="danger">',
				'<span class="glyphicon glyphicon-trash"></span>', '</a>' ]
				.join('');
	}
	window.operateEvents = {
		'click #look' : function(e, value, row, index) {
			$("#myModal").find('.modal-title').text('业务员信息');
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
			$("#myModal").find('.modal-title').text('余额充值');
			$("#modalTab input").removeAttr("readOnly");
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
		$("input#money").val("");
		$("input#record").val("");
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
		$("input#money").val(row.money);
		$("input#record").val(row.record);
	}

	function checkIsNull(str) {
		if (str == "" || str == null) {
			return true;
		} else {
			return false;
		}
	}
</script>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" onclick="insert()" class="btn btn-primary"
					data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus"></span> 新增业务
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
					data-url="${pageContext.request.contextPath}/back/download_list.action"
					data-cache="false" data-height="450" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-width="80"
								data-formatter="nameFormatter">文件名</th>
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