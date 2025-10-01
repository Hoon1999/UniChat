<template>
    <div id="container">
        <div v-if="type === 'friends'" id="header">
            <InfoCard />
            <div id="options">
                <i id="edit-card" class="fa fa-pencil-square-o" aria-hidden="true"></i>
                <i id="add-card" class="fa fa-user-plus" aria-hidden="true"></i>
            </div>
        </div>
        <div id="middle">
            <input type="text" id="search" placeholder="Search">
            <!-- 방 생성 버튼 -->
            <i v-if="type === 'chatting'" id="add-chat" class="fa fa-plus-square-o" aria-hidden="true"
                @click="createChattingRoom"></i>
        </div>

        <div id="body">
            <!-- 친구 리스트(친구 카드를 클릭하면 아래로 펼쳐진다. 친구의 이메일도 볼 수 있다.) -->
            <InfoCard v-for="card in cards" :key="card.id" :data="card" @clickedCard="$emit('clickedCard', $event)"/>
        </div>
    </div>
</template>
<script>
import InfoCard from './InfoCard.vue';

export default {
    name: "InfoCardListComponent",
    props: {
        type: String,
    },
    components: {
        InfoCard: InfoCard
    },
    data() {
        return {
            cards: [],
        }
    },
    methods: {
        loadChattingRooms() {
            console.log("로드 채팅 룸 호출됨");
            const self = this;
            const xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    // 만들어진 채팅방을 삽입
                    // 채팅방 목록 초기화
                    self.cards = [];

                    let results = JSON.parse(this.responseText).data;
                    console.log("results[0]: ", results[0]);
                    console.log("response: ", this.responseText);

                    for (let i = 0; i < results.length; i++) {
                        self.cards.push(results[i])
                    }
                }
            }
            xhttp.open("POST", "http://localhost:8080/chatRoom");
            xhttp.send();
        },

        createChattingRoom() {
            // xhttp 내부에서 this 는 xhttp 를 가리킨다.
            // self 에 vue 인스턴스를 미리 담아놓는다.
            const self = this;
            const xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    self.loadChattingRooms();
                }
            };
            xhttp.open("POST", "http://localhost:8080/createChattingRoom");
            xhttp.send();
        }
    }
}
</script>
<style scoped>
#container {
    display: flex;
    flex-direction: column;
    height: 72vh;
}

#header {
    display: flex;
}

#edit-card,
#add-card {
    position: absolute;
}

#edit-card {
    right: 30px;
}

#add-card {
    right: 5px;
}

#middle {
    display: flex;
    justify-content: center;
    align-items: center;
}

#search {
    display: inline-block;
    width: 80%;
    height: 30px;
    padding-left: 15px;
    margin-top: 10px;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 10px;
    border-radius: 5px;
    border: 0;
}

#add-chat {
    /* display: ; */
    margin: auto;
    font-size: 20pt;
}

#body {
    /* height: 55vh; */
    /* display: block; */
    display: 1;
    /* flex-direction: column; */
    overflow-y: scroll;
    overflow-x: hidden;
}

/* 기본 스크롤바는 네모난 흰색 배경이 있어서 디자인을 해침 */
/* 그래서 새로운 스크롤바를 만들어서 적용 */
#body::-webkit-scrollbar {
    width: 6px;
}

#body::-webkit-scrollbar-track {
    background-color: rgba(0, 0, 0, 0);
}

#body::-webkit-scrollbar-thumb {
    background-color: rgba(0, 0, 0, 0.2);
    border-radius: 6px;
}
</style>