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
    };

};

function onMessage(event) {
    display(event.data)
}

function onOpen() {
    console.log("ws opened: " + wsUrl)

}


function display() {
    updatePrivateChat();
}


async function updatePrivateChat() {
    let receiver = document.getElementById("receiverInput").value
    const resp = await fetch("/api/chat/history/" + receiver + "/" + currentUsername, {
        method: "GET"
    });
    const data = await resp.json();
    let message = document.getElementById("outputPrivate");
    let msg = "";
    for (let i = 0; i < data.length; i++) {
        if (data[i].sender.username === currentUsername) {
            if (data[i].received === true) {
                messageClass = "sender"
            } else {
                messageClass = "offLineReceiver"
            }
        } else if (!(data[i].sender.username === currentUsername)) {
            messageClass = "receiver"
        }

        msg += `<div class="${messageClass}"> ${data[i].sender.username} : ${data[i].message}</div>`;
    }

    message.innerHTML = msg;
}




