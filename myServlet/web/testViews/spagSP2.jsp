<%--
  Created by IntelliJ IDEA.
  User: Seunghoon Baik
  Date: 4/25/2020
  Time: 11:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%-- MVC Model 2 방식 --%>

<html>
<head>
    <title>Spaghetti Code MVC 2</title>
</head>

<%
    pageContext.setAttribute("result", "Hello Page Context Scope");
%>

<body>

<h1> MVC 2 </h1>

${requestScope.result} --> Request Scope<br>
${pageScope.result} --> Page Scope<br>
${names[0]}, ${names[1]}<br>
${notice.title}<br>
${param.num} --> Parameter<br>
${param.num >= 3} --> param.num >= 3<br>
${param.num ge 3} --> param.num ge 3<br>
${param.num <= 3} --> param.num <= 3<br>
${param.num le 3} --> param.num le 3<br>
${param.num > 3} --> param.num > 3<br>
${param.num gt 3} --> param.num gt 3<br>
${param.num < 3} --> param.num < 3<br>
${param.num lt 3} --> param.num lt 3<br>
${empty param.num} --> empty param.num<br>
${param.num == null || param.num.equals("")} --> param.num == null || param.num.equals("")<br>
${not empty param.num} --> not empty param.num<br>
${param.num != null || !param.num.equals("")} --> param.num != null || !param.num.equals("")<br>
${header.accept} --> Header Accept<br>

</body>
</html>
