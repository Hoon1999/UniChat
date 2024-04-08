package webchat.knung.repository;

import webchat.knung.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public List<Member> findAll();
    public Optional<Member> findByMemberId(Long memberId);
    public Optional<Member> findByLoginId(String loginId);
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByName(String name);
    public Optional<Member> findByEmailAndQna(String email, String qna);
    public Optional<Member> findByLoginIdAndEmailAndQna(String loginId, String email, String qna);

    public boolean existsByLoginIdOrEmail(String loginId, String email);
}
