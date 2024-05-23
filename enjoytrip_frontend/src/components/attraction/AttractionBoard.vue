<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { addPlans } from "@/api/attrplan";

const content = ref();
const router = useRouter();

const goDetailPlan = (id) => {
  router.push({ path: `/attraction/${id}` });
};

onMounted(async () => {
  let data = await addPlans();
  data = data.map((elem) => {
    let imgSrc = `${import.meta.env.VITE_BACKEND_BASE_URL}/img/board/${elem.img}`;
    return { ...elem, img: imgSrc };
  });
  content.value = data;
});
</script>
<template>
  <div style="max-width: 1280px; margin: 0 auto">
    <h1>여행계획</h1>
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
                    <div class="d-flex align-center text-caption text-medium-emphasis me-1">
                      {{ item.raw.start_date.split(" ")[0] }} ~
                      {{ item.raw.end_date.split(" ")[0] }}
                    </div>

                    <v-btn class="text-none" size="small" text="지도와 함께보기" border flat
                      @click="goDetailPlan(item.raw.id)">
                    </v-btn>
                  </div>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </template>

        <template v-slot:footer="{ page, pageCount, prevPage, nextPage }">
          <div class="d-flex align-center justify-center pa-4">
            <v-btn :disabled="page === 1" density="comfortable" icon="mdi-arrow-left" variant="tonal" rounded
              @click="prevPage"></v-btn>

            <div class="mx-2 text-caption">Page {{ page }} of {{ pageCount }}</div>

            <v-btn :disabled="page >= pageCount" density="comfortable" icon="mdi-arrow-right" variant="tonal" rounded
              @click="nextPage"></v-btn>
          </div>
        </template>
      </v-data-iterator>
    </v-card>
  </div>
</template>

<style scoped>
.container {
  max-width: 1280px;
  margin: 0 auto;
}

h1 {
  margin: 20px 0;
  text-align: center;
}
</style>
