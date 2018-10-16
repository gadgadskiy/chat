var stompClient = null;
connect();

function connect() {
    var socket = new SockJS('/my-web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/message', function (message) {
            showMessage(message.body);
        });
    });
}

function send() {
    var name = $("#name");
    var messageContent = $("#messageContent");
    if (name.val() === "" || messageContent.val() === "") {
        alert("Your name and message shoudn't be empty");
        return;
    }
    stompClient.send("/app/chat", {}, JSON.stringify({'text': getTime() + name.val() + ": " + messageContent.val()}));
    messageContent.val("");
}

function getTime() {
    var dt = new Date();
    var minutes = dt.getMinutes() < 10 ? "0" + dt.getMinutes() : dt.getMinutes();
    var seconds = dt.getSeconds() < 10 ? "0" + dt.getSeconds() : dt.getSeconds();
    return "[" + dt.getHours() + ":" + minutes + ":" + seconds + "] ";
}

function showMessage(message) {
    $("#messagesBlock").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function () {
        send();
    });
});