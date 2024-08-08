<%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/13
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
  <style>
    #shell {
      width: 350px;
      padding: 40px;
      display: flex;
      align-items: center;
      flex-direction: column;
      background-color: #ffffff49;
      border-radius: 50px;
    }
  </style>
</head>
<body>
<%@include file="error.jsp"%>
    <form id="shell" method="post" action="listUser.jsp?login=login" onsubmit="return check()">
      <div>
        <label for="username">用户名：</label>
        <input type="text" id="username" placeholder="请输入用户名" name="username">
      </div>

      <div>
        <label for="password">密码：</label>
        <input type="text" id="password" placeholder="请输入密码" name="password">
      </div>


      <input type="submit" value="登录" >
    </form>
<button onclick="goRegiser()">注册</button>
</body>
<script>
    function check() {
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        if (username === "" || password === "") {
            window.alert("用户名或密码不能为空");
            return false;
        }
        return true;
    }
    function goRegiser() {
        window.location.href = 'register.jsp';
    }
</script>
</html>
