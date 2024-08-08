<%@ page import="java.sql.Connection" %>
<%@ page import="com.project.demo.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/26
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交处理</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String ageS = request.getParameter("age");
    if ("".equals(ageS)) {
        response.sendRedirect("update.jsp?id=" + id + "&error=" + URLEncoder.encode("年龄不能为空", "UTF-8"));
        return;
    }
    int age = Integer.parseInt(ageS);
    if ("".equals(username) || "".equals(password)) {
        response.sendRedirect("update.jsp?id=" + id + "&error=" + URLEncoder.encode("用户名或密码不能为空", "UTF-8"));
        return;
    }
    if (username.length() > 20 || password.length() > 20) {
        response.sendRedirect("update.jsp?id=" + id + "&error=" + URLEncoder.encode("用户名或密码不能为空", "UTF-8"));
        return;
    }
    // 更新到数据库
    Connection connection = ConnectDB.getConnection();
    if (connection == null) {
        response.sendRedirect("update.jsp?id=" + id + "&error=数据库连接失败");
        return;
    }
    String sql = "update user_test set username = ?, password = ?, age = ? where id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql);) {
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setInt(3, age);
        ps.setLong(4, Long.parseLong(id));

        int i = ps.executeUpdate();
        if (i == 0) {
            String error = "更新失败";
            String encode = URLEncoder.encode(error, "UTF-8");
            response.sendRedirect("listUser.jsp?id=" + id + "&error=" + encode);
        } else {
            String success = "更新成功";
            String encode = URLEncoder.encode(success, "UTF-8");
            response.sendRedirect("listUser.jsp?id=" + id + "&success=" + encode);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

%>
</body>
</html>
