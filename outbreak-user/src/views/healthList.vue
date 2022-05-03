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
          placeholder="按定位查找"
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
      <el-table :data="tableData" style="width: 100%" ref="tableDate">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="今日行程">
                <el-input
                  type="textarea"
                  readonly
                  autosize
                  v-model="props.row.route"
                ></el-input>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="序号" type="index" width="80"></el-table-column>
        <el-table-column label="健康状态" prop="healthState">
          <template slot-scope="scope">
            <p v-if="scope.row.healthState == 0">健康</p>
            <p v-if="scope.row.healthState == 1">咳嗽、发烧等</p>
            <p v-if="scope.row.healthState == 2">其他</p>
          </template>
        </el-table-column>
        <el-table-column
          label="接种疫苗数(针)"
          prop="vaccineNum"
        ></el-table-column>
        <el-table-column label="体温(℃)" prop="temperature"> </el-table-column>
        <el-table-column label="提交时间" prop="createTime"> </el-table-column>
        <el-table-column label="定位" prop="location"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- <el-button
              type="primary"
              icon="el-icon-edit"
              size="small"
              @click="handleEdit(scope.$index + 1, scope.row)"
              >编辑</el-button
            > -->
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
    </el-main>
  </div>
</template>

<script>
import { getDayHealthInfo, deleteInfoByHealthId } from "../api/data";
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
    };
  },
  created() {
    this.sarchData();
  },
  methods: {
    // 初始化与搜索
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
      getDayHealthInfo(
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
    // handleEdit(index, rows) {
    //   var oDate1 = new Date();
    //   var oDate2 = new Date(rows.createTime);
    //   if (oDate1.getDay() === oDate2.getDay()) {
    //     alert("可编辑");
    //   } else {
    //     this.$message({
    //       type: "error",
    //       message: "不可编辑!",
    //     });
    //   }
    // },
    //删除
    handleDelete(index, rows) {
      // alert(rows[index].healthId);
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteInfoByHealthId(rows[index]).then(({ data: res }) => {
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
</style>


// var oDate1 = new Date(date1);
//     var oDate2 = new Date(date2);
//     if(oDate1.getTime() > oDate2.getTime())