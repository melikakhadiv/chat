<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="refresh" content="1">
</head>
<body>

<table class="table-hover table" >
    <thead>
    <tr>
        <th>Username</th>
        <th>Online</th>
    </tr>
    </thead>
    <tbody id="userTableBody">
    <c:forEach var="session" items="${sessionScope.sessions}">
        <td>${session}</td>
        <td></td>
    </c:forEach>
    </tbody>
</table>
</body>
<jsp:include page="jsp/js-import.jsp"></jsp:include>
<script src="/jsp/assets/js/my.js"></script>
</html>
