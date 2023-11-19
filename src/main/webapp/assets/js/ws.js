let wsUrl = "ws://localhost/chat";
let ws = new WebSocket(wsUrl);

ws.onmessage = function (event) {
    onMessage(event);
};

ws.onopen = function () {
    onOpen();
};

window.onbeforeunload = function () {
    console.log("ws closed: " + wsUrl)
    ws.onclose = function () {
        ws.close();
    }; // disable onclose handler first

};

function onMessage(event) {
    console.log("onmessage" + event);
    display(event.data);
}

function onOpen() {
    console.log("ws opened: " + wsUrl)
}

function display(dataString) {
    console.log("ine " + dataString)
    let data = JSON.parse(dataString);
    console.log("data " + data)
    let msg = "<p>User " + username + " said : " + message + "</p>";
    document.getElementById("output").innerHTML += msg + " </br>";
    // document.getElementById("user").innerHTML = data.username +" </br>";
}

function send() {
    let message = document.getElementById("message").value;
    let username = document.getElementById("username").value;
    // let user = document.getElementById("user").value;
    let msg = {
        "message": message,
        "username": username,
    };
    console.log("sending " + message)
    ws.send(JSON.stringify(msg))
}

