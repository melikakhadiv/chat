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
<div class="noscript"><h2 style="color: #ff0000">Seems your browser doesn't support JavaScript! Websockets rely on JavaScript being enabled. Please enable
    JavaScript and reload this page!</h2></div>
<div>
    <p>
        <input type="text" placeholder="type and press enter to chat" id="chat" />
    </p>
    <div id="console-container">
        <div id="console"/>
    </div>

    <script src="/assets/js/websocket.js"></script>
</div>
</body>
</html>
