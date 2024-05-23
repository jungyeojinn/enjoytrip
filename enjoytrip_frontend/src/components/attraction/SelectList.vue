<script setup>
import Card from "./Card.vue";

const props = defineProps(["selectPlace"]);
const emit = defineEmits(["deletePlace"]);

const delPlace = (index) => {
  emit("deletePlace", index);
};
</script>

<template>
  <div class="w-25 overflow-y-auto">
    <h2>선택한 여행지</h2>
    <div>빠밤</div>
    <Card class="mx-auto mb-2" v-for="locateInfo of props.selectPlace" :locateInfo="locateInfo" @del-place="delPlace" />
  </div>
</template>

<style scoped>
h2 {
  max-width: 344px;
  margin: 30px auto;
}

div {
  max-width: 344px;
  margin: 30px auto;
}
</style>
