<template>
 
  <el-row class="tac">
  <el-col :span="20">
    <el-menu
    default-active="activeIndex"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
    @select="handleSelect"
    router>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>导航</span>
        </template>
      <el-menu
    default-active="/placeInfo"
    router
  >
    <el-menu-item-group>
      <el-menu-item index="/placeInfo">
        <router-link to="/placeInfo">个人管理</router-link>
      </el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="/studentInfo">
        <router-link to="/studentInfo">部门管理</router-link>
      </el-menu-item>
    </el-menu-item-group>
  </el-menu>
      </el-submenu>
    </el-menu>
  </el-col>
</el-row>
</template>
  <script> 
  export default {
    name: "Aside",
    data() {
      return {
        activeIndex: '/placeInfo'// 默认选中的菜单项
      }
    },

    methods: {    
      handleSelect(index) {
      if (this.$router.currentRoute.path !== index) {
        this.activeIndex = index;
        this.$router.push(index).catch(err => {
          if (err.name !== 'NavigationDuplicated') {
            console.error(err);
          }
        });
      }
    },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }

    },
  }
  </script>
  
  <style scoped>
  .icon {
    margin-right: 6px;
  }
  
  .el-sub-menu .el-menu-item {
    height: 50px;
    line-height: 50px;
    padding: 0 45px;
    min-width: 199px;
  }
  </style>