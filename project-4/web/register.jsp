<%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/19
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
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
    <form action="add.jsp" method="post" id="shell">
        <div>
            <label for="username">用户名：</label>
            <input type="text" placeholder="请输入用户名" name="username" id="username">
        </div>
        
        <div>
            <label for="password">密码：</label>
            <input type="password" placeholder="请输入密码" name="password" id="password">
        </div>

        <div>
            <label for="age">年龄：</label>
            <input type="text" name="age" id="age" placeholder="请输入年龄">
        </div>

        <input type="submit" value="注册">
    </form>
</body>
</html>
