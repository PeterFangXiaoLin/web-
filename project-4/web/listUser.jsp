<%@ page import="java.sql.Connection" %>
<%@ page import="com.project.demo.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="com.project.demo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/19
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<%
    String login = request.getParameter("login");
    if (login != null && !login.isEmpty()) {
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            throw new RuntimeException("数据库连接失败");
        }
        String s = "select * from user_test where username= ? and password = ?";
        try (PreparedStatement ps = connection.prepareStatement(s);) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));

                request.getSession().setAttribute("USER", user);
            } else {
                response.sendRedirect("login.jsp?error=" + URLEncoder.encode("用户不存在", "UTF-8"));
                return;
            }
        }
    }
%>

<%
    String sql = "select * from user_test";
    List<User> list = new ArrayList<>();
    Connection connection = ConnectDB.getConnection();
    try (PreparedStatement ps = connection.prepareStatement(sql);) {
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            User user = new User();

            user.setId(resultSet.getLong(1));
            user.setUsername(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setAge(resultSet.getInt(4));

            list.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<table>
    <tr>
        <th>用户id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>年龄</th>
        <th>修改</th>
        <th>删除</th>
    </tr>
    <%
        for (User user : list) {
            long id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();
            int age = user.getAge();
    %>
    <tr>
        <td><%=id%>
        </td>
        <td><%=username%>
        </td>
        <td><%=password%>
        </td>
        <td><%=age%>
        </td>
        <td>
            <a href="update.jsp?id=<%=id%>">修改</a>
        </td>
        <td>
            <button onclick="del(<%=id%>)">删除</button>
        </td>
    </tr>
    <%}%>
</table>
<%@include file="success.jsp"%>
<%@include file="error.jsp"%>
</body>
<script>
    function del(id) {
        const b = confirm("你确定要删除吗？");
        if (b) {
            window.location.href = 'delete.jsp?id=' + id;
        }
    }
</script>
</html>
