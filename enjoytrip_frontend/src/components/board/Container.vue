<template>
  <h1>게시판</h1>

  <div style="max-width: 1280px; margin: 0 auto">
    <v-container>
      <v-btn @click="goWritePage">글쓰기</v-btn>
      <v-data-table-server v-model:items-per-page="itemsPerPage" :headers="headers" :items="serverItems"
        :items-length="totalItems" :loading="loading" item-value="name" @update:options="loadItems">
        <template v-slot:item.title="{ item }">
          <a @click="goToDetail(item.id)" style="cursor: pointer">
            {{ item.title }}
          </a>
        </template>
      </v-data-table-server>
    </v-container>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

import { getBoardList } from "@/api/board";
import { useUserStore } from "@/store/userStore";

const userStore = useUserStore();
const router = useRouter();

const content = ref([]);
const itemsPerPage = ref(10);
const headers = ref([
  {
    title: "게시글 번호",
    align: "start",
    key: "id",
  },
  { title: "제목", key: "title", align: "end" },
  { title: "작성일", key: "modified_at", align: "end" },
]);

const serverItems = ref([]);
const loading = ref(true);
const totalItems = ref(0);

onMounted(async () => {
  const pageSelect = document.querySelector(".v-data-table-footer__items-per-page");
  pageSelect.style.display = "none";

  const data = await getBoardList(0, itemsPerPage.value);
});

const loadItems = async ({ page, itemsPerPage, sortBy }) => {
  loading.value = true;

  const data = await getBoardList(page - 1, itemsPerPage);

  serverItems.value = data.content;
  totalItems.value = data.totalElements;
  loading.value = false;
};

const goWritePage = () => {
  if (!userStore.isLogin()) {
    alert("로그인 먼저 해주세요");
    router.push({ name: "login" });
  } else {
    router.push({ name: "boardWrite" });
  }
};

const goToDetail = (id) => {
  router.push({ path: `/board/${id}` });
};
</script>

<style scoped>
h1 {
  margin: 20px 0;
  text-align: center;
}

.v-data-table-footer__items-per-page {
  display: none;
}
</style>
