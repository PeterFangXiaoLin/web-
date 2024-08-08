<%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/26
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: url("/img/a.jpg");
            background-size: cover;
        }

        .shell {
            width: 350px;
            padding: 40px;
            display: flex;
            align-items: center;
            flex-direction: column;
            background-color: #ffffff49;
            border-radius: 50px;
        }

        .title {
            font-size: 80px;
            margin-bottom: 30px;
            color: #fff;
            text-shadow: 0 0 10px #9db7ff80;
        }

        #username, #password {
            width: 100%;
            height: 50px;
            margin: 10px 0;
            box-sizing: border-box;
            color: rgb(0, 0, 0);
            border: 5px solid lightblue;
            border-radius: 100px;
            padding: 5px 20px 0 20px;
            font-size: 18px;
            outline: none;
        }

        #loginBtn {
            width: 100%;
            height: 50px;
            padding: 10px;
            margin: 15px 0;
            border-radius: 100px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            font-size: 20px;
            letter-spacing: 3px;
        }

        input::placeholder {
            color: #92A7E8;
        }

        .footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            margin-top: 20px;
        }

        .Remember {
            opacity: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 18px;
            color: #7597ff;
        }

        #rememberMe {
            display: block;
            width: 25px;
            height: 25px;
            margin-right: 10px;
            background-color: #007bff;
        }

        #goRegister {
            border: none;
            background-color: #ffffff00;
            color: #7597ff;
            font-size: 18px;
        }
    </style>
</head>
<body>
<%@include file="success.jsp" %>
<%@include file="failure.jsp" %>
<div class="shell">
    <h2 class="title">Login</h2>
    <form action="/login" method="post" onsubmit="return check()">
        <input type="text" id="username" name="username" placeholder="请输入用户名">
        <input type="password" id="password" name="password" placeholder="请输入密码">
        <input type="submit" value="Login" id="loginBtn">
    </form>
    <div class="footer">
        <div class="Remember">
            <input type="checkbox" id="rememberMe">
            <label for="rememberMe">记住我</label>
        </div>
        <button id="goRegister" onclick="goRegister()">去注册</button>
    </div>
</div>

<script>
    function check() {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        if (username == '' || password == '') {
            alert('账号或密码为空');
            return false;
        }
        return true;
    }

    function goRegister() {
        window.location.href = "register.jsp";
    }
</script>
</body>
</html>
