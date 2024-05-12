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

function 함수() {
    첨부파일을 가져온다.
    가져온 첨부파일을 Base64로 인코딩한다.
    {isfile:true, data:인코딩된파일} 을 stringify 한다.
    stringify 된 json을 socket.send로 서버에 전달한다.
}