<template>
  <v-container class="container">
    <v-card-title>ĐĂNG NHẬP</v-card-title>
    <v-text-field label="Username" variant="outlined" density="compact"></v-text-field>
    <v-text-field label="Password" variant="outlined" type="password" density="compact"></v-text-field>

    <div style="" class="router-login">
    <router-link to="/register" class="text">Bạn chưa có tài khoản?</router-link>
    </div>

    <v-btn class="btn">Đăng nhập</v-btn>

    <div class="text-login-google">
    <a class="text"> - hoặc đăng nhập với -</a>
    </div>
    <v-btn >
      <img src="https://i.pinimg.com/736x/74/65/f3/7465f30319191e2729668875e7a557f2.jpg" class="icon-google"/>
      Google
    </v-btn>

  </v-container>
</template>

<script setup lang="ts">
</script>
