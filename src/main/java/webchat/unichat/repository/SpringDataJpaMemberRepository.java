package webchat.unichat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webchat.unichat.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    @Override
    Optional<Member> findByLoginId(String loginId);

    @Override
    Optional<Member> findByEmail(String email);

    @Override
    Optional<Member> findByName(String name);

    @Override
    Optional<Member> findByEmailAndQna(String email, String qna);

    @Override
    Optional<Member> findByLoginIdAndEmailAndQna(String loginId, String email, String qna);
}
