<template>
  <div class="main">
    <div class="header">
      <h3>餐厅点餐系统</h3>
      <div class="btn-logout">
        <span style="margin-right: 50px;">当前用户：{{ curName }}</span>
        <el-button type="danger" @click="logout">退出登录</el-button>
      </div>

    </div>
    <div style="height: 1px; width: 100%; background-color: #f0f0f0;"></div>
    <div class="content">
      <div class="sidebar">

        <el-menu :default-active="activeMenu" router class="custom-menu">
          <el-menu-item index="/dashboard/message">
            <!-- <el-icon><icon-menu /></el-icon> -->
            <span>通知</span>
          </el-menu-item>
          <el-menu-item index="/dashboard/food">
            <!-- <el-icon><icon-menu /></el-icon> -->
            <span>菜品管理</span>
          </el-menu-item>
          <el-menu-item index="/dashboard/order">
            <!-- <el-icon><icon-menu /></el-icon> -->
            <span>订单管理</span>
          </el-menu-item>
          <el-menu-item index="/dashboard/pay">
            <!-- <el-icon><icon-menu /></el-icon> -->
            <span>结算管理</span>
          </el-menu-item>
          <el-menu-item index="/dashboard/statics">
            <!-- <el-icon><icon-menu /></el-icon> -->
            <span>经营数据</span>
          </el-menu-item>
          <el-menu-item index="/dashboard/user">
            <!-- <el-icon><icon-menu /></el-icon> -->
            <span>用户管理</span>
          </el-menu-item>

        </el-menu>
      </div>

      <router-view class="container">
      </router-view>


    </div>

  </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router';
import { clear,setUser} from '../utils/auth';
import axios from '../axios'

const router = useRouter();
const route = useRoute();
const activeMenu = ref(route.path);
const curName = ref('')
watch(route, (newRoute) => {
  activeMenu.value = newRoute.path;
});
function logout() {
  clear(); // 清除缓存的用户名
  router.push('/login'); // 重定向到登录页
}


const getInfo = () => {

  axios.get('user/info' ).then(res => {
    const user = res
    console.log(user)
    setUser(user)
    curName.value = user.nickname
  })
}

getInfo()

</script>
<style lang="css" scoped>
/* 使用 ::v-deep 来覆盖 el-menu 的样式 */
::v-deep .custom-menu {
  font-size: 16px;
  /* 设置菜单的字体大小 */
  font-weight: 600;
  background-color: transparent;
}

::v-deep .custom-menu .el-menu-item {
  font-size: 16px;
  /* 覆盖子元素 el-menu-item 的字体大小 */
  font-weight: 600;
  background-color: transparent;
}

.main {
  max-width: 1280px;
  margin: 0 auto;
  background-color: transparent;
}

.header {
  height: 80px;
  /* background-color: #409eff; */
  position: relative;
  background-color: transparent;
  display: flex;
  align-items: center;
}

.btn-logout {
  position: absolute;
  right: 10px;

}

.sidebar {
  width: 20%;
}

.content {
  overflow: hidden;
  box-sizing: border-box;
  display: flex;
  /* height: 100vh; */
}

.container {


  width: 100%;
  height: 100%;
  padding: 20px;
  /* margin: 30px; */
  border-radius: 12px;
  box-sizing: border-box;
}
</style>
