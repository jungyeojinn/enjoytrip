<script setup>
import { ref, onMounted } from 'vue';

const sido = ref([
  { area: '서울', code: 'FL' },
  { area: '경기', code: 'GA' },
  { area: '강원', code: 'NE' },
  { area: '제주', code: 'CA' },
  { area: '부산', code: 'NY' },
]);

const emit = defineEmits(['changeSido', 'changeGungu', 'changeType']);

const handleSido = (value) => {
  emit('changeSido', value);
}

const handleGungu = (value) => {
  emit('changeGungu', value);
}

const handleType = (value) => {
  emit('changeType', value);
}

onMounted(() => {
});
</script>

<template>
  <div class="w-75">
    <div id="select-area" class="d-flex ">
      <v-select class="w-25" :items="sido" item-title="area" item-value="code" label="시도"
        @update:modelValue="handleSido"></v-select>
      <v-select class="w-25" :items="sido" item-title="area" item-value="code" label="군구"
        @update:modelValue="handleGungu"></v-select>
      <v-select class="w-25" :items="sido" item-title="area" item-value="code" label="여행지 유형"
        @update:modelValue="handleType"></v-select>
    </div>
    <div id="map"> 지도 영역</div>
  </div>
</template>

<style scoped>
#map {
  height: 90%;
}

#select-area {
  height: 10%;
}
</style>
