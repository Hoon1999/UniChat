package webchat.unichat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webchat.unichat.domain.ChatMessage;
import webchat.unichat.dto.MessageDto;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataJpaMessageRepository extends JpaRepository<ChatMessage, Long>, MessageRepository {
    @Override
    ChatMessage save(ChatMessage chatMessage);

    @Override
    ChatMessage findByMsgId(Long msgId);

    @Override
    List<MessageDto> findByReceiverIdAndSendTimeGreaterThanEqual(Long receiverId, LocalDateTime sendTime);
}
