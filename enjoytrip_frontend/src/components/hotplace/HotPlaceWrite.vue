<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

import { addHotplace } from "@/api/hotPlace";
import { useUserStore } from "@/store/userStore";
import KakaoMap from "./KakaoMap.vue";

const imageSrc = ref("/src/assets/img-placeholder.png");
const title = ref();
const description = ref();
const placeName = ref("지역을 선택해 주세요");
const latitude = ref();
const longitude = ref();
const dialog = ref(false);

const userStore = useUserStore();
const router = useRouter();

const setLocation = (data) => {
  placeName.value = data.placeName;
  latitude.value = data.latitude;
  longitude.value = data.longitude;
};

const closeDialog = () => {
  dialog.value = false;
};

const onFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      imageSrc.value = e.target.result;
    };
    reader.readAsDataURL(file);
    console.log(reader);
  }
};

const onSubmit = async () => {
  const formData = new FormData();

  const data = {
    title: title.value,
    description: description.value,
    placeName: placeName.value,
    latitude: Number(latitude.value),
    longitude: Number(longitude.value),
    userId: userStore.id,
  };

  const json = JSON.stringify(data);
  const blob = new Blob([json], { type: "application/json" });

  formData.append("hotplace", blob);

  const base64Data = imageSrc.value.split(",")[1];
  const byteCharacters = atob(base64Data);
  const byteNumbers = new Array(byteCharacters.length);
  for (let i = 0; i < byteCharacters.length; i++) {
    byteNumbers[i] = byteCharacters.charCodeAt(i);
  }
  const byteArray = new Uint8Array(byteNumbers);
  const imageBlob = new Blob([byteArray], { type: "image/png" }); // 이미지 유형에 맞게 MIME 타입 지정

  console.log(imageBlob);
  formData.append("img", imageBlob);

  let result = await addHotplace(formData);

  if (result) {
    alert("핫플래이스 등록 완료");
    router.push({ name: "hotplace" });
  } else {
    alert("핫플래이스 등록 오류");
  }
};
</script>

<template>
  <div style="max-width: 1280px; margin: 0 auto">
    <v-container fluid>
      <v-col>
        <v-row cols="12" sm="6">
          <v-textarea
            v-model="title"
            label="제목"
            row-height="15"
            rows="1"
            variant="outlined"
            auto-grow
          ></v-textarea>
        </v-row>
        <v-row cols="12" sm="6">
          <v-btn height="40" min-width="80" @click="dialog = true"> 지역 탐색 </v-btn>
          <v-card class="ml-2 mb-2" max-width="344">
            <v-img height="200px" :src="imageSrc" cover></v-img>
            <v-card-title> {{ placeName }} </v-card-title>
            <v-file-input
              accept="image/png, image/jpeg, image/bmp"
              prepend-icon="mdi-camera"
              hide-input
              style="bottom: 0; right: 0"
              @change="onFileChange"
            ></v-file-input>
          </v-card>
        </v-row>
        <v-row cols="12" sm="6">
          <v-textarea v-model="description" label="내용" variant="outlined"></v-textarea>
        </v-row>
      </v-col>

      <v-btn height="40" min-width="80" @click="onSubmit"> 등록하기 </v-btn>
    </v-container>
  </div>

  <v-dialog width="800px" v-model="dialog" persistent>
    <KakaoMap @set-location="setLocation" @close-dialog="closeDialog" />
  </v-dialog>
</template>

<style scoped></style>
