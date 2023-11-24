<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>chat box</title>
    <link rel="stylesheet" href="/jsp/assets/css/myCss.css">
</head>
<body>
<div class="top"></div>
<div class="box">
    <div class="left">
        <div class="topp">
            <h3>CHATBOX</h3>
        </div>
        <div class="search">
            <input class="in" type="text" placeholder="search buddy..">
            <div class="ico">
                <!--               put search icon-->
                <img src="" class="icon1" alt="">
            </div>
        </div>
        <ul id="chat-users">
            <li id="user-info">
                <div class="friend">
                    <div class="img-name">
                        <img id="usernameImage" src="" class="ava" alt="">
                        <div>
                            <h3 id="usernameText">Users</h3>
                        </div>
                    </div>
                    <div class="time"><p class="p">Today</p></div>
                </div>
            </li>
        </ul>
    </div>
    <div class="right">
        <div class="right-top">
            <div class="img-name">
                <img src="" class="ava" alt="">
                <div>
                    <input type="text" id="username" class="in2" placeholder="username">
                </div>
            </div>
            <!--            put three dot icon (more menu)-->
            <img src="" class="icon2" alt="">
        </div>

        <div class="mid">
            <div id="output" class="${sessionScope.sender != null ? "sender" : "receiver"}"></div>
        </div>
        <div class="btm">
            <input type="text" id="message" class="in2" placeholder="typing...">
            <button class="ico3" onclick="send()">Send</button>
            <%--                        <ion-icon name="paper-plane-outline"></ion-icon>--%>
        </div>
    </div>
</div>
<jsp:include page="../assets/js/ws.js"></jsp:include>
<jsp:include page="/jsp/js-import.jsp"></jsp:include>

<script>
    setInterval(async function () {
        const response = await fetch("/api/users",
            {
                method: "GET"
            });
        const users = await response.json();

        const ul = document.getElementById("chat-users");

        ul.innerHTML = "";

        users.forEach(function (user) {
            const li = document.getElementById("userF-info").cloneNode(true);
            li.getElementById("usernameText").innerText = user;
            ul.appendChild(li);
        });

        // show users on table
    }, 5000);
</script>
</body>
</html>