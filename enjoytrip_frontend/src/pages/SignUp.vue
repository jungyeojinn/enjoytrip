<template>
  <div class="h-100 d-flex justify-md-center align-center">
    <v-card class="mx-auto" elevation="8" style="min-width: 500px" rounded="lg">
      <v-img class="mx-auto my-6" max-width="228" src="/src/assets/logo.png"></v-img>
      <v-form ref="form" v-model="isValid" class="d-flex flex-column ga-md-3 pa-4 pt-6">
        <v-text-field v-model="userId" color="deep-purple" counter="6" :rules="[rules.short(6)]" label="아이디"
          style="min-height: 60px" type="text" variant="outlined"></v-text-field>
        <v-text-field v-model="nickName" color="deep-purple" counter="6" :rules="[rules.short(6)]" label="닉네임"
          style="min-height: 60px" type="text" variant="outlined"></v-text-field>
        <v-text-field v-model="password" color="deep-purple" label="비밀번호" style="min-height: 60px" type="password"
          variant="outlined"></v-text-field>
        <v-text-field v-model.lazy="passwordEqu" :rules="[rules.equvalent]" color="deep-purple" label="비밀번호 확인"
          style="min-height: 60px" type="password" variant="outlined"></v-text-field>
        <v-text-field v-model="email" :rules="[rules.email]" color="deep-purple" label="이메일" type="email"
          variant="outlined"></v-text-field>
      </v-form>
      <v-divider></v-divider>
      <v-card-actions>
        <v-btn variant="text" @click="form.reset()"> 초기화 </v-btn>
        <v-spacer></v-spacer>
        <v-btn :disabled="!isValid" :loading="isLoading" color="deep-purple-accent-4" @click="singup">
          회원가입
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { login, signUp } from '@/api/user';
import { useUserStore } from '@/store/userStore';
import { useRouter } from 'vue-router';

const router = useRouter();
const userStore = useUserStore();

const userId = ref(undefined)
const nickName = ref(undefined);;
const email = ref(undefined);
const isValid = ref(false);
const isLoading = ref(false);
const password = ref(undefined);
const passwordEqu = ref(undefined);
const rules = ref({
  email: v => !!(v || '').match(/@/) || '올바른 이메일 형식이 아니에요. 😥',
  short: len => v => (v || '').length <= len || `너무 길어요. ${len}이하 길이로 설정해주세요. 😥`,
  length: len => v =>
    (v || '').length >= len ||
    `Invalid character length, required ${len}`,
  password: v =>
    !!(v || '').match(
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).+$/
    ) ||
    'Password must contain an upper case letter, a numeric character, and a special character',
  equvalent: v => password.value === v || '비밀번호가 다릅니다. 😥',
  required: v => !!v || 'This field is required'
});

const singup = async () => {
  const [emailId, emailDomain] = email.value.split('@');

  const userData = {
    userId: userId.value,
    nickName: nickName.value,
    password: password.value,
    emailId, emailDomain
  }

  let reuslt = await signUp(userData);

  if (reuslt) {
    const data = await login({ userId: userData.userId, password: userData.password });

    userStore.setUserInfo(data.userId, data.nickname);
    userStore.setCookie(data.accessToken, data.refreshToken);

    router.push({ name: 'home' });
  } else {
    alert('아이디가 중복되었습니다.');
  }
}
</script>
