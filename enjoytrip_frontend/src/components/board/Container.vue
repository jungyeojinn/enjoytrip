<script setup>
import { ref } from "vue";

const content = ref([
  {
    name: "Frozen Yogurt",
    hit: 159,
  },
  {
    name: "Ice cream sandwich",
    hit: 237,
  },
  {
    name: "Eclair",
    hit: 262,
  },
  {
    name: "Cupcake",
    hit: 305,
  },
  {
    name: "Gingerbread",
    hit: 356,
  },
  {
    name: "Jelly bean",
    hit: 375,
  },
  {
    name: "Lollipop",
    hit: 392,
  },
  {
    name: "Honeycomb",
    hit: 408,
  },
  {
    name: "Donut",
    hit: 452,
  },
  {
    name: "KitKat",
    hit: 518,
  },
]);
</script>

<template>
  <v-table id="table">
    <thead>
      <tr>
        <th class="text-left">작성자</th>
        <th class="text-left">조회수</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in content" :key="item.name">
        <td>{{ item.name }}</td>
        <td>{{ item.hit }}</td>
      </tr>
    </tbody>
  </v-table>
  <div class="text-center">
    <v-container>
      <v-row justify="center">
        <v-col cols="8">
          <v-container class="max-width">
            <v-pagination v-model="page" :length="15"></v-pagination>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped>
#table {
  max-width: 1280px;
  margin: 0 auto;
}
</style>
