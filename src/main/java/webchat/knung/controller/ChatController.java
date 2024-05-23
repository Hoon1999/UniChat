package webchat.knung.controller;

import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webchat.knung.domain.ChatRoom;
import webchat.knung.domain.Member;
import webchat.knung.dto.ChatRoomDto;
import webchat.knung.dto.MessageDto;
import webchat.knung.service.ChatService;
import webchat.knung.service.MemberService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {
    ChatService chatService;
    MemberService memberService;

    public ChatController(ChatService chatService, MemberService memberService) {
        this.chatService = chatService;
        this.memberService = memberService;
    }

    @GetMapping(value = "/chatRoom")
    public String chatRoom() {
        return "chatRoom";
    }
    @PostMapping(value = "/chatRoom")
    @ResponseBody
    public JSONObject chatRoomList(HttpSession session) {
        Member member = new Member();
        member.setMemberId((Long) session.getAttribute("memberId"));

        List<ChatRoomDto> chatRoomDtos = chatService.listSearch(member);
        JSONArray data = new JSONArray();
        for (ChatRoomDto item : chatRoomDtos) {
            JSONObject obj = new JSONObject();
            obj.put("roomId", item.getChat_room_id());
            obj.put("name", item.getChat_room_name());
            obj.put("img_link", item.getChat_room_img());
            obj.put("last_date", item.getLast_date());

            if( item.getChat_seq() != null)
                obj.put("message_seq", chatService.searchMessage(item.getChat_seq()).getContent());
            else
                obj.put("message_seq", "새 채팅방 입니다.");
            //System.out.println(obj.toString());

            data.add(obj);
        }
        JSONObject chattingRoomList = new JSONObject();
        chattingRoomList.put("data", data);

        return chattingRoomList;
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
                msg)
                .getSendTime();

        return sendTime;
    }

    // 서버에서 클라이언트로 메세지를 보냄
    @GetMapping(value = "loadMessage")
    @ResponseBody
    public List<MessageDto> loadMessage(@RequestParam("chatRoomId") Long chatRoomId, @RequestParam("lastTime") LocalDateTime lastTime) {
        return chatService.loadMessage(chatRoomId, lastTime);
    }

    // 클라이언트에서 채팅방에 접속
    @GetMapping(value = "/chattingRoom/{roomId}")
    public String chatGet(@PathVariable("roomId") String roomId, Model model, HttpSession session) {
        // 접속하려는 채팅방 정보를 받아서 특정 동작을 수행하도록 하는 코드를 추가해야함.
        // 특정 동작 : 1번방 정보를 받으면, 1번방과 연결되도록 하는 동작.
        model.addAttribute("roomId", roomId);
        model.addAttribute("memberId", session.getAttribute("memberId"));
        return "chatting_room";
    }

    // 채팅방 만들기
    @PostMapping(value = "createChattingRoom")
    @ResponseBody
    public String createChattingRoom(HttpSession session) {
        // 채팅방을 만듬
        ChatRoom result = chatService.createChattingRoom();
        // 참여중인 채팅방에 본인을 추가.
        chatService.addUserInChattingRoom((Long) session.getAttribute("memberId"), result.getChatRoomId());

        return result.getChatName();
    }

    // 채팅방 정보 수정
    @GetMapping(value = "/editChattingRoom/{roomId}")
    public String chattingRoomEditor(@PathVariable("roomId") String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "edit_chatting_room";
    }

    @PostMapping(value = "/editChattingRoom")
    @ResponseBody
    public String editChattingRoom(@ModelAttribute ChattingRoomEditForm form) throws IOException {
        System.out.println("FormRoomID : " + form.getRoomId());
        System.out.println("FormRoomName : " + form.getRoomName());
//        System.out.println("file : " + form.getRoomImage());
        chatService.updateChattingRoom(form);
        String str = "<script> window.close() </script>";
        return str;
    }

    // 채팅방에 친구 초대
    @GetMapping("/inviteUser/{roomId}")
    public String inviteUserForm(@PathVariable("roomId") String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "invite_user_form";
    }
    @PostMapping("/inviteUser")
    @ResponseBody
    public String inviteUser(@RequestParam("roomId") String roomId,
                             @RequestParam("loginId") String loginId) {
        if(loginId.compareTo("") == 0) {
            return "로그인 아이디를 입력해주세요";
        }
        Optional<Member> result = memberService.findByLoginId(loginId);

        if(result.isPresent()) {
            chatService.addUserInChattingRoom(result.get().getMemberId(), Long.parseLong(roomId));
            return "초대가 완료되었습니다.";
        }
        else {
            return "존재하지 않는 아이디 입니다.";
        }
    }
}
