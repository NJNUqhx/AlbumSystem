import { createRouter, createWebHistory } from 'vue-router'

import AdminHome from '../views/AdminHome'
import NotFound from '../views/NotFound'
import ExaminePhoto from '../views/ExaminePhoto'
import ExamineMoment from '../views/ExamineMoment'
import HandleMoment from '../views/HandleMoment'
import HandleComment from '../views/HandleComment'
import LoginView from '../views/LoginView'
import RegisterView from '../views/RegisterView'
import store from '../store/index'

const routes = [
  {
    path: "/",
    name: "home",
    component: AdminHome,
    meta:{
      requestAuth: true,
    }
  },
  {
    path: "/examine/photo/",
    name: "examinephoto",
    component: ExaminePhoto,
    meta:{
      requestAuth: true,
    }
  },
  {
    path: "/examine/moment/",
    name: "examinemoment",
    component: ExamineMoment,
    meta:{
      requestAuth: true,
    }
  },
  {
    path: "/handle/comment/",
    name: "handlecomment",
    component: HandleComment,
    meta:{
      requestAuth: true,
    }
  },
  {
    path: "/handle/moment/",
    name: "handlemoment",
    component: HandleMoment,
    meta:{
      requestAuth: true,
    }
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
    meta:{
      requestAuth: false,
    }
  },
  {
    path: "/admin/account/login/",
    name: "login",
    component: LoginView,
    meta:{
      requestAuth: false,
    }
  },
  {
    path: "/admin/account/register/",
    name: "register",
    component: RegisterView,
    meta:{
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// router.beforeEach((to, from, next) =>{
//   if(to.meta.requestAuth && !store.state.admin.is_login){
//     next({name: "login"});
//   }else{
//     next();
//   }
// })
router.beforeEach((to, from, next) => {

  let flag = 1;
  const jwt_token = localStorage.getItem("jwt_token");

  if (jwt_token) {
    store.commit("updateToken", jwt_token);
    store.dispatch("getInfo", {
      success() {
      },
      error() {
        alert("token无效，请重新登录！");
        router.push({ name: 'login' });
      }
    })
  } else {
    flag = 2;
  }

  if (to.meta.requestAuth && !store.state.admin.is_login) {
    if (flag === 1) {
      next();
    } else {
      alert("请先进行登录！");
      next({name: "login"});
    }
  } else {
    next();
  }
})

export default router
