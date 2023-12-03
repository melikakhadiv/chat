const userInfo = document.getElementById("user-info");
userInfo.addEventListener("click", async function () {
    // todo: set receiver on session
    var receiver = document.getElementById("user-info").innerText;
    const response = await fetch("/Chat?" + new URLSearchParams({
        receiver: receiver,
    }),
        {
            method: "POST"
        })
    console.log("Div was clicked!" + receiver);
});