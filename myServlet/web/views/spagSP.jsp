<%--
  Created by IntelliJ IDEA.
  User: Seunghoon Baik
  Date: 4/25/2020
  Time: 11:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int num = 0;
    String numStr = request.getParameter("num");

    if(numStr != null && !numStr.equals(""))
    {
        num = Integer.parseInt(numStr);
    }// if

    String result = "";

    if(num % 2 != 0)
    {
        result = "Odd";
    }// if
    else
    {
        result = "Even";
    }// else

%>
<%-- MVC Model 1 방식 --%>
<%-- ------------------------------------------------------------------------------- ---%>


<html>
<head>
    <title>Spaghetti Code MVC 1</title>
</head>
<body>

<h1> MVC 1 </h1>

<%= result %> Number!!!

</body>
</html>
