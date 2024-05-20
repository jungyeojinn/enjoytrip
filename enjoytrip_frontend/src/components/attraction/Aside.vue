<script setup>
import { ref, computed } from 'vue';
import { getOneContent } from '@/api/attraction';

const reveal = ref(false);
const translateX = ref(344);
const dialog = ref(false);
const selectedLocate = ref(null);

const props = defineProps(['locate']);
const emit = defineEmits(['clickButton']);

const callAddPlace = () => {
  dialog.value = false;
  emit('clickButton', selectedLocate.value);
}

const toggleCard = () => {
  reveal.value = !reveal.value;
  translateX.value = translateX.value === 0 ? 344 : 0;
}

const move = computed(() => {
  return `transform: translateX(${translateX.value}px);`;
});

const setDialog = ({ data }) => {
  selectedLocate.value = data;
  dialog.value = true;
}

const openDialog = (contentId) => {
  // console.log(contentId);
  getOneContent(contentId, setDialog, () => { });
}
</script>

<template>
  <v-expand-transition class="position-absolute" :style="move" width="344"
    style="z-index: 2; right:0; top: 65px; height: 85%; transition: transform 0.5s ease-in-out;">
    <v-card class="position-relative overflow-y-auto">
      <v-list lines="two">
        <v-list-subheader inset>탐색된 여행지</v-list-subheader>

        <v-list-item v-for="locate in props.locate" :key="locate.contentId" :subtitle="locate.addr1"
          :title="locate.title" class="cursor-pointer" @click="openDialog(locate.contentId)">
          <template v-slot:prepend>
            <v-avatar>
              <v-img :src="locate.firstImage"></v-img>
            </v-avatar>
          </template>
        </v-list-item>
      </v-list>
    </v-card>
  </v-expand-transition>
  <v-card-actions class="position-absolute" :style="move"
    style="z-index: 2; top: 65px; right:334px; transition: transform 0.5s ease-in-out;">
    <v-btn @click="toggleCard" class="bg-white">
      <v-icon v-if="reveal" icon="mdi-chevron-right" size="36"></v-icon>
      <v-icon v-else icon="mdi-chevron-left" size="36"></v-icon>
    </v-btn>
  </v-card-actions>

  <!-- Dialog -->
  <v-dialog v-model="dialog" max-width="600px">
    <v-card>
      <v-card-title>{{ selectedLocate?.title }}</v-card-title>
      <v-col cols="auto">
        <v-btn size="small" @click="callAddPlace">여행지 선택</v-btn>
      </v-col>
      <v-card-text>
        <v-img :src="selectedLocate.firstImage"></v-img>
        <p>{{ selectedLocate?.overview }}</p>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="dialog = false">닫기</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style>
/* 필요한 스타일 추가 */
</style>
