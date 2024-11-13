<template>
  <div>
    <!-- Header -->
    <el-header style="background-color: #f0f0f0; height: 50px; line-height: 50px; text-align: center;">
      <Header/>
    </el-header>

    <!-- Content -->
    <el-container style="height: calc(100vh - 50px);">
      <!-- Aside -->
      <el-aside style="width: 200px;">
        <Aside/>
      </el-aside>

      <!-- Main Content Area -->
      <el-main style="padding: 20px;">
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>
  <script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {
  name: "layout",
  components: {
    Header,
    Aside,
  },
  mounted() {
  console.log('Token on home page:', window.sessionStorage.getItem('token'));
}
}
</script>

<style scoped>

</style>