<script setup>
import { ref } from 'vue';

</script>

<template>
  <v-toolbar border color="#FFFFFF">
    <v-container id="header-wrap" class="d-flex justify-space-between align-center">
      <router-link to="/">
        <img src="/src/assets/logo.png" style="width: 80px;">
      </router-link>
      <nav class="navbar d-flex justify-space-between">
        <router-link to="/hotplace" exact-active-class="active-link">핫플레이스</router-link>
        <router-link to="/board" exact-active-class="active-link">게시판</router-link>
        <router-link to="/attraction" exact-active-class="active-link">여행지 탐색</router-link>
      </nav>
      <v-btn icon="mdi-account-circle"></v-btn>
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
