<template>
    <div class="register-container">
      <div class="register-form">
        <h2>注册</h2>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="form.userName" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="loginPwd">
            <el-input v-model="form.loginPwd" type="password" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model.number="form.age" placeholder="请输入年龄"></el-input>
          </el-form-item>
          <el-form-item label="头像" prop="headPic">
            <el-input v-model="form.headPic" placeholder="请输入头像链接"></el-input>
          </el-form-item>
          <el-form-item>
             <el-button class="button2" @click="handleRegist">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <script src="../assets/js/Register.js">
</script>

  <style scoped>
  .register-container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    height: 100%;
    width: 100%;
    top: 0;
    left: 0;
    background: linear-gradient(
        
            135deg,
            hsl(170deg, 80%, 70%),
            hsl(190deg, 80%, 70%),
            hsl(250deg, 80%, 70%),
            hsl(320deg, 80%, 70%),
            hsl(320deg, 80%, 70%),
            hsl(250deg, 80%, 70%),
            hsl(190deg, 80%, 70%),
            hsl(190deg, 80%, 70%),
            hsl(170deg, 80%, 70%)
    );
    background-size: 600%;
    animation: myanimation 15s linear infinite;
  }
  @keyframes myanimation {
    0% {
        background-position: 0 0;
    }
    100% {
        background-position: 100% 100%;
    }
}

  .register-form {
    width: 400px;
    padding: 20px;
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0,0,0,0.1);
  }
  </style>
  