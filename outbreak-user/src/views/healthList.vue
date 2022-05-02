<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">每日健康</el-breadcrumb-item>
          <el-breadcrumb-item>历史健康记录</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <!-- 搜索 -->
      <div style="width: 1250px">
        <el-input
          style="width: 300px"
          placeholder="按姓名查找"
          type="text"
          v-model="inputStr"
          class="input-with-select"
        ></el-input>
        &nbsp;
        <el-date-picker
          v-model="value1"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        >
        </el-date-picker>
        &nbsp;
        <el-button
          slot="append"
          icon="el-icon-search"
          @click="sarchData"
        ></el-button>
        <el-button icon="el-icon-refresh" @click="refresh">重置</el-button>
      </div>
      <!-- 记录列表 -->
      <el-table
        :data="
          tableData.slice(
            (this.currentPage2 - 1) * pageSize,
            this.currentPage2 * pageSize
          )
        "
        style="width: 100%"
        ref="tableDate"
      >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="今日行程" prop="route">
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="序号" type="index" width="80"></el-table-column>
        <el-table-column label="健康状态" prop="healthState" >
        </el-table-column>
        <el-table-column label="接种疫苗数" prop="vaccineNum">
        </el-table-column>
        <el-table-column label="体温" prop="temperature">
        </el-table-column>
        <el-table-column label="提交时间" prop="createTime">
        </el-table-column>
        <el-table-column label="定位" prop="location">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="small"
              @click="handleEdit(scope.$index + 1, scope.row)"
              >编辑</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage2"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="sizes, prev, pager, next"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-main>
  </div>
</template>

<script>
import {getDayHealthInfo, getHealthNumByUserId} from "../api/data"
export default {
  data() {
    return {
      userId:JSON.parse(localStorage.getItem("data")).userId,
      inputStr: "",
      value1: "",
      tableData: [],
      addtitle: "",
      dialogVisible: false,
      skeleton: false,
      currentPage2: 1, //默认第一页
      total: 0, //总条数
      pageSize: 5, //默认展示5条
    };
  },
  created() {
    this.sarchData();
  },
  methods: {
    // 初始化与搜索
    sarchData() {
      getDayHealthInfo(this.userId).then(({data:res})=>{
        if(res.code==200){
          this.tableData=res.data;
        }
      });
      getHealthNumByUserId(this.userId).then(({data:res})=>{
        this.total=res;
      });
    },
    refresh() {},
    // 每页条数
    handleSizeChange(val) {
      this.pageSize = val;
      this.currentPage2 = 1;
    },
    // 当前页
    handleCurrentChange(val) {
      this.currentPage2 = val;
    },
  },
};
</script>

<style scoped>
</style>