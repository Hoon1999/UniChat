package webchat.unichat.repository;

import webchat.unichat.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public List<Member> findAll();
    public Optional<Member> findByMemberId(Long memberId);
    public Optional<Member> findByLoginId(String loginId);
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByName(String name);
    public Optional<Member> findByEmailAndQuestionAndAnswer(String email, String question, String answer);
    public Optional<Member> findByLoginIdAndEmailAndQuestionAndAnswer(String loginId, String email, String question, String answer);

    public boolean existsByLoginIdOrEmail(String loginId, String email);
}
