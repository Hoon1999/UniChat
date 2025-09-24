<template>
    <div>
        <h2>Unichat</h2>
        <br />
        <form>
            <AuthInputBox v-if="type === 'sign-in' || type === 'reset'" type="text" placeholder="이메일" required="requred" />
            <AuthInputBox v-if="type === 'sign-in'" type="password" placeholder="비밀번호" required="requred" />
            <AuthInputBox v-if="type === 'sign-in'" type="submit" value="로그인" required="requred" />
            
            <a href="#" id="google-btn">Google로 로그인하기</a>
            <p id="auth-links">
                <a href="#" id="sign-up">회원 가입</a>
                <a href="#" id="reset">비밀번호 찾기</a>
            </p>
        </form>
    </div>
</template>
<script>
import AuthInputBox from './AuthInputBox.vue';

export default {
    name: "AuthFormComponent",
    components: {
        AuthInputBox: AuthInputBox,
    },
    props: {
        type: String,
    }
}
</script>
<style scoped>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: system-ui;
    }

    h2 {
        text-align: center;
    }
    
    #google-btn {
        padding: 15px 60px;
        width: 100%;
        background-color: white;
        border-radius: 5px;
        text-decoration: none;
        color: black;
        font-weight: 600;
    }

    #google-btn .bx {
        font-size: 22px;
        vertical-align: middle;
    }
    
    #auth-links {
        text-align: center;
        margin-top: 25px;
    }

    #auth-links a {
        color: #aaa;
        text-decoration: none;
    }

    #auth-links a:not(:last-child)::after {
        margin-left: 5px;
        margin-right: 5px;
        content: "∣";
        color: #aaa;
    }
</style>