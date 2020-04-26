<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int x = 1;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add the two numbers</title>
    <link href="/style/calc.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div>

    <form action="/views/calc" method="post">

        <table>
            <tr>
                <td class="output" colspan="4">${}</td>
            </tr>
            <tr>
                <td><input type="submit" name="operator" value="%"></td>
                <td><input type="submit" name="operator" value="CE"></td>
                <td><input type="submit" name="operator" value="C"></td>
                <td><input type="submit" name="operator" value="BS"></td>
            </tr>
            <tr>
                <td><input type="submit" name="value" value="7"></td>
                <td><input type="submit" name="value" value="8"></td>
                <td><input type="submit" name="value" value="9"></td>
                <td><input type="submit" name="operator" value="*"></td>
            </tr>
            <tr>
                <td><input type="submit" name="value" value="4"></td>
                <td><input type="submit" name="value" value="5"></td>
                <td><input type="submit" name="value" value="6"></td>
                <td><input type="submit" name="operator" value="-"></td>
            </tr>
            <tr>
                <td><input type="submit" name="value" value="1"></td>
                <td><input type="submit" name="value" value="2"></td>
                <td><input type="submit" name="value" value="3"></td>
                <td><input type="submit" name="operator" value="+"></td>
            </tr>
            <tr>
                <td><input type="submit" name="value" value="0">0</td>
                <td><input type="submit" name="value" value=".">.</td>
                <td><input type="submit" name="operator" value="=">=</td>
            </tr>
        </table>


    </form>



</div>

</body>
</html>
