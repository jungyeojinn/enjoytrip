import axios from "axios";

// axios 객체 생성
export default axios.create({
  //baseURL: 'http://localhost:8080/enjoytrip', // .env 로 옮기기
  baseURL: import.meta.env.VITE_BACKEND_BASE_URL,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});
