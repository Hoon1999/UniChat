<template>
    <div id="menu-box">
        <div class="menu-header">
            <div :id="friends" class="selected" @click="clickedMenu">친구</div>
            <div :id="chatting" @click="clickedMenu">채팅</div>
            <!-- <div :id="setting"  @click="clickedMenu">설정</div> -->
        </div>
        <div class="menu-body">
            <InfoCardList v-if="type === friends || type === chatting" :type="type"/>
            <SettingList v-if="type === setting" />
        </div>
    </div>
</template>
<script>
import InfoCardList from './InfoCardList.vue';
import SettingList from './SettingList.vue';

export default {
    name: "MenuBoxComponent",
    methods: {
        clickedMenu(e) {
            const id = e.target.id;
            for(var i = 0; i<2; i++ ) {
                document.getElementsByClassName("menu-header")[0].children[i].classList.remove("selected");
            }
            document.getElementById(id).classList.add("selected");
            this.type = id;
        }
    },
    components: {
        InfoCardList: InfoCardList,
        SettingList: SettingList,
    },
    data() {
        return {
            friends: "friends",
            chatting: "chatting",
            setting: "setting",
            type: "friends",
        }
    }
}
</script>
<style scoped>
    #menu-box {
        width: 300px;
        height: 80vh;
        margin-right: 20px;
        background-color: rgba(255, 255, 255, 0.1);
        backdrop-filter: blur(15PX);
        -webkit-backdrop-filter: blur(15PX);
        border: 1px solid rgba(255, 255, 255, 0.1);
        border-radius: 15px;
    }

    #menu-box {
        display: grid;
        /* grid 모양을 정의하는 부분. */
        grid-template-areas:
            "menu-header"
            "menu-body";
        /* row 를 10등분하고 각 행을 1:9 비율로 설정 */
        grid-template-rows: 1fr 9fr;
    }

    .menu-header {
        display: flex;
        /* 균등하게 분배하여 위치시킨다 */
        justify-content: space-around;
        /* 최대한 길게 늘려준다 */
        /* 대신 중앙 정렬은 안시켜줌 */
        align-items: stretch;
        /* height: 55px; */
    }
    .menu-header > div {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
    }
    
    .selected {
        background-color: rgba(0, 0, 0, 0.2);
        border-radius: 15px;
    }
</style>