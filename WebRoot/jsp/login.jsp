<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css"/>

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
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<body>
			<%@ include file="header.jsp" %>
	
	
	
	
	
	
<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('${pageContext.request.contextPath }/images/loginbg.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>会员登录</font>USER LOGIN

				<div>&nbsp;</div>
<form class="form-horizontal">
  
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-6">
      <input onblur="usernameCheck(this)" type="text" class="form-control" id="username" placeholder="请输入用户名">
   	  <span id="uSpan"></span>
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-6">
      <input onblur="passwordCheck(this)" id="password" type="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
      <span id="pSpan"></span>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input onclick="login()" type="button"  width="100" value="登录"  border="0"
    style="background: url('${pageContext.request.contextPath }/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
    </div>
  </div>
</form>
</div>			
	</div>
</div>
</div>	
	<%@include file="footer.jsp" %>
	<script type="text/javascript">
		/**
			1.给两个input设置失去焦点事件
			2.如果某个input中没有输入内容,提示XXX不能为空
			
			提示: js中  字符串转型Boolean类型的
				 只要字符串的长度不为0,那么转为完都是true
		
		*/
		function usernameCheck(input){
			//flag=true 代表用户输入用户名
			//flag=false 代表用户没有输入用户名
			var flag = Boolean(input.value);
			var span = document.getElementById("uSpan");
			if(!flag){
				span.innerHTML = "用户名不能为空"
				span.style.color = "red";
			}else{
				span.innerHTML = "";
			}
		}
		function passwordCheck(input){
			//flag=true 代表用户输入用户名
			//flag=false 代表用户没有输入用户名
			var flag = Boolean(input.value);
			var span = document.getElementById("pSpan");
			if(!flag){
				span.innerHTML = "密码不能为空"
				span.style.color = "red";
			}else{
				span.innerHTML = "";
			}
		}
		
		function login(){
			/*
				1.判断用户名和密码是否有未填的,如果有,点击事件无效果,也就是不发送请求
			*/
			var input1 = document.getElementById("username");
			var input2 = document.getElementById("password");
			var username = input1.value;
			var password = input2.value;
			if(!username || !password){//用户名或密码 其中一个未填写,就不会发送请求
				return;
			}
			//程序能走到这一步: 用户输入用户名 也输入秒=密码
			//可以发送AJAX请求
			var url = "${pageContext.request.contextPath}/login";
			var fn = function(data){
				//如果响应结果是成功  跳转到主页面
				if (data=="true") {
					window.location = "${pageContext.request.contextPath}/index";
				} else {
				//jQuery 
				// var span = document.getElementById("pSpan");
				// span.innerHTML = ".....";
				// span.style.color = "red";
					$("#pSpan").html("用户名或密码错误");
					$("#pSpan").css({"color":"red"});
				}
				//如果是失败 ,提示用户失败原因
			
			};
			$.post(url,{"username":username,"password":password},fn,"text");
			
			
		}
		
		
	
	</script>
	
	
	
	
	
	
	
</body></html>