<%@ page import="java.sql.Connection" %>
<%@ page import="com.project.demo.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/19
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>添加</title>
</head>
<body>
    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));

        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            out.print("数据库连接失败");
            response.setHeader("refresh", "5;url=register.jsp");
        } else {
            String sql = "insert into user_test (username, password, age) values (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setInt(3, age);
                int count = preparedStatement.executeUpdate();
                if (count > 0) {
                    out.print("注册成功, 3秒后跳转");
                    response.setHeader("refresh", "3;url=login.jsp");
                } else {
                    out.print("注册失败，系统错误");
                    response.setHeader("refresh", "5;url=register.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    %>
</body>
</html>
