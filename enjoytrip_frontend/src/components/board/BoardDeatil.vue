<script setup>
import BoardComment from "./BoardComment.vue";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getBoard } from "@/api/board";

const route = useRoute();
const content = ref({});
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
        작성자 > <strong>{{ content.user_id }} </strong>
      </v-card-text>

      <v-img :width="300" cover :src="content.img"></v-img>
      <v-card-text>
        <p>{{ content.content }}</p>
      </v-card-text>
    </v-card>
  </v-container>
  <!-- <BoardComment /> -->
</template>

<style scoped></style>
