<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";

import { getBoard, updateBoard } from "@/api/board";
import { useUserStore } from "@/store/userStore";

const imageSrc = ref("/src/assets/img-placeholder.png");
const title = ref();
const description = ref();

const userStore = useUserStore();
const router = useRouter();
const route = useRoute();

const setLocation = (data) => {
  placeName.value = data.placeName;
  latitude.value = data.latitude;
  longitude.value = data.longitude;
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
    type: 0,
    title: title.value,
    content: description.value,
    user_id: userStore.num,
    id: route.params.id,
  };

  const json = JSON.stringify(data);
  const blob = new Blob([json], { type: "application/json" });

  formData.append("board", blob);

  const base64Data = imageSrc.value.split(",")[1];
  const byteCharacters = atob(base64Data);
  const byteNumbers = new Array(byteCharacters.length);
  for (let i = 0; i < byteCharacters.length; i++) {
    byteNumbers[i] = byteCharacters.charCodeAt(i);
  }
  const byteArray = new Uint8Array(byteNumbers);
  const imageBlob = new Blob([byteArray], { type: "image/png" }); // 이미지 유형에 맞게 MIME 타입 지정

  formData.append("img", imageBlob);

  let result = await updateBoard(formData);

  if (result) {
    alert("게시글 수정 완료");
    router.push({ name: "board" });
  } else {
    alert("게시글 수정 오류");
  }
};

onMounted(async () => {
  let data = await getBoard(route.params.id);
  if (data.img !== null) {
    imageSrc.value = `${import.meta.env.VITE_BACKEND_BASE_URL}/img/board/${data.img}`;
  }
  title.value = data.title;
  description.value = data.content;
  console.log(data);
});
</script>

<template>
  <div style="max-width: 1280px; margin: 0 auto">
    <v-container fluid>
      <v-col>
        <v-row cols="12" sm="6">
          <v-textarea v-model="title" label="제목" row-height="15" rows="1" variant="outlined" auto-grow></v-textarea>
        </v-row>
        <v-row cols="12" sm="6">
          <v-card class="ml-2 mb-2" max-width="344">
            <v-img height="200px" :src="imageSrc" cover></v-img>
            <v-card-title> 썸네일을 선택해 주세요 </v-card-title>
            <v-file-input accept="image/png, image/jpeg, image/bmp" prepend-icon="mdi-camera" hide-input
              style="bottom: 0; right: 0" @change="onFileChange"></v-file-input>
          </v-card>
        </v-row>
        <v-row cols="12" sm="6">
          <v-textarea v-model="description" label="내용" variant="outlined"></v-textarea>
        </v-row>
      </v-col>

      <v-btn height="40" min-width="80" @click="onSubmit"> 수정하기 </v-btn>
    </v-container>
  </div>
</template>

<style scoped></style>
