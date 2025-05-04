package webchat.unichat.repository;

import webchat.unichat.domain.ChatMessage;
import webchat.unichat.dto.MessageDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository {
    public ChatMessage save(ChatMessage chatMessage);
    public ChatMessage findByMsgId(Long msgId);
    public List<MessageDto> findByReceiverIdAndSendTimeGreaterThanEqual(Long receiverId, LocalDateTime sendTime);
}
