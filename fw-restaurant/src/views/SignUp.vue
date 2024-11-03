<template>
  <div class="main">
    <div>
      <h1>巴别塔早餐</h1>
    </div>
    <el-form class="form" :model="form" label-width="auto" style="width: 600px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" />
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password"/>
      </el-form-item>
      <el-form-item label="角色">
        <el-select v-model="form.role" placeholder="请选择">
          <el-option v-for="item in roleOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
    </el-form>
    <div>
      <el-button @click="signup" class="button">注册</el-button>
    </div>

  </div>
</template>

<script setup>
import { reactive } from 'vue'

import { useRouter } from "vue-router";
import axios from "../axios2";
import { ElMessage } from 'element-plus'
const form = reactive({
  username: "",
  password: "",
  nickname: "",
  role: '0',
  
});


const roleOptions = [{ value: '0', label: '管理员' }, { value: '1', label: '厨师' },{ value: '2', label: '服务员' }]
const router = useRouter();

function signup() {
  if (form.username && form.password) {


    if (!validateString(form.username)) {
      ElMessage.error("用户名只能由英文字母或数字组成")
      return
    }

    axios.post('user', form)
      .then(response => {
        console.log(response)
        ElMessage.success('注册成功')
        router.replace('/login');   // 使用 replace 替换当前页面，不保留登录页的历史记录

      })



  } else {
    ElMessage.error('请输入完整')
  }
}

function validateString(str) {
  // 正则表达式：^ 表示开始，$ 表示结束，a-zA-Z0-9 表示字母和数字
  const regex = /^[a-zA-Z0-9]+$/;
  return regex.test(str);
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
