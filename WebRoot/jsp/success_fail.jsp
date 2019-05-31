<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册${msg eq "success"? "成功":"失败" }页面</title>
</head>
<body>
	注册${msg eq "success"? "成功":"失败" },<font color="red" size="5" id="second">5</font>秒后进入到
	${msg eq "success"? "登录":"注册" }页面
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
				window.location = "${pageContext.request.contextPath}/jsp/${msg eq 'success'? 'login':'register'}.jsp";
				//定时器执行结束   一定要清除
				window.clearInterval(timer);
			}
		}, 1000);
		
		
		
		
		
		
		
	
	</script>
</body>
</html>