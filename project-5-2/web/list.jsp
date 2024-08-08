<%@ page import="com.project.model.entity.User" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.model.dao.impl.UserDaoImpl" %>
<%@ page import="com.project.model.dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/27
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <style>
        #shell {
            display: flex;
        }
        p {
            margin-left: auto;
        }
        #btn {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<%@include file="success.jsp"%>
<%@include file="failure.jsp"%>
<%
    User loginUser = (User) request.getSession().getAttribute("USER");
    if (loginUser == null) {
        response.sendRedirect("login.jsp?error=" + URLEncoder.encode("请先登录"));
    }
%>
<div id="shell">
    <p><%=loginUser == null ? "请登录" : loginUser.getUsername()%>, 欢迎</p>
    <button onclick="logout()" id="btn">退出登录</button>
</div>
<div>
    <table>
        <tr>
            <th>id</th>
            <th>用户名</th>
            <th>密码</th>
            <th>年龄</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        <%

            UserDao userDao = new UserDaoImpl();
            List<User> list = userDao.list();
            for (User user : list) {
        %>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getUsername()%></td>
            <td><%=user.getPassword()%></td>
            <td><%=user.getAge()%></td>
            <td><a href="update.jsp?id=<%=user.getId()%>">修改</a></td>
            <td><button onclick="del(<%=user.getId()%>)">删除</button></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>

<script>
    function logout() {
        window.location.href = "login.jsp?success=退出成功";
    }

    function del(id) {
        const b = confirm("你确定要删除吗？");
        if (b) {
            window.location.href = "/del?id=" + id;
        }
    }
</script>
</html>
