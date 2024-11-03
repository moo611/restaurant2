<template>
  <div class="main-order">
    <div class="header">
      <span style="font-size: 16px;">订单</span>

    </div>
    <div style="height: 1px; background-color: #e0e0e0;"></div>
    <van-list class="top" v-model:loading="loading" :finished="finished" finished-text="没有更多了" @load="getOrderList">
      <div style="padding: 10px; margin: 8px; border-radius: 5px;background-color: white;" v-for="item in state.data" :key="item">

        <div class="row">
          <span style="font-size: 12px;">订单id：{{ item.id }}</span>
        </div>
        <div class="row">
          <span style="font-size: 12px;">订单状态: {{ getStatus(item.status) }}</span>
        </div>
        <div class="row">
          <span style="font-size: 12px;">下单时间: {{ item.createTime }}</span>
        </div>
      </div>
    </van-list>

  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { getUser } from '../../utils/auth'
import axios from '../../axios'
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10000,
  createBy: ''
})
const show = ref(false)
const state = reactive({
  data: []
});
const getStatus = (status) => {
  if (status == '0') {
    return '待出餐'
  }else if(status=='1'){
    return '待结算'
  }
  return '已结算'
}
const loading = ref(false);
const finished = ref(false);


const getOrderList = () => {
  queryParams.createBy = getUser().username
  axios.get('order/list', { params: queryParams }).then(res => {

    // for (let i = 0; i < res.list.length; i++) {
    //   state.data.push(res.list[i])
    // }
    state.data = res.list

    loading.value = false;

    if (state.data.length >= res.total) {
      finished.value = true
    }
    console.log(state.data)

  })

}

getOrderList()


</script>

<style lang="css" scoped>
.main-order {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  background-color: #f0f0f0
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

/* .list {
  height: 100%;
  width: 100%;
} */
.top {
  overflow: auto;
  flex: 1;
  width: 100%;
}

.bottom {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 120px;

}

.van-list-container {
  flex-grow: 1;
  max-height: 100%;
  /* 确保 van-list 不会超出容器 */
  overflow: auto;
  /* 内容过多时滚动 */
}
.row{
  margin-bottom: 10px;
}

</style>