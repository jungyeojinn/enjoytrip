<script setup>
import { ref } from "vue";
import SelectList from "./SelectList.vue";
import KakaoMap from "./KakaoMap.vue";

const selectPlace = ref([]);
</script>

<template>
  <div class="container d-flex w-100">
    <SelectList />
    <KakaoMap />
  </div>
</template>

<style scoped>
.container {
  height: 88vh;
}
</style>
