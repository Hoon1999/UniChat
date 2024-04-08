package webchat.knung.repository;

import webchat.knung.domain.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository {
    public ChatRoom save(ChatRoom chatRoom);
    public Optional<ChatRoom> findById(Long id);

}
