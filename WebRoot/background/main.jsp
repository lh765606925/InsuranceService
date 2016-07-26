<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>管理系统</title>
        <link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" >
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/background/resources/css/style.css"
			type="text/css" media="screen">	
<!-- bootstrap -->
<!-- jQuery.js必须放到bootstrap.js上面，否则会导致js打开modal失败-->
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-dialog.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/fileinput.js"></script>
<script	src="${pageContext.request.contextPath}/background/resources/js/myjs.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-table.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-dialog.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/fileinput.css" rel="stylesheet">

		<% 
	   String leftPage="/background/common/left.jsp";
       
       String left1 = request.getParameter("leftPage");
       String left2= (String)request.getAttribute("leftPage");
       if(left1!=null&&left1!=""){
           leftPage=left1;
       }
       if(left2!=null&&left2!=""){
           leftPage=left2;
       }
       
       String mainPage = (String) request.getAttribute("mainPage");
       if(mainPage==null||mainPage.equals("")){
          mainPage="/background/common/default.jsp";
       }
       %>
<script type="text/javascript">
    $(function(){
        $(".defaultPage #p1").animate({
           opacity:'1',
           fontSize:'30px',
           marginTop:'150px',
           marginBottom:'20px'
           },function(){
             $(".defaultPage #p2").animate({
               opacity:'1',
               fontSize:'30px',
               marginLeft:'37%',
               });
           });
        var boardDiv = "<div id='message' style='display:none;'><span id='spanmessage'></span></div>";
		   $(document.body).append(boardDiv);   
        
         });

</script>

	</head>
	<body id="main_page">
 		<jsp:include page="/background/common/head.jsp" />
		<div id="center">
			<div id="left">
				<jsp:include page="<%= leftPage%>" />
			</div>
			<div id="right">
				<jsp:include page="<%= mainPage%>" />
			</div>
		</div>
	</body>
</html>