<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 17.09.2019
  Time: 21:07
  To change this template_data use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login-page</title>
</head>
<body>
<form action="j_security_check" method="post">
    Username : <input type="text" name="j_username"/><br>
    Password : <input type="password" name="j_password"/>
    <input type="submit" value="login"/>
</form>
</body>
</html>
