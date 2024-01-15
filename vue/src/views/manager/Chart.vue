<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="12">
        <el-card>
          <div style="width: 100%; height: 400px" id="line">

          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div style="width: 100%; height: 400px" id="bar">

          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="10" style="margin: 10px">
      <el-col :span="12">
        <el-card>
          <div style="width: 100%; height: 400px" id="pie">

          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import * as echarts from 'echarts';

const option = {
  title: {
    text: '销售趋势图',
    left: 'center'
  },
  legend: {
    left: 'left'
  },
  tooltip: {
    trigger: 'axis'
  },
  xAxis: {
    type: 'category',
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '金额',
      data: [],
      type: 'line',
      smooth: true
    }
  ]
};

const option1 = {
  title: {
    text: '柱状图',
    left: 'center'
  },
  legend: {
    left: 'left'
  },
  tooltip: {
    trigger: 'axis'
  },
  xAxis: {
    type: 'category',
    data: ['水果', '零食', '饮料', '主食', '小食', '生活用品', '蔬菜']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '金额',
      data: [],
      type: 'bar',
      smooth: true
    },
  ]
};

const option2 = {
  title: {
    text: '订单销售统计',
    subtext: '比例图',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '50%',
      data: [
        { value: 1048, name: 'Search Engine' },
        { value: 735, name: 'Direct' },
        { value: 580, name: 'Email' },
        { value: 484, name: 'Union Ads' },
        { value: 300, name: 'Video Ads' }
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
};
export default {
  name: "Chart",
  data() {
    return {}
  },
  mounted() {
    var lineDom = document.getElementById('line');
    var lineChart = echarts.init(lineDom);

    var barDom = document.getElementById('bar');
    var barChart = echarts.init(barDom);
    barChart.setOption(option1)

    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);
    pieChart.setOption(option2)

    this.$request.get('orders/chart').then(res => {
      option.xAxis.data = res.data ?.map(v => v.date) || []
      option.series[0].data = res.data ?.map(v => v.value) || []
      lineChart.setOption(option)
    })
  }
}
</script>

<style scoped>

</style>