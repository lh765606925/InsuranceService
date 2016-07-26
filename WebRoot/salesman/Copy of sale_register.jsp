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

    
    </script>
    
</head>
<body>
    <div class="container">
<form class="form-register" action="${pageContext.request.contextPath}/back/salesman_registerSalesman.action" method="post" enctype="multipart/form-data" >
        <h2 class="form-register-heading">欢迎注册^_^</h2><br>
<ul class="nav nav-pills" role="tablist" id="myTab">
  <li role="presentation" class="active"><a href="#basic" role="tab" data-toggle="tab">基本信息</a></li>
  <li role="presentation"><a href="#finance" role="tab" data-toggle="tab">财务信息</a></li>
  <li role="presentation"><a href="#paperwork" role="tab" data-toggle="tab">证件信息</a></li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
  <div role="tabpanel" class="tab-pane fade in active" id="basic">
        <br><div class="form-group"><input type="text" id="invate" name="invate" class="form-control" placeholder="邀请人识别码"></div>
        <div class="form-group"><input type="text" id="name" name="salesman.name" class="form-control" placeholder="昵称" reg="^.+$" tip="昵称不能为空"></div>
        <div class="form-group"><input type="text" id="realname" name="salesman.realName" class="form-control" placeholder="真实姓名" reg="^.+$" tip="真实姓名不能为空"></div>
        <div class="panel panel-default">
        <div class="radio" style="margin-left: 5px">
        <label><input type="radio" id="sex" name="salesman.sex" value="男" checked="checked">男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label><input type="radio" id="sex" name="salesman.sex" value="女">女</label>
   </div>
</div>
        <div class="form-group"><input type="tel" id="phone" name="salesman.phone" onblur="checkExit()" class="form-control" placeholder="手机号码" reg="^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$" tip="手机号码不能为空"></div>
        <div class="form-group"><input type="email" id="email" name="salesman.email" class="form-control" placeholder="邮箱" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="邮箱地址，如xxx@qq.com"></div>
        <div class="form-group"><input type="password" id="password" name="salesman.passWord" class="form-control" placeholder="密码" reg="^.{6,}$" tip="密码长度至少为6个字符"></div>
        <div class="form-group"><input type="password" id="repassword" class="form-control" placeholder="确认密码" reg="^.{6,}$" tip="两次密码输入要保持一致！"></div>
        <div class="form-group"><input type="text" id="nowaddr" name="salesman.nowAddr" class="form-control" placeholder="现住址" reg="^.+$" tip="现住址不能为空"></div>
        <div class="form-group"><input type="text" id="workaddr" name="salesman.workAddr" class="form-control" placeholder="工作地址" reg="^.+$" tip="工作地址不能为空"></div>
        <button class="btn btn-lg btn-primary btn-block" type="button" 
         id="basicNex">下一步</button>
  </div>
  <div role="tabpanel" class="tab-pane fade " id="finance">
   <br><div class="form-group"><input type="text" id="bank" name="salesman.bank" class="form-control" placeholder="开户银行" reg="^.+$" tip="开户银行不能为空"></div>
   <div class="form-group"><input type="text" id="bankuser" name="salesman.bankUser" class="form-control" placeholder="开户名" reg="^.+$" tip="开户名不能为空"></div>
   <div class="form-group"><input type="text" id="banknum" name="salesman.bankNum" class="form-control" placeholder="银行帐号" reg="^.+$" tip="银行帐号不能为空"></div>
  <br><button class="btn btn-lg btn-default" type="button" style="width: 40%;float: left;"
  onclick="$('a[href=#basic]').tab('show')">上一步</button>
  <button class="btn btn-lg btn-primary" type="button" style="width: 40%;float: right;"
  id="financeNex">下一步</button>
  </div>
  <div role="tabpanel" class="tab-pane fade " id="paperwork">
  <br><div class="form-group"><input type="text" id="idnum" name="salesman.idNum" class="form-control" placeholder="身份证号" reg="/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/" tip="身份证号不能为空"></div>
<div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="身份证正面照片">正面照片</abbr></div>
  <input type="file" id="idpic1" name="idPic" class="form-control" reg="^.+$" tip="请选择身份证正面照片">
</div>
</div>
<div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="身份证反面照片">反面照片</abbr></div>
  <input type="file" id="idpic2" name="idPic" class="form-control" reg="^.+$" tip="请选择身份证反面照片">
</div>
</div>
  <div class="form-group"><input type="text" id="congye" name="salesman.congYe" class="form-control" placeholder="从业证号" reg="^.+$" tip="从业证号不能为空"></div>
  <div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="从业证正面照片">正面照片</abbr></div>
  <input type="file" id="cypic1" name="cyPic" class="form-control" reg="^.+$" tip="请选择从业证正面照片">
</div>
</div>
  <div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="从业证反面照片">反面照片</abbr></div>
  <input type="file" id="cypic2" name="cyPic" class="form-control"  reg="^.+$" tip="请选择从业证正面照片">
</div>
</div>
  <br><div class="form-group"><input type="text" id="zhanye" name="salesman.zhanYe" class="form-control" placeholder="展业证号"  reg="^.+$" tip="展业证号不能为空"></div>
<div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="展业证正面照片">正面照片</abbr></div>
  <input type="file" id="zypic1" name="zyPic" class="form-control" reg="^.+$" tip="请选择展业证正面照片">
  </div>
</div>
<div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="展业证反面照片">反面照片</abbr></div>
  <input type="file" id="zypic2" name="zyPic" class="form-control" reg="^.+$" tip="请选择展业证正面照片">
  </div>
</div>
  <br><button class="btn btn-lg btn-default btn-block" type="button"
  onclick="$('a[href=#finance]').tab('show')">上一步</button>
  <br><button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
  </div>
</div>
      </form>

    </div> <!-- /container -->


</body>
</html>