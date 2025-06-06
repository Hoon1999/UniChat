package webchat.unichat.form;

public class MemberForm {
    private Long memberId;
    private String loginId;
    private String password;
    private String email;
    private String answer;
    private String question;
    private String name;

    public MemberForm() {
    }

    public MemberForm(Long memberId, String loginId, String password, String email, String answer, String question, String name) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.answer = answer;
        this.question = question;
        this.name = name;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
