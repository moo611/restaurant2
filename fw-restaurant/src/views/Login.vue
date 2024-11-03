<template>
  <div class="main">
    <div>
      <h1>巴别塔早餐</h1>
    </div>
    <el-form class="form" :model="form" label-width="auto" style="width: 600px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password"/>
      </el-form-item>
    </el-form>
    <div>
      <el-button @click="login" class="button">登录</el-button>
      <el-button @click="goSign" class="button">注册</el-button>
    </div>


  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { ref } from "vue";
import { useRouter } from "vue-router";
import { setToken } from "../utils/auth";
import axios from "../axios2";
import { ElMessage } from 'element-plus'
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
        router.replace('/dashboard/food');   // 登录成功后重定向到受保护页面

      })


  } else {
    ElMessage.error('请输入完整')
  }
}

function goSign() {
  router.push("/signup")
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
}

.form {
  margin: 40px auto;
}
</style>