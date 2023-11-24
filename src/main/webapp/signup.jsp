<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/30/2023
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signup</title>
    <jsp:include page="/jsp/css-import.jsp"></jsp:include>
</head>
<body>
<form action="/User" method="post" enctype="multipart/form-data">
    <input type="text" name="username" placeholder="username"><br>
    <input type="text" name="password" placeholder="password"><br>
    <input type="text" name="firstname" placeholder="firstname"><br>
    <input type="text" name="lastname" placeholder="lastname"><br>
    <input type="text" name="nickname" placeholder="nickname"><br>
    <input type="radio" name="privateAcc" id="privateAcc" value="privateAcc">
    <label for="privateAcc"> Private Account</label><br>
    <input type="file" name="file" placeholder="img"><br>
    <input type="submit" value="sign up">
</form>

<jsp:include page="/jsp/js-import.jsp"></jsp:include>
</body>
</html>
