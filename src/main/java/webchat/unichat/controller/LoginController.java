package webchat.unichat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import webchat.unichat.domain.Member;
import webchat.unichat.form.MemberForm;
import webchat.unichat.service.MemberService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class LoginController {
    MemberService memberService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginController(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 로그인
    @GetMapping("/")
    public String login() {
        return "unichat";
    }

    @GetMapping("/login_page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/chatting_room_list")
    public String chattingRoomList() {
        return "chatting_room_list";
    }


    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    /**
     * 회원가입 form 을 받아서 처리하는 controller
     *
     * form 의 데이터를 Member 객체에 담아서 회원가입 로직을 처리하는 Service 로 넘깁니다.
     * form 은 name, email, password 를 가집니다.
     *
     * @return
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public String register(MemberForm form) {
        Member member = new Member();
        member.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
        member.setEmail(form.getEmail());
        member.setName(form.getName());

        try {
            boolean result = memberService.signUp(member);
            if(result)
                return "success";
            else
                return "exist";
        } catch(Exception e) {
            return "fail";
        }
    }

    @PostMapping(value = "/register/check-duplicate")
    @ResponseBody
    public Map<String, String> checkDuplicate(@RequestBody Map<String, String> data) {
        Map<String, String> response = new HashMap<>();
        Set<String> keys = data.keySet();
        for(String key : keys) {
            if(key.equals("email")){
                response = memberService.checkDuplicateEmail(data.get(key));
            }
            else if(key.equals("loginId")) {
                response = memberService.checkDuplicateLoginId(data.get(key));
            }
        }
        return response;
    }

    // 아이디 찾기
    @GetMapping(value = "/findId")
    public String findIdForm() {
        return "id_find";
    }
    @PostMapping(value = "/findId")
    @ResponseBody
    public String findId(MemberForm form) {
        Member member = new Member();
        member.setEmail(form.getEmail());

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
        member.setEmail(form.getEmail());

        return memberService.findPw(member);
    }
}
