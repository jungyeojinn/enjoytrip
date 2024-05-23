<script setup>
import { ref } from "vue";
import SelectList from "./SelectList.vue";
import KakaoMap from "./KakaoMap.vue";

const selectPlace = ref([]);

const addPlace = (value) => {
  let has = selectPlace.value.some((elem) => {
    return elem.contentId === value.contentId;
  });

  if (has) return;

  let newArr = selectPlace.value.slice(0);
  newArr.push(value);
  selectPlace.value.push(value);
  console.log(selectPlace.value);
};

const deletePlace = (contentId) => {
  let newArr = selectPlace.value.filter((elem) => {
    return elem.contentId !== contentId;
  });
  selectPlace.value = newArr;
};

const swapPlace = (num1, num2) => { };
</script>

<template>
  <div class="container d-flex w-100">
    <SelectList :selectPlace="selectPlace" @delete-place="deletePlace" />
    <KakaoMap @add-place="addPlace" />
  </div>
</template>

<style scoped>
.container {
  height: 100%;
}
</style>
