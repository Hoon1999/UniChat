package webchat.knung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webchat.knung.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    @Override
    Optional<Member> findByMid(String mid);

    @Override
    Optional<Member> findByEmail(String email);

    @Override
    Optional<Member> findByName(String name);

    @Override
    Optional<Member> findByEmailAndQna(String email, String qna);

    @Override
    Optional<Member> findByMidAndEmailAndQna(String mid, String email, String qna);
}
