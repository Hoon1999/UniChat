package webchat.knung.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id", unique = true, nullable = false)
    private Long chatRoomId;
    @Column(name = "chat_room_name", nullable = false)
    private String chatName;
    @Column(name = "chat_room_img")
    private String chatRoomImg;

    public ChatRoom() {
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setChatRoomImg(String chatRoomImg) {
        this.chatRoomImg = chatRoomImg;
    }
}
