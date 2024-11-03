<template>
  <div style="width: 100%; height: 100%;">
    <div class="header">
      <el-date-picker value-format="YYYY-MM-DD" unlink-panels v-model="dateRange" type="daterange" range-separator="至"
        start-placeholder="开始日期" end-placeholder="结束日期" :size="size" :shortcuts="shortcuts" />
      <el-button type="primary" @click="getData" style="margin-left: 20px;">查询</el-button>
    </div>
    <div ref="chartRef" style="width: 100%; height: 400px;"></div>

  </div>

</template>
<script lang="js" setup>
import axios from '../../axios';
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'
const dateRange = ref('')
const shortcuts = [
  {
    text: 'Last week',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
      return [start, end]
    },
  },
  {
    text: 'Last month',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
      return [start, end]
    },
  },
  {
    text: 'Last 3 months',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
      return [start, end]
    },
  },
]

// 绑定图表容器的 ref
const chartRef = ref(null)
let chartInstance = null // 用来保存 ECharts 实例

// 初始化图表实例
const initChart = () => {
  if (chartRef.value) {
    chartInstance = echarts.init(chartRef.value)

    // 基础的配置项，可在这里设置默认样式
    chartInstance.setOption({
      title: { text: '营收统计', left: 'center' },
      tooltip: { trigger: 'axis' },
      xAxis: { type: 'category' },
      yAxis: { type: 'value', name: '价格' },
      series: [{ name: '价格', type: 'line', smooth: false, data: [] }]
    })

    // 监听窗口大小变化，调整图表尺寸
    window.addEventListener('resize', () => {
      chartInstance.resize()
    })
  }
}

// 更新图表数据
const updateChartData = (data) => {
  if (chartInstance) {
    chartInstance.setOption({
      xAxis: { data: data.map(item => item.date) },  // 更新横轴数据
      series: [{ data: data.map(item => item.price) }] // 更新折线图数据
    })
  }
}


const getData = () => {

  console.log(dateRange.value)
  let params = {}
  if (dateRange.value) {
    params.startTime = dateRange.value[0]
    params.endTime = dateRange.value[1]
  }
  console.log(params)
  axios.get('order/count', { params: params }).then(res => {
    updateChartData(res)
  })


}


// 在组件挂载时初始化图表
onMounted(() => {
  initChart()
  getData() // 示例：默认请求整个年份的数据
})
</script>