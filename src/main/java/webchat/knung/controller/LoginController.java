package webchat.knung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webchat.knung.domain.Member;
import webchat.knung.service.MemberService;

@Controller
public class LoginController {
    MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    /*@PostMapping("/athentication")
    public String athentication(loginForm form) {
        return "temp";
    }*/
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }
    @PostMapping(value = "/register")
    public String register(MemberForm form) {
        Member member = new Member();
        member.setM_id(form.getM_id());
        member.setPassword(form.getPassword());
        member.setEmail(form.getEmail());
        member.setName(form.getName());
        member.setQna(form.getQna());

        memberService.signUp(member);

        return "redirect:/";
    }
}
