async function refreshUsers() {
    let user = document.getElementById("username");
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
            getHistoryChat(user);
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

const btn = document.getElementById("sendToAllBtn");
const sendBtn = document.getElementById("sendBtn");
var input = document.getElementById("messageText");
input.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        btn.click();

    }
});
btn.addEventListener("click", function handleClick(event) {
    event.preventDefault();
    const firstInput = document.getElementById("messageText");
    firstInput.value = "";
});

document.getElementById('groupChat').addEventListener('click', function(event) {
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


