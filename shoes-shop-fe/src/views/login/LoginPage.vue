<template>
  <v-container class="container">
    <v-card-title>ĐĂNG NHẬP</v-card-title>
    <v-text-field
      label="Username"
      variant="outlined"
      density="compact"
      v-model="username"
    ></v-text-field>
    <v-text-field
      label="Password"
      variant="outlined"
      type="password"
      density="compact"
      v-model="password"

    ></v-text-field>

    <div style="" class="router-login">
      <router-link to="/register" class="text">{{ t('login.is-has-account') }}</router-link>
    </div>

    <v-btn class="btn" @click="login">{{ t('login.btn-login') }}</v-btn>

    <div class="text-login-google">
      <a class="text"> {{ t('login.login-google') }}</a>
    </div>
    <v-alert v-if="errorMessage" type="error" class="mt-2">
      {{ errorMessage }}
    </v-alert>
    <!--    <GoogleLogin :callback="handleGoogleLogin">-->
    <!--      <v-btn>-->
    <!--        <img-->
    <!--          src="https://i.pinimg.com/736x/74/65/f3/7465f30319191e2729668875e7a557f2.jpg"-->
    <!--          class="icon-google"-->
    <!--        />-->
    <!--        Google-->
    <!--      </v-btn>-->
    <!--    </GoogleLogin>-->
  </v-container>
</template>

<script setup lang="ts">
import { loginService } from '@/services/LoginService'
import { useI18n } from 'vue-i18n'
import { ref } from 'vue'
import router from '@/router'

const { t } = useI18n()

const username = ref('')
const password = ref('')
const errorMessage = ref('')

const login = async () => {
  try {
    const response = await loginService.login(username.value, password.value)
    localStorage.setItem("userId",response.data.id)

    if (response.status === 200) {
       router.push('/home')
    }
  }catch (error :any) {
        errorMessage.value = error.response.data ;
  }

}
</script>
