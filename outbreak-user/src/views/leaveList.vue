<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">请假报备</el-breadcrumb-item>
          <el-breadcrumb-item>申请记录</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <!-- 搜索 -->
      <div style="width: 1250px">
        <el-input
          style="width: 300px"
          placeholder="按请假原因查找"
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
      <div style="height: 10px"></div>
      <el-card id="box-card1" shadow="hover">
        <div slot="header">
          <span>我的请假申请记录</span>
          <!-- <el-button style="float: right; padding: 3px 0" type="text"
            >查看未审批假条</el-button
          > -->
        </div>
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
                <el-form-item
                  v-if="props.row.leaveState !== 0"
                  label="审批理由"
                >
                  <el-input
                    type="textarea"
                    readonly
                    autosize
                    v-model="props.row.leaveNo"
                  ></el-input>
                </el-form-item>
                <!-- <el-progress :percentage="50"></el-progress> -->
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
            label="序号"
            type="index"
            leaveType
            width="80"
          ></el-table-column>
          <el-table-column label="请假原因" prop="leaveType"> </el-table-column>
          <el-table-column label="请假开始时间" prop="startTime">
          </el-table-column>
          <el-table-column label="请假结束时间" prop="endTime">
          </el-table-column>
          <el-table-column label="审批状态" prop="leaveState">
            <template slot-scope="scope">
              <p v-if="scope.row.leaveState == 0">未审批</p>
              <p v-if="scope.row.leaveState == 1">审核不通过</p>
              <p v-if="scope.row.leaveState == 2">审核通过</p>
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
                >编辑</el-button
              >
              <el-button
                size="small"
                type="danger"
                icon="el-icon-delete"
                @click="handleDelete(scope.$index, tableData)"
              >
                删除
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
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          ></el-pagination>
        </div>
      </el-card>
    </el-main>
    <el-dialog :visible.sync="dialogVisible" width="40%">
    </el-dialog>
  </div>
</template>

<script>
import { getLeaveMeInfo, deleteInfoByLeaveId } from "../api/data";
export default {
  data() {
    return {
      inputStr: "",
      userId: JSON.parse(localStorage.getItem("data")).userId,
      pageNum: 1, //页码
      pageSize: 5, //数量
      tableData: [],
      total: 0,
      value1: "",
      dialogVisible:false,
    };
  },
  created() {
    this.sarchData();
  },
  methods: {
    sarchData() {
      if (this.value1.length != 0) {
        var m = this.value1.toString().split(",");
        var d1 = new Date(m[0]);
        var d2 = new Date(m[1]);
        var datetime1 =
          d1.getFullYear() +
          "-" +
          (d1.getMonth() + 1) +
          "-" +
          d1.getDate() +
          " " +
          d1.getHours() +
          ":" +
          d1.getMinutes() +
          ":" +
          d1.getSeconds();
        var datetime2 =
          d2.getFullYear() +
          "-" +
          (d2.getMonth() + 1) +
          "-" +
          d2.getDate() +
          " " +
          d2.getHours() +
          ":" +
          d2.getMinutes() +
          ":" +
          d2.getSeconds();
      }
      getLeaveMeInfo(
        this.userId,
        this.pageNum,
        this.pageSize,
        this.inputStr,
        datetime1,
        datetime2
      ).then(({ data: res }) => {
        if (res.code == 200) {
          this.tableData = res.data.records;
          this.total = res.data.total;
          this.pageSize = res.data.size;
          this.pageNum = res.data.current;
        }
      });
    },
    //重置
    refresh() {
      this.inputStr = "";
      this.value1 = "";
      this.pageNum = 1;
      this.sarchData();
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
    //编辑
    handleEdit(index, rows) {
      this.dialogVisible=true;
      this.$message({
        type: "success",
        message: "请修改后再提交!",
      });
    },
    //删除
    handleDelete(index, rows) {
      // alert(rows[index].healthId);
      this.$confirm("此操作将永久删除该条请假申请, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteInfoByLeaveId(rows[index]).then(({ data: res }) => {
            if (res.code == 200) {
              rows.splice(index, 1);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.sarchData();
            } else {
              this.$message({
                type: "error",
                message: "删除失败!",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>

<style scoped>
#box-card1 {
  width: 90%;
  margin: 0 auto;
}
</style>