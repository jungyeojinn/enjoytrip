<script setup>
import { ref } from 'vue';
const visible = ref(false);

</script>

<template>
  <div class="h-100 d-flex justify-md-center align-center">
    <v-card class="mx-auto pa-12 pb-8" elevation="8" min-width="448" rounded="lg">
      <v-img class="mx-auto my-6" max-width="228" src="/src/assets/logo.png"></v-img>

      <div class="text-subtitle-1 text-medium-emphasis">ID</div>

      <v-text-field density="compact" placeholder="Enter your ID" prepend-inner-icon="mdi-identifier"
        variant="outlined"></v-text-field>

      <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
        Password
      </div>

      <v-text-field :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'" :type="visible ? 'text' : 'password'"
        density="compact" placeholder="Enter your password" prepend-inner-icon="mdi-lock-outline" variant="outlined"
        @click:append-inner="visible = !visible"></v-text-field>

      <v-btn class="mb-8" color="blue" size="large" variant="tonal" block>
        Log In
      </v-btn>

      <v-card-text class="text-center">
        <a class="text-blue text-decoration-none" href="/signup" rel="noopener noreferrer">
          회원이 아니신가요? <v-icon icon="mdi-chevron-right"></v-icon>
        </a>
      </v-card-text>
    </v-card>
  </div>
</template>
