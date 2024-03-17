package webchat.knung.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "chatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id", unique = true, nullable = false)
    long msgId;
    @Column(name = "sender_id", nullable = false)
    long senderId;
    @Column(name = "receiver_id", nullable = false)
    long receiverId;
    @CreationTimestamp
    @Column(name = "send_time", nullable = false)
    LocalDateTime sendTime;
    @Column(name = "content", nullable = false)
    String content;
}
