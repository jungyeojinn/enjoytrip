<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { getHotplaceDetail, deleteHotPlace } from "@/api/hotPlace";
import { useUserStore } from "@/store/userStore";

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
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

const onClickDelBtn = async () => {
  let result = window.confirm("정말 삭제하시겠습니까?");
  if (result) {
    await deleteHotPlace(route.params.id);
    alert("삭제 되었습니다.");
  } else {
    alert("삭제 오류.");
  }
  router.push({ name: "hotplace" });
};

const onClickEditBtn = async () => {
  router.push({ path: `/hotplace/edit/${route.params.id}` });
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

      <v-btn v-if="content.userId === userStore.num" class="mt-2" @click="onClickDelBtn">삭제하기</v-btn>
      <v-btn v-if="content.userId === userStore.num" class="mt-2" @click="onClickEditBtn">수정하기</v-btn>
    </v-card>
  </v-container>
</template>

<style scoped></style>
