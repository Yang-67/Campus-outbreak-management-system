<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">我的班级</el-breadcrumb-item>
          <el-breadcrumb-item>人员信息</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <!-- 搜索 -->
      <div style="width: 1250px">
        <el-select
            v-model="inputClass"
            clearable
            placeholder="请选择班级"
          >
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
          placeholder="按姓名查找"
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
        <!-- <el-button icon="el-icon-edit" @click="addStudent">添加学生</el-button> -->
      </div>
      <div style="height: 10px"></div>

<!-- 记录列表 -->
        <el-table :data="tableData" style="width: 100%" ref="tableDate">
          <el-table-column
            label="序号"
            type="index"
            leaveType
            width="80"
          ></el-table-column>
          <el-table-column label="学号" prop="userId"> </el-table-column>
          <el-table-column label="姓名" prop="userName">
          </el-table-column>
          <el-table-column label="性别" prop="userSex">
            <template slot-scope="scope">
              <p v-if="scope.row.userSex == 0">女</p>
              <p v-if="scope.row.userSex == 1">男</p>
            </template>
          </el-table-column>
          <el-table-column label="邮箱" prop="userEmail">
          </el-table-column>
          <el-table-column label="住址" prop="userAddress">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
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
      <el-dialog title="添加新学生信息" :visible.sync="dialogVisible" width="40%">
    </el-dialog>
    </el-main>
  </div>
</template>

<script>
import {
  getStudentInfoS,
  deleteUserByIdS,
  getClassesByUserId,
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
      getStudentInfoS(
        this.userId,
        this.pageNum,
        this.pageSize,
        this.inputClass,
        this.inputName
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
      this.inputClass = "";
      this.inputName = "";
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
    //删除
    handleDelete(index, rows) {
      // alert(rows[index].healthId);
      this.$confirm("此操作将永久删除该学生信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteUserByIdS(rows[index]).then(({ data: res }) => {
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
    // addStudent(){
    //   this.dialogVisible=true;
    // }
  },
};
</script>

<style scoped>
#box-card1 {
  width: 90%;
  margin: 0 auto;
}
</style>