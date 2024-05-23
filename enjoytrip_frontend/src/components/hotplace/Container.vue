<script setup>
import { ref, onMounted } from "vue";
import { useUserStore } from "@/store/userStore";
import { useRouter } from "vue-router";
import { getHotplace } from "@/api/hotPlace";

const userStore = useUserStore();
const router = useRouter();

const content = ref([]);
const addHotPlace = () => {
  if (!userStore.isLogin()) {
    alert("로그인 먼저 해주세요");
    router.push({ path: "/login" });
    return;
  }

  router.push({ path: "/hotplace/write" });
};

const goDetail = (id) => {
  console.log(id);
  router.push({ path: `/hotplace/${id}` });
};

onMounted(async () => {
  let data = await getHotplace();
  content.value = data.map((elem) => {
    let img =
      elem.img === null
        ? "/src/assets/img-placeholder.png"
        : `${import.meta.env.VITE_BACKEND_BASE_URL}/img/hotplace/${elem.img}`;

    return { ...elem, img };
  });
});
</script>

<template>
  <div style="max-width: 1280px; margin: 0 auto">
    <v-btn height="50" min-width="164" class="my-5" @click="addHotPlace">
      핫플레이스 등록하기
    </v-btn>
    <v-card>
      <v-data-iterator :items="content" :items-per-page="9">
        <template v-slot:default="{ items }">
          <v-container class="pa-2" fluid>
            <v-row dense>
              <v-col v-for="item in items" :key="item.title" cols="auto" md="4">
                <v-card class="pb-3" border flat>
                  <v-img :src="item.raw.img" cover width="500" height="190"></v-img>

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
                      @click="goDetail(item.raw.id)"
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

            <div class="mx-2 text-caption">Page {{ page }} of {{ pageCount }}</div>

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
  </div>
</template>
