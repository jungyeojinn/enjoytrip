import { defineStore } from "pinia";
import { useCookies } from "vue3-cookies";
import { ref } from "vue";
const { cookies } = useCookies();

export const useUserStore = defineStore(
  "userStore",
  () => {
    const id = ref("");
    const nickname = ref("");
    const token = ref("");

    const isLogin = () => {
      return id.value !== "";
    };

    const setCookie = (accessToken, refreshToken) => {
      token.value = accessToken;
      cookies.set("accessToken", accessToken, import.meta.env.VITE_ACCESS_TIME);
      cookies.set(
        "refreshToken",
        refreshToken,
        import.meta.env.VITE_REFRESH_TIME
      );
    };

    const setUserInfo = (userId, nickName) => {
      id.value = userId;
      nickname.value = nickName;
    };

    const clearUserName = () => {
      id.value = "";
      nickname.value = "";
    };

    return {
      id,
      nickname,
      token,
      isLogin,
      setCookie,
      setUserInfo,
      clearUserName,
    };
  },
  {
    persist: true,
  }
);
