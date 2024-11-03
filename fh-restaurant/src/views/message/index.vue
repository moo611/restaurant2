<template>
  <div class="main-message">
    <div class="header">
      <img @click="router.go(-1)" src="../../assets/back.png" style="position: absolute; left: 10px; width: 25px; height: 25px;"/>
      <span style="font-size: 16px;">通知</span>

    </div>
    <div style="height: 1px; background-color: #e0e0e0;"></div>
    <van-list class="top" v-model:loading="loading" :finished="finished" finished-text="没有更多了" @load="getMessageList">
      <div style="padding: 10px; margin: 8px; border-radius: 5px;background-color: white;" v-for="item in state.data" :key="item">

        <div>
          <span style="font-size: 12px;">订单id：{{ item.id }}</span>
        </div>
        <div>
          <span style="font-size: 12px;">内容: {{ item.message }}</span>
        </div>
        <div>
          <span style="font-size: 12px;">通知时间: {{ item.createTime }}</span>
        </div>
      </div>
    </van-list>

  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { reactive, ref } from 'vue'
import { getUser } from '../../utils/auth'
import axios from '../../axios'
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10000,
  to: ''
})
const router = useRouter()
const state = reactive({
  data: []
});

const loading = ref(false);
const finished = ref(false);


const getMessageList = () => {
  queryParams.to = getUser().username
  console.log(getUser())
  
  axios.get('message/list', { params: queryParams }).then(res => {

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

getMessageList()


</script>
<style lang="css" scoped>

.main-message {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
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
</style>