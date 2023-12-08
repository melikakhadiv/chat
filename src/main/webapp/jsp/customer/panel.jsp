<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>chat box</title>
    <jsp:include page="../css-import.jsp"></jsp:include>
</head>
<body>
<div class="top"></div>
<div class="box">
    <div class="left">
        <div class="topp">
            <h3>CHATBOX</h3>
        </div>
        <div class="search">
            <div class="ico">
              <button id="sendToAll" class="ico3" onclick="">Send To All</button>
            </div>
        </div>
        <button onclick="getOnlineUsers()">Get Users</button>
        <form action="/chat" method="post">
            <div>
                <select name="receiver" id="users">

                </select>
            </div>

            <%--    <input  type="text" name="receiver">--%>
            <input  type="text" name="message">
            <input type="submit" value="Send">
        </form>
    </div>
    <div class="right">
        <div class="right-top">
            <div class="img-name">
                <%--                <img src="" class="bi bi-person" alt="-">--%>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-person-fill" viewBox="0 0 16 16">
                    <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                </svg>
                <div>
                    <h3 id="username" class="in2">${username}</h3>
                </div>
            </div>
        </div>

        <div class="mid">
            <div id="output" class="${sender == username ? "sender" : "receiver"}"></div>
        </div>
        <div class="btm">
            <input type="text" id="message" class="in2" placeholder="typing...">
            <button class="ico3" id="sendBtn" onclick="send()">Send</button>
        </div>
    </div>
</div>

<script>
    // setInterval(
        async function getOnlineUsers() {
        const response = await fetch("/api/users",
            {
                method: "GET"
            });
        const users = await response.json();

        const usersCmb = document.getElementById("users");
        users.forEach(function (user){
            const userOption = document.createElement("option");
            userOption.innerHTML = user;
            userOption.value = user;
            usersCmb.appendChild(userOption);
        });
        console.log(users);

    }
    // , 5000);
</script>

<jsp:include page="/jsp/js-import.jsp"></jsp:include>
<%-- todo:  cloneNode has an error--%>
<script src="jsp/assets/js/ws.js"></script>
</body>
</html>
