<template>
    <div id="message-box" :class="type">
        <div v-if="type !== 'my-message'" id="profile-image"></div>
        <div v-if="type === 'my-message'" id="message-time"> {{ data?.send_time }} </div>
        <div class="message-content"> {{ data?.content }} </div>
        <div v-if="type !== 'my-message'" id="message-time"> {{ data?.send_time }} </div>
    </div>
</template>
<script>
export default {
    name: "MessageBoxComponent",
    props: {
        type: String,
        data: Object,
    },
    data() {
        return {
            content: "message 입니다", 
            time: "오전 01:35"
        }
    }
}
</script>
<style scoped>
    #message-box {
        display: flex;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .my-message {
        justify-content: flex-end;
        margin-right: 10px;
    }
    .my-message .message-content {
        /* background-color: #4e4fd1; */
        background-color: orange;
        color: rgba(255, 255, 255, 1);
    }
    
    .others-message {
        justify-content: flex-start;
    }
    .others-message .message-content {
        /* background-color: orange; */
        background-color: rgba(0, 0, 0, 0.6);
        color: white;
    }

    #profile-image {
        /* 크기가 적용되도록 inline-block 사용 */
        display: inline-block;
        width: 40px;
        height: 40px;
        /* width와 height 의 비율 동일하게 설정 */
        aspect-ratio: 1 / 1;

        /* 둥글게 설정 */
        border-radius: 50%;

        margin-left: 10px;
        margin-right: 10px;
        border: 1px solid black;
    }

    .message-content {
        padding: 10px;
        border: 1px solid black;
        border-radius: 15px
    }

    #message-time {
        margin-top: auto;
        margin-left: 5px;
        margin-right: 5px;

        /* 줄바꿈 방지 */
        white-space: nowrap;
    }
</style>