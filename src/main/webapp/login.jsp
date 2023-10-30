<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/30/2023
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <jsp:include page="/css-import.jsp"></jsp:include>
</head>
<body>
<form action="j_security_check" method="post">
    <label for="userId">UserName</label>
    <input id="userId" type="text" name="j_username">
    <label for="passId">Password</label>
    <input id="passId" type="password" name="j_password">
    <input type="submit" value="Login">
</form>

<jsp:include page="/js-import.jsp"></jsp:include>
</body>
</html>
