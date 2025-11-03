package webchat.unichat.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import webchat.unichat.jwt.JWTFilter;
import webchat.unichat.jwt.JWTUtil;
import webchat.unichat.jwt.LoginFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. csrf 비활성화
        http.csrf(auth -> auth.disable());

        // 2. form 로그인 비활성화
        http.formLogin((auth) -> auth.disable());

        // 3. http basic 인증 방식 disable
        http.httpBasic((auth) -> auth.disable());

        // 4. 요청 경로별 인가 작업
        // login, 회원가입, 아이디 찾기, 비밀번호 찾기는 인증없이 통과할 수 있도록 설정한다.
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers( "/", "/login", "/register", "/findId", "/findPw").permitAll()
                .requestMatchers( "login_page", "/register/check-duplicate", "/chatting_room_list").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll());
//                .anyRequest().authenticated());

        // 5. session 을 stateless 로 설정
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 6. Filter 등록
        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
