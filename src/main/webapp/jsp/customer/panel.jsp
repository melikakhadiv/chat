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
            <div class="img-name">
                <img src="${sessionScope.get("userImage")}" class="ava" alt="">
                <div>
                    <h3 id="username">${sessionScope.username}</h3>
                </div>
            </div>
        </div>
        <div class="topp">
            <h3 class="" id="groupChat">Group Chat</h3>
        </div>
        <div>
            <ul id="chat-users">

            </ul>
        </div>
    </div>

    <%--group chat--%>
    <div id="hidden-div" class="right">
        <div  class="right-top">
            <h3>Group Chat</h3>
        </div>
        <div id="group" class="mid">
            <div id="output" class="${sessionScope.sender != null ? "sender" : "receiver"}"></div>
        </div>
        <div class="btm">
            <input id="broadcastMsg" class="in2" type="text" placeholder="a message for all your friend ... "
                   name="broadcastMsg">
            <input type="submit" id="sendToAllBtn" onclick="send()" value="Send" class="ico3">
        </div>
    </div>

    <%--modal private chat--%>
    <div class="modal fade right" id="exampleModalLong" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLongTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header right-top">
                    <h3 class="modal-title" id="receiverName"></h3>
                </div>
                <div class="mid">
                    <div id="outputPrivate"
                         class="${sessionScope.sender != null ? "sender" : "receiver"}"></div>
                </div>
                <div class="btm">
                    <form action="/chat" method="post">
                        <input type="hidden" id="receiverInput" name="receiver" value="">
                        <input type="text" id="messageText" class="in2" placeholder="typing..." name="message">
                        <input type="submit" id="sendBtn" name="sendBtn" value="Send" class="ico3">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/js-import.jsp"></jsp:include>
<script src="jsp/assets/js/ws.js"></script>

</body>
</html>