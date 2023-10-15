var wsUrl = "ws://" + document.location.host + document.location.pathname + "chat";
var ws = new WebSocket(wsUrl);

ws.onmessage = function (event) {
    onMessage(event);
};

ws.onopen = function () {
    onOpen();
};

function onMessage(event) {
    console.log(event);
    display(event.data);
};

function onOpen() {
    console.log("ws opened: " + wsUrl)
};

function display(dataString) {
    var data = JSON.parse(dataString);
    var message = "<p>User " + data.sender.username + " : " + data.message + "</p>";
    document.getElementById("output").innerHTML += message +" </br>";
};

function send() {
    var message = document.getElementById("message").value;
    var username = document.getElementById("username").value;
    var json ={
        "message": message,
        "username" : username
    };
    console.log("sending " + message)
    ws.send(JSON.stringify(json))

}

