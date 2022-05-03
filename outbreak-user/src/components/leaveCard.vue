<template>
  <div id="app">
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span>我的假条</span>
        <!-- <el-button style="float: right; padding: 3px 0" type="text"
          >操作按钮</el-button
        > -->
      </div>
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="200px"
      >
        <el-form-item label="申请人">
          <el-input readonly v-model="name" class="w"></el-input>
        </el-form-item>
        <el-form-item label="请假类型" prop="leaveType">
          <el-radio-group v-model="ruleForm.leaveType">
            <el-radio label="外出"></el-radio>
            <el-radio label="病假"></el-radio>
            <el-radio label="其它"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-select
            v-model="ruleForm.classId"
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
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            v-model="ruleForm.startTime"
            type="datetime"
            placeholder="选择日期时间"
            align="right"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="返校时间" prop="endTime">
          <el-date-picker
            v-model="ruleForm.endTime"
            type="datetime"
            placeholder="选择日期时间"
            align="right"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="请假理由" prop="leaveReason">
          <el-input
            type="textarea"
            maxlength="30"
            v-model="ruleForm.leaveReason"
            class="w"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >提交</el-button
          >
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { insertLeaveInfo, selectAllClasses } from "../api/data";
export default {
  name: "LeaveCard",
  props: ["leaveInfo"],
  data() {
    return {
      name: JSON.parse(localStorage.getItem("data")).userName,
      ruleForm: {
        userId: "",
        leaveType: "",
        classId: "",
        startTime: "",
        endTime: "",
        leaveReason: "",
      },
      rules: {
        healthState: [
          { required: true, message: "请选择请假类型", trigger: "change" },
        ],
        classId: [{ required: true, message: "请选择班级", trigger: "change" }],
        startTime: [
          { required: true, message: "请选择开始时间", trigger: "change" },
        ],
        endTime: [
          { required: true, message: "请选择结束时间", trigger: "change" },
        ],
        leaveReason: [
          { required: true, message: "请选择请假类型", trigger: "change" },
        ],
      },
      options: [],
    };
  },
  created() {
    selectAllClasses().then(({ data: res }) => {
      this.options = res.data;
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var d1 = new Date(this.ruleForm.startTime);
          var d2 = new Date(this.ruleForm.endTime);
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
          this.ruleForm.startTime = datetime1;
          this.ruleForm.endTime = datetime2;
          this.ruleForm.userId = JSON.parse(
            localStorage.getItem("data")
          ).userId;
          insertLeaveInfo(this.ruleForm).then(({ data: res }) => {
            if (res.code == 200) {
              this.$message({
                type: "success",
                message: "提交成功",
              });
            } else {
              this.$message({
                type: "error",
                message: "提交失败",
              });
            }
          });
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  margin: 0 auto;
  width: 90%;
  height: 600px;
}
.w {
  width: 500px;
}
</style>