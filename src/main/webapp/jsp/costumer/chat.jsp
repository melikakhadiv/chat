<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/30/2023
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../css-import.jsp"></jsp:include></head>
<body>
<div class="row">
    <div class="col-10">
        <div id="output"></div>
        <input id="username" placeholder="username" value="${sessionScope.username}"><br/>
<%--        ok shod mersi--%>
        <input id="message" type="text">
        <button onclick="send()">Send</button>
    </div>
    <%--    <div class="col-2">--%>
    <%--        <input id="user" >--%>
    <%--    </div>--%>
</div>

<%--<jsp:include page="../../js-import.jsp"></jsp:include>--%>
<script src="/assets/js/ws.js"></script>
</body>
</html>
