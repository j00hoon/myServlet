<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Seunghoon Baik
  Date: 4/25/2020
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int cnt = 3;
    String cnt_str = request.getParameter("cnt");

    if(cnt_str != null && !cnt_str.equals(""))
    {
        cnt = Integer.parseInt(request.getParameter("cnt"));
    }// if
%>

<html>
<head>
    <title>Hello JSP</title>
</head>
<body>

<% for(int i = 0; i < cnt; i++){%>
    Hello<br>
<% } %>

</body>
</html>
