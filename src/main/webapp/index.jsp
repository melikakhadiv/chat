<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Chat-App
</h1>
<br/>


<%--<a href="${"/jsp/"}${role}${"/panel.jsp"}">Login</a>--%>
<a href="${"/jsp/"}${"admin"}${"/panel.jsp"}">Login</a>
<%--<a href="/login.jsp">Login</a>--%>
<a href="/signup.jsp">Signup</a>
<a href="/chat.jsp">chat</a>
</body>
</html>