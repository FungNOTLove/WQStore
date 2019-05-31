<%-- java中的指令   声明此页所有信息 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员注册</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css"/>
		<%-- src   url    href --%>
<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;
}
 </style>
</head>
<body>

	<%-- 引入头部文件 --%>
	<%@ include file="header.jsp" %>

			





<div class="container" style="width:100%;background:url('${pageContext.request.contextPath }/image/regist_bg.jpg');">
<div class="row"> 

	<div class="col-md-2"></div>

	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
		<font>会员注册</font>USER REGISTER
		<form class="form-horizontal" style="margin-top:5px;"
		action="${pageContext.request.contextPath }/register?method=regist"
		method="post" enctype="multipart/form-data">
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username"
			      onblur="checkname()">
			      <span id="msg"></span>
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email">
			    </div>
			  </div>
			 <div class="form-group">
			    <label for="usercaption" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="usercaption" placeholder="请输入姓名" name="name">
			    </div>
			  </div>
			  <div class="form-group opt">  
			  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
			  <div class="col-sm-6">
			    <label class="radio-inline">
			  <input type="radio" name="sex" id="inlineRadio1" value="boy"> 男
			</label>
			<label class="radio-inline">
			  <input type="radio" name="sex" id="inlineRadio2" value="girl" checked="checked"> 女
			</label>
			</div>
			  </div>		
			  <div class="form-group">
			    <label for="logo" class="col-sm-2 control-label">头像</label>
			    <div class="col-sm-6">
			    <input type="file"  name="photo" onchange='PreviewImage(this)' />
				<br/>
				<div id="imgPreview" style='width:120px; height:100px;'>
				 <img id="img1" src="" width="120" height="100" /> 
				</div>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input type="submit"  width="100" value="注册"  border="0"
				    style="background: url('${pageContext.request.contextPath }/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
			    </div>
			  </div>
			</form>
	</div>
	
	<script type="text/javascript">
 	
 	//图片预览功能
 	function PreviewImage(imgFile){
 	    var filextension=imgFile.value.substring(imgFile.value.lastIndexOf("."),imgFile.value.length);
 	    filextension=filextension.toLowerCase();
 	    if ((filextension!='.jpg')&&(filextension!='.gif')&&(filextension!='.jpeg')&&(filextension!='.png')&&(filextension!='.bmp')){
 	        alert("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 !");
 	        imgFile.focus();
 	    }else{
 	        var path;
 	        if(document.all){
 	            imgFile.select();
 	            path = document.selection.createRange().text;
 	            document.getElementById("imgPreview").innerHTML="";
 	            document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果      
 	        }else{
 	            path=window.URL.createObjectURL(imgFile.files[0]);
 	            document.getElementById("imgPreview").innerHTML = "<img id='img1' width='120px' height='100px' src='"+path+"'/>";
 	        }
 	    }
 	}
 	
 	function checkname(){
 		/*
 			1. 获取用户输入的内容
 			2. 判断用户输入的内容是否满足要求
 		*/
 		var input = document.getElementById("username");
 		var span = document.getElementById("msg");
 		var username = input.value;
 		
 		//AJAX 发送异步任务请求
 		//4个参数: url 请求地址
 		//       json  封装请求附带param,发送过去的数据
 		//       fn    function方法,当请求成功后执行的方法
 		//	     type  响应数据的类型
 		//参数就是响应过来的数据
 		var fn = function(data){
 			if(data=="true"){//可以注册
 				span.innerHTML = "名字可以使用";
 				span.style.color = "green";
 			}else{
 				span.innerHTML = "名字已被使用";
 				span.style.color = "red";
 			}
 		};
 	
 		$.post("${pageContext.request.contextPath}/register",
 		{"method":"checkname","username":username},fn,"text");
 	
 	
 	}
 	
 </script>
	
	
	
	<div class="col-md-2"></div>
  
</div>
</div>
	  
	<%-- jsp指令  引入其他文件 --%>
	<%@include file="footer.jsp" %>

</body></html>




    