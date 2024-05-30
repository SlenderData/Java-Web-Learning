<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'el.jsp' starting page</title>
  </head>
  <body>
    	<%
    		//暂且表示后台Servlet中存储的数据
    		request.setAttribute("number", 10);
    		request.setAttribute("string", "abc");
    		int[] is={1,2,3,4,5};
    		request.setAttribute("arr", is);
    		User u=new User();
    		u.setId(1);
    		u.setUsername("admin");
    		u.setPassword("123");
    		u.setPhone("12345678901");
    		u.setAddress("南京");
    		request.setAttribute("u", u);
    		User u2=new User();
    		u2.setId(2);
    		u2.setUsername("admin2");
    		u2.setPassword("123456");
    		u2.setPhone("9876543210");
    		u2.setAddress("镇江");
    		List<User> users=new ArrayList<User>();
    		users.add(u);
    		users.add(u2);
    		request.setAttribute("us", users);
    		
    		pageContext.setAttribute("pc", 1);
    		request.setAttribute("req", 2);
    		session.setAttribute("ses", 3);
    		application.setAttribute("appli", 4);
    		
    		//pageContext.setAttribute("aaa", 1);
    		//request.setAttribute("aaa", 2);
    		//session.setAttribute("aaa", 3);
    		application.setAttribute("aaa", 4);
    	 %>
    	 <c:out value="${string }"></c:out><br>
    	 ${string }<br>
    	 <c:if test="${u.username eq 'admin' }">
    	 	<span>hello</span>
    	 </c:if>
    	 <br>
    	 											index count<br>
    	 <c:forEach begin="1" end="3" items="${arr }" var="s" varStatus="c">
    	 	下标：${c.index}   
    	 	数字：${c.count}   
    	 	值：${s }<br>
    	 </c:forEach>
    	 <br>
    	 <c:forEach items="${us }" var="user" varStatus="c">
    	 	${c.count }  
    	 	${user.id }  
    	 	${user.username }  
    	 	${user.password }  
    	 	${user.phone }  
    	 	${user.address }  
    	 	<br>
    	 </c:forEach>
    	 
    	 
    	 <hr>
    	 pageContext:${pc }<br>
    	 request:${req }<br>
    	 session:${ses }<br>
    	 application:${appli }<br>
    	 <hr>
    	 ${aaa }
    	 <hr>
    	 ${pageContext }<br>
    	 ${pageContext.request }<br>
    	 ${pageContext.session }<br>
    	 ${pageContext.servletConfig }<br>
    	 <hr>
    	 <%=request.getContextPath() %><br>
    	  ${pageContext.request.contextPath }<br>
    	 <hr>
    	 ${number+1 }<br>
    	 ${string eq "abc" }<br>
    	 ${empty us }<br>
    	 ${empty us?"空":"有值" }<br>
    	 <hr>
    	 <%=request.getAttribute("number") %><br>
    	 <%=request.getAttribute("string") %><br>
    	 ${number }<br>
    	 ${string }<br>
    	 ${arr[0] }<br>
    	 ${arr[1] }<br>
    	 ${arr[2] }<br>
    	 ${arr[3] }<br>
    	 ${arr[4] }<br>
    	 ${u }<br>
    	 ${u.id }<br>
    	 ${u.username }<br>
    	 ${u.password }<br>
    	 ${u.phone }<br>
    	 ${u.address }<br>
    	 ${us }<br>
    	 ${us[1] }<br>
    	 ${us[1].id }<br>
    	 ${us[1].username }<br>
    	 ${us[1].password }<br>
    	 ${us[1].phone }<br>
    	 ${us[1].address }<br>
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
  </body>
</html>
