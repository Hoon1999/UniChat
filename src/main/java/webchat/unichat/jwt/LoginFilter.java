package webchat.unichat.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import webchat.unichat.dto.MemberDetails;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // request 에서 username 과 password 를 꺼내 AuthenticationManager 에게 넘겨준다.
        // Authentication Manager 는 적절한 Provider 를 선택하여 인증을 하도록 넘겨준다.
        // Provider 는 받은 값을 UserDetailsService 의 loadUserByUsername 의 리턴값과 비교한다.
//        String username = obtainUsername(request);
        String username = request.getParameter("email");
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        // 인증에 성공하면 호출된다.
        // JWT 를 발급해주자.

        // authentication.getPrincipal() 은 user details 을 반환한다. Type 은 Object 형태로 반환된다.
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();

        Long memberId = memberDetails.getMemberId();
        String username = memberDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = memberDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator(); // 굳이 Iterator 에 보관하지 않고 authorities.iterator().next() 로 축약가능.
        GrantedAuthority auth = iterator.next(); // collection 의 첫번째 값이 auth 에 보관된다.
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(memberId, username, role, 60*60*10L*100); // 36초

//        response.addHeader("Authorization", "Bearer " + token);
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 인증에 실패하면 호출된다.
        System.out.println("로그인 실패");
        response.setStatus(401);
    }
}
