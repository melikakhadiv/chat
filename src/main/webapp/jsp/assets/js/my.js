async function refreshUsers() {
    const response = await fetch("/api/users",
        {
            method: "GET"
        });
    const users = await response.json();
    const ul = document.getElementById("chat-users");

    ul.innerHTML = "";

    users.forEach(await function (user) {
        var li = document.createElement("li");
        li.id = "user-info-" + user.toString();

        //todo: show modal
        li.onclick = function (event) {
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


