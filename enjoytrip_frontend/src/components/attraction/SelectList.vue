<script setup>
import Card from "./Card.vue";
import { ref } from "vue";
import { useRouter } from "vue-router";

import { useUserStore } from "@/store/userStore";
import { addPlan, addPlanItem } from "@/api/attrplan";

const userStore = useUserStore();
const router = useRouter();

const imageSrc = ref("/src/assets/img-placeholder.png");
const props = defineProps(["selectPlace"]);
const emit = defineEmits(["deletePlace"]);
const title = ref("");
const startDate = ref("");
const endDate = ref("");

const delPlace = (index) => {
  emit("deletePlace", index);
};

const dialog = ref(false);

const onFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      imageSrc.value = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

const onSubmit = async () => {
  const formData = new FormData();

  const base64Data = imageSrc.value.split(",")[1];
  const byteCharacters = atob(base64Data);
  const byteNumbers = new Array(byteCharacters.length);
  for (let i = 0; i < byteCharacters.length; i++) {
    byteNumbers[i] = byteCharacters.charCodeAt(i);
  }
  const byteArray = new Uint8Array(byteNumbers);
  const imageBlob = new Blob([byteArray], { type: "image/png" }); // 이미지 유형에 맞게 MIME 타입 지정

  formData.append("img", imageBlob, "avatar.png");

  const planId = await addPlan(
    title.value,
    startDate.value,
    endDate.value,
    userStore.num,
    formData
  );

  const items = props.selectPlace.map((elem, idx) => {
    const order = idx;
    const plans_id = elem.planId;
    const attraction_info_id = elem.contentId;
    return {
      order,
      plans_id,
      attraction_info_id,
    };
  });

  await addPlanItem(planId, items);

  dialog.value = true;
};
</script>

<template>
  <div class="container w-25 overflow-y-auto">
    <div class="text-center pa-4">
      <v-btn @click="dialog = true"> 여행계획 만들기 </v-btn>

      <v-dialog v-model="dialog" width="auto">
        <v-card max-width="400" prepend-icon="mdi-airplane" text="여행 계획 제목을 입력해주세요">
          <div style="width: 150px; margin: 0 auto">
            <v-avatar size="150">
              <v-img :src="imageSrc" cover></v-img>
            </v-avatar>
            <v-file-input accept="image/png, image/jpeg, image/bmp" prepend-icon="mdi-camera" hide-input
              style="bottom: 0; right: 0" @change="onFileChange"></v-file-input>
          </div>
          <v-text-field v-model="title" class="mx-auto" width="200" label="제목" variant="outlined"></v-text-field>
          <template v-slot:actions>
            <v-btn class="ms-auto" text="Ok" @click="onSubmit"></v-btn>
          </template>
        </v-card>
      </v-dialog>
    </div>

    <h2>선택한 여행지</h2>
    <div style="display: flex; align-items: center">
      <label style="padding-right: 5px">시작</label>
      <input v-model="startDate" type="date" style="border: solid 1px; margin-right: 10px" />

      <label style="padding-right: 5px">종료</label>
      <input v-model="endDate" type="date" style="border: solid 1px" />
    </div>
    <div class="card-con">
      <Card class="mx-auto mb-2" v-for="locateInfo of props.selectPlace" :locateInfo="locateInfo"
        @del-place="delPlace" />
    </div>
  </div>
</template>

<style scoped>
h2 {
  max-width: 344px;
  margin: 30px auto;
}

.container {
  max-width: 344px;
  margin: 30px auto;
}

.card-con {
  height: 600px;
  overflow-y: scroll;
}
</style>
