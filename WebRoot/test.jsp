<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-1.3.2.min.js">
</script>
<script type="text/javascript">
  function show(){
    $('#share_weixin').show();
  }
  
  function hiden(){
    $('#share_weixin').hide();
  }
</script>

<style type="text/css">
#share_weixin {
   display: none;
   position:fixed;
   z-index:3000;
   
   left:50px;
   top:2px;
   width:260px;
}
#share_weixin img {
   width:260px;
   height:165px;
}
.share_btn {
   font-size:16px;
   color:#666666;
   line-height:20px;
   text-align:center;	
   border:1px solid #666666;
   background-color:#eeeeee;
   padding:10px 0;
   width:100%;
}
.share_btn:active{background-color:#cccccc;}
#share_bg {
    background-color:#000; 
    width:100%; 
    height:100%; 
    left:0; 
    top:0; 
    filter:alpha(opacity=80); 
    opacity:0.8; 
    position:fixed!important; 
    z-index:29999; 
}

</style>
</head>

<body>
<div>
<button onclick="show()">分享到朋友圈</button>
</div>

<div id="share_weixin" onclick="$('#share_weixin').hide()">
<div id="guide"><img src="${pageContext.request.contextPath}/tishi.png"></div>
<div><button class="share_btn">关闭提示</button></div>
<div id="share_bg"></div>
</div>


</body>
</html>