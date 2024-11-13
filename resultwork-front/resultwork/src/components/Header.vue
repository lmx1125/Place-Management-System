<template>
  <div style="line-height: 50px;display: flex">
    <div style="width: 200px; font-weight: bold; color: dodgerblue">测盟汇</div>

    <div style="flex: 1"></div>
    <div class="right-info">
      <el-button icon="el-icon-user" @click="load()" circle></el-button>
      <el-button type="info" @click=" logout()">退出</el-button>
    </div>
  </div>
</template>
  
<script>
import axios from 'axios';
import { Loading } from 'element-ui';
export default {
  methods: {
    load(){
      this.$router.push({ path: '/selfinfo' });

    },
    logout() {
      const token = window.sessionStorage.getItem('token'); // 将获取到的token赋值给变量token
      console.log(token)
      if (!token) {
        console.log("未找到有效的token，请先登录");
        return;
      }
      const headers = {
        'Authorization': token
      };
      axios.post('/main/logout', null, { headers })
      .then(response => {
        const data = response.data;
        if (data.code === '0') {
          window.sessionStorage.removeItem('User');
          localStorage.removeItem('token');
          console.log("成功登出");
          this.$router.push({ path: '/login' });
        } else {
          console.log("登出失败");
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });
    }
  }
}

</script>
  <style scoped>
.right-info {
  width: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 1.5%;
}

.right-info:hover {
  cursor: pointer;
}
</style>