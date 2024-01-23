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
    text: '销售金额统计图',
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
    text: '菜品销量统计图',
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
      name: '销量',
      data: [],
      type: 'bar',
      smooth: true
    },
  ]
};

const option2 = {
  title: {
    text: '菜品销量占比',
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
      data: [],
      label: {
        show: true,
        formatter(param) {
          return param.name + '(' + param.percent + '%)'
        }
      },
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
    return {
      businessId: JSON.parse(localStorage.getItem('xm-user') || '{}').id
    }
  },
  mounted() {
    let lineDom = document.getElementById('line');
    let lineChart = echarts.init(lineDom);

    let barDom = document.getElementById('bar');
    let barChart = echarts.init(barDom);
    barChart.setOption(option1)

    let pieDom = document.getElementById('pie');
    let pieChart = echarts.init(pieDom);
    pieChart.setOption(option2)
    this.$request.post('orders/countByDay/' + this.businessId).then(res => {

      // 折线图
      option.xAxis.data = res.data.data ?.map(v => v.dayTime) || []
      option.series[0].data = res.data.data ?.map(v => v.actual) || []
      lineChart.setOption(option)

      // 柱状图
      // option1.xAxis.data = res.data.bar ?.map(v => v.name) || []
      // option1.series[0].data = res.data.bar ?.map(v => v.value) || []
      // barChart.setOption(option1)

      // 饼图
      // option2.series[0].data = res.data?.bar || []
      // pieChart.setOption(option2)
    })
  }
}
</script>

<style scoped>

</style>