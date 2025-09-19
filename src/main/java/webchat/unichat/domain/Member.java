package webchat.unichat.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long memberId;
    @Column(name = "login_id", unique = true, nullable = false)
    private String loginId;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "question", nullable = false)
    private String question;
    @Column(name = "answer", nullable = false)
    private String answer;
    @Column(name = "role", nullable = true)
    private String role;
    @Column(name = "account_status", nullable = false)
    private String accountStatus;
    @Column(name = "account_expire", nullable = false)
    private LocalDateTime accountExpire;
    @Column(name = "password_expire", nullable = false)
    private LocalDateTime passwordExpire;
    @Column(name = "account_disable_reason", nullable = true)
    private String accountDisableReason;


    public Member() {
    }

    public Member(Long memberId, String loginId, String password, String email, String qna_q, String name, String qna_a) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.question = qna_q;
        this.name = name;
        this.answer = answer;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public LocalDateTime getAccountExpire() {
        return accountExpire;
    }

    public LocalDateTime getPasswordExpire() {
        return passwordExpire;
    }

    public String getAccountDisableReason() {
        return accountDisableReason;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setAccountExpire(LocalDateTime accountExpire) {
        this.accountExpire = accountExpire;
    }

    public void setPasswordExpire(LocalDateTime passwordExpire) {
        this.passwordExpire = passwordExpire;
    }

    public void setAccountDisableReason(String accountDisableReason) {
        this.accountDisableReason = accountDisableReason;
    }
}
