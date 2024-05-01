package webchat.knung.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import webchat.knung.domain.Member;
import webchat.knung.dto.ChatRoomDto;
import webchat.knung.dto.MessageDto;
import webchat.knung.service.ChatService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChatController {
    ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(value = "/chatRoom")
    public String chatRoom() {
        return "chatRoom";
    }
    @PostMapping(value = "/chatRoom")
    @ResponseBody
    public List<ChatRoomDto> chatRoomList(HttpSession session) {
        Member member = new Member();
        member.setLoginId((String) session.getAttribute("loginId"));
        return chatService.listSearch(member);
    }

    /**
     * 클라이언트에서 서버로 메시지 전송
     * Get 방식으로 memberId 와 chatRoomId 를 받으면 위험하다고 생각
     * @param msg
     * @return
     */
    @PostMapping(value = "sendMessage")
    @ResponseBody
    public LocalDateTime sendMessage(@RequestParam("memberId") Long memberId,
                                     @RequestParam("chatRoomId") Long chatRoomId,
                                     @RequestParam("msg") String msg) {
        LocalDateTime sendTime = chatService.sendMessage(
                memberId,
                chatRoomId,
                msg);

        return sendTime;
    }

    // 서버에서 클라이언트로 메세지를 보냄
    @GetMapping(value = "loadMessage")
    @ResponseBody
    public List<MessageDto> loadMessage(@RequestParam("chatRoomId") Long chatRoomId, @RequestParam("lastTime") LocalDateTime lastTime) {
        return chatService.loadMessage(chatRoomId, lastTime);
    }

    // 클라이언트에서 채팅방에 접속
    @GetMapping(value = "/chattingRoom")
    public String chatGet() {
        // 접속하려는 채팅방 정보를 받아서 특정 동작을 수행하도록 하는 코드를 추가해야함.
        // 특정 동작 : 1번방 정보를 받으면, 1번방과 연결되도록 하는 동작.
        return "chattingRoom";
    }
}
