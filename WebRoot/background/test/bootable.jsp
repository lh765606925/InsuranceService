<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试bootstrap-table</title>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.1.3.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-table.css" rel="stylesheet">
<script>
$(function(){
	$("#sbm").click(function(){
		document.getElementById("form1").checkValidity();
	});
});
function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="javascript:void(0)" title="Like">',
            '<i class="glyphicon glyphicon-heart"></i>',
        '</a>',
        '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
            '<i class="glyphicon glyphicon-edit"></i>',
        '</a>',
        '<a class="remove ml10" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
</script>
</head>
<body>
<table data-toggle="table" 
       data-side-pagination="server"
       data-pagination="true"
       data-page-list="[5, 10, 20, 50, 100, 200]"
       data-show-refresh="true" 
       data-search="true" 
       data-show-toggle="true"
       data-url="${pageContext.request.contextPath}/back/role_list.action" 
       data-cache="false" 
       data-height="500">
    <thead>
        <tr>
            <th data-field="roleId">Item ID</th>
            <th data-field="roleName">Item Name</th>
            <th data-field="powerIds">Item Price</th>
            <th data-field="roleDesc">Item Price</th>
            <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
        </tr>
    </thead>
</table>
</body>
</html>