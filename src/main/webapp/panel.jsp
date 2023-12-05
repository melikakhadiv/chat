;<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>chat box</title>
    <link rel="stylesheet" href="assets/css/myCss.css">
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
            <c:forEach var="user" items="${sessionScope.username}">
            <li>
                <div class="friend">
                    <div class="img-name">
                        <img src="" class="ava" alt="">
                        <div>
                            <h3>${user}</h3>
                        </div>
                    </div>
                    <div class="time"><p class="p">Today</p></div>
                </div>
            </li>
            </c:forEach>
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
            <div id="output"  class="${sessionScope.sender != null ? "sender" : "receiver"}" ></div>
        </div>
        <div class="btm">
            <button class="ico3" id="file">+</button>
            <input type="text" id="message" class="in2" placeholder="  typing...">
            <button class="ico3" id="sendBtn" onclick=send()>Send</button>
            <%--                        <ion-icon name="paper-plane-outline"></ion-icon>--%>
        </div>
    </div>
</div>


<jsp:include page="jsp/js-import.jsp"></jsp:include>
<script src="/assets/js/ws.js">
    console.log("test")
</script>
</body>
</html>