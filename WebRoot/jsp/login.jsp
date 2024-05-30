<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录</title>
  </head>
  <body>
  		<%
  			String msg=(String)request.getAttribute("message");
  		 %>
    	<h1>用户登录</h1>
    	<hr>
    	<form action="<%=request.getContextPath() %>/login.user" method="post">
    		用户名：<input type="text" name="username"><br>
    		密码：<input type="password" name="password"><br>
    		<input type="submit" value="登录">
    	</form>
    	<%
    		if(msg!=null){
    	 %>
    	<span style="color:red;"><%=msg %></span>
    	<%
    		}
    	 %>
    	<a href="<%=request.getContextPath() %>/list.product">返回</a>
  </body>
</html>
