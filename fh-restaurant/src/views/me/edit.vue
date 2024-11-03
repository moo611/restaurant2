<template>
  <div class="main">
    <div class="header">
      <img @click="router.go(-1)" src="../../assets/back.png"
        style="position: absolute; left: 10px; width: 25px; height: 25px;" />
      <span style="font-size: 16px;">通知</span>

    </div>
    <van-form class="form" :model="state.form" label-width="auto">
      <van-field v-model="state.form.username" name="username" label="手机号" placeholder="请填写手机号"
        :rules="[{ required: true, message: '请填写手机号' }]" :disabled="true" />
      <van-field v-model="state.form.nickname" name="nickname" label="昵称" placeholder="请输入昵称"
        :rules="[{ required: true, message: '请填写昵称' }]" />
    </van-form>

    <van-button class="button" @click="saveOrUpdate">确定修改</van-button>
  </div>
</template>

<script setup lang="js">
import { getUser, setUser } from '../../utils/auth';
import axios from '../../axios';
import { reactive } from 'vue';
import { showToast } from 'vant';
import { useRouter } from 'vue-router';
const router = useRouter()
const state = reactive({
  form: {
    id: '',
    username: '',
    nickname: ''
  }
})

state.form = { ...getUser() }
console.log(state.form)
const saveOrUpdate = () => {
  axios.put('user', state.form).then(res => {
    showToast('修改成功')
    getInfo()
  })
}
const getInfo = () => {

  axios.get('user/info').then(res => {
    const user = res
    console.log(user)
    setUser(user)

  })
}
</script>

<style lang="css" scoped>
.main {
  flex-direction: column;
  /* 子元素垂直排列 */
  height: 100vh;
  /* 高度占满整个屏幕 */
  display: flex;
 
  /* 水平居中 */
  align-items: center;
  /* 垂直居中 */
}

.form {
  margin: 40px auto;
}

.button {
  margin: 40px 10px;
  width: 100px;
}
.header {
  background-color: white;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 50px;
}
</style>