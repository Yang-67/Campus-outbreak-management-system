<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">我的班级</el-breadcrumb-item>
          <el-breadcrumb-item>班级通知</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <!-- 搜索 -->
      <div style="width: 1250px">
        <el-input
          style="width: 300px"
          placeholder="按标题查找"
          type="text"
          v-model="inputStr"
          class="input-with-select"
        ></el-input>
        <el-button
          slot="append"
          icon="el-icon-search"
          @click="sarchData"
        ></el-button>
        <el-button icon="el-icon-refresh" @click="refresh">重置</el-button>
      </div>
      <!-- 通知展示 -->
      <el-table :data="tableData" ref="tableDate">
        <el-table-column label="序号" type="index" width="50"></el-table-column>
        <el-table-column label="标题" prop="informTitle"></el-table-column>
        <el-table-column label="内容" prop="informContent"></el-table-column>
        <el-table-column label="发布时间" prop="releaseTime"></el-table-column>
        <el-table-column label="操作" prop="num" width="190">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="small"
              @click="handleLook(scope.$index, scope.row)"
            >
              查看内容详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 页码 -->
      <div style="width: 50%; margin: 0 auto">
        <el-pagination
          background
          layout="sizes, prev, pager, next"
          :current-page="pageNum"
          :page-size="pageSize"
          :total="total"
          :page-sizes="[5, 10, 15, 20]"
          @current-change="sarchData"
          @size-change="handleSizeChange"
        ></el-pagination>
      </div>
      <!-- 内容详情 -->
      <el-dialog title="内容详情" :visible.sync="dialogVisible" width="40%">
        <el-form ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="标题">
            <el-input readonly v-model="ruleForm.informTitle"/>
          </el-form-item>
          <el-form-item label="发布者">
            <el-input readonly v-model="ruleForm.userName"/>
          </el-form-item>
          <el-form-item label="内容">
            <el-input type="textarea" readonly v-model="ruleForm.informContent" maxlength="50" />
          </el-form-item>
        </el-form>
      </el-dialog>
    </el-main>
  </div>
</template>

<script>
import { getAllInformByUserId,selectUserInfo } from "../api/data";
export default {
  data() {
    return {
      inputStr: "",
      userId: JSON.parse(localStorage.getItem("data")).userId,
      pageNum: 0, //页码
      pageSize: 5, //数量
      tableData: [],
      total: 0,
      dialogVisible: false,
      ruleForm: {
        informTitle: "",
        informContent: "",
        userName:"",
      },
    };
  },
  created() {
    this.sarchData();
  },
  methods: {
    // 初始化与搜索
    sarchData() {
      getAllInformByUserId(
        this.userId,
        this.pageNum,
        this.pageSize,
        this.inputStr
      ).then(({ data: res }) => {
        this.tableData = res.data.records;
        this.total = res.data.total;
        this.pageSize = res.data.size;
        this.pageNum = res.data.pages;
      });
    },

    //重置
    refresh() {
      this.inputStr = "";
      this.pageNum = 1;
      this.sarchData();
    },
    // 更改显示数量
    handleSizeChange(value) {
      this.pageSize = value;
      this.pageNum = 1;
      this.sarchData();
    },
    handleLook(index, rows) {
      this.dialogVisible = true;
      this.ruleForm.informTitle=rows.informTitle;
      this.ruleForm.informContent=rows.informContent;
      selectUserInfo(rows.userId).then(({data:res})=>{
        this.ruleForm.userName=res.data.userName;
      })
    },
  },
};
</script>

<style scoped>
</style>