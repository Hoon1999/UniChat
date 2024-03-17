package webchat.knung.repository;

import webchat.knung.domain.ChatRoom;
import webchat.knung.dto.ChatRoomDto;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository {
    public ChatRoom save(ChatRoom chatRoom);
    public Optional<ChatRoom> findById(long id);
    public List<ChatRoomDto> findChatRoom(long id); 이거 참여중인 채팅방에서 구현해야함
}
