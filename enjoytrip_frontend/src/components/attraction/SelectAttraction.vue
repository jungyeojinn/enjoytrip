<script setup>
import { ref, onMounted } from "vue";
import { getSido, getGungu, getContent } from "@/api/attraction";

const defaultSelect = { gugunName: '지역 선택', gugunCode: 0 };
const sidoOption = ref([]);
const gunguOption = ref([defaultSelect]);
const typeOption = ref([{
  contentTypeId: 12, contentTypeName: "관광지"
}, {
  contentTypeId: 14, contentTypeName: "문화시설"
}, {
  contentTypeId: 15, contentTypeName: "축제공연행사"
}, {
  contentTypeId: 25, contentTypeName: "여행코스"
}, {
  contentTypeId: 28, contentTypeName: "레포츠"
}, {
  contentTypeId: 32, contentTypeName: "숙박"
}, {
  contentTypeId: 38, contentTypeName: "쇼핑"
}, {
  contentTypeId: 39, contentTypeName: "음식점"
}]);

const emit = defineEmits(['setMarker']);

const selectSido = ref(null);
const selectGungu = ref(null);
const selectType = ref(null);

const setSido = ({ data }) => {
  let sido = data.map((elem) => {
    let { sidoName, sidoCode } = elem;
    return { sidoName, sidoCode };
  });

  sidoOption.value = sido;
}

const setGungu = ({ data }) => {
  let gungu = data.map((elem) => {
    let { gugunCode, gugunName } = elem;
    return { gugunCode, gugunName };
  });

  gunguOption.value = [{ gugunName: '지역 선택', gugunCode: 0 }].concat(gungu);
}

const changeSido = (sido) => {
  if (sido === 0) return;
  getGungu(sido, setGungu, () => { });
  selectGungu.value = null;
};

const changeGungu = (gungu) => {
  if (gungu === 0) return;

};

const chageType = (typeNumber) => {
  if (selectSido.value === null || selectGungu.value === null) return;
  if (selectSido.value === 0 || selectGungu.value === 0) return;

  getContent(selectSido.value, selectGungu.value, selectType.value, ({ data }) => {
    emit('setMarker', data);
  }, () => { });

};

onMounted(() => {
  getSido(setSido, () => { });
})
</script>

<template>
  <div id="select-area" class="d-flex align-sm-center position-absolute pl-3" style='z-index:2;'>
    <v-select v-model="selectSido" hide-details="true" max-width="150" :items="sidoOption" label="시도"
      @update:modelValue="changeSido" item-title="sidoName" item-value="sidoCode" variant="solo"></v-select>
    <v-select v-model="selectGungu" hide-details="true" max-width="150" :items="gunguOption" label="군구"
      @update:modelValue="changeGungu" item-title="gugunName" item-value="gugunCode" variant="solo"></v-select>

    <v-select v-model="selectType" hide-details="true" :items="typeOption" max-width="300" label="여행지 유형"
      @update:modelValue="chageType" item-title="contentTypeName" item-value="contentTypeId" multiple variant="solo">
      <template v-slot:selection="{ item, index }">
        <v-chip v-if="index < 2">
          <span>{{ item.title }}</span>
        </v-chip>
        <span v-if="index === 2" class="text-grey text-caption align-self-center">
          (+{{ selectType.length - 2 }} others)
        </span>
      </template>
    </v-select>
  </div>
</template>

<style scoped></style>