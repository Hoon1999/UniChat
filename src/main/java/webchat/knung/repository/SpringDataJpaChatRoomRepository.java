package webchat.knung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webchat.knung.domain.ChatRoom;

public interface SpringDataJpaChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepository {

}
