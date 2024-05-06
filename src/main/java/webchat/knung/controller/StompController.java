package webchat.knung.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {
    // 일단 테스트용 코드
    @MessageMapping("/room/{roomId}") // 클라이언트가 /chatting/room/방번호 로 메세지를 보내면 이 메서드에서 캐치한다.
    @SendTo("/topic/room/{roomId}") // send to 는 메서드의 리턴 값이 향하는 곳을 설정한다.(리턴 값을 받을 메세지 브로커를 설정한다.)
    public String myStompHandler1(@DestinationVariable("roomId") String roomId, String message) {
        System.out.println("클라이언트가 보낸 메세지 : " + message);

        return message;
    }

}
