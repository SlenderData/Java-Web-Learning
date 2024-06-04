<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<% String msg = (String) request.getAttribute("msg"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<h1>用户注册</h1>
<hr/>
<form onsubmit="return checkAll();" action="<%=request.getContextPath()%>/register.user" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td>
                <input type="text" name="username" id="username" onfocus="clearMsg('usernameMsg');"
                       onblur="checkUsername();">
            </td>
            <td>
                <span id="usernameMsg" style="color: red;"></span>
            </td>
        </tr>
        <tr>
            <td>密 码:</td>
            <td>
                <input type="password" name="password" id="password" onfocus="clearMsg('passwordMsg');"
                       onblur="checkPassword();">
            </td>
            <td>
                <span id="passwordMsg" style="color: red;"></span>
            </td>
        </tr>
        <tr>
            <td>电 话:</td>
            <td>
                <input type="text" name="phone" id="phone" onfocus="clearMsg('phoneMsg');" onblur="checkPhone();">
            </td>
            <td>
                <span id="phoneMsg" style="color: red;"></span>
            </td>
        </tr>
        <tr>
            <td>地 址:</td>
            <td>
                <input type="text" name="address">
            </td>
        </tr>
    </table>
    <input type="submit" value="注册">
</form>
<script type="text/javascript">
    function checkUsername() {
        // 根据id获取标签元素
        var un = document.getElementById("username");
        // 获取当前标签的value值
        var unValue = un.value;
        if ("" == unValue) {
            console.log("用户名不能为空！");
            // 获取用户名后信息提示标签
            var unMsg = document.getElementById("usernameMsg");
            unMsg.innerHTML = "用户名不能为空！";
            return false;
        }
        return true;
    }

    function checkPassword() {
        var pwdValue = document.getElementById("password").value;
        var reg = /^[A-z]{3,10}[0-9]{3,10}$/;
        if (reg.test(pwdValue)) {
            return true;
        }
        var pwdMsg = document.getElementById("passwordMsg");
        pwdMsg.innerHTML = "格式错误！";
        return false;
    }

    function checkPhone() {
        var phoneValue = document.getElementById("phone").value;
        var reg = /^1((34[0-8])|(8\d{2})|(([35][0-35-9]|4[579]|66|7[35678]|9[1389])\d{1}))\d{7}$/;
        if (reg.test(phoneValue)) {
            return true;
        }
        var phoneMsg = document.getElementById("phoneMsg");
        phoneMsg.innerHTML = "格式错误！";
        return false;
    }

    // 清空对应错误信息
    function clearMsg(text) {
        var obj = document.getElementById(text);
        obj.innerHTML = "";
    }

    function checkAll() {
        return checkUsername() && checkPassword() && checkPhone();
    }
</script>
</body>
<br>
<h1 style="color: red;"><%=msg != null ? msg : "" %>
</h1>
<a href="<%=request.getContextPath()%>/show.goods">返回</a>
</html>
