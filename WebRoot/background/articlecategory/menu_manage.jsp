<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var url;

	$(function() {
		$('#treeGrid')
				.treegrid(
						{
							url : '${pageContext.request.contextPath}/back/articlecategory_treeGrid.action',
							onLoadSuccess : function() {
								$("#treeGrid").treegrid('expandAll');
							}
						});
	});

	function formatIconCls(value, row, index) {
		return "<div class="+value+">&nbsp;</div>";
	}

	function openAuthAddDialog() {
		var node = $('#treeGrid').treegrid('getSelected');
		if (node == null) {
			$.messager.alert('系统提示', '请选择一个父菜单节点！');
			return;
		}
		$("#dlg").dialog("open").dialog("setTitle", "添加菜单");
		url = "${pageContext.request.contextPath}/back/power_addPower.action?power.parentId="
				+ node.pid;
	}

	function openAuthModifyDialog() {
		var node = $('#treeGrid').treegrid('getSelected');
		if (node == null) {
			$.messager.alert('系统提示', '请选择一个要修改的菜单！');
			return;
		}
		$("#dlg").dialog("open").dialog("setTitle", "修改菜单");
		$("#powerName").val(node.powerName);
		$("#powerDesc").val(node.powerDesc);
		$("#iconCls").val(node.iconCls);
		url = "${pageContext.request.contextPath}/back/power_updatePower.action?power.pid="
				+ node.pid + "&power.parentId=" + node.parentId;
	}

	function deleteAuth() {
		var node = $("#treeGrid").treegrid('getSelected');
		if (node == null) {
			$.messager.alert('系统提示', '请选择一个要删除的菜单节点！');
			return;
		}
		$.messager.confirm("系统提示", "您确认要删除这个菜单节点吗?", function(r) {
			if (r) {
				$.post(
						"${pageContext.request.contextPath}/back/power_delPower.action?power.pid="
								+ node.pid, function(result) {
							if (result.success) {
								$.messager.alert('系统提示', "您已成功删除这个菜单节点！");
								$("#treeGrid").treegrid("reload");
							} else {
								$.messager.alert('系统提示', result.errorMsg);
							}
						}, "json");
			}
		});
	}

	function saveAuth() {
		$("#fm").form(
				"submit",
				{
					url : url,
					onSubmit : function() {
						return $(this).form("validate");
					},
					success : function(result) {
						var result = eval('(' + result + ')');
						if (result.success) {
							$.messager.alert('系统提示', '保存成功');
							closeAuthDialog();
							$("#treeGrid").treegrid("reload");
						} else {
							$.messager.alert('系统提示', "<font color=red>"
									+ result.errorMsg + "</font>");
							return;
						}
					}
				});
	}

	function closeAuthDialog() {
		$("#dlg").dialog("close");
		$("#fm").form('clear');
		$("#iconCls").val("icon-item");
	}

	//页面加载时的 Loading 效果
	$(window).load(function() {
		window.setTimeout(function() {
			$('body').removeClass('loading');
		}, 2000);
	});
</script>

<style type="text/css">
.loading {
	background:
		url(${pageContext.request.contextPath}/background/resources/images/big_load.gif)
		50% no-repeat;
}

.loading .main {
	opacity: 0;
	opacity: 0;
}
</style>
</head>
<body class="loading" style="margin: 1px;">
	<div class="main">
		<table id="treeGrid" class="easyui-treegrid" toolbar="#tb"
			data-options="idField:'typeid',treeField:'name',fit:true,fitColumns:true,rownumbers:true">
			<thead>
				<tr>
					<th field="typeid" width="30" align="center">编号</th>
					<th field="name" width="80">名称</th>
					<th field="typeid" width="100" align="center">备注</th>
				</tr>
			</thead>
		</table>
		<div id="tb">
			<div>
				<a href="javascript:openAuthAddDialog()" class="easyui-linkbutton"
					iconCls="icon-add" plain="true">添加</a> <a
					href="javascript:openAuthModifyDialog()" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true">修改</a> <a
					href="javascript:deleteAuth()" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true">删除</a>
			</div>
		</div>

		<div id="dlg" class="easyui-dialog"
			style="width: 570px;height: 350px;padding: 10px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post">
				<table cellspacing="5px;">
					<tr>
						<td>名称：</td>
						<td><input type="text" id="powerName" name="producttype.name"
							class="easyui-validatebox" required="true" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>图标：</td>
						<td><input type="text" id="iconCls"
							name="producttype.iconCls" value="icon-item"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td valign="top">备注：</td>
						<td colspan="4"><textarea rows="7" cols="50"
								name="producttype.powerDesc" id="powerDesc"></textarea></td>
					</tr>
				</table>
			</form>
		</div>

		<div id="dlg-buttons">
			<a href="javascript:saveAuth()" class="easyui-linkbutton"
				iconCls="icon-ok">保存</a> <a href="javascript:closeAuthDialog()"
				class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
		</div>
	</div>
</body>
</html>