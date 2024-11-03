<template>
  <div class="main">
    <div class="header">
      <el-input v-model="table" placeholder="请输入桌号" style="width: 120px;"/>
      <el-button type="primary" @click="getData" style="margin-left: 20px;">查询</el-button>
      <div class="right">
        <span>总计：{{ totalPrice }}￥</span>
        <el-button @click="handleUpdate" style="margin-left: 30px;">结账</el-button>
      </div>
     
    </div>
    <el-table class="my-table" :data="state.data">
      <el-table-column prop="id" label="订单id" />
      <el-table-column prop="totalPrice" label="价格" />
      <el-table-column prop="status" label="订单状态" :formatter="statusFormatter" width="80" />
      <el-table-column prop="createTime" label="下单时间" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="handelView(scope.$index, scope.row)">
            详情
          </el-button>

        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="dialogVisible1" width="500" @close="clearData">

      <div v-for="item in state.dishes" style="padding: 10px;width: 100%;">
        <span>菜名：{{ item.name }}</span>
        <span style="margin-left: 50px;">数量：{{ item.quantity }}</span>
      </div>

      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="dialogVisible1 = false">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>

</template>

<script setup lang="js">
import { reactive, ref } from 'vue';
import axios from '../../axios'
import { ElMessage } from 'element-plus';
const table = ref('')
const totalPrice=ref(0)
const statusFormatter = (row, col, cellValue) => {
  return '已出餐'
}
const state = reactive({
  data: [],
  dishes: []
})
const dialogVisible1 = ref(false)
const getData = () => {
  axios.get('order/pay/' + table.value).then(res => {
    state.data = res

    state.data.forEach(item=>{
      totalPrice.value+=item.totalPrice
    })

  })


}

const handleUpdate=()=>{

  const params=state.data.map(item=>{return item.id})
  if(!params || params.length==0){
    ElMessage.error('没有可结算订单')
    return
  }
  axios.post('order/pay',params).then(res=>{
    ElMessage.success("结算成功")
    getData()

  })

}

const handelView = (index, row) => {
  state.dishes = JSON.parse(row.foodInfo)

  dialogVisible1.value = true
}

const clearData = () => {
  state.dishes = []

}
</script>

<style lang="css" scoped>
.header{
  height: 50px;
  position: relative;
  display: flex;
  align-items: center;
  margin-bottom: 30px;
}
.right{
  position: absolute;
  right: 10px;
}

</style>