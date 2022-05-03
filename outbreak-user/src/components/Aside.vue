<template>
  <el-menu
    :default-openeds="['1']"
    :default-active="onRoutes"
    style="min-height: 100%; overflow-x: hidden"
    background-color="#334256"
    text-color="#ffffff"
    active-text-color="#20a0ff"
    :collapse-transition="false"
    :collapse="isCollapse"
    router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img
        src="../assets/school.png"
        alt=""
        style="width: 20px; position: relative; top: 5px; right: 5px"
      />
      <b style="color: white" v-show="logoTextShow">校园管理系统</b>
    </div>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span slot="title">我的班级</span>
      </template>
      <el-menu-item v-if="identity === 1" index="/inform">
        &emsp;&emsp;<span>班级通知</span>
      </el-menu-item>
      <el-menu-item v-if="identity === 2" index="/createInform">
        &emsp;&emsp;<span>发布通知</span>
      </el-menu-item>
      <el-menu-item v-if="identity === 2" index="/userList">
        &emsp;&emsp;<span>人员信息</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu v-if="identity === 1" index="2">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span slot="title">每日健康</span>
      </template>
      <el-menu-item index="/healthInfo">
        &emsp;&emsp;<span>健康信息填报</span>
      </el-menu-item>
      <el-menu-item index="/healthList">
        &emsp;&emsp;<span>历史健康记录</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu v-if="identity === 2" index="2">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span slot="title">每日健康</span>
      </template>
      <el-menu-item index="/shealthList">
        &emsp;&emsp;<span>健康信息记录</span>
      </el-menu-item>
      <el-menu-item index="/shealthDay">
        &emsp;&emsp;<span>每日提交进度</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu v-if="identity === 1" index="3">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">请假报备</span>
      </template>
      <el-menu-item index="/leaveInfo">
        &emsp;&emsp;<span>我的申请</span>
      </el-menu-item>
      <el-menu-item index="/leaveList">
        &emsp;&emsp;<span>申请记录</span>
      </el-menu-item>
    </el-submenu>

    <el-submenu v-if="identity === 2" index="3">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">假条汇总</span>
      </template>
      <el-menu-item index="/sleaveState">
        &emsp;&emsp;<span>假条审核</span>
      </el-menu-item>
      <el-menu-item index="/sleaveList">
        &emsp;&emsp;<span>审核记录</span>
      </el-menu-item>
    </el-submenu>
    <el-menu-item index="/news">
      <i class="el-icon-setting"></i>
      <span slot="title">数据统计</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      identity: JSON.parse(localStorage.getItem("data")).identity,
    };
  },
  computed: {
    onRoutes() {
      return this.$route.path.replace("/Home", "/news");
    },
  },
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean,
  },
};
</script>

<style scoped>
</style>
