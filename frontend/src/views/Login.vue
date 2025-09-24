<template>
    <div id="background">
        <div class="inner-box">
            <AuthForm :type="type"/>
        </div>
    </div>
</template>
<script>
import AuthForm from '@/components/AuthForm.vue';

export default {
    name: "LoginComponent",
    components: {
        AuthForm: AuthForm
    },
    data() {
        return {
            type: "sign-in"
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

    #background {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 100vh;
        background: url(../assets/back.png);
        background-size: cover;
        background-position: bottom;
        background-repeat: no-repeat;
    }

    .inner-box {
        /* display: flex; */
        /* position: relative; */
        /* justify-content: center; */
        /* align-items: center; */
        padding: 30px;
        /* 글래스모피즘 적용 */
        /* 1. 배경에 반투명 필터 만들기 */
        background-color: rgba(255, 255, 255, 0.1);
        /* 2. 배경을 블러 처리 */
        backdrop-filter: blur(15px);
        -webkit-backdrop-filter: blur(15px);
        box-shadow: 0, 8px, 32px, rgba(0, 0, 0, 0.2);
        border: 1px solid rgba(255, 255, 255, 0.2);
        border-radius: 15px;
    }
</style>