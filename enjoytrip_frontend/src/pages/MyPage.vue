<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

import { getUserInfo, updateUserInfo } from "@/api/user";
import { useUserStore } from "@/store/userStore";

const userStore = useUserStore();
const route = useRoute();

const nickName = ref();
const email = ref();
const avatarSrc = ref();

onMounted(async () => {
  let userData = await getUserInfo(route.params.id);

  if (userData === null) {
    console.log("error");
    return;
  }

  nickName.value = userStore.nickname;
});

const submitData = async () => {
  const formData = new FormData();

  formData.append("emailId", email.value.split("@")[0]);
  formData.append("emailDomain", email.value.split("@")[1]);
  formData.append("nickName", nickName.value);
  formData.append("profileImg", avatarSrc.value);

  let result = updateUserInfo(route.params.id, formData);

  if (result) {
    alert("회원정보 수정 완료");
  } else {
    alert("회원정보 수정 오류");
  }
};

// 파일이 업로드되면 호출되는 함수
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

console.log(route.params.id);
</script>

<template>
  <div class="h-100 d-flex justify-md-center align-center">
    <v-card style="width: 344px">
      <v-card-item class="position-relative mx-auto" style="width: 150px; box-sizing: content-box">
        <v-avatar color="grey" size="150">
          <v-img :src="avatarSrc" cover></v-img>
        </v-avatar>
        <v-file-input accept="image/png, image/jpeg, image/bmp" prepend-icon="mdi-camera" hide-input
          class="position-absolute" style="bottom: 0; right: 0" @change="onFileChange"></v-file-input>
      </v-card-item>
      <v-card-text>
        <v-text-field v-model="nickName" label="닉네임" required></v-text-field>
        <v-text-field v-model="email" label="이메일" required></v-text-field>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" variant="text"> 정보 수정 </v-btn>
        <v-btn color="red" variant="text"> 회원 탈퇴 </v-btn>
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
