
  import { Message } from 'element-ui';
  import axios from 'axios';
  export default {
    name: "Register",
    data() {
      return {
        form: {
          userName: '',
          loginPwd: '',
          name: '',
          age: null,
          headPic: ''
        },
        rules: {
          userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
          loginPwd: [{ required: true, message: '请输入密码', trigger: 'blur' }],
          name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
          age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
          headPic: [{ required: true, message: '请输入头像链接', trigger: 'blur' }]
        }
      };
    },
    methods: {
        async handleRegist() {
          try {
            const response = await axios.post('/user/register', this.form);
            const res = response.data;
            if (res.code === '0') {
              this.$message({
                showClose: true,
                message: '注册成功',
                type: 'success'
              });
              // 注册成功后跳转到登录页面
              this.$router.push({ path: "/login" });
            } else {
              this.$message({
                showClose: true,
                message: res.msg,
                type: 'error'
              });
            }
          } catch (error) {
            console.error('Error occurred during registration:', error);
            this.$message.error('注册失败：' + error.message);
          }
        }
      }
  };