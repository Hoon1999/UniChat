package webchat.knung.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "participationChatRoom")
public class ParticipationChatRoom {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    long id;
    @Column(name = "chat_id", unique = true, nullable = false)
    long chatId;
    @Column(name = "chat_seq", nullable = false)
    long chatSeq;
    @CreationTimestamp
    @UpdateTimestamp
    @Column(name = "last_date", nullable = false)
    LocalDateTime lastDate;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "id")
    Member member;
}
