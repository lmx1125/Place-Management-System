import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import { Button, Select,Message,Tabs } from 'element-ui';
import axios from 'axios';
import VueRouter from 'vue-router'; // 导入 Vue Router
import router from './router'; // 导入路由配置文件
Vue.use(VueRouter)
axios.defaults.baseURL="http://localhost:8088"
Vue.prototype.$http=axios
Vue.config.productionTip = false;
Vue.component(Button.name, Button);
Vue.component(Select.name, Select);
Vue.component(Message.name, Message);
Vue.component(Tabs.name, Tabs);
Vue.use(ElementUI);
new Vue({
  render: h => h(App),
  router:router
}).$mount('#app')
