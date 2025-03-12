<template>
  <v-container style="text-align: center; width: 65%">
    <v-card-title>ĐĂNG KÝ</v-card-title>

    <v-text-field
      label="Username"
      variant="outlined"
      density="compact"
      v-model="username"
      :error-messages="errors.username"
    ></v-text-field>

    <v-text-field
      label="Password"
      variant="outlined"
      type="password"
      density="compact"
      v-model="password"
      :error-messages="errors.password"
    ></v-text-field>

    <v-text-field
      label="Số điện thoại"
      variant="outlined"
      density="compact"
      v-model="phoneNumber"
      :error-messages="errors.phoneNumber"
    ></v-text-field>

    <v-text-field
      label="Email"
      variant="outlined"
      density="compact"
      type="email"
      v-model="email"
      :error-messages="errors.email"
    ></v-text-field>

    <v-btn class="btn" @click="register">Đăng ký</v-btn>
  </v-container>
</template>


<script setup lang="ts">
import { ref } from "vue";
import { loginService } from "@/services/LoginService";
import router from "@/router";

const username = ref("");
const password = ref("");
const phoneNumber = ref("");
const email = ref("");
const errors = ref<Record<string, string>>({});

const register = async () => {
  errors.value = {};
  try {
    const response = await loginService.register(
      username.value,
      password.value,
      phoneNumber.value,
      email.value
    );
    if (response.status === 200) {
      router.push("/");
    }
  } catch (error: any) {
      errors.value = error.response.data || "Lỗi Đăng ký tài khoản";
  }
};
</script>

