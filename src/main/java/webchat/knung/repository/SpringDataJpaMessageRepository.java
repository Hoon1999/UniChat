package webchat.knung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webchat.knung.domain.ChatMessage;
import webchat.knung.dto.MessageDto;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataJpaMessageRepository extends JpaRepository<ChatMessage, Long>, MessageRepository {
    @Override
    ChatMessage save(ChatMessage chatMessage);

    @Override
    List<MessageDto> findByReceiverIdAndSendTimeGreaterThanEqual(Long receiverId, LocalDateTime sendTime);
}
