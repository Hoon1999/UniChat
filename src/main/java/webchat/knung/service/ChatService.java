package webchat.knung.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webchat.knung.domain.Member;

@Service
@Transactional
public class ChatService {
    public void listSearch(Member member) {
        member 에서 id를 꺼냄;
        꺼낸 id로 db에 내가 속한 채팅방 조회;
    ParticipationChatRoomRepository participationChatRoomRepository;
    MessageRepository messageRepository;

    public ChatService(ParticipationChatRoomRepository participationChatRoomRepository, MessageRepository messageRepository) {
        this.participationChatRoomRepository = participationChatRoomRepository;
        this.messageRepository = messageRepository;
    }

    public List<ChatRoomDto> listSearch(Member member) {
        return participationChatRoomRepository.findByMemberId(member.getMemberId());
    }
}
