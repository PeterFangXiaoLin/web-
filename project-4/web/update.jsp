<%@ page import="com.project.demo.User" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.project.demo.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/20
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
</head>
<body>
<%@include file="success.jsp"%>
<%@include file="error.jsp"%>
<%
    long id = Long.parseLong(request.getParameter("id"));
    Connection connection = ConnectDB.getConnection();
    if (connection == null) {
        throw new RuntimeException("数据库连接失败");
    }
    String sql = "select * from user_test where id= ?";
    User user = null;
    try (PreparedStatement ps = connection.prepareStatement(sql);) {
        ps.setLong(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong(1));
            user.setUsername(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setAge(resultSet.getInt(4));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<form action="edit.jsp?id=<%=id%>" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td>
                <input type="text" name="username" value="<%=user.getUsername()%>">
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="text" name="password" value="<%=user.getPassword()%>">
            </td>
        </tr>
        <tr>
            <td>年龄</td>
            <td>
                <input type="text" name="age" value="<%=user.getAge()%>">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
