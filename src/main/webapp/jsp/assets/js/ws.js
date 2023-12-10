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


function display(dataString) {
    console.log("ine " + dataString)
    let data = JSON.parse(dataString);
    console.log("data " + data)
    let msg = "<p>"  + data.message + "</p>";
    document.getElementById("output").innerHTML += msg + " </br>";
    // document.getElementById("user").innerHTML = data.username +" </br>";
}

function send() {
    console.log("send method")
    let broadcastMsg = document.getElementById("broadcastMsg").value;
    let messageText = document.getElementById("messageText").value;
    if (broadcastMsg != null){
        console.log("broadcast: " + broadcastMsg)
        ws.send(JSON.stringify(broadcastMsg))
    }else if (messageText != null){
        console.log("message: " + broadcastMsg)
        ws.send(JSON.stringify(messageText))
    }

}


