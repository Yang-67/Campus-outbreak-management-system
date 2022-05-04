<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">我的班级</el-breadcrumb-item>
          <el-breadcrumb-item>发布通知</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <el-card class="box-card" shadow="hover">
        <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-width="200px"
        >
          <el-form-item label="发布者">
            <el-input readonly v-model="name" class="w"></el-input>
          </el-form-item>
          <el-form-item label="通知标题" prop="informTitle">
            <el-input v-model="ruleForm.informTitle" class="w"></el-input>
          </el-form-item>
          <el-form-item label="通知内容" prop="informContent">
            <el-input
              type="textarea"
              maxlength="40"
              v-model="ruleForm.informContent"
              class="w"
            ></el-input>
          </el-form-item>
          <div id="C">
            <el-transfer
              :titles="titles"
              v-model="valueClass"
              :props="{
                key: 'classId',
                label: 'className',
              }"
              :data="dataClass"
            >
            </el-transfer>
          </div>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
              >提交</el-button
            >
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>
  </div>
</template>

<script>
import {selectAllClasses, insertInformInfoS } from "../api/data";
export default {
  data() {
    return {
      name: JSON.parse(localStorage.getItem("data")).userName,
      ruleForm: {
        userId: "",
        informTitle: "",
        informContent: "",
        classId: "",
      },
      titles: ["全部班级", "需要进行通知的班级"],
      dataClass: [
        {
          classId: 1,
          className: "信管1901",
        },
      ],
      valueClass: ['0'],
      rules: {
        informTitle: [
          { required: true, message: "请填写通知标题", trigger: "change" },
        ],
        informContent: [
          { required: true, message: "请填写通知内容", trigger: "change" },
        ],
      },
    };
  },
  created() {
    //查询班级
    selectAllClasses().then(({ data: res }) => {
      this.dataClass = res.data;
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.userId = JSON.parse(
            localStorage.getItem("data")
          ).userId;
          this.ruleForm.classId=this.valueClass+",";
          insertInformInfoS(this.ruleForm).then(({ data: res }) => {
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
.box-card {
  margin: 0 auto;
  width: 80%;
}
.w {
  width: 500px;
}
#C {
  margin-left: 13%;
}
</style>