<script setup>
import { ref } from "vue";

const content = ref([
  {
    img: "/src/assets/img-placeholder.png",
    title: "제목",
    subtitle: "소제목",
    advanced: false,
    duration: "8",
  },
  {
    img: "/src/assets/img-placeholder.png",
    title: "제목",
    subtitle: "소제목",
    advanced: true,
    duration: "10",
  },
  {
    img: "/src/assets/img-placeholder.png",
    title: "제목",
    subtitle: "소제목",
    advanced: false,
    duration: "9",
  },
  {
    img: "/src/assets/img-placeholder.png",
    title: "제목",
    subtitle: "소제목",
    advanced: true,
    duration: "12",
  },
  {
    img: "/src/assets/img-placeholder.png",
    title: "제목",
    subtitle: "소제목",
    advanced: false,
    duration: "11",
  },
  {
    img: "/src/assets/img-placeholder.png",
    title: "제목",
    subtitle: "소제목",
    advanced: true,
    duration: "10",
  },
  {
    img: "/src/assets/img-placeholder.png",
    title: "제목",
    subtitle: "소제목",
    advanced: true,
    duration: "11",
  },
  {
    img: "/src/assets/img-placeholder.png",
    title: "제목",
    subtitle: "소제목",
    advanced: false,
    duration: "9",
  },
]);
</script>

<template>
  <v-card>
    <v-data-iterator :items="content" :items-per-page="12" :search="search">
      <template v-slot:default="{ items }">
        <v-container class="pa-2" fluid>
          <v-row dense>
            <v-col v-for="item in items" :key="item.title" cols="auto" md="4">
              <v-card class="pb-3" border flat>
                <v-img
                  :src="item.raw.img"
                  cover="true"
                  width="500"
                  height="190"
                ></v-img>

                <v-list-item :subtitle="item.raw.subtitle" class="mb-2">
                  <template v-slot:title>
                    <strong class="text-h6 mb-2">{{ item.raw.title }}</strong>
                  </template>
                </v-list-item>

                <div class="d-flex justify-space-between px-4">
                  <div
                    class="d-flex align-center text-caption text-medium-emphasis me-1"
                  >
                    <v-icon icon="mdi-thumb-up-outline" start></v-icon>
                    <div class="text-truncate">{{ item.raw.duration }}</div>
                  </div>

                  <v-btn
                    class="text-none"
                    size="small"
                    text="더 알아보기"
                    border
                    flat
                  >
                  </v-btn>
                </div>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </template>

      <template v-slot:footer="{ page, pageCount, prevPage, nextPage }">
        <div class="d-flex align-center justify-center pa-4">
          <v-btn
            :disabled="page === 1"
            density="comfortable"
            icon="mdi-arrow-left"
            variant="tonal"
            rounded
            @click="prevPage"
          ></v-btn>

          <div class="mx-2 text-caption">
            Page {{ page }} of {{ pageCount }}
          </div>

          <v-btn
            :disabled="page >= pageCount"
            density="comfortable"
            icon="mdi-arrow-right"
            variant="tonal"
            rounded
            @click="nextPage"
          ></v-btn>
        </div>
      </template>
    </v-data-iterator>
  </v-card>
</template>
