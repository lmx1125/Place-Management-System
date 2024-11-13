<template>
    <div>
      <el-card>
        <div slot="header" class="clearfix">
          <span>个人信息</span>
        </div>
        <el-row justify="center">
          <el-col :span="16">
            <el-form :model="userInfo" label-width="80px" readonly>
              <el-form-item label="头像：">
                <el-image :src="userInfo.headPic" shape="square" size="large"></el-image>
              </el-form-item>
              <el-form-item label="用户名">
                <el-input v-model="userInfo.userName" readonly></el-input>
              </el-form-item>
              <el-form-item label="姓名">
                <el-input v-model="userInfo.name" readonly></el-input>
              </el-form-item>
              <el-form-item label="年龄">
                <el-input v-model="userInfo.age" readonly></el-input>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </template>
  
  
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        userInfo: {
          userName: '',
          name: '',
          age: 0,
          headPic: ''
        }
      };
    },
    mounted() {
      this.getUserInfo();
    },
    methods: {
      async getUserInfo() {
        try {
          const userString = window.sessionStorage.getItem('User');
          if (userString) {
            const userInfo = JSON.parse(userString);
            const { userName } = userInfo;
            const response = await axios.get(`/user/getUserInfo/${userName}`);
            const fullUserInfo = response.data;
            // 将完整的用户信息更新到 userInfo 对象中
            this.userInfo = fullUserInfo;
          }
        } catch (error) {
          console.error('Error fetching full user info:', error);
        }
      }
    }
  }
  </script>
  
  <style scoped>
  /* 可以添加一些自定义样式 */
  </style>
  