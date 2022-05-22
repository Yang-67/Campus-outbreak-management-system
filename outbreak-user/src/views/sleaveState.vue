<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">假条汇总</el-breadcrumb-item>
          <el-breadcrumb-item>假条审核</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <!-- 搜索 -->
      <div style="width: 1250px">
        <el-select v-model="inputClass" clearable placeholder="请选择班级">
          <el-option
            v-for="item in options"
            :key="item.classId"
            :label="item.className"
            :value="item.classId"
          >
          </el-option>
        </el-select>
        &nbsp;
        <el-input
          style="width: 300px"
          placeholder="按学号查找"
          type="text"
          v-model="inputName"
          class="input-with-select"
        ></el-input>
        &nbsp;
        <el-button
          slot="append"
          icon="el-icon-search"
          @click="sarchData"
        ></el-button>
        <el-button icon="el-icon-refresh" @click="refresh">重置</el-button>
      </div>
      <div style="height: 10px"></div>
      <!-- 记录列表 -->
      <el-table :data="tableData" style="width: 100%" ref="tableDate">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="请假理由">
                <el-input
                  type="textarea"
                  readonly
                  autosize
                  v-model="props.row.leaveReason"
                ></el-input>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          label="序号"
          type="index"
          leaveTypeuserId
          width="80"
        ></el-table-column>
        <el-table-column label="申请人学号" prop="userId"> </el-table-column>
        <el-table-column label="请假原因" prop="leaveType"> </el-table-column>
        <el-table-column label="请假开始时间" prop="startTime">
        </el-table-column>
        <el-table-column label="请假结束时间" prop="endTime"> </el-table-column>
        <el-table-column label="审批状态" prop="leaveState">
          <template slot-scope="scope">
            <p v-if="scope.row.leaveState == 0">未审批</p>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.leaveState !== 2"
              type="primary"
              icon="el-icon-edit"
              size="small"
              @click="handleEdit(scope.$index + 1, scope.row)"
              >审核</el-button
            >
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
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        ></el-pagination>
      </div>
    </el-main>
    <el-dialog title="确认审核" :visible.sync="dialogVisible" width="40%">
      <el-form label-position="left" inline class="demo-table-expand">
        <div style="width:80%; margin: 0 auto">
          <el-form-item label="审核理由">
            <el-input type="textarea" autosize v-model="leaveNo"></el-input>
          </el-form-item>
          <el-form-item>
            <el-radio v-model="radio" label="2">同意</el-radio>
            <el-radio v-model="radio" label="1">驳回</el-radio>
          </el-form-item>
          </br>
          <el-form-item>
            <el-button type="primary" size="small" @click="submitInfoS"
              >提交</el-button
            ></el-form-item
          >
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {
  getAllLeaveInfoByS,
  getClassesByUserId,
  UpdStuLeaveInfo,
} from "../api/data";
export default {
  data() {
    return {
      inputName: "",
      inputClass: "",
      userId: JSON.parse(localStorage.getItem("data")).userId,
      pageNum: 1, //页码
      pageSize: 5, //数量
      tableData: [],
      total: 0,
      options: [],
      dialogVisible: false,
      leaveNo: "无",
      ID: "", // 审批人
      radio: "2", // 是否同意
    };
  },
  created() {
    this.sarchData();
    getClassesByUserId(JSON.parse(localStorage.getItem("data")).userId).then(
      ({ data: res }) => {
        this.options = res.data;
      }
    );
  },
  methods: {
    sarchData() {
      // var id = JSON.parse(localStorage.getItem("data")).userId;
      getAllLeaveInfoByS(
        JSON.parse(localStorage.getItem("data")).userId,
        this.pageNum,
        this.pageSize,
        this.inputClass,
        this.inputName
      ).then(({ data: res }) => {
        this.tableData = res.data.records;
        this.total = res.data.total;
        this.pageSize = res.data.size;
        this.pageNum = res.data.current;
      });
    },
    // 每页条数
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.sarchData();
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum;
      this.sarchData();
    },
    //审核
    handleEdit(index, rows) {
      this.ID = rows.leaveId;
      this.dialogVisible = true;
    },
    //审核实现
    submitInfoS() {
      UpdStuLeaveInfo(this.ID, this.radio, this.leaveNo).then(({ data: res }) => {
        if (res.code == 200) {
          this.leaveNo = "无";
          this.dialogVisible = false;
          this.$message({
            type: "success",
            message: "审批成功!",
          });
          this.sarchData();
        }
      });
    },
    //重置
    refresh() {
      this.inputClass = "";
      this.inputName = "";
      this.pageNum = 1;
      this.sarchData();
    },
  },
};
</script>

<style scoped>
</style>