package webchat.knung.repository;

import webchat.knung.domain.ChatMessage;
import webchat.knung.dto.MessageDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository {
    public ChatMessage save(ChatMessage chatMessage);
    public List<MessageDto> findByReceiverIdAndSendTimeGreaterThanEqual(Long receiverId, LocalDateTime sendTime);
}
