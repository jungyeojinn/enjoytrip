<script setup>
import BoardComment from "./BoardComment.vue";
</script>

<template>
  <v-container>
    <h1>제목</h1>
    <v-container>
      <v-row align="center">
        <v-avatar size="36px">
          <v-img
            alt="Avatar"
            src="https://avatars0.githubusercontent.com/u/9064066?v=4&s=460"
          ></v-img>
        </v-avatar>

        <v-col class="text-no-wrap text-left">
          <strong>사용자</strong>
        </v-col>
      </v-row>
    </v-container>
    <v-img
      :width="300"
      aspect-ratio="16/9"
      cover
      src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
    ></v-img>
    <div>
      <p>글 내용</p>
    </div>
  </v-container>
  <BoardComment />
</template>

<style scoped></style>
