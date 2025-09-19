package webchat.unichat.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import webchat.unichat.domain.Member;
import webchat.unichat.dto.MemberDetails;
import webchat.unichat.repository.MemberRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> byLoginId = memberRepository.findByLoginId(username);

        // 조회된 member 가 있다.
        if(byLoginId.isPresent()) {
            Member member = byLoginId.get();
            return new MemberDetails(member);
        }

        // 조회된 member 가 없다.
        return null;
    }
}
