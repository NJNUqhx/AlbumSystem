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
import store from '../store/index'
import UserMomentView from '../views/UserMomentView'
import UserPhotoView from '../views/UserPhotoView'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/user/photo/upload/',
    name: 'uploadphoto',
    component: UploadPhoto,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/404/',
    name: '404',
    component: NotFoundView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/userlist/',
    name: 'userlist',
    component: UserListView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/userprofile/:userid',
    name: 'userprofile',
    component: UserProfileView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/user/account/login/',
    name: 'login',
    component: LoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/user/account/register/',
    name: 'register',
    component: RegisterView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/:catchAll(.*)/',
    redirect: "/404",
  },
  {
    path: '/myalbum/',
    name: 'myalbum',
    component: MyAlbumView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/myphoto/',
    name: 'myphoto',
    component: MyPhotoView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/myalbum/:album_id',
    name: 'myalbum_1',
    component: AlbumView,
    props: true,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/moments/',
    name: 'moments',
    component: MomentView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/user/photos/',
    name: 'user_photos',
    component: UserPhotoView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/user/moments/',
    name: 'user_moments',
    component: UserMomentView,
    meta: {
      requestAuth: true,
    }
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

/* router.beforeEach((to, from, next) => {
  const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
    store.commit("updateToken", jwt_token);
    store.dispatch("getinfo", {
      success() {
      },
      error() {
        alert("token无效,请重新登录!");
        router.push({ name: 'login' });
      }
    })
  } 
  if (!jwt_token && !store.state.user.is_login) {
    next({ name: "login" });
  } else {
    next();
  }
}) */
router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "login"});
  } else {
    next();
  }
})

export default router
