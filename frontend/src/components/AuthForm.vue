<template>
    <div>
        <h2>Unichat</h2>
        <br />
        <form>
            <AuthInputBox v-if="type === signUp" type="text" placeholder="닉네임" required="requred" />
            <AuthInputBox v-if="type === signUp || type === signIn || type === reset" type="text" placeholder="이메일" required="requred" />
            <AuthInputBox v-if="type === reset" type="text" placeholder="인증번호" required="requred" />
            <AuthInputBox v-if="type === signUp || type === signIn" type="password" placeholder="비밀번호" required="requred" />
            <AuthInputBox v-if="type === signUp" type="confirm-password" placeholder="비밀번호 확인" required="requred" />
            <AuthInputBox type="submit" :value="submitText" required="requred" />
            
            <a v-if="type === signIn" href="#" id="google-btn">Google로 로그인하기</a>
            <p id="auth-links">
                <a href="#" v-if="type !== signUp" :id="signUp" @click="$emit('changeType', signUp)">회원 가입</a>
                <a href="#" v-if="type !== signIn" :id="signIn" @click="$emit('changeType', signIn)">로그인</a>
                <a href="#" v-if="type !== reset" :id="reset" @click="$emit('changeType', reset)">비밀번호 찾기</a>
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
    },
    computed: {
        submitText() {
            // props 로 받은 변수는 this 를 붙여줘야한다.
            if(this.type === "sign-in") {
                return "로그인"
            }
            if(this.type === "sign-up") {
                return "회원가입"
            }
            if(this.type === "reset") {
                return "비밀번호 재설정"
            }
            return "";
        }
    },
    data() {
        return {
            signIn: "sign-in",
            signUp: "sign-up",
            reset: "reset"
        }
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