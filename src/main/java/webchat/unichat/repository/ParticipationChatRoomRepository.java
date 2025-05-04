package webchat.unichat.repository;

import webchat.unichat.domain.ParticipationChatRoom;
import webchat.unichat.dto.ChatRoomDto;

import java.util.List;
import java.util.Optional;

public interface ParticipationChatRoomRepository {
    public ParticipationChatRoom save(ParticipationChatRoom pChatRoom);
    public List<ParticipationChatRoom> findByChatRoomId(Long chatRoomId);
    public Optional<ParticipationChatRoom> findByMemberIdAndChatRoomId(Long memberId, Long chatRoomId);
    public List<ChatRoomDto> findByMemberId(Long memberId);
    public Long deleteByMemberIdAndChatRoomId(Long memberId, Long chatRoomId);
}
