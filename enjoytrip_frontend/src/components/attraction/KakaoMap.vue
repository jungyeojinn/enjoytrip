<script setup>
import { ref, onMounted } from "vue";

const sido = ref([
  { area: "서울", code: "FL" },
  { area: "경기", code: "GA" },
  { area: "강원", code: "NE" },
  { area: "제주", code: "CA" },
  { area: "부산", code: "NY" },
]);

const selectSido = ref({ area: "", code: "" });
const selectGungu = ref({ area: "", code: "" });
const selectType = ref({ area: "", code: "" });

const emit = defineEmits([]);

const changeSido = (sido) => {
  console.log(`시도 요청 ${sido}`);
};

const changeGungu = (gungu) => {
  console.log(`군구 요청 ${gungu}`);
};

const chageType = (typeNumber) => {
  console.log(`여행지 타입 요청 ${typeNumber}`);
};

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
  <div class="w-75">
    <div id="select-area" class="d-flex">
      <h3 class="d-flex align-sm-center">여행지 선택</h3>
      <v-select
        v-model="selectSido"
        hide-details="true"
        max-width="150"
        :items="sido"
        label="시도"
        @update:model-value="changeSido"
        item-title="area"
        itme-value="code"
        variant="solo"
        return-object
      ></v-select>
      <v-select
        v-model="selectGungu"
        hide-details="true"
        max-width="150"
        :items="sido"
        label="군구"
        @update:modelValue="changeGungu"
        item-title="area"
        itme-value="code"
        variant="solo"
        return-object
      ></v-select>
      <v-select
        v-model="selectType"
        hide-details="true"
        max-width="150"
        :items="sido"
        label="여행지 유형"
        @update:modelValue="chageType"
        item-title="area"
        itme-value="code"
        variant="solo"
        return-object
      ></v-select>
    </div>
    <div id="map">지도 영역</div>
  </div>
</template>

<style scoped>
#map {
  height: 90%;
}

#select-area {
  width: 100%;
  height: 10%;
  gap: 50px;
}
</style>
