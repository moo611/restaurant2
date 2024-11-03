// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import Login from "../views/Login.vue";
import Home from "../views/home/Home.vue";
import Order from "../views/order/index.vue";
import Me from "../views/me/index.vue";
import DashBoard from "../views/DashBoard.vue";
import Message from "../views/message/index.vue";
import SignUp from "../views/SignUp.vue";
import Edit from "../views/me/edit.vue";
import { getToken } from "../utils/auth"; // 访问缓存的用户名

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "SignUp",
    component: SignUp,
  },
  {
    path: "/dashboard",
    name: "DashBoard",
    component: DashBoard,
    meta: { requiresAuth: true },
    children: [
      {
        path: "home",
        name: "Home",
        component: Home,
        meta: { requiresAuth: true },
      },
      {
        path: "order",
        name: "Order",
        component: Order,
        meta: { requiresAuth: true },
      },
      {
        path: "me",
        name: "Me",
        component: Me,
        meta: { requiresAuth: true },
      },
    ],
  },
  {
    path: "/edit",
    name: "Edit",
    component: Edit,
    meta: { requiresAuth: true },
  },
  {
    path: "/message",
    name: "Message",
    component: Message,
    meta: { requiresAuth: true },
  },
  {
    path: "/",
    redirect: "/dashboard/home",
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 导航守卫
router.beforeEach((to, from, next) => {
  const isAuthenticated = !!getToken(); // 根据缓存判断是否登录

  if (to.meta.requiresAuth && !isAuthenticated) {
    next("/login"); // 不带参数的重定向
  } else {
    next(); // 允许访问
  }
});

export default router;
