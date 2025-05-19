package webchat.unichat.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import webchat.unichat.domain.Member;
import webchat.unichat.form.LoginForm;
import webchat.unichat.form.MemberForm;
import webchat.unichat.service.MemberService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    // 로그인 인증
    @PostMapping("/authentication")
    @ResponseBody
    public Map<String, String> authentication(@RequestBody LoginForm form, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        System.out.println(form.toString());
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member = memberService.athentication(member);

        if (member != null) {
            // 로그인 성공. 메인 페이지로 이동
            session.setAttribute("memberId", member.getMemberId());
            session.setMaxInactiveInterval(1800); // 30분간 세션 유지
//            response.put("memberId", member.getMemberId().toString());
            response.put("result", "success");
            response.put("message", "/chatting_room_list");
        }
        else {
            // 로그인 실패. 로그인 페이지로 이동
            response.put("result", "fail");
            response.put("message", "로그인 실패\n 아이디 또는 비밀번호를 확인해주세요" );
        }
        return response;
    }
    @GetMapping("/chatting_room_list")
    public String chattingRoomList(HttpSession session) {
        if(session.getAttribute("memberId") == null) {
            // 로그인하지 않고 주소창에 /chatting_room_list 를 입력하고 넘어오면
            // 로그인 창으로 돌려보낸다.
            return "login";
        }
        return "chatting_room_list";
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
