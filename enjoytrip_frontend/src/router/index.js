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

import AttractionDetail from "@/components/attraction/AttractionDetail.vue";
import HotPlaceWrite from "@/components/hotplace/HotPlaceWrite.vue";
import HotPlaceEdit from "@/components/hotplace/HotPlaceEdit.vue";

import BoardDeatil from "@/components/board/BoardDeatil.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardEdit from "@/components/board/BoardEdit.vue";
import HotPlaceDeatil from "@/components/hotplace/HotPlaceDeatil.vue";

const { cookies } = useCookies();
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: "/", component: Attraction, name: "home" },
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
    { path: "/attraction/:id", component: AttractionDetail },
    { path: "/attraction-board", component: AttractionBoard },
    { path: "/login", component: Login, name: "login" },
    { path: "/signup", component: SignUp },
    { path: "/mypage/:id", component: MyPage },
    { path: "/hotplace/write", component: HotPlaceWrite },
    { path: "/hotplace/edit/:id", component: HotPlaceEdit },
    { path: "/hotplace/:id", component: HotPlaceDeatil },
    { path: "/board/write", component: BoardWrite, name: "boardWrite" },
    { path: "/board/edit/:id", component: BoardEdit },
  ],
  linkExactActiveClass: "active-link",
});

export default router;
