package webchat.knung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webchat.knung.domain.Member;
import webchat.knung.service.ChatService;

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
    @PostMapping(value = "chatRoom")
    @ResponseBody
    public Object chatRoomList() {
        Member member = new Member();
        JSON 객체변수 만듬;

        member.setId(세션에서 유저아이디를 가져옴);
        JSON 객체변수 = chatService.listSearch(member);
        return JSON 객체변수;
    }
}