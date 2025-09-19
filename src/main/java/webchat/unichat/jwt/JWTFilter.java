package webchat.unichat.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import webchat.unichat.domain.Member;
import webchat.unichat.dto.MemberDetails;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. JWT 를 검증한다.
        // 1-1. request 헤더에서 JWT 를 꺼낸다.
        // 1-2. JWT 형식이 맞는지 검사한다.
        // 1-3. 유효한 JWT 인지 검증한다.
//        String authorization = request.getHeader("Authorization");
        String authorization = null;
        if (request.getCookies() == null) {
            filterChain.doFilter(request,response);
            return;
        }
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("Authorization")) {
                authorization = "Bearer " + cookie.getValue();
            }
        }
        if(authorization  == null || !authorization.startsWith("Bearer")) {
            System.out.println("Token 이 없거나 잘못된 형식입니다. : " + authorization);
            filterChain.doFilter(request,response);
            return ;
        }
        String token = authorization.split(" ")[1];
        try {
            jwtUtil.isExpired(token);

        } catch (ExpiredJwtException e) {
            System.out.println("Token 이 만료되었습니다.");
            filterChain.doFilter(request,response);
            return;
        }
        String memberId = jwtUtil.getMemberId(token);
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // 2. 인증 정보를 보관한다.
        Member member = new Member();
        member.setMemberId(Long.parseLong(memberId));
        member.setLoginId(username);
        member.setPassword("temp");
        member.setRole(role);

        MemberDetails memberDetails = new MemberDetails(member);
        Authentication authToken = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
