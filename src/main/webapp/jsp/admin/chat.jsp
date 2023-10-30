<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/16/2023
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <div class="col-10">
        <div id="output"></div>
        <input id="username" placeholder="username"><br/>
        <input id="message" type="text">
        <button onclick="send()">Send</button>
    </div>
    <%--    <div class="col-2">--%>
    <%--        <input id="user" >--%>
    <%--    </div>--%>
</div>

<script src="assets/js/ws.js"></script>
</div>
</body>
</html>
