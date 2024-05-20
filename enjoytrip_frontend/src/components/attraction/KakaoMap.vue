<script setup>
import { ref, onMounted } from "vue";
import { createMarker, createBounds, removeAllMarker } from '@/utils/kakao';
import SelectAttraction from './SelectAttraction.vue';
import Aside from './Aside.vue';

const markers = ref([]);
const locate = ref([]);
const infowindow = ref(null);
const map = ref(null);

const emit = defineEmits(['addPlace']);

const clickButton = (value) => {
  emit('addPlace', value);
}

const setMarker = (data) => {
  locate.value = data.map((elem) => {
    if (elem.firstImage.length > 0) {
      return elem;
    } else {
      return { ...elem, firstImage: "/src/assets/img-placeholder.png" };
    }
  });
  let markerArr = [];

  createBounds(map.value, data);
  removeAllMarker(markers.value);

  data.forEach((elem) => {
    createMarker(map.value, elem, markerArr);
  });

  markers.value = markerArr;
}

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 5,
  };

  map.value = new kakao.maps.Map(container, options);
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.onload = () => kakao.maps.load(initMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
    document.head.appendChild(script);
  }
});
</script>

<template>
  <div class="w-75 h-100">
    <SelectAttraction @set-marker="setMarker" />
    <div id="map"></div>
    <Aside :locate="locate" @click-button="clickButton" />
  </div>
</template>

<style scoped>
#map {
  height: 100%;
}

#select-area {
  width: 100%;
  height: 10%;
  gap: 50px;
}
</style>
