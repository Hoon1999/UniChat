package webchat.knung.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "participationChatRoom")
public class ParticipationChatRoom {
    @Id
    @Column(name = "chat_id", unique = true, nullable = false)
    private Long chatId;
    @Column(name="member_id")
    private Long memberId;
    @Column(name = "chatRoom_id")
    private Long chatRoomId;
    @Column(name = "chat_seq", nullable = false)
    private Long chatSeq;
    @UpdateTimestamp
    @Column(name = "last_date", nullable = false)
    private LocalDateTime lastDate;

    @ManyToOne
    @JoinColumn
    ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn
    Member member;
}
