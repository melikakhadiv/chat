const userInfo = document.getElementById("user-info");
userInfo.addEventListener("click", async function () {
    let receiver = document.getElementById("receiver").innerText;
    setUserAsReceiver(receiver)
    console.log("receiver div was clicked!");
});

function setUserAsReceiver(receiverName) {
    let receiver = document.getElementById("receiver").innerText;
    let sender = document.getElementById("username").innerText;
    console.log("receiver: " + receiver)
    const response = fetch("/api/chat/" + receiver + "/" + sender,
        {
            method: "GET"
        });
    const data = response.json();
    let message = document.getElementById("output");
    var msg = '';
    for (var i = 0; i < data.length; i++) {
        for (var j = 0; j < data[i].length; j++) {
            msg += data[i][j].message+ ' ';
            console.log(msg)
        }
        msg = '<div>' + msg + '</div>';
    }
    message.innerHTML = msg
}