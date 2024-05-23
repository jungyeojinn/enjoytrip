<script setup>
import { ref, onMounted } from "vue";

const markers = ref([]);
const locate = ref([]);
const infowindow = ref(null);
const map = ref(null);
const search = ref("");

const placeName = ref();
const latitude = ref();
const longitude = ref();

const emit = defineEmits(["setLocation", "closeDialog"]);

const setLoc = () => {
  emit("setLocation", {
    placeName: placeName.value,
    latitude: latitude.value,
    longitude: longitude.value,
  });
  emit("closeDialog");
};

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 5,
  };

  map.value = new kakao.maps.Map(container, options);
  infowindow.value = new kakao.maps.InfoWindow({ zIndex: 1 });
};

const displayMarker = (place) => {
  let marker = new kakao.maps.Marker({
    map: map.value,
    position: new kakao.maps.LatLng(place.y, place.x),
  });

  // 마커에 클릭이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "click", function () {
    placeName.value = place.place_name;
    latitude.value = place.y;
    longitude.value = place.x;

    infowindow.value.setContent(
      '<div style="padding:5px;font-size:12px;">' + place.place_name + "</div>"
    );
    infowindow.value.open(map.value, marker);
  });
};

const placesSearchCB = (data, status, pagination) => {
  if (status === kakao.maps.services.Status.OK) {
    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    let bounds = new kakao.maps.LatLngBounds();

    for (var i = 0; i < data.length; i++) {
      displayMarker(data[i]);
      bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
    }

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.value.setBounds(bounds);
  }
};

const wordSearch = () => {
  let ps = new kakao.maps.services.Places();
  ps.keywordSearch(search.value, placesSearchCB);
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.onload = () => kakao.maps.load(initMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0&libraries=services";
    document.head.appendChild(script);
  }
});
</script>

<template>
  <v-card min-height="600">
    <div style="display: flex; align-items: center">
      <v-text-field
        v-model="search"
        label="Search"
        prepend-inner-icon="mdi-magnify"
        variant="outlined"
        hide-details
        single-line
        width="200px"
      ></v-text-field>
      <v-btn class="ms-auto" text="검색" @click="wordSearch"></v-btn>
    </div>
    <div style="height: 31em">
      <div class="w-100 h-100">
        <div id="map"></div>
      </div>
    </div>
    <v-btn class="ms-auto" text="선택한 지역 등록" @click="setLoc"></v-btn>
  </v-card>
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
