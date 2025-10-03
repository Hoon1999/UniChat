// api/index.js

import request from "./axiosInstance.js";

export function sendRequest(url, method = "get", payload = {}) {
    const config = { url, method };
    
    if(method.toLowerCase()  === "get") {
        // get 요청은 params 로
        config.params = payload;
    } else {
        // post, put 등 요청은 body 에 담는다.
        // data 로 전달하면 body 에 담는다.
        config.data = payload;
    }

    return request(config)
}