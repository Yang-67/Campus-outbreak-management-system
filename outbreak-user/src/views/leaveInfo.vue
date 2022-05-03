<template>
  <div id="app">
    <el-main>
      <!-- 头 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '' }">请假报备</el-breadcrumb-item>
          <el-breadcrumb-item>我的申请</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider></el-divider>
      <Leavecard></Leavecard>
    </el-main>
  </div>
</template>

<script>
import Leavecard from "@/components/leaveCard.vue"
export default {
  name: "leaveInfo",
  data() {
    return {};
  },
  components: {
    Leavecard,
  },
};
</script>

<style scoped>
</style>