package webchat.knung.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import webchat.knung.domain.ChatMessage;
import webchat.knung.service.ChatService;
import webchat.knung.service.MemberService;

import java.time.LocalDateTime;

@Controller
public class StompController {
    @Autowired
    ChatService chatService;
    @Autowired
    MemberService memberService;
    // 일단 테스트용 코드
    @MessageMapping("/room/{roomId}") // 클라이언트가 /chatting/room/방번호 로 메세지를 보내면 이 메서드에서 캐치한다.
    @SendTo("/topic/room/{roomId}") // send to 는 메서드의 리턴 값이 향하는 곳을 설정한다.(리턴 값을 받을 메세지 브로커를 설정한다.)
    public JSONObject myStompHandler1(@DestinationVariable("roomId") String roomId, String message) throws ParseException {
        System.out.println("클라이언트가 보낸 메세지 : " + message);

        JSONObject obj = (JSONObject) (new JSONParser().parse(message));
        System.out.println("송신자 : " + obj.get("sender") + " 메세지 내용 : " + obj.get("content"));

        ChatMessage savedMessage = chatService.sendMessage(Long.parseLong((String) obj.get("sender")), Long.parseLong(roomId), (String) obj.get("content"));
        LocalDateTime sendTime = savedMessage.getSendTime();
        Long msgId = savedMessage.getMsgId();
        String sender = memberService.findUserName(Long.parseLong((String) obj.get("sender")));

        chatService.updateParticipationChatRoom(Long.parseLong((String) obj.get("sender")), Long.parseLong(roomId),msgId, sendTime);// 참가중인 방 테이블을 업데이트한다.

        // sendMessage 메서드의 리턴값(저장된시간) 이랑 클라이언트가 보낸 메세지 내용 취합하여 사용자에게 리턴하기
        obj.put("sender_name", sender);
        obj.put("send_time", sendTime);
        return obj;
    }

}
