package webchat.knung.service;

import webchat.knung.domain.Member;
import webchat.knung.repository.MemberRepository;

public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public void signUp(Member member) {
        memberRepository.save(member);
    }
}
