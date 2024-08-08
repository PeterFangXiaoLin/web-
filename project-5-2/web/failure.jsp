<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/27
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>失败</title>
</head>
<body>
<%
    String error = request.getParameter("error");
    if (error != null && !error.isEmpty()) {
        error = URLDecoder.decode(error, "UTF-8");

%>
<script>
    alert('<%= error %>')
</script>
<%}%>
</body>
</html>
