<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Cache-Control" content="no-store,no-cache,must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <title>login</title>
    <jsp:include page="/jsp/css-import.jsp"></jsp:include>
</head>
<body>
<form action="j_security_check" method="post">
    <label for="userId">UserName</label>
    <input id="userId" type="text" name="j_username">
    <label for="passId">Password</label>
    <input id="passId" type="password" name="j_password">
    <input type="submit" value="Login">
</form>
</body>
</html>
