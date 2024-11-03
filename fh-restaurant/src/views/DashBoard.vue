<template>

  <div class="main">
    <div class="container">
      <router-view></router-view>
    </div>
    <div style="height: 1px; background-color: #e0e0e0;"></div>
    <div class="bottom">
      <div class="item" @click="goRoute('/dashboard/home')">
        <span class="title">
          首页
        </span>
      </div>
      <div class="item" @click="goRoute('/dashboard/order')">
        <span class="title">
          订单
        </span>

      </div>
      <div class="item" @click="goRoute('/dashboard/me')">
        <span class="title">
          我的
        </span>

      </div>
    </div>
  </div>

</template>

<script setup lang="js">
import router from '../router';
const goRoute = (route) => {
  router.replace(route)
}
</script>

<style lang="css" scoped>
.main {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
}

.container {
  flex: 1;
  width: 100%;
  overflow: auto;
}

.bottom {
  height: 50px;
  display: flex;
  width: 100%;
  flex-direction: row;
}

.item {
  flex: 1;
  text-align: center;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.title {
  font-size: 16px;

}
</style>