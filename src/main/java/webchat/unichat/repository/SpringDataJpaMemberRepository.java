package webchat.unichat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webchat.unichat.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
}
