<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员管理</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- jquery-ui-bootstrap  -->
<style>
td a {
	margin-left: 5px;
	font-size: 1.2em;
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
	var url;

	function save() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return true;
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#myModal').modal('hide');
					$("#managerTable").bootstrapTable('refresh');
					showDlog("保存成功", "success");
				} else {
					showDlog("保存失败", "danger");
					return;
				}
			}
		});
	}

	function createSelect() {
		$.getJSON("${pageContext.request.contextPath}/back/role_list.action",
				function(data) {
					$.each(data, function(i, item) {
						$("<option></option").attr("title", item.roleDesc).val(
								item.roleId).text(item.roleName).appendTo(
								$("#mySelect"));
					});
				});
	}

	function clearForm() {
		$("#name").val("");
		$("#passWord").val("");
	}

	function fillForm(row) {
		$("#name").val(row.userName);
		// 		$("#passWord").val(row.passWord);
	}

	function add() {
		clearForm();
		createSelect();
		url = "${pageContext.request.contextPath}/back/manager_add.action";
		$("#myModal").find('.modal-title').text('管理员添加');
		$("#myModal").modal('show');
	}

	function roleFormatter(value, row, index) {
		return row.role.roleName;
	}

	function operateFormatter(value, row, index) {
		return [
				'<a id="modify" title="修改" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-edit"></span>', '</a>',
				'<a id="remove" title="删除" class="danger">',
				'<span class="glyphicon glyphicon-trash"></span>', '</a>' ]
				.join('');
	}
	window.operateEvents = {
		'click #modify' : function(e, value, row, index) {
			$("#myModal").find('.modal-title').text('管理员修改');
			fillForm(row);
			createSelect();
			url = "${pageContext.request.contextPath}/back/manager_update.action";
		},
		'click #remove' : function(e, value, row, index) {
			var dialog = BootstrapDialog.confirm('确认删除这条数据吗？', 'danger',
					function(result) {
						if (result) {
							$.post("manager_delete.action", {
								ids : row.mid
							}, function(result) {
								var result = eval('(' + result + ')');
								if (result.success) {
									dialog.close();
									$("#managerTable")
											.bootstrapTable('refresh');
								}
							});
						} else {
							dialog.close();
						}
					});
			dialog.setSize(BootstrapDialog.SIZE_SMALL);
		}
	};
</script>
</head>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" onclick="add()" class="btn btn-primary">
					<span class="glyphicon glyphicon-plus"></span> 管理员添加
				</button>
				&nbsp;&nbsp;
				<button type="button" onclick="deles()" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>
			<div class="main_table">
				<table id="managerTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/manager_list.action"
					data-cache="false" data-height="512" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="mid" data-width="50">ID</th>
							<th data-field="userName" data-width="80">用户名</th>
							<th data-field="date">上次登录时间</th>
							<th data-field="ip">上次登录IP</th>
							<th data-field="role" data-formatter="roleFormatter">角色</th>
							<th data-field="operate" data-align="center" data-width="80"
								data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog  modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">
							<form id="fm" method="post">
								<div class="form-group">
									<label for="name">用户名</label> <input type="text"
										class="form-control" id="name" name="manager.userName"
										placeholder="用户名" required>
								</div>
								<div class="form-group">
									<label for="passWord">密码</label> <input type="text"
										class="form-control" id="passWord" name="manager.passWord"
										placeholder="密码" required>
								</div>
								<div class="form-group">
									<label for="passWord">角色</label> <select class="form-control"
										id="mySelect" name="manager.role.roleId">
										<option>请选择角色...</option>

									</select>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" onclick="save()">保存</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>