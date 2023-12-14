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
    console.log("onmessage: " + event.data);
    display(event.data);
}

function onOpen() {
    console.log("ws opened: " + wsUrl)

}


function display(dataString) {
    let broadcastMsg = document.getElementById("broadcastMsg").value;
    let  messageText = document.getElementById("messageText").value;
    let  sender = document.getElementById("username").innerText;
    console.log("ine " + dataString)
    let data = JSON.parse(dataString);
    console.log("data " + data)
    let msg = "<p>" + data + "</p>";
    if (broadcastMsg != null){
        document.getElementById("output").innerHTML += sender + ": "+ msg + " </br>";
    }else if (messageText != null){
        document.getElementById("outputPrivate").innerHTML += sender + ": "+ msg + " </br>";
    }
}

function send() {
    console.log("send method")
    let broadcastMsg = document.getElementById("broadcastMsg").value;
    let  messageText = document.getElementById("messageText").value;
   if (broadcastMsg != null){
       ws.send(JSON.stringify(broadcastMsg))
   }else if (messageText != null){
       ws.send((JSON.stringify(messageText)))
   }
}


