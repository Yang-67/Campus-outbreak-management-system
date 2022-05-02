<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">每日健康</el-breadcrumb-item>
          <el-breadcrumb-item>健康信息填报</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
      >
        <el-form-item label="姓名">
          <el-input readonly v-model="name" class="w"></el-input>
        </el-form-item>
        <el-form-item label="健康状况" prop="healthState">
          <el-radio-group v-model="ruleForm.healthState">
            <el-radio label="健康"></el-radio>
            <el-radio label="咳嗽、发烧"></el-radio>
            <el-radio label="其它"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="体温(℃)" prop="temperature">
          <el-input v-model="ruleForm.temperature" class="w"></el-input>
        </el-form-item>
        <el-form-item label="疫苗针次" prop="vaccineNum">
          <el-radio-group v-model="ruleForm.vaccineNum">
            <el-radio label="1"></el-radio>
            <el-radio label="2"></el-radio>
            <el-radio label="3"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="定位" prop="location">
          <el-input v-model="ruleForm.location" class="w"></el-input>
        </el-form-item>
        <el-form-item label="今日行程" prop="route">
          <el-input
            type="textarea"
            maxlength="30"
            v-model="ruleForm.route"
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
    </el-main>
  </div>
</template>

<script>
import { insertHealthInfo } from "../api/data";
export default {
  data() {
    return {
      name: JSON.parse(localStorage.getItem("data")).userName,
      ruleForm: {
        userId: "",
        healthState: "",
        vaccineNum: "",
        temperature: "",
        location: "",
        route: "",
        deleteFlag:1
      },
      rules: {
        temperature: [
          { required: true, message: "请输入体温", trigger: "change" },
        ],
        healthState: [
          { required: true, message: "请选择健康状况", trigger: "change" },
        ],
        vaccineNum: [
          { required: true, message: "请选择接种疫苗次数", trigger: "change" },
        ],
        location: [
          { required: true, message: "请输入当前位置", trigger: "change" },
        ],
        route: [
          { required: true, message: "请简要说明今日行程", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.ruleForm.healthState == "健康") {
            this.ruleForm.healthState = 0;
          } else if (this.ruleForm.healthState == "咳嗽、发烧") {
            this.ruleForm.healthState = 1;
          } else {
            this.ruleForm.healthState = 2;
          }
          if (this.ruleForm.vaccineNum == "1") {
            this.ruleForm.vaccineNum = 1;
          } else if (this.ruleForm.vaccineNum == "2") {
            this.ruleForm.vaccineNum = 2;
          } else {
            this.ruleForm.vaccineNum = 3;
          }
          (this.ruleForm.userId = JSON.parse(
            localStorage.getItem("data")
          ).userId),
            insertHealthInfo(this.ruleForm).then(({ data: res }) => {
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
.w {
  width: 500px;
}
</style>