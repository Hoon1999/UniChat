package webchat.unichat.service;

import org.springframework.transaction.annotation.Transactional;
import webchat.unichat.domain.Member;
import webchat.unichat.repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public boolean signUp(Member member) {
        boolean isExist = memberRepository.existsByLoginIdOrEmail(member.getLoginId(), member.getEmail());
        if(isExist) {
            System.out.println("exist");

            return false;
        }
        memberRepository.save(member);
        return true;
    }
    /**
     * 로그인
     */
    public Member athentication(Member member) {
        // DB에 로그인 아이디 조회
        Optional<Member> byMemberId = memberRepository.findByLoginId(member.getLoginId());
        if(byMemberId.isPresent()) {
            // 로그인 아이디가 존재한다.
            Member findMember = byMemberId.get();
            if (findMember.getPassword().equals(member.getPassword())) {
                // 로그인 비밀번호가 일치한다.
                return findMember;
            }
            else {
                // 아이디는 일치하지만 비밀번호는 일치하지 않는다.
                return null;
            }
        }else {
            // 아이디가 존재하지 않는다.
            return null;
        }
    }
    /**
     * 아이디 찾기
     */
    public String findId(Member member) {
        Optional<Member> byEmailAndQna = memberRepository.findByEmailAndQna(member.getEmail(), member.getQna());
        if(byEmailAndQna.isPresent()) {
            // NULL 이 아니다.(이메일 + 질의응답 이 일치하는 레코드가 존재한다.)
            Member findMember = byEmailAndQna.get();
            return "회원님의 아이디는 " + findMember.getLoginId() + " 입니다.";
        }
        else {
            // 조회 결과가 Null 이다.
            // 이메일이 없는 경우, 이메일은 있으나 질의응답이 틀린경우, 질의응답만 존재하는 경우.
            return "일치하는 회원 정보가 존재하지 않습니다.";
        }
    }
    public String findPw(Member member) {
        Optional<Member> result = memberRepository.findByLoginIdAndEmailAndQna(member.getLoginId(), member.getEmail(), member.getQna());
        if(result.isPresent()){
            return "회원님의 비밀번호는" + result.get().getPassword() + " 입니다.";
        }
        else {
            return "일치하는 회원정보가 존재하지 않습니다.";
        }

    }

    /**
     * member Id 로 해당 회원의 이름을 찾습니다.
     * @param memberId
     * @return
     */
    public String findUserName(Long memberId) {
        Optional<Member> result = memberRepository.findByMemberId(memberId);
        if(result.isPresent()) {
            return result.get().getName();
        }
        else {
            // 해당하는 유저가 존재하지 않음.
            return "UnKnown";
        }
    }
    public Map<String, String> checkDuplicateEmail(String email) {
        Map<String, String> rt = new HashMap<>();
        Optional<Member> result = memberRepository.findByEmail(email);

        if(result.isPresent()) {
            rt.put("result", "fail");
            rt.put("message", "이미 사용중인 이메일입니다.");
        }
        else {
            rt.put("result", "success");
            rt.put("message", "사용할 수 있는 이메일입니다.");
        }

        return rt;
    }
    public Map<String, String> checkDuplicateLoginId(String loginId) {
        Map<String, String> rt = new HashMap<>();
        Optional<Member> result = memberRepository.findByLoginId(loginId);
        if(result.isPresent()) {
            rt.put("result", "fail");
            rt.put("message", "이미 사용중인 아이디입니다.");
        }
        else {
            rt.put("result", "success");
            rt.put("message", "사용할 수 있는 아이디입니다.");
        }
        return rt;
    }
    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
