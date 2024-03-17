package webchat.knung.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "chatRoom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", unique = true, nullable = false)
    long id;
    @Column(name = "name", nullable = false)
    String name;


}
