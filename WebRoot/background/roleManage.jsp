<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
td a {
   margin-left: 10px;
   font-size:1.2em;
}
.success {
  color:#5cb85c;
}
.success:focus, .success:hover {
  color:#449d44;
}
.danger {
  color:#d9534f;
}
.danger:focus, .danger:hover {
  color:#c9302c;
}

.fixed-table-loading{
  background: url(${pageContext.request.contextPath}/background/resources/images/big_load.gif) 50% no-repeat;
  background-size:cover; 
}
#roleTable tbody{
  font-weight: 600;
}
</style>
<script>
var url;
function fillForm(row){
	$("#name").val(row.roleName);
	$("#powers").val(row.powerIds);
	$("#desc").val(row.roleDesc);
}

function clearForm(){
	$("#name").val("");
	$("#powers").val("");
	$("#desc").val("");
}

function add(){
	clearForm();
	$("#myModal").modal('show');
	$("#myModal").find('.modal-title').text('角色添加');
	url="${pageContext.request.contextPath}/back/role_add.action";
}

function save(){
	$("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			return true;
		},
		success:function(result){
			var result=eval('('+result+')');
			if(result.success){
		    	$('#myModal').modal('hide');
				$("#roleTable").bootstrapTable('refresh');
				showDlog("保存成功", "success");
			}else{
				showDlog("保存失败", "danger");
				return;
			}
		}
	});
}
function operateFormatter(value, row, index) {
    return [
			'<a id="modify" type="button" title="修改" data-toggle="modal" data-target="#myModal">',
			'<span class="glyphicon glyphicon-edit"></span>',
			'</a>',
			'<a id="remove" type="button" title="删除" class="danger">',
			'<span class="glyphicon glyphicon-trash"></span>',
			'</a>'
    ].join('');
}

window.operateEvents = {
        'click #modify': function (e, value, row, index) {
        	$("#myModal").find('.modal-title').text('角色修改');	
        	fillForm(row);
        	url="${pageContext.request.contextPath}/back/role_update.action";
        },
        'click #remove': function (e, value, row, index) {
        	var dialog = BootstrapDialog.confirm('确认删除这条数据吗？','danger', function(result){
                if(result) {
                   $.post("role_delete.action",{ids:row.roleId},function(result){
                	   var result=eval('('+result+')');
                	   if(result.success){
                		   dialog.close();
                		   $("#roleTable").bootstrapTable('refresh');
                		 }
                   });
                }else {
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
             <span class="glyphicon glyphicon-plus"></span> 角色添加
             </button>&nbsp;&nbsp;
		     <button type="button" onclick="deles()" class="btn btn-danger">
             <span class="glyphicon glyphicon-trash"></span> 批量删除
             </button>
		  </div>
			<div class="main_table" style="width: 100%;height: 100%">
   <table id="roleTable" class="table-condensed" 
       data-toggle="table" 
       data-pagination="true"
       data-page-list="[5, 10, 20, 50, 100, 200]"
       data-show-refresh="true" data-search="true" data-show-toggle="true"
       data-url="${pageContext.request.contextPath}/back/role_list.action" data-toolbar="#custom-toolbar"
       data-cache="false" data-height="450" data-click-to-select="true">
    <thead>
        <tr style="font-size: 1.2em;">
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="roleId">角色ID</th>
            <th data-field="roleName">角色名称</th>
            <th data-field="powerIds">角色权限</th>
            <th data-field="roleDesc">角色描述</th>
            <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
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
                                 <label for="name">角色名称</label>
                                     <input type="text" class="form-control" id="name" name="role.roleName" placeholder=""  required>
                               </div>
							  <div class="form-group">
                                 <label for="passWord">角色权限</label>
                                     <input type="text" class="form-control" id="powers" name="role.powerIds" placeholder="" required>
                               </div>
							  <div class="form-group">
                                 <label for="passWord">角色描述</label>
                                    <textarea id="desc" name="role.roleDesc" class="form-control" rows="3"></textarea>
                               </div>
							</form>
						</div>
						<div class="modal-footer">
                            <button type="button" class="btn btn-primary" onclick="save()">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>