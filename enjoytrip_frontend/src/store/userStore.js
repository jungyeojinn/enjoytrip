import { defineStore } from "pinia";

export const useUserStore = defineStore({
  id: "main",
  state: () => ({
    id: "",
    token: "",
  }),
  getters: {
    isLogin: (state) => {
      return state.id !== "";
    },
  },
  actions: {
    setUsername(id: string) {
      this.id = id;
    },
    clearUsername() {
      this.id = "";
    },
    setToken(token: string) {
      this.token = token;
    },
  },
});
