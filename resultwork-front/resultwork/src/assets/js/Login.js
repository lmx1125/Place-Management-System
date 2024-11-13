
import { Message } from 'element-ui';
import axios from 'axios';
export default {
  name: "Login",
  data() {
    return {
      form: {
        userName: '',
        loginPwd: '',
      },
      rules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        loginPwd: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('/user/login', this.form);
        const responseData = response.data;

        if (responseData.code === '0') {
          Message.success('登录成功');
          window.sessionStorage.setItem('User', JSON.stringify(this.form));
          window.sessionStorage.setItem('token', responseData.token); // 存储令牌
          console.log(responseData.token)
          console.log('Token before navigation:', window.sessionStorage.getItem('token'));
          this.$router.push({ path: '/home' });
        } else {
          Message.error(responseData.msg);
        }
      } catch (error) {
        console.error('Error occurred during login:', error);
        Message.error('登录失败：' + error.message);
      }
    },
    handleRegister() {
      this.$router.push({ path: '/register' });
    }
  
  }
};
