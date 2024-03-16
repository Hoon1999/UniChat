package webchat.knung.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import webchat.knung.controller.LoginController;
import webchat.knung.domain.Member;
import webchat.knung.repository.MemberRepository;
import webchat.knung.service.MemberService;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public SpringConfig(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(memberService);
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
