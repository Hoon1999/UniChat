package webchat.unichat.repository;

import webchat.unichat.domain.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository {
    public ChatRoom save(ChatRoom chatRoom);
    public Optional<ChatRoom> findById(Long id);

}
