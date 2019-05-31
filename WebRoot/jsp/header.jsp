<%@page import="com.fung.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<div class="col-md-4">
					<img src="${pageContext.request.contextPath }/img/logo3.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
						<%-- 如果未登录  ,显示 登录  注册
							   如果登录,显示  欢迎你xxx,退出 --%>
					<%-- JSTL表达式  --%>
					<c:choose>
						<%-- c:when 代表如果怎样怎样     if
							 test的值就是   判断条件
							 ${empty user} 就代表 如果 user是null  未登录状态--%>
						<c:when test="${empty user}">
							<li><a href="${pageContext.request.contextPath }/jsp/login.jsp">登录</a></li>
							<li><a href="${pageContext.request.contextPath }/jsp/register.jsp">注册</a></li>
						</c:when>
						<%-- c:otherwise 否则怎样怎样   else--%>
						<c:otherwise>
							<li>欢迎您:${user.username }</li>
							<li><a href="${pageContext.request.contextPath }/orderlist?currpage=1">我的订单</a></li>
							<li><a href="${pageContext.request.contextPath }/exit">退出</a></li>
						</c:otherwise>
					</c:choose>	
						<li><a href="${pageContext.request.contextPath }/cart">购物车</a></li>
					</ol>
				</div>
			</div>
			<!--
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="${pageContext.request.contextPath }/index">首页</a>
						</div>
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<%-- 种类列表  需要发送请求  从数据库中查询出
									 所有的种类信息  动态添加

										class="active"  能标记当前种类  也就是说种类底色是黑色
								--%>
								<!-- <li><a href="#">手机数码<span class="sr-only">(current)</span></a></li> -->
								
								
								
								
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
				<script type="text/javascript">
					/*
						这里的js作用就是:
							发送ajax请求获取所有的  种类信息
							FAQ? 什么时候发送请求?
							当页面加载的时候就要发送
							得到的所有相应信息必须是  所有的种类信息包括名字和id
							用java表示的话就是  List<Category> lists;
							FAQ? js 不能使用
							使用 JSON
							JSONObject   {"cid":1,"cname":"手机数码"}
							JSONArray   {{"age":10,"name":"Lilei"},
										{"age":10,"name":"Lilei"}};
							
					*/ 
					//当页面加载的时候就开始执行的事件
					window.onload = function(){
						var url = "${pageContext.request.contextPath}/category";
						var fn = function(data){
							var jsonAry = data;
							//遍历 这个jsonAry得到每一个Category对象
							//得到每一个种类对象就能得到对应的  种类的名字和id
							//jQuery中的each方法来遍历     类似java的for-each
							//两个参数   第一个参数是要遍历的  数组/集合
							//第二个参数是  每次遍历要执行的方法    这个方法有两个参数 第一个是
							//             第一个是当前遍历的元素下标,第二个是当前遍历的元素
							var fn1 = function(index,element){
								//拿到每一个 category信息
								//创建一个li
								var li = document.createElement("li");
								//创建一个a标签
								var a = document.createElement("a");
								$(a).html(element.cname);
								//给 a标签添加 href
								$(a).prop("href","${pageContext.request.contextPath}/product?currpage=1&cid="+element.cid);
								//将 a 放入 li
								$(li).append(a);
								//将 li 放入 ul
								$("ul[class='nav navbar-nav']").append(li);
								
							};
							$.each(jsonAry,fn1);
							
						};
						$.post(url,{"method":"getCategory"},fn,"json");
						
						
					};
				
				
				
				
				
				
				
				</script>
			</div>
			
			
			