<template>
    <div>
        <input :type="type" :name="name" :value="value" :required="required"/>
        <span v-if="type !== 'submit'"> {{ placeholder }}</span>
    </div>
</template>
<script>
export default {
    name: "AuthInputBoxComponent",
    props: {
        // 부모 Component 에서 가져온 데이터의 자료형을 적어야한다.
        placeholder: String,
        value: String,
        type: String,
        name: String,
        required: String,
    },
    data() {
        return {
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

    div {
        position: relative;
        margin-bottom: 25px;
    }

    input {
        width: 261px;
        height: 55px;
        border: 0;
        outline: 0;
        background: transparent;
        padding-left: 20px;
        font-size: 14px;
        box-shadow: 0, 5px, 15px, rgba(0, 0, 0, 0.05);
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    span {
        position: absolute;
        left: 15px;
        top: 20px;
        transition: 0.5s;
    }

    input:focus ~ span,
    input:valid ~ span {
        position: absolute;
        left: 10px;
        top: -12px;
        padding: 4px;
        transition: 0.5s;
        background-color: white;
        border-radius: 5px;
        pointer-events: none;
    }

    input[type="text"]:focus,
    input[type="password"]:focus {
        border: 2px solid white;
    }

    input[type="submit"] {
        width: 100%;
        padding-left: 0;
        background-color: orange;
        box-shadow: none;
        border: none;
        letter-spacing: 0;
        /* 버튼에 마우스 올렸을때 손가락 모양 표시되게 설정  */
        cursor: pointer;
        color: white;
        margin-top: 10px;
        font-size: 16px;
        /* 색이 변경되는 애니메이션 적용 속도 */
        transition: 1.5s;
        font-weight: 600;
    }

    input[type="submit"]:hover {
        transition: 0.5s;
        background-color: white;
        color: orange;
    }
</style>