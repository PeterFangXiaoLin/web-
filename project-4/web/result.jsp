<%--
  Created by IntelliJ IDEA.
  User: helloworld
  Date: 2024/5/13
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String t = new String(request.getParameter("radius").getBytes("iso-8859-1"), "utf-8");
  double r = Double.parseDouble(t);
  double c = 2 * r * 3.14;
  double s = r * r * 3.14;
  out.print("圆的周长为：" + c);
  out.print("圆的面积为：" + s);
%>
</body>
</html>
