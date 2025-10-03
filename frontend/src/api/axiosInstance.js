import axios from "axios";

// axios 인스턴스를 생성합니다.
const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/",
    timeout: 1000 * 5, // 시간 제한 설정, 타임아웃되면 err 처리된다.
});

// request 인터셉터
axiosInstance.interceptors.request.use(
    (config) => {
        // 요청을 보내기 전에 수행해야 할 일
        // 모든 request 요청에 공통적으로 필요한 것을 작성해주면 됨

        return config;
    },
    (error) => {
        // 오류 요청을 보내기 전에 수행해야 할 일 
        // 모든 오류요청에 공통적으로 필요한 것을 작성해주면 됨

        return Promise.reject(error);
    }
);

// response 인터셉터
axiosInstance.interceptors.response.use(
    (response) => {
        // http status가 200인 경우 응답 바로 직전에 대해 작성. 
        
        return response;
    },
    (error) => {
        // http status가 200인 아닌 경우 응답 바로 직전에 대해 작성.
        return Promise.reject(error)
    }
);

export default axiosInstance;