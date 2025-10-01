<template>
    <div id="background">
        <MenuBox @clickedCard="changeRoomId" />
        <ChattingBox :roomId="roomId"/>
    </div>
</template>
<script>
import ChattingBox from '@/components/ChattingBox.vue';
import MenuBox from '@/components/MenuBox.vue';

export default {
    name: "ChattingComponent",
    components: {
        ChattingBox: ChattingBox,
        MenuBox: MenuBox,
    },
    data() {
        return {
            roomId: "",
        }
    },
    methods: {
        changeRoomId(roomId) {
            this.roomId = roomId;
        }
    },
}
</script>
<style scoped>
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
</style>