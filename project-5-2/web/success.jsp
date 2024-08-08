<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/26
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功处理</title>
</head>
<body>
<%
    String success = request.getParameter("success");
    if (success != null && !success.isEmpty()) {
        success = URLDecoder.decode(success, "UTF-8");
%>
<script>
    alert('<%= success %>');
</script>

<%
    }
%>
</body>
</html>
