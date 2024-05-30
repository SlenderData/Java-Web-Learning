<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
  </head>
  <script type="text/javascript">
  		function checkUsername(){
//   			根据id获取标签元素
  			var un=document.getElementById("username");
//   			获取当前标签的value值
  			var unValue=un.value;
  			if(""==unValue){
  				console.log("用户名不能为空！");
//   				获取用户名后信息提示标签
  				var unMsg=document.getElementById("usernameMsg");
  				unMsg.innerHTML="用户名不能为空！";
  				return false;
  			}
  			return true;
  		}
  		
  		function checkPassword(){
  			var pwdValue=document.getElementById("password").value;		
  			var reg=/^[A-Z][a-z]{6}[0-9]{3,6}$/;
<%--  			var b=reg.test(pwdValue);--%>
<%--			if(pwdValue.length>=6&&pwdValue.length<=10){--%>
			if(reg.test(pwdValue)){
				return true;
			}
			var pwdMsg=document.getElementById("passwordMsg");
				pwdMsg.innerHTML="格式错误！";
			return false;
  		}
  		
function checkPhone(){
		var phoneValue=document.getElementById("phone").value;		
		var reg=/^1((34[0-8])|(8\d{2})|(([35][0-35-9]|4[579]|66|7[35678]|9[1389])\d{1}))\d{7}$/;
		if(reg.test(phoneValue)){
			return true;
		}
		var phoneMsg=document.getElementById("phoneMsg");
			phoneMsg.innerHTML="格式错误！";
		return false;
		}
  		
//   		清空对应错误信息
		function clearMsg(text){
			var obj=document.getElementById(text);
			obj.innerHTML="";
		}
		
		function checkAll(){
			return checkUsername()&&checkPassword()&&checkPhone();
		}
  </script>
  <body>
  		<%
  			String msg=(String)request.getAttribute("message");
  		 %>
    	<h1>用户注册</h1>
    	<hr>
    	<form onsubmit="return checkAll();" action="<%=request.getContextPath() %>/regist.user" method="post">
    		用户名：<input id="username" type="text" name="username" onfocus="clearMsg('usernameMsg');" onblur="checkUsername();">
    				<span id="usernameMsg" style="color:red;"></span>
    				<br>
    		密码：<input id="password" type="text" name="password" onfocus="clearMsg('passwordMsg');" onblur="checkPassword();" >
    				<span id="passwordMsg" style="color:red;"></span>
    		<br>
    		电话：<input id="phone" type="text" name="phone" onfocus="clearMsg('phoneMsg');" onblur="checkPhone();" >
    				<span id="phoneMsg" style="color:red;"></span>
    		<br>
    		地址：<input type="text" name="address"><br>
    		<input type="submit" value="注册">
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
