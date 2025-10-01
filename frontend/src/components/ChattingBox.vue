<template>
    <div class="container">
        <div class="header">
            <div id="image-box"></div>
            <div>
                <h2>{{ title }} 님과의 대화</h2>
                <div><i class="fa fa-user" aria-hidden="true"></i> {{ username }}</div>
            </div>
            <div id="call-buttons">
                <i class="fa fa-phone" aria-hidden="true"></i>
                <i class="fa fa-video-camera" aria-hidden="true"></i>
                <i class="fa fa-user-plus" aria-hidden="true"></i>
            </div>
        </div>

        <div class="center">
            <MessageBox type="others-message" />
            <MessageBox type="my-message" />
            <MessageBox v-for="box in boxes" :key="box.id" :data="box" />
        </div>

        <div class="footer">
            <input type="file" id="attached-file" @change="attachFile">
            <i class="fa fa-paperclip" aria-hidden="true" @click="clickAttachedFileButton"></i>
            <input type="text" id="text-box">
            <input v-if="showReset" type="reset" value="취소" @click="resetAttachFile">
            <input type="submit" value="전송">
        </div>
    </div>
</template>
<script>
import MessageBox from './MessageBox.vue';

export default {
    name: "ChattingBoxComponent",
    components: {
        MessageBox: MessageBox,
    },
    props: {
        roomId: String,
    },
    data() {
        return {
            title: "홍길동",
            username: "홍길동",
            showReset: false,
            tempText: "",
            boxes: [],
        }
    },
    methods: {
        clickAttachedFileButton() {
            document.getElementById("attached-file").click();
        },
        attachFile() {
            const fullPath = document.getElementById("attached-file").value;
            const fileName = fullPath.split("\\").pop();
            this.tempText = document.getElementById("text-box").value;
            document.getElementById("text-box").value = fileName;
            document.getElementById("text-box").disabled = true;
            this.showReset = true;
        },
        resetAttachFile() {
            document.getElementById("attached-file").value = "";
            document.getElementById("text-box").value = this.tempText;
            document.getElementById("text-box").disabled = false;
            this.showReset = false;
        },
        connectStomp(roomId) {
            /* global SockJS, Stomp */
            var sock = new SockJS("http://localhost:8080/stomp/chat");
            var client = Stomp.over(sock);
            const self = this;
            client.connect({},
                function () {
                    console.log("Stomp 서버에 연결되었습니다.");
                    client.subscribe("/topic/room/" + roomId, function (e) {
                        // 메세지를 수신하면 할 동작을 정의한다.
                        // 수신한 내용을 말풍선에 담아 화면에 출력한다.
                        let obj = JSON.parse(e.body);
                        let sender = obj.sender;
                        let sender_name = obj.sender_name;
                        let msg = obj.content;
                        let time = obj.send_time;
                        console.log(sender, " ", sender_name, " ", msg, " ", time);
                        self.boxes.push(obj);
                        // createSpeechBalloon(sender, sender_name, msg, time);
                        // document.getElementsByClassName("chat-container").item(0).lastChild.scrollIntoView();
                    });
                    //let msg = { sender : roomId, content : "접속 테스트용 메세지입니다." };
                    //socket.send("/chatting/room/" + roomId, {}, JSON.stringify(msg));
                });
            let tb = document.getElementById("text-box");
            tb.onkeypress = function (e) {
                if (e.keyCode == 13 && !e.shiftKey) {
                    let msg = { sender : "1", content : tb.value };
                    client.send("/chatting/room/" + roomId, {}, JSON.stringify(msg));
                    tb.value = ""; // 비워주기
                }
            }
        },
    },
    watch: {
        roomId(newVal) {
            this.connectStomp(newVal);
        }
    }
}
</script>
<style scoped>
.container {
    display: grid;
    /* grid 모양을 정의하는 부분. 
         * 여기서는 상 중 하 만 필요하므로 상 중 하 를 표현했다. */
    grid-template-areas:
        "header"
        "center"
        "footer";
    /* row 를 10등분하고 각 행을 1:8:1 비율로 설정 */
    grid-template-rows: 1fr 8fr 1fr;
    width: 800px;
    height: 80vh;
    background-color: rgba(255, 255, 255, 0.1);
    backdrop-filter: blur(15PX);
    -webkit-backdrop-filter: blur(15PX);
    border: 1px solid rgba(255, 255, 255, 0.1);
    border-radius: 15px;
}

.container>div.center {
    /* 화면을 벗어나는 채팅내역은 스크롤로 나타나게 함. */
    overflow-y: scroll;
    overflow-x: hidden;
    border-top: 1px solid rgba(255, 255, 255, 0.1);
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

/* 기본 스크롤바는 네모난 흰색 배경이 있어서 디자인을 해침 */
/* 그래서 새로운 스크롤바를 만들어서 적용 */
.container>div.center::-webkit-scrollbar {
    width: 6px;
}

.container>div.center::-webkit-scrollbar-track {
    background-color: rgba(0, 0, 0, 0);
}

.container>div.center::-webkit-scrollbar-thumb {
    background-color: rgba(0, 0, 0, 0.2);
    border-radius: 6px;
}

.container>div.header {
    display: flex;
    align-items: center;
}

#image-box {
    /* 크기가 적용되도록 inline-block 사용 */
    display: inline-block;
    height: 80%;
    /* width와 height 의 비율 동일하게 설정 */
    aspect-ratio: 1 / 1;

    /* 둥근 사각형 설정 */
    border-radius: 45%;

    margin-left: 10px;
    margin-right: 20px;
    border: 1px solid black;
}

#call-buttons {
    margin-left: auto;
    display: flex;
}

#call-buttons>* {
    margin-right: 20px;
}

.container>div.footer {
    display: flex;
}

.container>div.footer>* {
    border: none;
    cursor: pointer;
}

/* 첨부파일 버튼 숨기기 */
.container>div.footer>input[type="file"] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}

.container>div.footer>i {
    height: 25px;
    font-size: 25px;
    margin-top: auto;
    margin-bottom: auto;
    margin-left: 10px;
    margin-right: 10px;
}

.container>div.footer>input[type="text"] {
    width: 100%;
    height: 35px;
    margin-top: auto;
    margin-bottom: auto;
    padding-left: 10px;
    border-radius: 5px;
}

.container>div.footer>input[type="reset"],
.container>div.footer>input[type="submit"] {
    /* padding: 10px 20px; */
    width: 100px;
    height: 35px;
    margin-top: auto;
    margin-bottom: auto;
    border-radius: 5px;
}

.container>div.footer>input[type="reset"] {
    margin-left: 10px;
}

.container>div.footer>input[type="submit"] {
    margin-left: 10px;
    margin-right: 10px;
    background-color: orange;
    color: white;
}
</style>