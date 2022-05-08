<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">每日健康</el-breadcrumb-item>
          <el-breadcrumb-item>每日提交进度</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <div id="main" style="width: 550px; height: 400px; float: left"></div>
    </el-main>
  </div>
</template>

<script>
import * as echarts from "echarts";
import {members3} from "../api/data"
export default {
  data() {
    return {};
  },
  mounted() {
    this.getChart1();
  },
  methods: {
    getChart1() {
      var pieOption = {
        title: {
          text: "学生疫苗接种情况统计",
          subtext: "比例图",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            type: "pie",
            radius: "60%",
            label: {
              //饼图图形上的文本标签
              normal: {
                show: true,
                position: "inner", //标签的位置
                textStyle: {
                  fontWeight: 300,
                  fontSize: 14, //文字的字体大小
                  color: "#fff",
                },
                formatter: "{d}%",
              },
            },
            data: [], // 填空
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };

      var pieDom = document.getElementById("main");
      var pieChart = echarts.init(pieDom);
      members3(JSON.parse(localStorage.getItem("data")).userId).then(({ data: res }) => {
        pieOption.series[0].data = [
          { name: "已打卡", value: res.data[0] },
          { name: "未打卡", value: res.data[1] },
        ];
        pieChart.setOption(pieOption);
      });
    },
  },
};
</script>

<style scoped>
</style>