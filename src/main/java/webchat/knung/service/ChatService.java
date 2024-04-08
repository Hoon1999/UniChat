package webchat.knung.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webchat.knung.domain.ChatMessage;
import webchat.knung.domain.Member;
import webchat.knung.dto.ChatRoomDto;
import webchat.knung.dto.MessageDto;
import webchat.knung.repository.MessageRepository;
import webchat.knung.repository.ParticipationChatRoomRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ChatService {
    ParticipationChatRoomRepository participationChatRoomRepository;
    MessageRepository messageRepository;

    public ChatService(ParticipationChatRoomRepository participationChatRoomRepository, MessageRepository messageRepository) {
        this.participationChatRoomRepository = participationChatRoomRepository;
        this.messageRepository = messageRepository;
    }

    public List<ChatRoomDto> listSearch(Member member) {
        return participationChatRoomRepository.findByMemberId(member.getMemberId());
    }
    public List<MessageDto> loadMessage(Long chatRoomId, LocalDateTime time) {
        return messageRepository.findByReceiverIdAndSendTimeGreaterThanEqual(chatRoomId, time);
    }
    public LocalDateTime sendMessage(Long memberId, Long chatRoomId, String msg) {
        ChatMessage chatMessage = new ChatMessage(memberId, chatRoomId, msg);
        ChatMessage savedMessage = messageRepository.save(chatMessage);
        return savedMessage.getSendTime();
    }
}
