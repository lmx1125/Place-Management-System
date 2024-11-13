import Vue from 'vue';
import Router from 'vue-router';
import Login from '../components/Login.vue';
import Register from '../components/Register.vue'
import Header from '../components/Header.vue'
import Aside from '../components/Aside.vue'
import Home from '../components/Home.vue'
import PlaceInfo from '../components/PlaceInfo.vue'
import StudentInfo from '../components/StudentInfo.vue'
import SelftInfo from '../components/SelfInfo.vue'
import SelfInfo from '../components/SelfInfo.vue';
import upload from '../components/upload.vue';
Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/upload',
      name: 'upload',
      component: upload
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/selfInfo',
      name: 'SelfInfo',
      component: SelfInfo
    },
    {
      path:'/home',
      name:'Home',
      component: Home,
      children: [
        {
          path: '/header',
          name: 'Header',
          component: Header
        },
        {
          path: '/aside',
          name: 'Aside',
          component: Aside
        },
        {
          path:'/studentInfo',
          name:'StudentInfo',
          component:StudentInfo
        },
        {
          path:'/placeInfo',
          name:'PlaceInfo',
          component:PlaceInfo
        }
      ]
    }
  ]
});

// router.beforeEach((to, from, next) => {
//   const user = JSON.parse(window.sessionStorage.getItem('User'));
//   const token = window.sessionStorage.getItem('token');
//    if (to.path === '/login' || to.path === '/register') {
//     next(); 
//   } else if (!user || !token) {
//     next('/login'); 
//   } else {
//     next(); 
//   }
// });

export default router;
