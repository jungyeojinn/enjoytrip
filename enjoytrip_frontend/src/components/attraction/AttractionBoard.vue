<script setup>
import { ref } from "vue";
const desserts = ref([
  {
    name: "제목1",
    description: "여행계획",
    color: "#6EC1E4",
    calories: 159,
    fat: 6,
    carbs: 24,
    protein: 4,
    sodium: 87,
    calcium: "14%",
    iron: "1%",
  },
  {
    name: "제목2",
    description: "여행계획2",
    color: "#F4A261",
    calories: 237,
    fat: 9,
    carbs: 37,
    protein: 4.3,
    sodium: 129,
    calcium: "8%",
    iron: "1%",
  },
]);
</script>
<template>
  <v-data-iterator class="container" :items="desserts" item-value="name">
    <template v-slot:default="{ items, isExpanded, toggleExpand }">
      <v-row>
        <v-col
          v-for="item in items"
          :key="item.raw.name"
          cols="12"
          md="6"
          sm="12"
        >
          <v-card>
            <v-card-title class="d-flex align-center">
              <h4>{{ item.raw.name }}</h4>
            </v-card-title>

            <v-card-text>
              {{ item.raw.description }}
            </v-card-text>

            <div class="px-4 d-flex justify-sm-space-between">
              <v-switch
                :label="`계획 ${isExpanded(item) ? '숨기기' : '보이기'}`"
                :model-value="isExpanded(item)"
                density="compact"
                inset
                @click="() => toggleExpand(item)"
              ></v-switch>
              <v-btn
                class="text-none mt-5px"
                size="small"
                text="지도와 함께 보기"
                border
                flat
              >
              </v-btn>
            </div>

            <v-divider></v-divider>

            <v-expand-transition>
              <div v-if="isExpanded(item)">
                <v-list :lines="false" density="compact">
                  <v-list-item
                    :title="`🔥 Calories: ${item.raw.calories}`"
                    active
                  ></v-list-item>
                  <v-list-item :title="`🍔 Fat: ${item.raw.fat}`"></v-list-item>
                  <v-list-item
                    :title="`🍞 Carbs: ${item.raw.carbs}`"
                  ></v-list-item>
                  <v-list-item
                    :title="`🍗 Protein: ${item.raw.protein}`"
                  ></v-list-item>
                  <v-list-item
                    :title="`🧂 Sodium: ${item.raw.sodium}`"
                  ></v-list-item>
                  <v-list-item
                    :title="`🦴 Calcium: ${item.raw.calcium}`"
                  ></v-list-item>
                  <v-list-item
                    :title="`🧲 Iron: ${item.raw.iron}`"
                  ></v-list-item>
                </v-list>
              </div>
            </v-expand-transition>
          </v-card>
        </v-col>
      </v-row>
    </template>
  </v-data-iterator>
</template>

<style scoped>
.container {
  max-width: 1280px;
  margin: 0 auto;
}
</style>
