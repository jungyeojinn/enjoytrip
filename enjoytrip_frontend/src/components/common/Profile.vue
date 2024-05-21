<script setup>
import { useUserStore } from '@/store/userStore';
import { logout } from '@/api/user';

const userStore = useUserStore();
const exit = () => {
  logout(userStore.id);
  userStore.clear();
}
</script>

<template>
  <v-container class="container">
    <v-row justify="center">
      <v-menu min-width="200px" rounded>
        <template v-slot:activator="{ props }">
          <v-btn icon v-bind="props">
            <v-avatar>
              <v-img src="/src/assets/default-avatar.png" cover></v-img>
            </v-avatar>
          </v-btn>
        </template>
        <v-card>
          <v-card-text>
            <div class="mx-auto text-center">

              <h3>{{ userStore.nickname }}</h3>
              <p class="text-caption mt-1">
                {{ userStore.id }}
              </p>
              <v-divider class="my-3"></v-divider>
              <v-btn variant="text" rounded> 회원 정보 수정 </v-btn>
              <v-divider class="my-3"></v-divider>
              <v-btn variant="text" rounded @click="exit"> 로그아웃 </v-btn>
            </div>
          </v-card-text>
        </v-card>
      </v-menu>
    </v-row>
  </v-container>
</template>

<style scoped>
.container {
  width: 48px;
  margin: 0;
}
</style>
