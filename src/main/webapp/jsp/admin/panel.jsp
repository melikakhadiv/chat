<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>chat box</title>
    <link rel="stylesheet" href="/assets/css/myCss.css">
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
        </div>
        <ul>
            <li>
                <div class="friend">
                    <div class="img-name">
                        <img src="" class="ava" alt="">
                        <div>
                            <h3 id="user-list">Users</h3>
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
                    <input type="text" id="username" class="in2" value="${username}">
                </div>
            </div>
            <!--            put three dot icon (more menu)-->
            <img src="" class="icon2" alt="">
        </div>

        <div class="mid">
            <div id="output" class="${sessionScope.sender != null ? "sender" : "receiver"}"></div>
        </div>
        <div class="btm">
            <button class="ico3" id="file">+</button>
            <input type="text" id="message" class="in2" placeholder="  typing...">
            <button class="ico3" id="sendBtn" type="submit" onclick=send()>Send</button>
            <%--                        <ion-icon name="paper-plane-outline"></ion-icon>--%>
        </div>
    </div>
</div>

<button onclick="getUsers()">Get Users</button>

<jsp:include page="/js-import.jsp"></jsp:include>
<script src="/assets/js/ws.js">
    console.log("test")
</script>

<script>
    async function getUsers() {
        // setInterval(async function () {
        const response = await fetch("/api/users",
            {
                method: "GET"
            });
        const users = await response.json();
        document.getElementById("user-list").innerText = users;

        const ul = document.getElementById("ul");
        ul.innerHTML = "";

        users.forEach(function (user){
            var li = document.createElement("li");
            li.appendChild(document.createTextNode(user));
            ul.appendChild(li);
        });




        console.log(users);
        // show users on table
        // }, 50000)
    }
</script>

</body>
</html>