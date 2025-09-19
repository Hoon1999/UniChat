package webchat.unichat.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import webchat.unichat.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemberDetails implements UserDetails {
    private final Member member;

    public MemberDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });

        return collection;
    }

    public Long getMemberId() {
        return member.getMemberId();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        // true : 만료되지 않음
        // false : 만료됨
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime accountExpire = member.getAccountExpire();
        if(today.isBefore(accountExpire))
            return true;
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // true : lock 아님
        // false : locked
        String accountStatus = member.getAccountStatus();
        if(accountStatus.equals("locked"))
            return false;

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // true : 만료되지 않음
        // false : 만료됨
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime passwordExpire = member.getPasswordExpire();

        if(today.isBefore(passwordExpire))
            return true;

        return false;
    }

    @Override
    public boolean isEnabled() {
        // true : 활성화 상태
        // false : 비활성화 상태
        String accountStatus = member.getAccountStatus();
        if(accountStatus.equals("active"))
            return true;
        else if(accountStatus.equals("disable"))
            return false;

        return false;
    }
}
