<script setup>
const props = defineProps(['locateInfo']);
console.log(props.locateInfo);
</script>

<template>
  <v-card class="mx-auto d-flex" max-width="344">
    <v-card-item style="flex: 0.7">
      <v-avatar size="70">
        <v-img :src="props.locateInfo.firstImage" cover></v-img>
      </v-avatar>
    </v-card-item>
    <v-card-item class="pl-0" style="flex: 2">
      <v-card-title> {{ props.locateInfo.title }} </v-card-title>
      <v-card-subtitle> {{ props.locateInfo.addr1 }} </v-card-subtitle>
      <v-col cols="auto" class="pl-0">
        <v-btn size="small">삭제</v-btn>
      </v-col>
    </v-card-item>
  </v-card>
</template>

<style scoped></style>
