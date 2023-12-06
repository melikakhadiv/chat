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
        <ul id="chat-users">
            <li id="user-info">
                <div class="friend">
<%--                    <div class="img-name">--%>
<%--                        &lt;%&ndash;                        <img id="usernameImage" src="image/person.jpeg" alt="img">&ndash;%&gt;--%>
<%--                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"--%>
<%--                             class="bi bi-person-fill" viewBox="0 0 16 16">--%>
<%--                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>--%>
<%--                        </svg>--%>
<%--    <c:forEach var="user" items="${onlineUsers}">--%>
<%--                        <div>--%>
<%--                            <h6 id="usernameText">${user.username}</h6>--%>
<%--                        </div>--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--                </c:forEach>--%>
    <div>
        <h6 id="receiver">melika</h6>
    </div>
                    <div class="time"><p class="p">Today</p></div>
                </div>
            </li>
            <li>
                <div class="friend">
                    <h6>sara</h6>
                </div>
            </li>
        </ul>
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

<%--<script>--%>
<%--    setInterval(async function () {--%>
<%--        const response = await fetch("/api/users",--%>
<%--            {--%>
<%--                method: "GET"--%>
<%--            });--%>
<%--        const users = await response.json();--%>

<%--        const ul = document.getElementById("chat-users");--%>

<%--        ul.innerHTML = "";--%>

<%--        users.forEach(function (user) {--%>
<%--            const li = document.getElementById("user-info").cloneNode(true);--%>
<%--            li.getElementById("usernameText").innerText = user;--%>
<%--            ul.appendChild(li);--%>
<%--        });--%>

<%--// show users on table--%>
<%--    }, 5000);--%>
<%--</script>--%>

<jsp:include page="/jsp/js-import.jsp"></jsp:include>
<%-- todo:  cloneNode has an error--%>
<script src="jsp/assets/js/ws.js"></script>
</body>
</html>
