<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">数据统计</el-breadcrumb-item>
          <el-breadcrumb-item></el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <!-- <template>
        <el-alert title="成功提示的文案" type="success"> </el-alert>
        <div style="height: 3px"></div>
        <el-alert title="消息提示的文案" type="info"> </el-alert>
        <div style="height: 3px"></div>
        <el-alert title="警告提示的文案" type="warning"> </el-alert>
        <div style="height: 3px"></div>
        <el-alert title="错误提示的文案" type="error"> </el-alert>
        <div style="height: 3px"></div>
      </template> -->
      <el-card id="box-card">
        <template>
          <el-alert
            :title="title1"
            type="success"
            :description="content1"></el-alert>
          <div style="height: 3px"></div>
          <el-alert
            :title="title2"
            type="success"
            :description="content2"></el-alert>
          <div style="height: 3px"></div>
        </template>
      </el-card>
      <h5></h5>
      <!-- 健康状况占比 -->
      <div id="main" style="width: 550px; height: 400px; float:left;"></div>
      <!-- 疫苗接种情况 -->
      <div id="pie" style="width: 550px; height: 400px; float:right;"></div>
    </el-main>
    </el-main>
  </div>
</template>

<script>
import * as echarts from "echarts";
import { members1, members2, getStudentInfo } from "../api/data";
export default {
  data() {
    return {
      title1: "...",
      content1: "暂无通知",
      title2: "...",
      content2: "暂无通知",
    };
  },
  mounted() {
    this.getChart1();
    this.getChart2();
    this.allInform();
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

      var pieDom = document.getElementById("pie");
      var pieChart = echarts.init(pieDom);
      members1().then(({ data: res }) => {
        pieOption.series[0].data = [
          { name: "未接种", value: res.data[0] },
          { name: "接种1针", value: res.data[1] },
          { name: "接种2针", value: res.data[2] },
          { name: "接种3针", value: res.data[3] },
        ];
        pieChart.setOption(pieOption);
      });
    },
    getChart2() {
      var option = {
        title: {
          text: "学生健康情况统计",
          subtext: "趋势图",
          left: "center",
        },
        xAxis: {
          type: "category",
          data: ["健康", "发烧、咳嗽等", "其他"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [],
            type: "line",
          },
          {
            data: [],
            type: "bar",
          },
        ],
      };

      var chartDom = document.getElementById("main");
      var myChart = echarts.init(chartDom);

      members2().then(({ data: res }) => {
        option.series[0].data = res.data;
        option.series[1].data = res.data;
        myChart.setOption(option);
      });
    },
    allInform() {
      getStudentInfo().then(({ data: res }) => {
        if (res.data.length == 1) {
          this.title1 = res.data[0].informTitle;
          this.content1 = res.data[0].informContent;
        }
        if (res.data.length == 2) {
          this.title1 = res.data[0].informTitle;
          this.content1 = res.data[0].informContent;
          this.title2 = res.data[1].informTitle;
          this.content2= res.data[1].informContent;
        }
      });
    },
  },
};
</script>

<style scoped>
#box-card {
  height: 160px;
  background: url("../assets/card.png");
}
</style>