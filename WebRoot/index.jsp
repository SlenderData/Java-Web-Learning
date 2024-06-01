<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  		<%-- <%
  			response.sendRedirect(request.getContextPath()+"/list.product");
  		%> --%>
    	<%-- <jsp:forward page="/list.product"></jsp:forward> --%>
  		<%
  			out.print("<h1 style='color:red;'>hello out</h1>");
  			out.print("<h1 style='color:red;'>hello out</h1>");
  			pageContext.setAttribute("aaa", "aaa");
  		 	String sessionId=request.getSession().getId();
  		 	String sessionId2=session.getId();
  		 	application.setAttribute("admin", "admin");
//  		 	int i=1/0;
  		 %>
  		 <%=pageContext.getAttribute("aaa") %><br>
  		 <%=sessionId %><br>
  		 <%=sessionId2 %><br>
  		 <%=application.getAttribute("admin") %><br>
  </body>
</html>
