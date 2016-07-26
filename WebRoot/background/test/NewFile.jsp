<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	  <h3>多个文件上传实例</h3>
       <form action="${pageContext.request.contextPath}/back/salesman_Test.action" enctype="multipart/form-data" method="post">
         <table>
           <tr><td><input type="file" name="upload"></td></tr>
           <tr><td><input type="submit" value="上传" /></td></tr>
         </table>  
           
       </form>
</body>
</html>