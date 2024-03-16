package webchat.knung.repository;

import webchat.knung.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public List<Member> findAll();
    public Optional<Member> findOne(long id);
    public Optional<Member> findByM_id(String m_id);
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByName(String name);
    public Optional<Member> findByEmailAndQna(String email, String qna);
    public Optional<Member> findByM_idAndEmailAndQna(String m_id, String email, String qna);

}
