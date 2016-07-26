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
    <link rel="icon" href="http://v3.bootcss.com/favicon.ico">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/salesman/resources/css/register.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.1.3.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/ie-emulation-modes-warning.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bt-validate.js" type="text/javascript"></script> 
    

<script type="text/javascript" src="resources/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="resources/js/jquery.bgiframe.min.js"></script>
<link  href="resources/css/validate.css" rel="stylesheet" type="text/css" />
    
    
    <script type="text/javascript">
       $(function(){
    	   $('#myTab a').click(function (e) {
    		   e.preventDefault();
    		   $(this).tab('show');
    		 });
    	   
    	   
       });
    
    </script>
    
</head>
<body>
    <div class="container">
      <form class="form-register" role="form">
        <h2 class="form-register-heading">欢迎注册^_^</h2><br>
<ul class="nav nav-pills" role="tablist" id="myTab">
  <li role="presentation" class="active"><a href="#basic" role="tab" data-toggle="tab">基本信息</a></li>
  <li role="presentation"><a href="#finance" role="tab" data-toggle="tab">财务信息</a></li>
  <li role="presentation"><a href="#paperwork" role="tab" data-toggle="tab">证件信息</a></li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
  <div role="tabpanel" class="tab-pane fade in active text-right" id="basic">
        <br><input type="text" class="form-control" placeholder="昵称" required="" reg="^.+$" tip="不能为空">
        <br><input type="text" class="form-control" placeholder="真实姓名" required="" >
        <br><input type="tel" class="form-control" placeholder="手机号码" required="" >
        <br><input type="email" class="form-control" placeholder="邮箱" required="" >
        <br><input type="password" class="form-control" placeholder="密码" required="">
        <br><input type="text" class="form-control" placeholder="现住址" required="">
        <br><input type="text" class="form-control" placeholder="工作地址" required="">
        <br><button class="btn btn-lg btn-primary btn-block" type="button" 
        onclick="$('a[href=#finance]').tab('show')">下一步</button>
  </div>
  <div role="tabpanel" class="tab-pane fade " id="finance">
  <br><input type="text" class="form-control" placeholder="开户银行" required="">
  <br><input type="text" class="form-control" placeholder="开户名" required="">
  <br><input type="text" class="form-control" placeholder="银行帐号" required="">
  <br><button class="btn btn-lg btn-default" type="button" style="width: 40%;float: left;"
  onclick="$('a[href=#basic]').tab('show')">上一步</button>
  <button class="btn btn-lg btn-primary" type="button" style="width: 40%;float: right;"
  onclick="$('a[href=#paperwork]').tab('show')">下一步</button>
  </div>
  <div role="tabpanel" class="tab-pane fade " id="paperwork">
  <br><input type="text" class="form-control" placeholder="身份证号" required="">
<div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="身份证正面照片">正面照片</abbr></div>
  <input type="file" class="form-control" required="">
</div>
</div>
<div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="身份证反面照片">反面照片</abbr></div>
  <input type="file" class="form-control" required="">
</div>
</div>
  <input type="text" class="form-control" placeholder="从业证号" required="">
  <div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="从业证正面照片">正面照片</abbr></div>
  <input type="file" class="form-control" required="">
</div>
</div>
  <div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="从业证反面照片">反面照片</abbr></div>
  <input type="file" class="form-control" required="">
</div>
</div>
  <br><input type="text" class="form-control" placeholder="展业证号" required="">
<div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="展业证正面照片">正面照片</abbr></div>
  <input type="file" class="form-control" required="">
  </div>
</div>
<div class="form-group">
  <div class="input-group">
  <div class="input-group-addon"><abbr title="展业证反面照片">反面照片</abbr></div>
  <input type="file" class="form-control" required="">
  </div>
</div>
  <br><button class="btn btn-lg btn-default btn-block" type="button"
  onclick="$('a[href=#finance]').tab('show')">上一步</button>
  <br><button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
  </div>
</div>
      </form>

<form id="zedit" class="show-grid form-inline">
                 
                    <fieldset>
                        
                        <div class="contextbox">
                            
                            <div class="row "> 
                                <div class="span3">
                                    <label>Label aaaaaaname</label>
                                    <input id="psw" class=" "  type="text"  btvd-type="mail"
                                    btvd-class='btvdclass'
                                    btvd-err="data-邮箱格式不正确！"   >    
                                </div>
                                <div class="span3">
                                    <label>Label aname</label>
                                    <input class=""  type="text" placeholder="a…" btvd-el='/^[0-9]\d*$/' 
                                    btvd-class='btvdclass'
                                    btvd-err="data-必须输入数字！" 
                                    >   
                                </div>
                            </div><br>
                            <div class="row "> 
                                <div class="span3">
                                    <label >Label bbname</label>
                                    <input class="hori-margin" type="text" placeholder="Type something…">
                                </div>
                                <div class="span3">   
                                    <label >Label bbbbbname</label>
                                    <input id="dd" class="hori-margin" type="text" placeholder="Type something…">
                                 </div>   
                             </div>
                             
                        </div>  
    
                        <div class="bottombox">
                            <div class="row contextbox "> 
                                 
                                    <button type="submit" 
                                       class="btn hori-margin  myright  ">Submitddddd</button>
                                    
                                    <button type="submit" class="btn hori-margin myright ">Submitzzz</button>  
                            </div> 
                        </div>
                            
                    </fieldset>
                </form>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./Signin Template for Bootstrap_files/ie10-viewport-bug-workaround.js"></script>
</body>
</html>