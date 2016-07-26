<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- bootstrap -->
<!-- <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet"> -->
<!-- <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"> -->
<!-- <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css" rel="stylesheet"> -->
<!-- <link href="${pageContext.request.contextPath}/bootstrap/css/fileinput.css" rel="stylesheet"> -->

<!-- <script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script> -->
<!-- <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script> -->
<!-- <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script> -->
<!-- <script src="${pageContext.request.contextPath}/bootstrap/js/fileinput.js"></script> -->

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="../js/fileinput.js" type="text/javascript"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js" type="text/javascript"></script>


<script type="text/javascript">
    $(function(){
    	$("#input-id").fileinput();
    	 
    	// with plugin options
    	$("#input-id").fileinput({
    		'showUpload':false, 
    		 initialPreview: ["<img src='d:/Desert.jpg' class='file-preview-image'>", "<img src='d:/Jellyfish.jpg' class='file-preview-image'>"],
    	        allowedFileExtensions : ['jpg', 'png','gif'],
    	        overwriteInitial: false,
    	        maxFileSize: 1000,
    	        maxFilesNum: 10,
    	        //allowedFileTypes: ['image', 'video', 'flash'],
    	        slugCallback: function(filename) {
    	            return filename.replace('(', '_').replace(']', '_');
    	        },
    		'uploadUrl': 'd:/',
    		'previewFileType':'any'});
    });
</script>
</head>
<body>
<input id="input-id" class="file" type="file" multiple=true data-preview-file-type="any">

</body>
</html>