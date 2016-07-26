<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function () {
	//文件上传地址
    var url = 'http://localhost:8080/InsuranceService/background/resources/images/';
    //初始化，主要是设置上传参数，以及事件处理方法(回调函数)
    $('#fileupload').fileupload({
        autoUpload: true,//是否自动上传
        url: url,//上传地址
        dataType: 'json',
        done: function (e, data) {//设置文件上传完毕事件的回调函数
            $.each(data.result.files, function (index, file) {//
                $('<p/>').text(file.name).appendTo('#files');
            });
        },
        progressall: function (e, data) {//设置上传进度事件的回调函数
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        }
    });
});
</script>
</head>
<body>
<center>  
    <h1>Struts 2完成上传</h1>  
      <form action="upload.action" method="post" enctype="multipart/form-data">  
        <table>  
            <tr>  
                <td>用户名:</td>  
                <td><input type="text" name="username" ></td>  
            </tr>  
            <tr>  
                <td>上传文件:</td>  
                <td><input type="file" name="myFile"></td>  
            </tr>  
            <tr>  
                <td><input type="submit" value="上传"></td>  
                <td><input type="reset"></td>  
            </tr>  
        </table>  
      </form>  
  </center>  
</body>
</html>