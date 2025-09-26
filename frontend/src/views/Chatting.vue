<template>
    <div id="background">
        <MenuBox />
        <ChattingBox />
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
    }
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