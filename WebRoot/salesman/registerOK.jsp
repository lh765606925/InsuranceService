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
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico">

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
  function is_weixn(){
	    var ua = navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i)=="micromessenger") {
	        return true;
	    } else {
	        return false;
	    }

	}
  
  function show(){
	  if(is_weixn()){
		  $('#tishi').show();
	  }else{
		  window.location.href="../../InsuranceData/apk/Jaia.apk";
	  }
  }
  </script>
   <style type="text/css">
   #down{
    width: 300px;
    text-align: center;
   }
   #tishi {
     display: none;
     background-color: black;
     opacity:0.5; 
     position: fixed;
     top:0;
     z-index: 2;
     width: 100%;
     height: 100%;
   }
   #img {
    margin:0px auto;
    width:330px;
   }
   #img img{
    position: relative;
    top:2px;
    left: 20px;
   }
   #tishi button {
   font-size:16px;
   color:#666666;
   line-height:20px;
   text-align:center;	
   border:1px solid #666666;
   background-color:#eeeeee;
   padding:10px 0;
   width:330px;
   }
   
   </style>
</head>
<body style="overflow:hidden;">
        <div id="tishi" onclick="$('#tishi').hide()">
           <div id="img">
           <img src="${pageContext.request.contextPath}/tishi.png" width="100%">
           </div>
        </div>
    <div >
      <div id="content" class="form-register">
        <h1 class="form-register-heading">提交成功！^_^</h1><h3>请耐心等待审核。。。</h3>
        <h3>您的邀请识别码已生成：</h3><font color="red" size="3em"><%=request.getParameter("showInvate") %>(即您注册的手机号码)</font>
        <div id="down">
        <img  onclick="show()" width="130px"
        src="${pageContext.request.contextPath}/salesman/resources/images/xiazai.png">
        <p>(点击下载安卓版:建安APP)</p>
        </div>
      </div>
    </div> 
</body>
</html>