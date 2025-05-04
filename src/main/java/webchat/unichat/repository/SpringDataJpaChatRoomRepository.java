package webchat.unichat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webchat.unichat.domain.ChatRoom;

public interface SpringDataJpaChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepository {

}
