let wsUrl = "ws://localhost/chat";
let ws = new WebSocket(wsUrl);

ws.onmessage = function (event) {
    onMessage(event);
};

ws.onopen = function () {
    onOpen();
};

window.onbeforeunload = function() {
    console.log("ws closed: " + wsUrl)
    ws.onclose = function () {}; // disable onclose handler first
    ws.close();
};

function onMessage(event) {
    console.log(event);
    display(event.data);
}

function onOpen() {
    console.log("ws opened: " + wsUrl)
}



function display(dataString) {
    console.log(dataString)
    let data = JSON.parse( dataString );
    let message = "<p>User " + data.username + " said : " + data.message + "</p>";
    document.getElementById("output").innerHTML += message +" </br>";
    // document.getElementById("user").innerHTML = data.username +" </br>";
}

function send() {
    let message = document.getElementById("message").value;
    let username = document.getElementById("username").value;
    // let user = document.getElementById("user").value;
    let json ={
        "message": message,
        "username" : username ,
    };
    console.log("sending " + message)
    ws.send(JSON.stringify(json))

}

