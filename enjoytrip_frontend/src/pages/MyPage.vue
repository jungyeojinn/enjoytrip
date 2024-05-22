<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

import { getUserInfo, updateUserInfo, deleteUser } from "@/api/user";
import { useUserStore } from "@/store/userStore";

const userStore = useUserStore();
const route = useRoute();
const router = useRouter();

const nickName = ref();
const email = ref();
const avatarSrc = ref();

onMounted(async () => {
  let userData = await getUserInfo(route.params.id);

  if (userData === null) {
    console.log("error");
    return;
  }

  nickName.value = userData.nickname;
  email.value = `${userData.emailId}@${userData.emailDomain}`;
  avatarSrc.value =
    userData.profileImg === null
      ? "/src/assets/default-avatar.png"
      : `${import.meta.env.VITE_BACKEND_BASE_URL}/img/user/${userData.profileImg}`;
});

const submitData = async () => {
  const formData = new FormData();

  const data = {
    userId: route.params.id,
    emailId: email.value.split("@")[0],
    emailDomain: email.value.split("@")[1],
    nickname: nickName.value,
  };

  const json = JSON.stringify(data);
  const blob = new Blob([json], { type: "application/json" });

  formData.append("user", blob);

  const base64Data = avatarSrc.value.split(",")[1];
  const byteCharacters = atob(base64Data);
  const byteNumbers = new Array(byteCharacters.length);
  for (let i = 0; i < byteCharacters.length; i++) {
    byteNumbers[i] = byteCharacters.charCodeAt(i);
  }
  const byteArray = new Uint8Array(byteNumbers);
  const imageBlob = new Blob([byteArray], { type: "image/png" }); // 이미지 유형에 맞게 MIME 타입 지정

  formData.append("img", imageBlob, "avatar.png");

  let result = await updateUserInfo(route.params.id, formData);

  if (result) {
    alert("회원정보 수정 완료");
    router.push({ name: "home" });
  } else {
    alert("회원정보 수정 오류");
  }
};

const onFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      avatarSrc.value = e.target.result;
    };
    reader.readAsDataURL(file);
    console.log(reader);
  }
};

const signout = async () => {
  let result = window.confirm("정말 탈퇴하시겠습니까?");
  if (result) {
    await deleteUser(route.params.id);
    userStore.clear();
    router.push({ name: "home" });
  } else {
    console.log("no");
  }
};
</script>

<template>
  <div class="h-100 d-flex justify-md-center align-center">
    <v-card style="width: 344px">
      <v-card-item
        class="position-relative mx-auto"
        style="width: 150px; box-sizing: content-box"
      >
        <v-avatar color="grey" size="150">
          <v-img :src="avatarSrc" cover></v-img>
        </v-avatar>
        <v-file-input
          accept="image/png, image/jpeg, image/bmp"
          prepend-icon="mdi-camera"
          hide-input
          class="position-absolute"
          style="bottom: 0; right: 0"
          @change="onFileChange"
        ></v-file-input>
      </v-card-item>
      <v-card-text>
        <v-text-field v-model="nickName" label="닉네임" required></v-text-field>
        <v-text-field v-model="email" label="이메일" required></v-text-field>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" variant="text" @click="submitData"> 정보 수정 </v-btn>
        <v-btn color="red" variant="text" @click="signout"> 회원 탈퇴 </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<style scoped>
.mdi-camera::before {
  z-index: 2;
  color: black;
}
</style>
