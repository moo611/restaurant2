<template>
  <div class="main">
    <div>
      <span style="color: white;">巴别塔早餐</span>
    </div>
    <van-form class="form" :model="form" label-width="auto">
      <van-field v-model="form.username" name="username" label="手机号" placeholder="请填写手机号"
        :rules="[{ required: true, message: '请填写手机号' }]" />
      <van-field v-model="form.password" name="password" label="密码" placeholder="请输入密码"
        :rules="[{ required: true, message: '请填写密码' }]" />
      <div>
        <van-button @click="router.push('/signup')" class="button">注册</van-button>
        <van-button type="primary" @click="login" class="button">登录</van-button>
      </div>
    </van-form>


  </div>
</template>

<script setup>

import { reactive } from 'vue'
import { ref } from "vue";
import { useRouter } from "vue-router";
import { setToken } from "../utils/auth";
import axios from "../axios2";
import { showToast } from 'vant'
const form = reactive({
  username: "",
  password: "",
});

const router = useRouter();

function login() {
  if (form.username && form.password) {

    axios.post('user/login', {
      username: form.username,
      password: form.password,
    })
      .then(response => {
        console.log(response)
        setToken(response); // 缓存token

        router.push('/dashboard/home')
      })


  } else {
    //ElMessage.error('请输入完整')
    showToast('请输入完整')
  }
}


</script>
<style lang="css" scoped>
.main {
  flex-direction: column;
  /* 子元素垂直排列 */
  height: 100vh;
  /* 高度占满整个屏幕 */
  display: flex;
  justify-content: center;
  /* 水平居中 */
  align-items: center;
  /* 垂直居中 */
  background-image: url(../assets/bg.jpg);
}

.form {
  border-radius: 10px;
  padding: 20px;
  margin: 20px auto;
  background-color: white;
}

.button {
  margin: 40px 10px;
  width: 100px;
  /* margin-top: 40px; */
}
</style>