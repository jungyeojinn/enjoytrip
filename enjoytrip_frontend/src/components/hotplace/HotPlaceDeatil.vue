<script setup>
import { useRoute } from "vue-router";
import { ref, onMounted } from "vue";
import { getHotplaceDetail } from "@/api/hotPlace";

const route = useRoute();
const content = ref({});
const map = ref();

const initMap = (lat, lng) => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(lat, lng),
    level: 5,
  };

  map.value = new kakao.maps.Map(container, options);

  new kakao.maps.Marker({
    map: map.value,
    position: new kakao.maps.LatLng(lat, lng),
  });
};

onMounted(async () => {
  const data = await getHotplaceDetail(route.params.id);
  if (data.img === null) {
    data.img = "/src/assets/img-placeholder.png";
  } else {
    data.img = `${import.meta.env.VITE_BACKEND_BASE_URL}/img/hotplace/${data.img}`;
  }
  content.value = data;

  if (window.kakao && window.kakao.maps) {
    initMap(content.value.latitude, content.value.longitude);
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
  <v-container style="max-width: 1280px">
    <v-card width="100%" class="pa-md-4">
      <v-card-title class="text-h6 text-md-h5 text-lg-h4">{{
        content.title
      }}</v-card-title>
      <v-card-text>
        작성자 > <strong>{{ content.userNickname }} </strong>
      </v-card-text>
      <v-card-title>
        <strong>{{ content.placeName }} </strong>
      </v-card-title>
      <v-img :width="300" cover :src="content.img"></v-img>
      <v-card-text>
        <p>{{ content.description }}</p>
      </v-card-text>
      <div id="map" style="height: 400px"></div>
    </v-card>
  </v-container>
</template>

<style scoped></style>
