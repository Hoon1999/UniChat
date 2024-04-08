package webchat.knung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webchat.knung.domain.ParticipationChatRoom;
import webchat.knung.dto.ChatRoomDto;

import java.util.List;

public interface SpringDataJpaParticipationChatRoomRepository extends JpaRepository<ParticipationChatRoom, Long>, ParticipationChatRoomRepository {
    @Override
    @Query(value = "select pcr.chatRoom_id, cr.chat_name, pcr.chat_seq, cr.chatRoomImg, pcr.last_date " +
            "from participationChatRoom as pcr join chatRoom as cr " +
            "where pcr.member_id = :memberId", nativeQuery = true)
    List<ChatRoomDto> findByMemberId(@Param("memberId") Long memberId);
}