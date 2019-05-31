<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品列表</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		<%@include file="header.jsp" %>

		<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath }/index">首页</a></li>
				</ol>
			</div>
			<%--  得到pagebean中的集合lists  这个集合中保存所有的当前页的商品信息
				 遍历这个集合   拿到每一个信息
			 --%>
			<!-- <div class="col-md-2">
				<a href="product_info.htm">
					<img src="products/1/cs10001.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="product_info.html" style='color:green'>冬瓜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div> -->
			<%-- item 代表当前遍历元素    items代表要遍历的数组或集合  他的值支持el表达式
				 begin  遍历起始下标     step 递增多少    varstatus 遍历的其他情况
			 --%>
			<c:forEach var="product" items="${pagebean.lists }" begin="0" step="1" >
				<div class="col-md-2" style="height: 300px;">
					<a href="${pageContext.request.contextPath}/productinfo?pid=${product.pid}">
						<img src="${pageContext.request.contextPath}/${product.pimage}" width="170" height="170" style="display: inline-block;">
					</a>
					<p><a href="${pageContext.request.contextPath}/productinfo?pid=${product.pid}" style='color:green;' >${product.pname }</a></p>
					<p><font color="#FF0000">商城价：&yen;${product.shop_price }</font></p>
				</div>
			
			</c:forEach>
			

		</div>

		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<c:choose>
					<c:when test="${pagebean.currPage eq 1 }"><%-- 当在第一页的时候不准使用 --%>
						<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath }/product?cid=${cid}&currpage=${pagebean.currPage-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:otherwise>
				</c:choose>
				
				
				
				<!-- <li class="active"><a href="#">1</a></li> -->
				<c:forEach begin="1" step="1" end="${pagebean.maxPages }"
					varStatus="status">
					<c:choose>
						<c:when test="${pagebean.currPage eq status.count }">
							<li class="active"><a href="${pageContext.request.contextPath }/product?cid=${cid}&currpage=${status.count }">${status.count }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/product?cid=${cid}&currpage=${status.count }">${status.count }</a></li>
						</c:otherwise>
					</c:choose>
					
					
				</c:forEach>
				
				<c:choose>
					<c:when test="${pagebean.currPage eq  pagebean.maxPages}"><%-- 当在最后一页的时候不准使用 --%>
						<li class="disabled">
							<a href="#" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${pageContext.request.contextPath }/product?cid=${cid}&currpage=${pagebean.currPage+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
				
				
			</ul>
		</div>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
		<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

			<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
			<div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
			<div style="clear: both;"></div>

			<div style="overflow: hidden;">

				<ul style="list-style: none;">
					<c:forEach var="p" items="${products }" begin="0" step="1">
						<li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;">
							<a href="${pageContext.request.contextPath }/productinfo?pid=${p.pid}">
								<img src="${pageContext.request.contextPath }/${p.pimage}" width="130px" height="130px" />
							</a>
						</li>
					</c:forEach>
				</ul>

			</div>
		</div>

<%@include file="footer.jsp" %>

	</body>

</html>