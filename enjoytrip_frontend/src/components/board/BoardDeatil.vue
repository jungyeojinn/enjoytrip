<script setup>
import BoardComment from "./BoardComment.vue";
import { onMounted } from "vue";
import { useRoute } from "vue-router";
import { getBoard } from "@/api/board";

const route = useRoute();
onMounted(async () => {
  const data = await getBoard(route.params.id);
  console.log(data);
});
</script>

<template>
  <v-container>
    <h1>제목</h1>
    <v-container>
      <v-row align="center">
        <v-col class="text-no-wrap text-left">
          <strong>사용자</strong>
        </v-col>
      </v-row>
    </v-container>
    <v-img :width="300" aspect-ratio="16/9" cover src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"></v-img>
    <div>
      <p>글 내용</p>
    </div>
  </v-container>
  <BoardComment />
</template>

<style scoped></style>
