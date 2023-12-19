const currentUsername = document.getElementById("username").innerText;

async function refreshUsers() {
    const response = await fetch("/api/users/all", {
        method: "GET"
    });
    const data = await response.json();
    let users = data.users;
    let onlineUsers = data.onlineUsers;
    const ul = document.getElementById("chat-users");
    const filteredUsers = users.filter(user => user.username.toString() !== currentUsername);

    ul.innerHTML = "";

    filteredUsers.forEach(async function (user) {
        let li = document.createElement("li");
        li.id = "user-info-" + user.username.toString();
        li.onclick = function (event) {
            $("#exampleModalLong").modal('toggle');
            let receiverImg = document.getElementById("receiverImg");
            if (user.photo) {
                receiverImg.src = user.photo.filePath;
            } else {
                receiverImg.src = "/jsp/customer/image/person.jpeg";
            }
            li.classList.remove("selected-user");
            li.classList.add("selected-user");
            let receiverName = document.getElementById("receiverName");
            receiverName.innerText = user.username.toString();
            setInterval(getHistoryChat(user.username.toString()) , 2000);
            let hiddenDiv = document.getElementById('hidden-div');
            hiddenDiv.style.display = "none";
            const receiverInput = document.getElementById("receiverInput");
            receiverInput.value = "";
            receiverInput.value = user.username.toString();
            console.log("receiver:" + receiverInput.value);

        };

        let friendDiv = document.createElement("div");
        friendDiv.classList.add("friend");
        li.appendChild(friendDiv);

        let imgDiv = document.createElement("div");
        imgDiv.classList.add("img-name");
        friendDiv.appendChild(imgDiv);

        let img = document.createElement("img");
        img.id = "usernameImage";
        img.classList.add("ava");

        if (user.photo) {
            img.src = user.photo.filePath;
        } else {
            img.src = "/jsp/customer/image/person.jpeg";
        }
        if (onlineUsers.includes(user.username)) {
            friendDiv.style.background="green"
            img.style.border = "3px solid green";
        } else {
            img.style.border = "3px solid gray";
        }
        imgDiv.appendChild(img);

        let div = document.createElement("div");
        imgDiv.appendChild(div);

        let h3 = document.createElement("h3");
        h3.id = "userNameText";
        h3.innerText = user.username;

        div.appendChild(h3);
        ul.appendChild(li);
    });
}

setInterval(refreshUsers, 2000);

const sendAllBtn = document.getElementById("sendAllBtn");
const sendPrivateBtn = document.getElementById("sendPrivateBtn");
let messageText = document.getElementById("messageText");
let broadcastMsg = document.getElementById("broadcastMsg");

messageText.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        sendPrivateBtn.click();
    }
});

broadcastMsg.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        sendAllBtn.click();
    }
});

sendAllBtn.addEventListener("click", function handleClick(event) {
    event.preventDefault();
    const firstInput = document.getElementById("broadcastMsg");
    firstInput.value = "";
});

sendPrivateBtn.addEventListener("click", function handleClick(event) {
    event.preventDefault();
    const firstInput = document.getElementById("messageText");
    firstInput.value = "";
});

document.getElementById('groupDiv').addEventListener('click', function (event) {
    console.log("group chat")
    const selectedUser = document.getElementsByClassName("selected-user")[0];
    let target = event.target;
    target.classList.add("selected-user");
    selectedUser.classList.remove("selected-user")
    let hiddenDiv = document.getElementById('hidden-div');
    hiddenDiv.style.display = (hiddenDiv.style.display === 'none' || hiddenDiv.style.display === '') ? 'block' : 'none';
    let modal = document.getElementById("exampleModalLong");
    modal.style.display = "none";
});

async function getHistoryChat(receiver) {
    const resp = await fetch("/api/chat/history/" + receiver + "/" + currentUsername, {
        method: "GET"
    });

    const data = await resp.json();
    let message = document.getElementById("outputPrivate");
    let msg = "";
    let messageClass = null;
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


function broadcast() {
    let sender = document.getElementById("username").innerText;
    let broadcastMsg = document.getElementById("broadcastMsg").value;
    let msg = "<p class='sender'>" + sender + " : " + broadcastMsg + "</p>";
    document.getElementById("output").innerHTML += msg + " </br>";
    const response = fetch("api/chat/broadcast/" + sender + "/" + broadcastMsg, {
        method: "post"
    });

}

async function privateMsg() {
    let sender = currentUsername;
    let receiver = document.getElementById("receiverInput").value;
    let messageText = document.getElementById("messageText").value;
    // document.getElementById("outputPrivate").innerHTML += "<p>" + sender + ": " + messageText + "</p>" + " </br>"
    const response = await fetch("api/chat/private/" + receiver + "/" + sender + "/" + messageText, {
        method: "POST"
    });
    await getHistoryChat(receiver);

}






