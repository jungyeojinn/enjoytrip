import { defineStore } from "pinia";
import { useCookies } from "vue3-cookies";

const { cookies } = useCookies();

export const useUserStore = defineStore({
  id: "main",
  state: () => ({
    id: "",
    nickname: "",
    token: "",
  }),
  getters: {
    isLogin: (state) => {
      return state.id !== "";
    },
  },
  actions: {
    setCookie(accessToken, refreshToken) {
      cookies.set("accessToken", accessToken, import.meta.env.VITE_ACCESS_TIME);
      cookies.set(
        "refreshToken",
        refreshToken,
        import.meta.env.VITE_REFRESH_TIME
      );
    },
    setUserInfo(id, nickname) {
      this.id = id;
      this.nickname = nickname;
    },
    clearUserName() {
      this.id = "";
      this.nickname = "";
    },
    setToken(token) {
      this.token = token;
    },
  },
});
