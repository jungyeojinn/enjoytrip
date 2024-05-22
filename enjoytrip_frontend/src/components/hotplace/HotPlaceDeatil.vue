<script setup>
import { useRoute } from "vue-router";
import { ref, onMounted } from "vue";
import { getHotplaceDetail } from "@/api/hotPlace";

const route = useRoute();
const content = ref({});

onMounted(async () => {
  const data = await getHotplaceDetail(route.params.id);
  if (data.img === null) {
    data.img = "/src/assets/img-placeholder.png";
  } else {
    data.img = `${import.meta.env.VITE_BACKEND_BASE_URL}/img/hotplace/${data.img}`;
  }
  content.value = data;
});
</script>

<template>
  <v-container style="max-width: 1280px">
    <h1>{{ content.title }}</h1>
    <v-container>
      <v-row align="center">
        <v-col class="text-no-wrap text-left">
          작성자 > <strong>{{ content.userNickname }}</strong>
        </v-col>
      </v-row>
    </v-container>
    <v-img :width="300" aspect-ratio="16/9" cover :src="content.img"></v-img>
    <div>
      <p>{{ content.description }}</p>
    </div>
  </v-container>
</template>

<style scoped></style>
