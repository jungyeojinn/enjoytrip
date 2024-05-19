import { createRouter, createWebHistory } from "vue-router/auto";

import Home from "@/pages/Home.vue";
import HotPlace from "@/pages/HotPlace.vue";
import Board from "@/pages/Board.vue";
import Attraction from "@/pages/Attraction.vue";
import AttractionBoard from "@/pages/AttractionBoard.vue";
import BoardDeatil from "@/components/board/BoardDeatil.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: "/", component: Home },
    { path: "/hotplace", component: HotPlace },
    {
      path: "/board",
      component: Board,
    },
    {
      path: "/board/:id",
      component: BoardDeatil,
    },
    { path: "/attraction", component: Attraction },
    { path: "/attraction-board", component: AttractionBoard },
  ],
  linkExactActiveClass: "active-link",
});

export default router;
