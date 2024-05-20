<script setup>
import { ref, onMounted } from "vue";
import SelectAttraction from './SelectAttraction.vue';

const markers = ref([]);
const infowindow = ref(null);
const map = ref(null);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 5,
  };

  map.value = new kakao.maps.Map(container, options);
};

const changeSize = (size) => {
  const container = document.getElementById("map");
  container.style.width = `${size}px`;
  container.style.height = `${size}px`;
  toRaw(map.value).relayout();
};

const displayMarker = (markerPositions) => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }

  const positions = markerPositions.map(
    (position) => new kakao.maps.LatLng(...position)
  );

  if (positions.length > 0) {
    markers.value = positions.map(
      (position) =>
        new kakao.maps.Marker({
          map: toRaw(map.value),
          position,
        })
    );

    const bounds = positions.reduce(
      (bounds, latlng) => bounds.extend(latlng),
      new kakao.maps.LatLngBounds()
    );

    toRaw(map.value).setBounds(bounds);
  }
};

const displayInfoWindow = () => {
  if (infowindow.value && infowindow.value.getMap()) {
    toRaw(map.value).setCenter(infowindow.value.getPosition());
    return;
  }

  var iwContent = '<div style="padding:5px;">Hello World!</div>',
    iwPosition = new kakao.maps.LatLng(33.450701, 126.570667),
    iwRemoveable = true;

  infowindow.value = new kakao.maps.InfoWindow({
    map: toRaw(map.value),
    position: iwPosition,
    content: iwContent,
    removable: iwRemoveable,
  });

  toRaw(map.value).setCenter(iwPosition);
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
    <SelectAttraction />
    <div id="map">지도 영역</div>
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
