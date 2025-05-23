package webchat.unichat.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webchat.unichat.domain.Member;
import webchat.unichat.service.MemberService;

@Controller
public class LoginController {
    MemberService memberService;

    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 로그인
    @GetMapping("/")
    public String login() {
        return "knung";
    }
    @GetMapping("/login")
    public String login2() {
        return "login";
    }
    // 로그인
    @PostMapping("/athentication")
    public String athentication(LoginForm form, HttpSession session, Model model) {
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member = memberService.athentication(member);

        if (member != null) {
            // 로그인 성공. 메인 페이지로 이동
            session.setAttribute("memberId", member.getMemberId());
            session.setMaxInactiveInterval(1800); // 30분간 세션 유지
            model.addAttribute("memberId", member.getMemberId());
            return "chatting_room_list";
        }
        else {
            // 로그인 실패. 로그인 페이지로 이동
            return "login";
        }
    }

    // 회원가입
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }
    @PostMapping(value = "/register")
    public String register(MemberForm form) {
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setEmail(form.getEmail());
        member.setName(form.getName());
        member.setQna(form.getQna());

        memberService.signUp(member);

        return "redirect:/";
    }

    // 아이디 찾기
    @GetMapping(value = "/findId")
    public String findIdForm() {
        return "Id_find";
    }
    @PostMapping(value = "/findId")
    @ResponseBody
    public String findId(MemberForm form) {
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setQna(form.getQna());

        return memberService.findId(member);
    }

    /**
     * 비밀번호 찾기
     */
    @GetMapping(value = "/findPw")
    public String findPwForm() {
        return "pw_find";
    }

    @PostMapping(value = "findPw")
    @ResponseBody
    public String findPw(MemberForm form) {
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setEmail(form.getEmail());
        member.setQna(form.getQna());

        return memberService.findPw(member);
    }
}
