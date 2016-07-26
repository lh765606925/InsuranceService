<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
<link href="${pageContext.request.contextPath}/background/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script> 
  <!-- jquery-ui-bootstrap  -->
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery-ui-bootstrap/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui-bootstrap/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/background/resources/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/background/resources/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
  </head>
      <div name="datetimepicker" class="input-append date">
        <input data-format="yyyy:MM:dd hh:mm:ss" type="text"></input>
        <span class="add-on">
          <i data-time-icon="icon-time" data-date-icon="icon-calendar">
          </i>
        </span>
      </div>
    <script type="text/javascript">
      $(function() {
        $("[name='datetimepicker']").datetimepicker({
        	language: 'zh-CN',
        	collapse: false
        });
      });
    </script>

  </body>
</html>
