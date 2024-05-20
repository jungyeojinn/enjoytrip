<script setup>
import AppFooter from '@/components/AppFooter.vue';
import AppHeader from '@/components/AppHeader.vue';
</script>

<template>
  <v-app>
    <AppHeader />
    <main>
      <RouterView />
    </main>
  </v-app>
</template>

<style scoped>
main {
  width: 100vw;
  height: 100%;
}
</style>
