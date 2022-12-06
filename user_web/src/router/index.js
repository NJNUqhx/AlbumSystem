import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView'
import NotFoundView from '../views/NotFoundView'
import UserListView from '../views/UserListView'
import UserProfileView from '../views/UserProfileView'
import RegisterView from '../views/RegisterView'
import MyAlbumView from '../views/MyAlbumView'
import MyPhotoView from '../views/MyPhotoView'
import AlbumView from '../views/AlbumView'
import MomentView from '../views/MomentView'
import UploadPhoto from '../views/UploadPhoto'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/user/photo/upload/',
    name: 'uploadphoto',
    component: UploadPhoto
  },
  {
    path: '/404/',
    name: '404',
    component: NotFoundView
  },
  {
    path: '/userlist/',
    name: 'userlist',
    component: UserListView
  },
  {
    path: '/userprofile/:userid',
    name: 'userprofile',
    component: UserProfileView
  },
  {
    path: '/user/account/login/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/user/account/register/',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/:catchAll(.*)/',
    redirect: "/404",
  },
  {
    path: '/myalbum/',
    name: 'myalbum',
    component: MyAlbumView
  },
  {
    path: '/myphoto/',
    name: 'myphoto',
    component: MyPhotoView
  },
  {
    path: '/myalbum/:album_id',
    name: 'myalbum_1',
    component: AlbumView,
    props: true
  },
  {
    path: '/moments/',
    name: 'moments',
    component: MomentView
  },
  {
    path: '/about/',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
