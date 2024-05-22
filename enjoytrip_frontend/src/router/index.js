import { createRouter, createWebHistory } from "vue-router/auto";
import { useCookies } from "vue3-cookies";

import Home from "@/pages/Home.vue";
import HotPlace from "@/pages/HotPlace.vue";
import Board from "@/pages/Board.vue";
import Attraction from "@/pages/Attraction.vue";
import AttractionBoard from "@/pages/AttractionBoard.vue";
import Login from "@/pages/Login.vue";
import SignUp from "@/pages/SignUp.vue";
import MyPage from "@/pages/MyPage.vue";

import HotPlaceWrite from "@/components/hotplace/HotPlaceWrite.vue";
import BoardDeatil from "@/components/board/BoardDeatil.vue";
import HotPlaceDeatil from "@/components/hotplace/HotPlaceDeatil.vue";

const { cookies } = useCookies();
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: "/", component: Home, name: "home" },
    { path: "/hotplace", component: HotPlace, name: "hotplace" },
    {
      path: "/board",
      component: Board,
      name: "board",
    },
    {
      path: "/board/:id",
      component: BoardDeatil,
    },
    { path: "/attraction", component: Attraction, name: "attraction" },
    { path: "/attraction-board", component: AttractionBoard },
    { path: "/login", component: Login },
    { path: "/signup", component: SignUp },
    { path: "/mypage/:id", component: MyPage },
    { path: "/hotplace/write", component: HotPlaceWrite },
    { path: "/hotplace/:id", component: HotPlaceDeatil },
  ],
  linkExactActiveClass: "active-link",
});

export default router;
