<script setup>
import { ref } from "vue";
import SelectList from "./SelectList.vue";
import KakaoMap from "./KakaoMap.vue";

const selectPlace = ref([]);

const addPlace = (value) => {
  let newArr = selectPlace.value.slice(0);
  newArr.push(value);
  selectPlace.value = newArr;
}

const deletePlace = (contentId) => {

}

const swapPlace = (num1, num2) => {

}
</script>

<template>
  <div class="container d-flex w-100">
    <SelectList :selectPlace="selectPlace" />
    <KakaoMap @add-place="addPlace" />
  </div>
</template>

<style scoped>
.container {
  height: 100%;
}
</style>
