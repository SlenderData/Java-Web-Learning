<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index2.jsp' starting page</title>
  </head>
  <body>
  		<%
  			String sessionId=request.getSession().getId();
  		 	String sessionId2=session.getId();
  		 %>
    	<%=pageContext.getAttribute("aaa") %><br>
  		 <%=sessionId %><br>
  		 <%=sessionId2 %><br>
  		 <%=application.getAttribute("admin") %><br>
  </body>
</html>
