<script setup>
import BoardComment from "./BoardComment.vue";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { getBoard, deleteBoard } from "@/api/board";
import { useUserStore } from "@/store/userStore";

const route = useRoute();
const router = useRouter();
const content = ref({});
const userStore = useUserStore();

const onClickDelBtn = async () => {
  let result = window.confirm("정말 삭제하시겠습니까?");
  if (result) {
    await deleteBoard(route.params.id);
    alert("삭제 되었습니다.");
    router.push({ name: "board" });
  }
};

const onClickEditBtn = async () => {
  router.push({ path: `/board/edit/${route.params.id}` });
};

onMounted(async () => {
  const data = await getBoard(route.params.id);
  if (data.img !== null) {
    data.img = `${import.meta.env.VITE_BACKEND_BASE_URL}/img/board/${data.img}`;
  }
  content.value = data;
  console.log(content.value);
});
</script>

<template>
  <v-container style="max-width: 1280px">
    <v-card width="100%" class="pa-md-4">
      <v-card-title class="text-h6 text-md-h5 text-lg-h4">{{
        content.title
      }}</v-card-title>
      <v-card-text>
        작성자 > <strong>{{ content.nickname }} </strong>
      </v-card-text>

      <v-img v-if="content.img !== null" :width="300" cover :src="content.img"></v-img>
      <v-card-text>
        <p>{{ content.content }}</p>
      </v-card-text>

      <v-btn v-if="content.userId === userStore.num" class="mt-2" @click="onClickDelBtn">삭제하기</v-btn>
      <v-btn v-if="content.userId === userStore.num" class="mt-2" @click="onClickEditBtn">수정하기</v-btn>
    </v-card>
  </v-container>
  <!-- <BoardComment /> -->
</template>

<style scoped></style>
