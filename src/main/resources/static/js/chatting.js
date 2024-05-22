var socket = null;

function connectStomp(roomId) {
    var sock = new SockJS("/stomp/chat");
    var client = Stomp.over(sock);
    socket = client;

    client.connect({},
                   function () {
                           console.log("Stomp 서버에 연결되었습니다.");
                           client.subscribe("/topic/room/" + roomId, function (e) {
                                // 메세지를 수신하면 할 동작을 정의한다.
                                // 수신한 내용을 말풍선에 담아 화면에 출력한다.
                                //console.log("클라이언트가 수신한 메세지 : " + e.body);
                                let obj = JSON.parse(e.body);
                                let sender = obj.sender;
                                let sender_name = obj.sender_name;
                                let msg = obj.content;
                                let time = obj.send_time;
                                createSpeechBalloon(sender, sender_name, msg, time);
                                document.getElementsByClassName("chat-container").item(0).lastChild.scrollIntoView();
                           });
                           //let msg = { sender : roomId, content : "접속 테스트용 메세지입니다." };
                           //socket.send("/chatting/room/" + roomId, {}, JSON.stringify(msg));
                   })
}

//function 함수() {
//    첨부파일을 가져온다.
//    가져온 첨부파일을 Base64로 인코딩한다.
//    {isfile:true, data:인코딩된파일} 을 stringify 한다.
//    stringify 된 json을 socket.send로 서버에 전달한다.
//}
