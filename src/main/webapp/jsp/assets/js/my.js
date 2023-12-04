const userInfo = document.getElementById("user-info");
userInfo.addEventListener("click", async function () {
    let receiver = document.getElementById("receiver").innerText;
    setUserAsReceiver(receiver)
    console.log("Div was clicked!" + receiver);
});

function setUserAsReceiver(receiverName) {
    let receiver = document.getElementById("receiver").innerText;
    let message = document.getElementById("message").value;
    let sender = document.getElementById("username").innerText;
    console.log(receiver)
    const response = fetch("/api/chat/" + receiver + "/" + sender + "/" + message,
        {
            method: "POST"
        });
}