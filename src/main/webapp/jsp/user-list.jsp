<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button onclick="getAllUsers()" type="button" class="icon1">refresh</button>
<table class="table-hover table" >
    <thead>
    <tr>
        <th>Username</th>
        <th>Online</th>
    </tr>
    </thead>
    <tbody id="userTableBody">
    </tbody>
</table>
</body>
<jsp:include page="js-import.jsp"></jsp:include>
<script src="/jsp/assets/js/my.js"></script>
</html>
