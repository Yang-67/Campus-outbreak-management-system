<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1">
      <span
        :class="collapseBtnClass"
        style="cursor: pointer; font-size: 18px"
        @click="collapse"
      ></span>

      <el-breadcrumb
        separator="/"
        style="display: inline-block; margin-left: 10px"
      >
      </el-breadcrumb>
    </div>
    <div class="user-avator">
      <img :src="urlMe" />
    </div>
    <el-dropdown style="width: 120px; cursor: pointer">
      <span>{{ userName }}</span>
      <i class="el-icon-arrow-down"></i>
      <el-dropdown-menu
        slot="dropdown"
        style="width: 100px; text-align: center"
      >
        <el-dropdown-item style="font-size: 14px; padding: 5px 0"
          ><span @click="userInfo">个人信息</span></el-dropdown-item
        >
        <el-dropdown-item style="font-size: 14px; padding: 5px 0"
          ><span @click="updPwd">修改密码</span></el-dropdown-item
        >
        <el-dropdown-item style="font-size: 14px; padding: 5px 0"
          ><span @click="close">退出</span></el-dropdown-item
        >
      </el-dropdown-menu>
    </el-dropdown>
    <el-dialog title="个人信息" :visible.sync="dialogVisible" width="40%">
      <el-form
        :model="ruleForm"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
        :rules="rules"
        ><el-upload
          class="avatar-uploader"
          action="http://localhost:8081/user/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="ruleForm.userUrl" :src="ruleForm.userUrl" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <el-form-item label="学号" prop="userId">
          <el-input readonly v-model="ruleForm.userId"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="userName">
          <el-input readonly v-model="ruleForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="userSex">
          <el-radio-group v-model="ruleForm.userSex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="邮箱" prop="userEmail">
          <el-input v-model="ruleForm.userEmail"></el-input>
        </el-form-item>
        <el-form-item label="家庭住址" prop="userAddress">
          <el-input v-model="ruleForm.userAddress"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">
          确 定
        </el-button>
      </span>
    </el-dialog>
    <!-- 修改密码 -->
    <el-dialog title="修改密码" :visible.sync="dialogVisible2" width="40%">
      <el-form
        :model="ruleForm1"
        ref="ruleForm1"
        status-icon
        :rules="rules"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="密码" prop="userPwd">
          <el-input
            type="password"
            v-model="ruleForm1.userPwd"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="userPwd2">
          <el-input
            type="password"
            v-model="ruleForm1.userPwd2"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitPwd('ruleForm1')"
            >提交</el-button
          >
          <el-button @click="resetForm('ruleForm1')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { selectUserInfo, updUserInfo, updUserPwd } from "../api/data";
// import { updUserInfo } from "../api/data";
export default {
  name: "Header",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm1.userPwd !== "") {
          this.$refs.ruleForm1.validateField("userPwd2");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm1.userPwd) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      dialogVisible: false, //是否显示dialog
      dialogVisible2: false,
      urlMe: "",
      ruleForm1: {
        userPwd: "",
        userPwd2: "",
      },
      ruleForm: {
        userId: "",
        userName: "",
        userSex: "",
        userAddress: "",
        userEmail: "",
        userUrl: "",
      },
      rules: {
        userName: [
          { required: true, message: "请输入员工姓名", trigger: "blur" },
          { min: 2, message: "长度在 2 个字符", trigger: "blur" },
        ],
        userAddress: [{ required: true, message: "家庭住址", trigger: "blur" }],
        userEmail: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        userSex: [{ required: true, message: "请选择性别", trigger: "change" }],
        userPwd: [{ validator: validatePass, trigger: "blur" }],
        userPwd2: [{ validator: validatePass2, trigger: "blur" }],
      },
    };
  },
  computed: {
    userName() {
      return localStorage.getItem("data")
        ? JSON.parse(localStorage.getItem("data")).userName
        : null;
    },
  },
  mounted() {
    this.urlMe = localStorage.getItem("data")
      ? JSON.parse(localStorage.getItem("data")).userUrl
      : null;
  },
  props: {
    collapseBtnClass: String,
    collapse: Function,
  },
  methods: {
    //退出
    close() {
      localStorage.removeItem("data");
      this.$router.push("/");
    },
    //显示个人信息
    userInfo() {
      this.dialogVisible = true;
      var id = JSON.parse(localStorage.getItem("data")).userId;
      selectUserInfo(id).then(({ data: res }) => {
        this.ruleForm = res.data;
      });
    },
    // 修改个人信息
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updUserInfo(this.ruleForm).then(({ data: res }) => {
            if (res.code == 200) {
              this.$message({
                type: "success",
                message: "修改成功",
              });
              this.dialogVisible = false;
              this.urlMe = this.ruleForm.userUrl;
            } else {
              this.$message({
                type: "error",
                message: "修改失败",
              });
            }
          });
        }
      });
    },
    //密码修改提交
    submitPwd(ruleForm1) {
      var id = JSON.parse(localStorage.getItem("data")).userId;
      this.$refs[ruleForm1].validate((valid) => {
        if (valid) {
          updUserPwd(id, this.ruleForm1.userPwd).then(({ data: res }) => {
            if (res.code == 200) {
              this.$message({
                type: "success",
                message: "修改成功,请重新登录!",
              });
              this.dialogVisible2 = false;
              this.close();
            } else {
              this.$message({
                type: "error",
                message: "修改失败,新密码不能与原密码相同",
              });
            }
          });
        }
      });
    },
    //重置
    resetForm(ruleForm1) {
      this.$refs[ruleForm1].resetFields();
    },
    //密码修改
    updPwd() {
      this.dialogVisible2 = true;
    },
    //头像路径
    handleAvatarSuccess(res) {
      this.ruleForm.userUrl = res;
    },
  },
};
</script>

<style scoped>
.user-avator {
  margin-left: 20px;
}

.user-avator img {
  margin-top: 10px;
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}
</style>