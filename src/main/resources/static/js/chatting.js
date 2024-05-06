var socket = null;

function connectStomp() {
    var sock = new SockJS("/stomp/chat");
    var client = Stomp.over(sock);
    socket = client;

    client.connect({},
                   function () {
                           console.log("Stomp 서버에 연결되었습니다.");
                           client.subscribe("/topic/room/1", function (e) { console.log("클라이언트가 수신한 메세지 : " + e);})
                           socket.send("/chatting/room/1", {}, "접속 테스트용 메세지입니다.");
                   })
}