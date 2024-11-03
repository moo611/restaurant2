<template>
  <div>
    <div class="header">
      <el-select style="width: 120px;" v-model="queryParams.status" placeholder="请选择" @change="getOrderList">
        <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>

    </div>
    <el-table class="my-table" :data="state.data.list">

      <el-table-column prop="id" label="id" />
      <el-table-column prop="table" label="桌号" />
      <el-table-column prop="totalPrice" label="总价" />
      <el-table-column prop="status" label="状态" :formatter="statusFormatter" width="80"/>
      <el-table-column prop="createTime" label="创建时间" />


      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="danger" @click="handelView(scope.$index, scope.row)">
            详情
          </el-button>
          <el-button type="primary" @click="handleEit(scope.$index, scope.row)" :disabled="scope.row.status!='0'">
            出餐
          </el-button>

        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="prev, pager, next" :total="state.data.total" :page-size="queryParams.pageSize"
      @change="onPageChange" />

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

<script lang="js" setup>
import { reactive, ref } from 'vue';
import axios from '../../axios';
import { ElMessage } from 'element-plus';

const statusOptions = [{label: '全部', value: 'all'},{ label: '进行中', value: '0' }, { label: '已完成', value: '1' }]
const statusFormatter = (row, column, cellValue) => {
  if (cellValue == '0') {
    return '进行中'
  }else if(cellValue=='1'){
    return '已出餐'
  } 
  return '已结算'
}
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  status: 'all'
})
const state = reactive({
  data: {},
  curOrder: {},
  dishes: []
})


const dialogVisible1 = ref(false)

const clearData = () => {
  state.dishes = []

}


const getOrderList = () => {
  let params = {}
  params.pageNum = queryParams.pageNum
  params.pageSize = queryParams.pageSize
  if(queryParams.status!='all'){
    params.status = queryParams.status
  }
  axios.get('order/list', { params: params }).then(res => {

    state.data = res

    console.log(state.data)

  })

}

const handleEit = (index, row) => {
  row.status = '1'
  axios.put('order', row).then(res => {

    dialogVisible1.value = false
    ElMessage.success('修改成功')
    getOrderList()

  })

}

const handelView = (index, row) => {
  state.dishes = JSON.parse(row.foodInfo)

  dialogVisible1.value = true
}

const onPageChange = (page, size) => {
  queryParams.pageNum = page
  getOrderList()
}

getOrderList()

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