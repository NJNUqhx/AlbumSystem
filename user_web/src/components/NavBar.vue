<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <router-link class="navbar-brand" :to="{name: 'home'}">AlbumSystem</router-link>
    
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
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" @click="(getAllApplicaitons(),refresh_photos(),get_comment_result(),get_moment_result())">
            消息
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#friend-request">好友申请</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#moment-report">动态举报</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#comment-report">评论举报</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#photo-list">审核信息</a></li>
          </ul>
        </li>

        <li class="nav-item dropdown" >
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ $store.state.user.nickname }}
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li>
                <router-link class="dropdown-item" :to="{name: 'mymoments'}">我的动态</router-link>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" @click="logout()">退出</a></li>
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
                  <div class="mb-3" v-for="apl in applications" :key="apl.id">
                    <div class="card">
                      <dic class="card-body">
                        <div class="row">
                            <div class="col">
                                <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/142492_lg_7c84a89707.jpg" alt="">
                            </div>
                            <div class="col-3">
                              <div class="friend-request-name">{{apl.applicantId}}</div>
                            </div>
                            <div class="col-5">
                              <div class="friend-request">{{apl.message}}</div>
                            </div>
                            <!--暂时无法判断自己的userId-->
                            <div class="col-3" v-if="(apl.applicantId == $store.state.user.userId)">
                              <div class="request-result">等待对方处理</div>
                            </div>
                            <div class="col-3" v-else-if="(apl.status == 0)">
                              <div class="btn btn-dark" @click="handleApplicaitons(apl.id, 1)">同意</div>
                              <div class="btn btn-light" @click="handleApplicaitons(apl.id, 0)">拒绝</div>
                            </div>
                            <div class="col-3" v-else-if="(apl.status == 1)">
                              <div class="request-result">已通过</div>
                            </div>
                            <div class="col-3" v-else-if="(apl.status == 2)">
                              <div class="request-result">拒绝</div>
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
                <h5 class="modal-title" id="exampleModalLabel">举报信息</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">申请信息</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">确认</button>
            </div>
            </div>
        </div>
      </div>

       <!--照片审核信息-->
       <div class="modal fade" id="photo-list" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
          <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">审核信息</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3" v-for="photo in photos" :key="photo.photoId">
              <div class="card">
                <dic class="card-body">
                  <div class="row">
                    <div class="col-3">
                      <img class="img-report" style="width: 120px; height: 120px" :src="require('D:/GitHub/AlbumSystem/images/' + photo.userId + '/' + photo.photoId + '.jpg')" alt="">
                    </div>
                    <div class="col-3">
                      <div class="friend-request">{{photo.time}}</div>
                    </div>
                    <div class="col-2">
                      <div class="photo-result">{{photo.result}}</div>
                    </div>
                    <div class="col-3">
                      <div class="photo-result">{{photo.advice}}</div>
                    </div>
                        <!-- <div class="col-3" v-if="(photo.status == 0)">
                          <div class="photo-result">未审核</div>
                        </div>
                        <div class="col-3" v-else-if="(photo.status == 1)">
                          <div class="photo-result">审核通过</div>
                        </div>
                        <div class="col-3" v-else-if="(photo.status == 2)">
                          <div class="photo-result">审核失败</div>
                        </div> -->
                    </div>
                  </dic>
                </div>
              </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">确认</button>
            </div>
          </div>
        </div>
      </div>

      <!--动态举报-->
      <div class="modal fade" id="moment-report" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
          <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">动态举报信息</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3" v-for="moment in moment_results" :key="moment.id">
              <div class="card">
                <dic class="card-body">
                  <div class="row">
                    <div class="col-3">
                      <div class="friend-request">动态{{moment.momentId}}</div>
                    </div>
                    <div class="col-3">
                      <div class="friend-request">{{moment.result}}</div>
                    </div>
                    <div class="col-3">
                      <div class="photo-result">{{moment.advice}}</div>
                    </div>
                    <div class="col-2">
                      <div class="photo-result">{{moment.time}}</div>
                    </div>
                    <!-- <div class="col-2">
                      <div class="photo-result">由管理员{{moment.adminId}}审核</div>
                    </div> -->
                    </div>
                  </dic>
                </div>
              </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">确认</button>
            </div>
          </div>
        </div>
      </div>

      <!--评论举报  -->
      <div class="modal fade" id="comment-report" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
          <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">评论举报信息</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3" v-for="comment in comment_results" :key="comment.id">
              <div class="card">
                <dic class="card-body">
                  <div class="row">
                    <div class="col-3">
                      <div class="friend-request">评论{{comment.id}}</div>
                    </div>
                    <div class="col-3">
                      <div class="friend-request">{{comment.result}}</div>
                    </div>
                    <div class="col-2">
                      <div class="photo-result">{{comment.advice}}</div>
                    </div>
                    <div class="col-3">
                      <div class="photo-result">{{comment.time}}</div>
                    </div>
                    </div>
                  </dic>
                </div>
              </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">确认</button>
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
import $ from 'jquery'
import { ref } from 'vue'
//import { Modal } from 'bootstrap/dist/js/bootstrap'

  export default {
    name: "NavBar",
    /* props: {
      applications:{
        required: true,
      }
    }, */
    setup() {
      const store = useStore();
      const jwt_token = localStorage.getItem("jwt_token");
      let applications = ref([]);
      let photos = ref([]);

      const logout = () => {
        //console.log("navbar:" + jwt_token);
        store.dispatch('logout');
      };

      console.log('id:'+store.state.user.is_login);
      
      const getAllApplicaitons = () =>{
            $.ajax({
                url: "http://127.0.0.1:3000/user/friend/getApplicationList/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    console.log(resp);
                    applications.value = resp;
                },
                error(resp) {
                  console.log(resp);
                }
            })
        }
      //getAllApplicaitons();
      const handleApplicaitons = (id, status) =>{
            $.ajax({
                url: "http://127.0.0.1:3000/user/friend/handleApplication/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                  id: id,
                  status: status,
                },
                success(resp) {
                    console.log(resp);
                    getAllApplicaitons();
                },
                error(resp) {
                  console.log(resp);
                }
            })
        }
        const refresh_photos = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/photo/getExamineResult/",
            type: "post",
            headers:{
              Authorization: "Bearer " + jwt_token, 
            },
            success(resp) {
              console.log(resp);
              photos.value = resp;
              //methods.forceRerender();
              //photo_name = ' ';
            },
            error(resp) {
              console.log(resp);
            }      
          });
        }

        //获取举报动态结果
        let moment_results = ref([]);
        const get_moment_result = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/moment/getExamineResult/",
            type: "post",
            headers:{
              Authorization: "Bearer " + jwt_token, 
            },
            success(resp) {
              console.log(resp);
              moment_results.value = resp;

            },
            error(resp) {
              console.log(resp);
            }      
          });
        }

        //获取举报评论结果
        let comment_results = ref([]);
        const get_comment_result = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/comment/handleResult/",
            type: "post",
            headers:{
              Authorization: "Bearer " + jwt_token, 
            },
            success(resp) {
              console.log(resp);
              comment_results.value = resp;
            },
            error(resp) {
              console.log(resp);
            }      
          });
        }

      return {
        logout,
        getAllApplicaitons,
        handleApplicaitons,
        refresh_photos,
        get_moment_result,
        get_comment_result,
        comment_results,
        moment_results,
        applications,
        photos,
      }
    }
}
</script>

<style scoped>

.navbar-brand {
  color: white;
}

.img-fluid {
  border-radius: 50%;
  margin-left: 5px;
}

/* img {
  border-radius: 50%;
  margin-left: 5px;
} */


.btn-dark {
  margin-right: 10px;
}

.friend-request-name{
  margin-top:8px;
}

.friend-request {
  margin-top:8px;
}

.photo-result{
  margin-top: 8px;
}
</style>