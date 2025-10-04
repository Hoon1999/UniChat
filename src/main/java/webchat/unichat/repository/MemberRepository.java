package webchat.unichat.repository;

import webchat.unichat.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public List<Member> findAll();
    public Optional<Member> findByMemberId(Long memberId);
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByName(String name);

    public boolean existsByEmail(String email);
}
