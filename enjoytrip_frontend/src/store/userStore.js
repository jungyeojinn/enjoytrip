import { defineStore } from "pinia";
import { useCookies } from "vue3-cookies";
import { ref } from "vue";
const { cookies } = useCookies();

export const useUserStore = defineStore(
  "userStore",
  () => {
    const id = ref("");
    const nickname = ref("");
    const profileImage = ref(null);

    const isLogin = () => {
      return id.value !== "";
    };

    const setCookie = (accessToken, refreshToken) => {
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

    const clear = () => {
      id.value = "";
      nickname.value = "";
      cookies.remove("accessToken");
      cookies.remove("refreshToken");
    };

    return {
      id,
      nickname,
      isLogin,
      setCookie,
      setUserInfo,
      clear,
    };
  },
  {
    persist: true,
  }
);
