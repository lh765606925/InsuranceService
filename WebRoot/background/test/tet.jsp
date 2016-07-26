<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>业务员管理1</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- jquery-ui-bootstrap  -->
<style>
td a {
   margin-left: 5px;
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
</style>

</head>
<body>
	<div class="center">
	
			<div class="main_table"> 
<!-- <table id="managerTable" class="table-condensed" 
data-side-pagination="server"
data-pagination="true"
data-page-list="[5, 10, 20, 50, 100, 200]"
data-toggle="table" 
data-show-refresh="true" 
data-search="true" 
data-show-toggle="true" 
data-toolbar="#custom-toolbar"
data-url="${pageContext.request.contextPath}/back/manager_list.action"
data-cache="false" 
data-height="450" 
data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="mid" data-width="50">ID</th>
							<th data-field="userName" data-width="80">用户名</th>
							<th data-field="date">上次登录时间</th>
							<th data-field="ip" >上次登录IP</th>
							<th data-field="role" data-formatter="roleFormatter">角色</th>
							<th data-field="operate" data-align="center" data-width="80" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>-->
				<table id="managerTable" class="table-condensed" 
data-side-pagination="server"
data-pagination="true"
data-page-list="[5, 10, 20, 50, 100, 200]"
data-toggle="table" 
data-show-refresh="true" 
data-search="true" 
data-show-toggle="true" 
data-toolbar="#custom-toolbar"
data-url="${pageContext.request.contextPath}/back/finance_list.action"
data-cache="false" 
data-height="450" 
data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="name" data-width="80"
								data-formatter="nameFormatter">昵1称</th>
							<th data-field="phone" data-width="100">手机号</th>
							<!-- <th data-field="idNum" data-width="100"
								data-formatter="idNumFormatter">身份证号</th> -->
							<th data-field="money" data-width="80">账户余额</th></tr>
					</thead>
				</table>
			<!-- 
				<table id="financeTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/finance_list.action"
					data-cache="false" data-height="450" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="sid" data-width="50">ID</th>
							<th data-field="name" data-width="80"
								data-formatter="nameFormatter">昵称</th>
							<th data-field="phone" data-width="100">手机号</th>
							 <th data-field="idNum" data-width="100"
								data-formatter="idNumFormatter">身份证号</th> 
							<th data-field="money" data-width="80">账户余额</th>
							<th data-field="record" data-width="80">付款记录</th>
							<th data-field="operate" data-align="center" data-width="80"
								data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table> -->	
				
				
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
                                 <label for="name">用户名</label>
                                     <input type="text" class="form-control" id="name" name="manager.userName" placeholder="用户名"  required >
                               </div>
							  <div class="form-group">
                                 <label for="passWord">密码</label>
                                     <input type="text" class="form-control" id="passWord" name="manager.passWord" placeholder="密码" required>
                               </div>
							  <div class="form-group">
                                 <label for="passWord">角色</label>
                                    <select class="form-control" id="mySelect" name="manager.role.roleId">
                                     <option>请选择角色...</option>

                                     </select>
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