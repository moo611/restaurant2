<template>
  <div>

    <el-table class="my-table" :data="state.data.list">
      <el-table-column prop="table" label="订单ID" />
      <el-table-column prop="table" label="桌号" />
      <el-table-column prop="message" label="内容" />
      <el-table-column prop="createTime" label="创建时间" />


      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" @click="handleView(scope.$index, scope.row)">
            查看详情
          </el-button>

        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="prev, pager, next" :total="state.data.total" :page-size="queryParams.pageSize"
      @change="onPageChange" />

    <el-dialog v-model="dialogVisible1" width="500" @close="clearData">

      <div v-for="item in state.dishes" style="padding: 10px;width: 100%;">
        <span>菜名：{{ item.name }}</span>    
        <span style="margin-left: 50px;">数量：{{item.quantity}}</span> 
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

<script lang="js" setup>
import { reactive, ref } from 'vue';
import axios from '../../axios';

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  to: 'admin'
})
const state = reactive({
  data: {},
  curMessage: {},
  dishes:[]
})


const dialogVisible1 = ref(false)

const clearData = () => {
  state.dishes = []

}


const getMessageList = () => {

  axios.get('message/list', { params: queryParams }).then(res => {

    state.data = res

    console.log(state.data)

  })

}


const handleView = (index, row) => {

  state.dishes = JSON.parse(row.foodInfo)

  dialogVisible1.value = true
}


const onPageChange = (page, size) => {
  queryParams.pageNum = page
  getMessageList()
}

getMessageList()

</script>

<style lang="css" scoped>
.header {
  height: 50px;
  position: relative;
  display: flex;
  align-items: center;
  margin-bottom: 30px;

}

.btn-add {
  position: absolute;
  right: 20px;
}
</style>