<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  订单提交成功,<font color="red" size="5" id="second">5</font>秒后进入到
	主页面
	<script type="text/javascript">
		/*
			1.获取当前秒数
			2.添加定时器 类似于JAVA的Timer类
		*/
		//1.得到id叫second的元素
		var font = document.getElementById("second");
		var second = font.innerHTML;
		//2.设置定时器 
		//两个参数: fn 定时执行的方法      ms  间隔毫秒值
		//没过一秒   font中的文字都要自减一下
		var timer = window.setInterval(function(){
			if(second>1){
				font.innerHTML = (--second);
			}else{
				window.location = "${pageContext.request.contextPath}/index";
				//定时器执行结束   一定要清除
				window.clearInterval(timer);
			}
		}, 1000);
		
		
		
		
		
		
		
	
	</script>
  </body>
</html>
