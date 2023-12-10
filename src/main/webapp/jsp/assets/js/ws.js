let wsUrl = "ws://localhost:80/chat";
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

// todo: show message
function display(dataString) {
    console.log("ine " + dataString)
    let data = JSON.parse(dataString);
    console.log("data " + data)
    let msg = "<p>" + data.message + "</p>";
    document.getElementById("output").innerHTML += msg + " </br>";
    // document.getElementById("user").innerHTML = data.username +" </br>";
}

function send() {
    console.log("send method")
    let message = document.getElementById("broadcastMsg").value;
    console.log("broadcast: " + message)
    ws.send(JSON.stringify(message))

}


