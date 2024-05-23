import { defineStore } from "pinia";
import { useCookies } from "vue3-cookies";
import { ref } from "vue";
const { cookies } = useCookies();

export const useUserStore = defineStore(
  "userStore",
  () => {
    const num = ref();
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

    const setUserInfo = (userNum, userId, nickName, imgPath) => {
      num.value = userNum;
      id.value = userId;
      nickname.value = nickName;
      profileImage.value =
        imgPath === null
          ? "/src/assets/default-avatar.png"
          : `${import.meta.env.VITE_BACKEND_BASE_URL}/img/user/${imgPath}`;
    };

    const clear = () => {
      num.value = -1;
      id.value = "";
      nickname.value = "";
      profileImage.value = "";
      cookies.remove("accessToken");
      cookies.remove("refreshToken");
    };

    return {
      num,
      id,
      nickname,
      profileImage,
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
