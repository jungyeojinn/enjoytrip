<script setup>
import { getDetailPlan, getPlanOrder } from "@/api/attrplan";
import { getOneContent } from "@/api/attraction";
import { createMarker, createBounds, removeAllMarker } from "@/utils/kakao";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const title = ref();
const planArr = ref([]);
const startDate = ref();
const endDate = ref();
const imgSrc = ref();
const typeOption = ref({
  12: "관광지",
  14: "문화시설",
  15: "축제공연행사",
  25: "여행코스",
  28: "레포츠",
  32: "숙박",
  38: "쇼핑",
  39: "음식점",
});

const map = ref();
const markers = ref();

const drawLine = (data) => {
  let linePath = data.map((elem) => {
    console.log(elem.latitude, elem.longitude);
    return new kakao.maps.LatLng(elem.latitude, elem.longitude);
  });

  let polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: "#FFAE00", // 선의 색깔입니다
    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "solid", // 선의 스타일입니다
  });

  polyline.setMap(map.value);
};

const setMarker = (data) => {
  let markerArr = [];

  createBounds(map.value, data);

  data.forEach((elem, index) => {
    createMarker(map.value, elem, markerArr);
    let infowindow = new kakao.maps.InfoWindow({
      content: elem.title,
    });

    infowindow.open(map.value, markerArr[index]);
  });
};

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 5,
  };

  map.value = new kakao.maps.Map(container, options);
};

onMounted(async () => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.onload = () => kakao.maps.load(initMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0&libraries=services";
    document.head.appendChild(script);
  }

  const data = await getDetailPlan(route.params.id);
  const planDataArr = await getPlanOrder(route.params.id);

  for (let plan of planDataArr) {
    await getOneContent(
      plan.attraction_info_id,
      ({ data }) => planArr.value.push(data),
      () => {}
    );
  }

  setMarker(planArr.value);
  drawLine(planArr.value);
  title.value = data.title;
  startDate.value = data.start_date;
  endDate.value = data.end_date;
  imgSrc.value = `${import.meta.env.VITE_BACKEND_BASE_URL}/img/board/${
    data.img
  }`;

  console.log(planArr.value);
});
</script>

<template>
  <v-container style="max-width: 1280px">
    <v-card width="100%" class="pa-md-4">
      <v-card-title class="text-h6 text-md-h5 text-lg-h4">{{
        title
      }}</v-card-title>
      <v-card-subtitle>
        <p>
          <!-- {{ startDate.split(" ")[0] }} ~ {{ endDate.split(" ")[0] }}
          {{ planArr.length }}개 여행지 -->
        </p>
      </v-card-subtitle>

      <v-img class="my-10" :width="300" cover :src="imgSrc"></v-img>

      <div id="map" style="height: 400px"></div>
      <v-timeline align="start">
        <v-timeline-item
          v-for="plan of planArr"
          :key="plan.contentId"
          size="large"
        >
          <template v-slot:icon>
            <v-avatar :image="plan.firstImage" size="60"></v-avatar>
          </template>
          <template v-slot:opposite>
            <span>{{ typeOption[plan.contentTypeId] }}</span>
          </template>
          <v-card class="elevation-2">
            <v-card-title class="text-h5"> {{ plan.title }} </v-card-title>
            <v-card-text>{{ plan.addr1 }}</v-card-text>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </v-card>
  </v-container>
</template>

<style scoped></style>
