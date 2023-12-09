<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat Box Panel</title>
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
            <input id="broadcastMsg" class="in" type="text" placeholder="a message for all your friend ... " name="broadcastMsg">
            <button id="sendToAllBtn" onclick="send()" class="ico3">Send To All</button>
            <div class="ico">
                <i class="fa fa-search"></i>
            </div>
        </div>
        <ul id="chat-users">

        </ul>
    </div>
    <div class="right">
        <div class="right-top">
            <div class="img-name">
                <img src="${sessionScope.get("userImage")}" class="ava" alt="">
                <div>
                    <h3 id="username">${sessionScope.username}</h3>
                </div>
            </div>
        </div>
        <div class="mid">
            <div id="output" class="${sessionScope.sender != null ? "sender" : "receiver"}"></div>
        </div>
        <div class="btm">
            <form action="/chat" method="post">
                <input type="text" id="messageText" class="in2" placeholder="typing..." name="message">
                <input type="submit" name="sendBtn" value="Send" class="ico3">
            </form>
        </div>
    </div>
</div>
<jsp:include page="/jsp/js-import.jsp"></jsp:include>
<script src="jsp/assets/js/ws.js"></script>
<script>
    async function refreshUsers() {
        const response = await fetch("/api/users",
            {
                method: "GET"
            });
        const users = await response.json();
        const ul = document.getElementById("chat-users");


        ul.innerHTML = "";

        users.forEach(await function (user) {
            var li = document.createElement("li");
            li.id = "user-info";
            li.onclick = function (event) {
                const selectedUser = document.getElementsByClassName("selected-user")[0];
                var target = event.target;
                //todo: set receiver onclick
                receiverInput.setAttribute("name","receiver");
                target.classList.add("selected-user");
                selectedUser.classList.remove("selected-user");
                console.log("name: " , receiverInput.value)
            };
            var friendDiv = document.createElement("div");
            friendDiv.classList.add("friend");
            li.appendChild(friendDiv);

            var imgDiv = document.createElement("div");
            imgDiv.classList.add("img-name");
            friendDiv.appendChild(imgDiv);

            var img = document.createElement("img");
            img.id = "usernameImage";
            img.classList.add("ava");
            img.src = user[1];
            imgDiv.appendChild(img);

            var div = document.createElement("div");
            imgDiv.appendChild(div);

            var receiverInput = document.createElement("input");
            // receiverInput.id = "userNameText";
            receiverInput.value = user;
            receiverInput.type="text";
            receiverInput.readOnly=true;




            div.appendChild(receiverInput);
            ul.appendChild(li);
        });
    };

    // refreshUsers();
    setInterval(refreshUsers(), 5000);

</script>
</body>
</html>