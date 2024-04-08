package webchat.knung.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "chatRoom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoom_id", unique = true, nullable = false)
    private Long chatRoomId;
    @Column(name = "chat_name", nullable = false)
    private String chatName;
    @Column(name = "chatRoomImg")
    private String chatRoomImg;

}
