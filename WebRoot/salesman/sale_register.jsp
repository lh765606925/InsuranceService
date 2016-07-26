<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员注册</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/salesman/favicon.ico">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/salesman/resources/css/register.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.1.3.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/ie-emulation-modes-warning.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
    

<script type="text/javascript" src="resources/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="resources/js/jquery.bgiframe.min.js"></script>
<link  href="resources/css/validate.css" rel="stylesheet" type="text/css" />
    
    
    <script type="text/javascript">
        $(function(){
           var invate = <%=request.getParameter("invate") %>;
           if(invate!=null){
        	   $("#invate").attr("readOnly",true);
        	   $("#invate").val(invate);
           }
        	
        });
       function checkExit(){
    	   var phone=$("#phone").val();
    	   var url_str="${pageContext.request.contextPath}/back/salesman_existSalesmanWithUserPhone.action";
    	   var exit = $.ajax({url: url_str,data:{phone:phone},dataType:"json",cache: false,async: false}).responseText;
    	   var result = eval('('+exit+')');
    	   if(result.exit){
    		   $("#phone").val("该手机号已被注册!");
 			   $("#phone").parent().addClass("has-error");
 			   $("#phone").focus();
 			   return false;
    	   }else{
    		   return true;
    	   }
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
	background: url(${pageContext.request.contextPath}/salesman/resources/images/loading.gif) 50% no-repeat;
	background-size:cover;
}

.loading .main {
	opacity: 0;
	opacity: 0;
}
</style>   
</head>
<body class="loading">
  <div class="main">
    <div class="container">
<form class="form-register" action="${pageContext.request.contextPath}/back/salesman_registerSalesman.action" method="post" enctype="multipart/form-data" >
        <h3 class="form-register-heading">欢迎注册建安保险会员^_^</h3><br>
<ul class="nav nav-pills" role="tablist" id="myTab">
  <li role="presentation" class="active nav-justified"><a href="#basic" role="tab" data-toggle="tab">基本信息</a></li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
  <div role="tabpanel" class="tab-pane fade in active" id="basic">
        <br><div class="form-group"><input type="text" id="invate" name="invate" class="form-control" placeholder="邀请人识别码" tip="邀请人识别码"></div>
        <div class="form-group"><input type="text" id="name" name="salesman.name" class="form-control" placeholder="昵称" reg="^.+$" tip="昵称不能为空"></div>
        <div class="form-group"><input type="text" id="realname" name="salesman.realName" class="form-control" placeholder="真实姓名" reg="^.+$" tip="真实姓名不能为空"></div>
        <div class="panel panel-default">
        <div class="radio" style="margin-left: 5px">
        <label><input type="radio" id="sex" name="salesman.sex" value="男" checked="checked">男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label><input type="radio" id="sex" name="salesman.sex" value="女">女</label>
   </div>
</div>
        <div class="form-group"><input type="tel" id="phone" name="salesman.phone" onblur="checkExit()" class="form-control" placeholder="手机号码" reg="1[0-9]{10}" tip="手机号码不能为空"></div>
        <div class="form-group"><input type="email" id="email" name="salesman.email" class="form-control" placeholder="邮箱" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="邮箱地址，如xxx@qq.com"></div>
        <div class="form-group"><input type="password" id="password" name="salesman.passWord" class="form-control" placeholder="密码" reg="^.{6,}$" tip="密码长度至少为6个字符"></div>
        <div class="form-group"><input type="password" id="repassword" class="form-control" placeholder="确认密码" reg="^.{6,}$" tip="两次密码输入要保持一致！"></div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
  </div>
</div>
      </form>

    </div> <!-- /container -->

</div>
</body>
</html>