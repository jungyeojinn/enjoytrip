<script setup>
import AppFooter from '@/components/AppFooter.vue';
import AppHeader from '@/components/AppHeader.vue';
</script>

<template>
  <v-app id="app">
    <AppHeader />
    <main>
      <RouterView />
    </main>
    <AppFooter />
  </v-app>
</template>

<style scoped></style>
