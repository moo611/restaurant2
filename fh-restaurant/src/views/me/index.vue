<template>
  <div style="width: 100%; height: 100%; display: flex; align-items: center; flex-direction: column;">
    <img src="../../assets/avt_default.png" style="width: 80px; height: 80px; border-radius: 50%; margin-top: 50px;" />
    <div>
      <span v-if="getUser()" style="font-size: 14px; ">{{ getUser().nickname }}</span>
    </div>
    <div style="margin-top: 10px;">
      <van-button type="primary" @click="router.push('/edit')" style="width: 100px; margin-right: 20px;">修改</van-button>
      <van-button type="danger" style="width: 100px;" @click="logout" >退出登录</van-button>
    </div>
    

  </div>
</template>
<script setup lang="js">
import { getUser,clear } from '../../utils/auth';
import { useRouter } from 'vue-router';
const router = useRouter()
const logout=()=>{

  clear()
  router.go(-1)
}
</script>

<style lang="css" scoped></style>