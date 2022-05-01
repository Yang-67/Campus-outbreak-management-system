<template>
  <div id="app">
    <el-card class="box-card">
      <h1 style="text-align: center">校园疫情管理系统</h1>
      <el-form :model="ruleForm" style="width: 400px; margin: 0 auto">
        <el-form-item label="账号">
          <el-input
            type="text"
            v-model="ruleForm.userId"
            placeholder="请输入账号"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input
            type="password"
            v-model="ruleForm.userPwd"
            placeholder="请输入密码"
            show-password
          ></el-input>
        </el-form-item>
        <div style="text-align: center; margin-top:50px">
          <el-button style="width:200px" type="primary" :plain="true" @click="submitForm(ruleForm)"
            >登录</el-button
          >
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { getLogin } from "../api/data";
export default {
  name: "login",
  components: {},
  data() {
    return {
      ruleForm: {
        userId: "3191905130",
        userPwd: "123456",
      },
    };
  },
  methods: {
    submitForm(formName) {
      if (formName.userId === "" || formName.userPwd === "") {
        this.$message({
          type: "info",
          message: "请输出完整信息",
        });
      } else {
        getLogin(this.ruleForm).then(({ data: res }) => {
          if (res.code == 200) {
            localStorage.setItem("data", JSON.stringify(res.data));
            this.$router.push("/Home");
            this.$message({
              type: "success",
              message: "登录成功",
            });
          } else if (res.code == 400) {
            this.$message({
              type: "info",
              message: res.msg,
            });
          } else if (res.code == 600) {
            this.$message({
              type: "info",
              message: "账号或密码错误!",
            });
          } else {
            this.$message({
              type: "error",
              message: "登录失败",
            });
          }
        });
      }
    },
  },
};
</script>

<style scoped>
#app {
  position: absolute;
  background: url("../assets/background.jpg");
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
}
.text {
  font-size: 14px;
  line-height: 100%;
}

.box-card {
  width: 600px;
  height: 400px;
  margin: 0 auto;
  margin-top: 100px;
  background: rgb(255, 255, 255, 0.5);
}
</style>