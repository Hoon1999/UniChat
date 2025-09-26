<template>
    <div id="info-card">
        <div id="image-box"></div>
        <div id="info">
                <h3 id="my-name">홍길동</h3>
                <div id="state-message">상태메세지</div>
            </div>
    </div>
</template>
<script>
export default {
    name: "InfoCardComponent"
}
</script>
<style scoped>
    #info-card {
        display: flex;
        margin-top: 15px;
        margin-bottom: 5px;
    }
    
    #image-box {
        /* 크기가 적용되도록 inline-block 사용 */
        display: inline-block;
        width: 50px;
        height: 50px;
        /* width와 height 의 비율 동일하게 설정 */
        aspect-ratio: 1 / 1;

        /* 둥근 사각형 설정 */
        border-radius: 50%;

        margin-top: auto;
        margin-bottom: auto;
        margin-left: 10px;
        margin-right: 20px;
        border: 1px solid black;
    }
</style>