<script setup>
import Profile from "@/components/common/Profile.vue";
import { useUserStore } from '@/store/userStore';

const userStore = useUserStore();
</script>

<template>
  <v-toolbar border color="#FFFFFF">
    <v-container id="header-wrap" class="d-flex justify-space-between align-center">
      <nav class="navbar d-flex justify-space-between align-center">
        <router-link to="/" class="mr-10">
          <img src="/src/assets/logo.png" style="width: 80px" />
        </router-link>
        <router-link to="/hotplace" exact-active-class="active-link">핫플레이스</router-link>
        <router-link to="/board" exact-active-class="active-link">게시판</router-link>
        <router-link to="/attraction-board" exact-active-class="active-link">여행지 리스트</router-link>
        <router-link to="/attraction" exact-active-class="active-link">여행지 탐색</router-link>
      </nav>
      <Profile v-if="userStore.isLogin()" />
      <router-link v-if="!userStore.isLogin()" to="/login" exact-active-class="active-link">로그인</router-link>
    </v-container>
  </v-toolbar>
</template>

<style scoped>
#header-wrap {
  margin: 0 auto;
  max-width: 1280px;
}

.navbar {
  gap: 10px;
}

a {
  color: black;
  text-decoration: none;
}

.active-link {
  color: blue;
}
</style>
