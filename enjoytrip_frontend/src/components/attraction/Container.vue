<script setup>
import { ref } from 'vue';
import ArrtractionList from './AttractionList.vue';
const selectPlace = ref([]);

const changeSido = (sido) => {
  console.log(`시도 요청 ${sido}`);
}

const changeGungu = (gungu) => {
  console.log(`군구 요청 ${gungu}`);
}

const chageType = (typeNumber) => {
  console.log(`여행지 타입 요청 ${typeNumber}`);
}

</script>

<template>
  <div class="container d-flex w-100">
    <ArrtractionList />
    <KakaoMap @change-sido="changeSido" @change-gungu="changeGungu" @change-type="chageType" />
  </div>
</template>

<style scoped>
.container {
  height: 88vh;
}
</style>