// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Login from '../views/Login.vue';
import SignUp from '../views/SignUp.vue';
import DashBoard from '../views/DashBoard.vue';
import Food from '../views/food/index.vue';
import User from "../views/user/index.vue"
import Message from '../views/message/index.vue'
import Order from '../views/order/index.vue'
import Statics from '../views/statics/index.vue'
import Pay from '../views/pay/index.vue'
import { getToken } from '../utils/auth'; // 访问缓存的用户名

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp,
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: DashBoard,
    meta: { requiresAuth: true },
    children:[
      {
        path: 'order',
        name: 'Order',
        component: Order,
        meta: { requiresAuth: true },
      },
      {
        path: 'statics',
        name: 'Statics',
        component: Statics,
        meta: { requiresAuth: true },
      },
      {
        path: 'user',
        name: 'User',
        component: User,
        meta: { requiresAuth: true },
      },
      {
        path: 'message',
        name: 'Message',
        component: Message,
        meta: { requiresAuth: true },
      },
      {
        path: 'food',
        name: 'Food',
        component: Food,
        meta: { requiresAuth: true },
      },
      {
        path: 'pay',
        name: 'Pay',
        component: Pay,
        meta: { requiresAuth: true },
      },

    ]
  },
  {
    path: '/',
    redirect: '/dashboard/message',
  },
  // {
  //   path:'/record',
  //   name:'Record',
  //   component: Record,
  //   meta:{requiresAuth: true}

  // }
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 导航守卫
router.beforeEach((to, from, next) => {
  const isAuthenticated = !!getToken(); // 根据缓存判断是否登录

  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/login'); // 未登录重定向到登录页
  } else {
    next(); // 允许访问
  }
});

export default router;
