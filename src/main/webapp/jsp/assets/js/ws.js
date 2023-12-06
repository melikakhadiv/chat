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
    let msg = "<p>" + data.username + " : " + data.message + "</p>";
    document.getElementById("output").innerHTML += msg + " </br>";
    // document.getElementById("user").innerHTML = data.username +" </br>";
}

function send() {
    console.log("send method")
    let message = document.getElementById("message").value;
    let username = document.getElementById("username").innerText;
    let receiver = document.getElementById("receiver").innerText;
    let sender = document.getElementById("username").innerText;
    let chat = {
        message: message,
        username: username,
        sender: sender,
        receiver: receiver
    };
    console.log("this is receiver:" + receiver)
    console.log("sending " + message)
    ws.send(JSON.stringify(chat))
    const response = fetch("/api/chat/" + receiver + "/" + sender + "/" + message,
        {
            method: "POST"
        });
}

const btn = document.getElementById("sendBtn");
var input = document.getElementById("message");
input.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        btn.click();

    }
});
btn.addEventListener("click", function handleClick(event) {
    event.preventDefault();
    const firstInput = document.getElementById("message");
    firstInput.value = "";
});
