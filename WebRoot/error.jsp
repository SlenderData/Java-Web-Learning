<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'erro.jsp' starting page</title>
  </head>
  <body>
    	<h1 style="color: red;">系统忙，请稍后再试</h1>
    	<%=exception %><br>
    	<%=exception.getMessage() %><br>
  </body>
</html>
