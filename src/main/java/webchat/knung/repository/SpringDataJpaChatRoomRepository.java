package webchat.knung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webchat.knung.domain.ChatRoom;
import webchat.knung.dto.ChatRoomDto;

import java.util.List;

public interface SpringDataJpaChatRoomRepository extends JpaRepository<ChatRoom, 다중키인데>, ChatRoomRepository {
    @Override
    @Query("select chat_id, chat_name, chat_seq" +
            "from participationChatRoom join chatRoom")
    List<ChatRoomDto> findChatRoom(long id); 이거 참여중인 채팅방에서 구현해야함
}
