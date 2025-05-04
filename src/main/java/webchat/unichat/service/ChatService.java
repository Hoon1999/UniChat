package webchat.unichat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import webchat.unichat.controller.ChattingRoomEditForm;
import webchat.unichat.domain.ChatMessage;
import webchat.unichat.domain.ChatRoom;
import webchat.unichat.domain.Member;
import webchat.unichat.domain.ParticipationChatRoom;
import webchat.unichat.dto.ChatRoomDto;
import webchat.unichat.dto.MessageDto;
import webchat.unichat.repository.ChatRoomRepository;
import webchat.unichat.repository.MessageRepository;
import webchat.unichat.repository.ParticipationChatRoomRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChatService {
    ParticipationChatRoomRepository participationChatRoomRepository;
    MessageRepository messageRepository;
    ChatRoomRepository chatRoomRepository;

    public ChatService(ParticipationChatRoomRepository participationChatRoomRepository, MessageRepository messageRepository, ChatRoomRepository chatRoomRepository) {
        this.participationChatRoomRepository = participationChatRoomRepository;
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public List<ChatRoomDto> listSearch(Member member) {
        return participationChatRoomRepository.findByMemberId(member.getMemberId());
    }
    public ChatMessage searchMessage(Long msgId) {
        return messageRepository.findByMsgId(msgId);
    }
    public List<MessageDto> loadMessage(Long chatRoomId, LocalDateTime time) {
        return messageRepository.findByReceiverIdAndSendTimeGreaterThanEqual(chatRoomId, time);
    }

    /**
     *
     * @param memberId
     * @param chatRoomId
     * @param msg
     * @return
     */
    public ChatMessage sendMessage(Long memberId, Long chatRoomId, String msg) {
        ChatMessage chatMessage = new ChatMessage(memberId, chatRoomId, msg);
        ChatMessage savedMessage = messageRepository.save(chatMessage);
        return savedMessage;
    }
    public ChatRoom createChattingRoom() {
        // 새 채팅방을 만듬.
        ChatRoom obj = new ChatRoom();
        obj.setChatName("new_room");
        //obj.setChatRoomImg("/images/default_profile_image.png");
        return chatRoomRepository.save(obj);
    }
    public void exitChattingRoom(Long memberId, Long roomId) {
        participationChatRoomRepository.deleteByMemberIdAndChatRoomId(memberId, roomId);
    }
    public void updateChattingRoom(ChattingRoomEditForm form) throws IOException {
        // DTO 를 꼭 써야하나?
        // 1. 이름 변경여부 확인.
        // 2. 파일 첨부여부 확인(채팅방 대표사진 변경여부).

        ChatRoom result = chatRoomRepository.findById(Long.parseLong(form.getRoomId())).get();

        if(form.getRoomName().compareTo("") == 0) {
            // 빈 문자열인지 검사.
            // 빈 문자열이면 이름을 변경하지 않는다.
        }
        else {
            // 원래는 빈 문자열 말고도 자릿수, 특수문자 등 다양한 제약을 걸어야 하지만 일단 생략함.
            result.setChatName(form.getRoomName());
        }


        if(form.getRoomImage().isEmpty()) {
            // 첨부 파일이 없는 경우.

        }
        else {
            // 1. 파일을 form 에서 꺼냄
            // 2. 파일의 이름을 저장용 이름으로 변경
            // 3. 파일을 경로 + 저장용이름에 저장.
            // 4. 저장이 완료된 파일의 경로를 ChatRoom 테이블의 이미지 column 에 저장.
            // 저장용 이름은 '임의의 숫자 + 원본 이름' 으로 이루어져 임의의 숫자를 제거하면 원본 이름이 된다.

            MultipartFile file = form.getRoomImage();
            String originalFilename = file.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String savePath = "C:/Users/2021102/Desktop/knung_images/" + storedFileName; // windows 용 경로. 해당 폴더가 있어야 한다. 없으면 안만들어주나봄.
//            String savePath = "/Users/kjhoon44/Desktop/knung_images/" + storedFileName; // for mac
            file.transferTo(new File(savePath));

            result.setChatRoomImg(storedFileName);
        }
        chatRoomRepository.save(result);
    }

    /**
     * 채팅방에 user를 추가하는 메서드
     * @param memberId 채팅방에 입장하는 user의 memberID
     * @param chatRoomId 입장할 채팅방 ID
     */
    public boolean addUserInChattingRoom(Long memberId, Long chatRoomId) {
        // 채팅방에 유저 추가.(채팅방에 사람이 들어옴)
        List<ParticipationChatRoom> result = participationChatRoomRepository.findByChatRoomId(chatRoomId);
        if(result != null && result.isEmpty()) {}
        else
            for(int i = 0 ; i < result.size(); i++)
                if(result.get(i).getMemberId().equals(memberId)) return false; // 초대하고자 하는 방에 해당 유저가 이미 존재하면 아무일도 안 일어남.

        participationChatRoomRepository.save(new ParticipationChatRoom(memberId, chatRoomId));
        return true;
    }

    /**
     * participation chat room 의 last date 열의 값을 update 하는 함수
     * @param memberId
     * @param roomId
     * @param time
     */
    public void updateParticipationChatRoom(Long memberId, Long roomId, Long msgId, LocalDateTime time) {
        Optional<ParticipationChatRoom> result = participationChatRoomRepository.findByMemberIdAndChatRoomId(memberId, roomId);
        if(result.isPresent()) {
            result.get().setLastDate(time);
            result.get().setChatSeq(msgId);
            participationChatRoomRepository.save(result.get());
        }
    }
}
