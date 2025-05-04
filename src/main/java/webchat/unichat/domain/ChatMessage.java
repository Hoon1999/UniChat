package webchat.unichat.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id", unique = true, nullable = false)
    private Long msgId;
    @Column(name = "sender_id", nullable = false)
    private Long senderId;
    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;
    @CreationTimestamp
    @Column(name = "send_time", nullable = false)
    private LocalDateTime sendTime;
    @Column(name = "content", nullable = false)
    private String content;

    public ChatMessage() {
    }

    public ChatMessage(Long senderId, Long receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public Long getMsgId() {
        return msgId;
    }

    public String getContent() {
        return content;
    }
    public LocalDateTime getSendTime() {
        return sendTime;
    }
}
