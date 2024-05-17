<script setup>
import { ref } from "vue";
import ArrtractionList from "./AttractionList.vue";
import KakaoMap from "./KakaoMap.vue";

const selectPlace = ref([]);
</script>

<template>
  <div class="container d-flex w-100">
    <ArrtractionList />
    <KakaoMap />
  </div>
</template>

<style scoped>
.container {
  height: 88vh;
}
</style>
