<%@ page import="com.project.demo.User" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.project.demo.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.util.concurrent.TimeUnit" %><%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/20
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除</title>
</head>
<body>
<%
    User user = (User)request.getSession().getAttribute("USER");
    if (user == null) {
        response.sendRedirect("login.jsp?error=" + URLEncoder.encode("未登录", "UTF-8"));
        return;
    }
    long id = Long.parseLong(request.getParameter("id"));
    if (user.getId() != id) {
        response.sendRedirect("listUser.jsp?error=" + URLEncoder.encode("删除失败，无权限", "UTF-8"));
        return;
    } else {
        request.getSession().removeAttribute("USER");
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            response.sendRedirect("listUser.jsp?error=" + URLEncoder.encode("数据库连接失败", "UTF-8"));
            return;
        }
        String sql = "delete from user_test where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                response.sendRedirect("listUser.jsp?success=" + URLEncoder.encode("删除成功", "UTF-8"));
                return;
            } else {
                response.sendRedirect("listUser.jsp?error=" + URLEncoder.encode("删除失败", "UTF-8"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>
</body>
</html>
