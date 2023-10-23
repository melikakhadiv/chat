<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Chat-App
</h1>
<br/>
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
</body>
</html>