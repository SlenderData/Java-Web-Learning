<%@page import="entity.User"%>
<%@page import="entity.Product"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="../error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>商品列表</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  <script type="text/javascript">
  			function addCart(){
  				var b=confirm("是否添加购物车？");
  				return b;
  			}
  
  </script>
  <body>
    	<h1>商品列表</h1>
  		<c:if test="${user == null }">
    	<a href="${pageContext.request.contextPath }/jsp/regist.jsp">注册</a>
    	<a href="${pageContext.request.contextPath }/jsp/login.jsp">登录</a>
    	</c:if>
    	<c:if test="${user != null }">
    	${user.username }用户在线...
    	<a href="${pageContext.request.contextPath }/logout.user">注销</a>
    	<a href="myOrders.html">我的历史订单</a>
    	</c:if>
    	<br>
    	<hr>
    	<table border="1">
    		<tr>
    			<th>序号</th>
    			<th>商品</th>
    			<th>价格</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${products }" var="p">
    		<tr>
    			<td>${p.id }</td>
    			<td>${p.name }</td>
    			<td>${p.price }</td>
    			<td>
    				<a href="${pageContext.request.contextPath }/add.cart?product_id=${p.id}" onclick="return addCart();">加入购物车</a>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    	<a href="${pageContext.request.contextPath}/list.cart">查看购物车</a>
  </body>
</html>
