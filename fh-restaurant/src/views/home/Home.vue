<template>
  <div class="main-home">
    <div class="header">
      <span style="font-size: 16px;">首页</span>
      <van-button style="position: absolute; right: 10px;" size="small" type="primary"
        @click="router.push('/message')">通知</van-button>
    </div>
    <div style="height: 1px; background-color: #e0e0e0;"></div>
    <van-list class="top" v-model:loading="loading" :finished="finished" finished-text="没有更多了" @load="getFoodList">
      <van-cell v-for="item in state.data" :key="item">
        <!-- 左侧图片 -->
        <template #icon>
          <van-image width="80px" height="80px" fit="cover" :src="item.url" />
        </template>

        <!-- 中间菜品名称和单价 -->
        <template #title>
          <div class="dish-info">
            <div class="dish-name">{{ item.name }}</div>
            <div v-if="item.category" class="dish-category">类别：{{ item.category }}</div>
            <div class="dish-price">￥{{ item.price }}</div>
          </div>
        </template>

        <!-- 右侧加减按钮和数字 -->
        <template #right-icon>
          <div class="counter">
            <van-button plain type="primary" @click="decrement(item)" :disabled="item.quantity <= 0"
              size="small">-</van-button>
            <span>{{ item.quantity }}</span>
            <van-button plain type="primary" @click="increment(item)" :disabled="item.quantity > 9"
              size="small">+</van-button>
          </div>
        </template>

      </van-cell>
    </van-list>


    <div class="bottom">
      <span style="margin-left: 20px;font-size: 14px;">共计：{{ state.price }}￥</span>
      <van-button type="primary" style="width: 100%; margin: auto 20px;" @click="onOrder">下单</van-button>
    </div>

    <van-dialog v-model:show="show" title="提交订单" show-cancel-button @cancel="clearData" @confirm="handleAdd">
      <van-field v-model="form.table" name="table" label="桌号" placeholder="请填写桌号"
        :rules="[{ required: true, message: '请填写桌号' }]" />
    </van-dialog>


  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { setUser } from '../../utils/auth'
import axios from '../../axios'
import { showToast } from 'vant';
import { useRouter } from 'vue-router';
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10000,

})
const onOrder = () => {
  if (state.price == 0) {
    showToast('需要先点好再下单')
    return
  }
  show.value = true
}
const show = ref(false)
const state = reactive({
  data: [],
  price: 0
});
const router = useRouter()
const loading = ref(false);
const finished = ref(false);
const form = reactive({

  table: '',
  foodInfo: '',
  totalPrice: 0

})

const getInfo = () => {

  axios.get('user/info').then(res => {
    const user = res
    console.log(user)
    setUser(user)

  })
}
const clearData = () => {
  form.table = ''
  form.foodInfo = ''
  form.totalPrice = 0
  state.price = 0
}

const getFoodList = () => {

  axios.get('food/list', { params: queryParams }).then(res => {

    // for (let i = 0; i < res.list.length; i++) {
    //   state.data.push(res.list[i])
    // }
    state.data = res.list.map(item => {
      return {
        ...item,
        quantity: 0
      }
    })

    loading.value = false;

    if (state.data.length >= res.total) {
      finished.value = true
    }
    console.log(state.data)

  })

}

const handleAdd = () => {

  const selectFoods = state.data.filter(item =>
    item.quantity > 0
  ).map(item => {
    return {
      foodId: item.id,
      name: item.name,
      price: item.price,
      quantity: item.quantity
    }
  })
  //console.log(selectFoods)
  let price = 0
  selectFoods.forEach(item => {
    //console.log(item)
    price += (item.price * item.quantity)
  })
  //console.log(price)
  //console.log(selectFoods)
  form.foodInfo = JSON.stringify(selectFoods)
  form.totalPrice = state.price
  console.log(form)
  axios.post('order', form).then(res => {
    showToast('下单成功')
    clearData()
    getFoodList()
  })
}


function increment(item) {
  state.price = 0
  item.quantity++;
  const selectFoods = state.data.filter(item =>
    item.quantity > 0
  ).map(item => {
    return {
      foodId: item.id,
      name: item.name,
      price: item.price,
      quantity: item.quantity
    }
  })
  selectFoods.forEach(item => {
    //console.log(item)
    state.price += (item.price * item.quantity)
  })
}
function decrement(item) {
  if (item.quantity > 0) {
    state.price = 0
    item.quantity--;
    const selectFoods = state.data.filter(item =>
      item.quantity > 0
    ).map(item => {
      return {
        foodId: item.id,
        name: item.name,
        price: item.price,
        quantity: item.quantity
      }
    })
    selectFoods.forEach(item => {
      //console.log(item)
      state.price += (item.price * item.quantity)
    })
  }
}



getFoodList()
getInfo()



</script>

<style lang="css" scoped>
.main-home {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;

}

.header {
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

.van-image {
  margin: 10px;
}

.dish-info {
  display: flex;
  flex-direction: column;
}

.dish-name {
  font-size: 16px;
  font-weight: bold;
}
.dish-category {
  font-size: 14px;
  color: #999;
}

.dish-price {
  font-size: 14px;
  color: #999;
}

.counter {
  display: flex;
  align-items: center;
}

.counter span {
  margin: 0 10px;
  font-size: 16px;
}
</style>