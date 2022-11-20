<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <router-link class="navbar-brand" :to="{name: 'home'}">Album System</router-link>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'home'}">首页</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'userlist'}">好友列表</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'myalbum'}">我的相册</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'myphoto'}">我的照片</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'moments'}">空间动态</router-link>
        </li>
      </ul>

      <ul class="navbar-nav" v-if="!$store.state.user.is_login">
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'login'}">登录</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'register'}">注册</router-link>
        </li>
      </ul>

      <ul class="navbar-nav" v-else>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            消息
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#friend-request">好友申请</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#report-list">举报</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#report-list">审核信息</a></li>
          </ul>
        </li>

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ $store.state.user.username }}
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li>
                <router-link class="dropdown-item" :to="{name: 'userprofile', params: {userId: $store.state.user.id}}">我的动态</router-link>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
          </ul>
        </li>

      </ul>
      <!--friend-request-->
      <div class="modal fade" id="friend-request" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">好友申请</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <div class="mb-3">
                    <div class="card">
                      <dic class="card-body">
                        <div class="row">
                            <div class="col">
                                <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/142492_lg_7c84a89707.jpg" alt="">
                            </div>
                            <div class="col-3">
                              <div class="friend-request-name">Zilong Xue</div>
                            </div>
                            <div class="col-5">
                              <div class="friend-request">交个朋友吧~</div>
                            </div>
                            <div class="col-3">
                              <div class="btn btn-dark">同意</div>
                              <div class="btn btn-light">拒绝</div>
                            </div>
                        </div>
                      </dic>
                    </div>
                  </div>
              </div>
              
            </div>
        </div>
      </div>

      <!--report-list-->
      <div class="modal fade" id="report-list" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">举报</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">申请</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">确认</button>
            </div>
            </div>
        </div>
      </div>

    </div>
  </div>
</nav>
</template>

<script>
import {useStore} from 'vuex';

  export default {
    name: "NavBar",
    setup() {
      const store = useStore();
      const logout = () => {
        store.commit('logout');
      };

      return {
        logout,
      }
    }
}
</script>

<style scoped>

.navbar-brand {
  color: white;
}

img {
  border-radius: 50%;
  margin-left: 5px;
}

.btn-dark {
  margin-right: 10px;
}

.friend-request-name{
  margin-top:8px;
}

.friend-request {
  margin-top:8px;
}
</style>