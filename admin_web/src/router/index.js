import { createRouter, createWebHistory } from 'vue-router'

import AdminHome from '../views/AdminHome'
import NotFound from '../views/NotFound'
import ExaminePhoto from '../views/ExaminePhoto'
import ExamineMoment from '../views/ExamineMoment'
import HandleMoment from '../views/HandleMoment'
import HandleComment from '../views/HandleComment'
import LoginView from '../views/LoginView'
import RegisterView from '../views/RegisterView'

const routes = [
  {
    path: "/",
    name: "home",
    component: AdminHome
  },
  {
    path: "/examine/photo/",
    name: "examinephoto",
    component: ExaminePhoto
  },
  {
    path: "/examine/moment/",
    name: "examinemoment",
    component: ExamineMoment
  },
  {
    path: "/handle/comment/",
    name: "handlecomment",
    component: HandleComment
  },
  {
    path: "/handle/moment/",
    name: "handlemoment",
    component: HandleMoment
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound
  },
  {
    path: "/admin/account/login/",
    name: "login",
    component: LoginView
  },
  {
    path: "/admin/account/register/",
    name: "register",
    component: RegisterView
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

export default router
