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

<input id="userId" placeholder="username">
<div id="output"></div>
<input id="message" type="text">
<button onclick="send()">Send</button>


<script src="assets/js/ws.js"></script>
</body>
</html>