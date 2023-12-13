async function refreshUsers() {
    const response = await fetch("/api/users" ,
        {
            method: "GET"
        });
    console.log(response)
    const users = await response.json();
    const ul = document.getElementById("chat-users");

    ul.innerHTML = "";

    users.forEach(await function (user) {
        var li = document.createElement("li");
        li.id = "user-info-" + user.toString();
        li.onclick = function (event) {
            $("#exampleModalLong").modal('toggle');
            let receiverName = document.getElementById("receiverName");
            receiverName.innerText=user;
            // getHistoryChat(user);
            var hiddenDiv = document.getElementById('hidden-div');
            hiddenDiv.style.display="none";
            const selectedUser = document.getElementsByClassName("selected-user")[0];
            const receiverInput = document.getElementById("receiverInput");
            receiverInput.value=user.toString();
            var target = event.target;
            target.classList.add("selected-user");
            selectedUser.classList.remove("selected-user");
            console.log("receiver:" + user)
        };

        var friendDiv = document.createElement("div");
        friendDiv.classList.add("friend");
        li.appendChild(friendDiv);

        var imgDiv = document.createElement("div");
        imgDiv.classList.add("img-name");
        friendDiv.appendChild(imgDiv);

        var img = document.createElement("img");
        img.id = "usernameImage";
        img.classList.add("ava");
        img.src = user[1];
        imgDiv.appendChild(img);

        var div = document.createElement("div");
        imgDiv.appendChild(div);

        var h3 = document.createElement("h3");
        h3.id = "userNameText";
        h3.innerText = user;

        div.appendChild(h3);
        ul.appendChild(li);

    });
};

setInterval(refreshUsers(), 5000);

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

document.getElementById('groupDiv').addEventListener('click', function(event) {
    console.log("group chat")
    const selectedUser = document.getElementsByClassName("selected-user")[0];
    var target = event.target;
    target.classList.add("selected-user");
    selectedUser.classList.remove("selected-user")
    var hiddenDiv = document.getElementById('hidden-div');
    hiddenDiv.style.display = (hiddenDiv.style.display === 'none' || hiddenDiv.style.display === '') ? 'block' : 'none';
    let modal = document.getElementById("exampleModalLong");
    modal.style.display="none";
});

function getHistoryChat(receiver) {
    let sender = document.getElementById("username").innerText;
    console.log(receiver)

    const resp = fetch("/api/chat/" + receiver + "/" + sender,
        {
            method: "GET"
        });
    const data = resp.json();
    let message = document.getElementById("outputPrivate");
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

function broadcast(){
    let sender = document.getElementById("username").innerText;
    let broadcastMsg = document.getElementById("broadcastMsg").value;
    let msg = "<p>" + sender + " : " + broadcastMsg + "</p>";
    document.getElementById("output").innerHTML += msg + " </br>";
    const response = fetch("api/chat/" + broadcastMsg , {
        method: "post"
    });
    let data = response.json();
    console.log("broadcast: " + data)
}

function privateMsg(){
    let sender = document.getElementById("username").innerText;
    let receiver = document.getElementById("receiverInput").value;
    let messageText = document.getElementById("messageText").value;
    let msg = "<p>" + sender + " : " + messageText + "</p>";
    document.getElementById("outputPrivate").innerHTML += msg + " </br>";
    const response = fetch("api/chat/"+ receiver + "/" + sender + "/" + messageText , {
        method: "POST"
    });
    let data = response.json();
    console.log("private: " + data)

}

