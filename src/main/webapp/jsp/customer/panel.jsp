<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat Box Panel</title>
    <link rel="stylesheet" href="/jsp/assets/css/myCss.css">
<%--    <jsp:include page="/jsp/css-import.jsp"></jsp:include>--%>
</head>
<body>
<div class="top">
    <form action="/User" method="post">
    <input class="ico3 float-right" name="logout" id="logout" type="submit" value="Logout">
    </form>
</div>
<div class="box">
    <div class="left">
        <div class="topp ">
            <div class="img-name ">
                <img src="${(sessionScope.get("userImage")== null)? "/jsp/customer/image/person.jpeg" : sessionScope.get("userImage")}"
                     class="ava" alt="">
                <div >
                    <h3 id="username">${sessionScope.username}</h3>
                </div>
            </div>
            <form action="/User" method="get" target="_blank">
                <input class="ico3 float-right" name="usersTable" id="usersTable"  type="submit" value="users">
            </form>

        </div>
        <div class="topp" id="groupDiv">
            <h3 class="" id="groupChat">Group Chat</h3>
        </div>
        <div>
            <ul id="chat-users">

            </ul>
        </div>
    </div>

    <%--group chat--%>
    <div class="modal fade right" id="hidden-div" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLongTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header right-top">
                    <h3>Group Chat</h3>
                </div>
                <div id="group" class="mid">
                    <div id="output"></div>
                </div>
                <div class="btm">
                    <input id="broadcastMsg" class="in2" type="text"
                           placeholder="a message for all your friend ... "
                           name="broadcastMsg">
                    <button class="ico3" id="sendAllBtn" onclick="broadcast()">Send</button>
                </div>
            </div>
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
                    <div id="outputPrivate"></div>
                </div>
                <div class="btm">
                    <input type="hidden" id="receiverInput" name="receiver" value="">
                    <input type="text" id="messageText" class="in2" placeholder="typing..." name="message">
                    <button class="ico3" id="sendPrivateBtn" onclick="privateMsg()">Send</button>
                </div>
            </div>
        </div>
    </div>

</div>
<jsp:include page="/jsp/js-import.jsp"></jsp:include>
<script src="jsp/assets/js/ws.js"></script>

</body>
</html>