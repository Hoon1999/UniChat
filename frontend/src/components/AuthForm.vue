<template>
    <div>
        <h2>Unichat</h2>
        <br />
        <form id="form" @submit.prevent="submitForm">
            <AuthInputBox v-if="type === signUp" type="text" name="name" placeholder="닉네임" required="required" />
            <AuthInputBox v-if="type === signUp || type === signIn || type === reset" type="text" name="email"  @change="checkDuplicate" placeholder="이메일" required="required" />
            <label v-if="type === signUp" id="checkEmail" style="display: none;"></label>
            <AuthInputBox v-if="type === reset" type="text" name="code" placeholder="인증번호" required="required" />
            <AuthInputBox v-if="type === signUp || type === signIn" type="password" name="password" placeholder="비밀번호" required="required" />
            <AuthInputBox v-if="type === signUp" type="password" name="confirm-password" placeholder="비밀번호 확인" required="required" />
            <AuthInputBox type="submit" :value="submitText" required="required" />

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
            if (this.type === "sign-in") {
                return "로그인"
            }
            if (this.type === "sign-up") {
                return "회원가입"
            }
            if (this.type === "reset") {
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
    },
    methods: {
        submitForm() {
            const form = document.getElementById("form");
            const formData = new FormData(form);
            const xhttp = new XMLHttpRequest();

            if (this.type === this.signIn) {
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        location.href = "/chatting_room_list"
                    }
                    else if (this.readyState == 4 && this.status != 200) {
                        alert("로그인에 실패하였습니다.");
                    }
                }
                xhttp.open("POST", "http://localhost:8080/login");
                xhttp.send(formData);
            }
            else if (this.type === this.signUp) {
                const checkEmail = document.getElementById("checkEmail");
                if (checkEmail.value != true) {
                    alert("이메일 중복을 확인해주세요.");
                    return;
                }
                const xhttp = new XMLHttpRequest();
                const form = new FormData(document.getElementById("form"));

                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        if (this.responseText == "success") {
                            alert("회원가입이 완료되었습니다.");
                            location.href = "/login_page"
                        }
                        else {
                            alert("회원가입에 실패하였습니다.");
                        }
                    }
                }
                xhttp.open("POST", "http://localhost:8080/register");
                xhttp.send(form);
            }
            else if (this.type === this.reset) {
                console.log("미구현");
            }
        },
        checkDuplicate(event) {
            if(this.type !== this.signUp) {
                return;
            }
            const inputName = event.target.name;
            const inputValue = event.target.value;
            const data = {
                [inputName]: inputValue
            }
            const xhttp = new XMLHttpRequest();

            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let label;
                    if (inputName == "email") {
                        label = document.getElementById("checkEmail");
                        label.style.display = "block";
                    }

                    const response = JSON.parse(this.responseText);
                    if(response.result == "success") {
                        // 경고 글자 색을 녹색으로 변경합니다
                        // value 가 true 면 회원가입 버튼을 클릭했을 때 통과가 가능합니다.
                        label.style.color = "green";
                        label.value = true;
                    }
                    else if(response.result == "fail") {
                        // 경고 문구 글자 색을 적색으로 변경합니다
                        // value 가 false 면 회원가입 버튼을 클릭했을 때 통과가 불가능합니다.
                        label.style.color = "red";
                        label.value = false;
                    }
                    label.innerText = response.message;
                }
            };
            xhttp.open("POST", "http://localhost:8080/register/check-duplicate");
            xhttp.setRequestHeader("Content-Type", "application/json");
            xhttp.send(JSON.stringify(data));
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