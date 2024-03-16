package webchat.knung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webchat.knung.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    @Override
    Optional<Member> findByM_id(String m_id);

    @Override
    Optional<Member> findByEmail(String email);

    @Override
    Optional<Member> findByName(String name);

    @Override
    Optional<Member> findByEmailAndQna(String email, String qna);

    @Override
    Optional<Member> findByM_idAndEmailAndQna(String m_id, String email, String qna);
}
