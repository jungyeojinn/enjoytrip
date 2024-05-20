<script setup>
import Card from "./Card.vue";

const props = defineProps(['selectPlace']);
console.log(props.selectPlace);
</script>

<template>
  <div class="w-25 overflow-y-auto">
    <h2>선택한 여행지</h2>
    <Card class="mx-auto mb-2" v-for="locateInfo of props.selectPlace" :locateInfo="locateInfo" />
  </div>
</template>

<style scoped>
h2 {
  max-width: 344px;
  margin: 30px auto;
}
</style>
