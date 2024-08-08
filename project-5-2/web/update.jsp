<%@ page import="com.project.model.dao.UserDao" %>
<%@ page import="com.project.model.dao.impl.UserDaoImpl" %>
<%@ page import="com.project.model.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/27
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新</title>
</head>
<body>
<%
    long id = Long.parseLong(request.getParameter("id"));
    UserDao userDao = new UserDaoImpl();
    User user = userDao.getOne(id);
%>
<form action="/update?id=<%=id%>" method="post" onsubmit="return check()">
    <label for="username">用户名</label>
    <input type="text" name="username" value="<%=user.getUsername()%>" id="username">
    <label for="password">密码</label>
    <input type="text" name="password" id="password" value="<%=user.getPassword()%>">

    <label for="age">年龄</label>
    <input type="text" name="age" id="age" value="<%=user.getAge()%>">

    <input type="submit" value="提交">
</form>
</body>
<script>
    function check() {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const age = document.getElementById("age").value;
        if (username === '') {
            alert('用户名不能为空');
            return false;
        }
        if (password === '') {
            alert('密码不能为空');
            return false;
        }
        if (age === '') {
            alert('年龄不能为空');
            return false;
        }
        if (isNaN(age)) {
            alert('年龄非法');
            return false;
        }
        return true;
    }
</script>
</html>
