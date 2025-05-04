package webchat.unichat.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "participation_chat_room")
public class ParticipationChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", unique = true, nullable = false)
    private Long chatId;
    @Column(name="member_id")
    private Long memberId;
    @Column(name = "chat_room_id")
    private Long chatRoomId;
    @Column(name = "chat_seq")
    private Long chatSeq;
    @UpdateTimestamp
    @Column(name = "last_date")
    private LocalDateTime lastDate;
//
//    @ManyToOne
//    @JoinColumn
//    ChatRoom chatRoom;
//    @ManyToOne
//    @JoinColumn
//    Member member;


    public ParticipationChatRoom() {
    }

    /**
     * 생성자입니다.
     * @param memberId 채팅방에 속해 있는 user 의 고유 ID 값입니다.
     * @param chatRoomId user 가 들어가 있는 채팅방 ID 값입니다.
     */
    public ParticipationChatRoom(Long memberId, Long chatRoomId) {
        this.memberId = memberId;
        this.chatRoomId = chatRoomId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Long getChatSeq() {
        return chatSeq;
    }

    public void setChatSeq(Long chatSeq) {
        this.chatSeq = chatSeq;
    }

    public LocalDateTime getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastDate = lastDate;
    }
}
