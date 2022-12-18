<template>
  <div class="container">
    <div class="row">
      <div class="col">
      </div>
      <div class="col-12">
        <div class="card">
          <h5 class="card-header">当前位置：</h5>
            <div class="card-body">
              <div class="album-table" :key="componentKey">
                <ul style="display: flex;flex-wrap: wrap;">
                <li style="	padding: 5px;list-style: none;margin-right: 15px;" v-for="photo in photos" :key= "photo.photoId">
                  <img class="file-img" :src="require('D:/GitHub/AlbumSystem/images/' + photo.userId + '/' + photo.photoId + '.jpg')" />
                  <div class="file-name">{{photo.name}}</div>
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-danger dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                      操作
                    </button>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="#" data-bs-toggle="modal" :data-bs-target="('#showPhoto' + photo.photoId)">显示信息</a></li>
                      <!-- <li><a class="dropdown-item" href="#">下载照片</a></li> -->
                      <!-- <li><a class="dropdown-item" href="#" data-bs-toggle="modal" :data-bs-target="('#editPhoto' + photo.photoId)">编辑照片</a></li>
                      <li><a class="dropdown-item" href="#" @click="delete_photo(photo)">删除照片</a></li> -->
                    </ul>
                  </div>

                <!--显示照片-->
                <div class="modal fade" :id="('showPhoto' + photo.photoId)" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">照片信息</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                        <div class="row">
                          <div class="col-7">
                            <img class="file-img-2" :src="require('D:/GitHub/AlbumSystem/images/' + photo.userId + '/' + photo.photoId + '.jpg')" />
                          </div>
                          <div class="col-5">
                            <div class="card">
                              <div class="card-body">
                                <div class="photo-name"><b>照片名：</b>{{photo.name}}</div>
                                <div class="createtime"><b>创建时间：</b>{{photo.time}}</div>
                                <div class="authority"><b>权限：</b>{{showAuthority(photo.authority)}}</div>
                                <div class="status"><b>审核状态：</b>{{showStatus(photo.status)}}</div>
                                <!-- <div class="description"><b>简介：</b>....</div> -->
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                  </li>
                </ul>
              </div>
            </div>
        </div>
      </div>
    </div>
    <FooterView></FooterView>
  </div>

</template>
  
  <script>
  //import AlbumInfo from '@/components/AlbumInfo';
  //import AlbumItem from '@/components/AlbumItem'
  import FooterView from '@/components/Footer'
  //import PhotoItem from '@/components/PhotoItem';
  import { ref } from 'vue'
  import $ from 'jquery'
  import { useRoute } from 'vue-router'
  //import { useStore } from 'vuex'
/*   import { Modal } from 'bootstrap/dist/js/bootstrap'
  import {reactive} from 'vue' */
  //import Vue from 'vue'
  //Vue.forceUpdate()
  //import Vue from 'vue'
 
  export default {
    name: 'MyPhotoView',
    components: {
      //ContentBase,
      //HomeBase,
      //AlbumInfo,
      //PhotoItem,
      FooterView,
    },
    data(){
      return {
        componentKey: 0,
        textarea: '',
        token: this.jwt_token,
      }
    },
    methods: {
    },

    setup() {
        //获取photo传参
        const route = useRoute();
        //const store = useStore();
        let photos = route.query.photos;
        let photo_info = ref([]);
        //let photo_name = ref[' '];
        const jwt_token = localStorage.getItem("jwt_token");

        const refresh_photos = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/photo/getList/",
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
        refresh_photos();

        const get_photo_info = (photoId) => {
                $.ajax({
                    url: "http://127.0.0.1:3000/user/photo/getPhoto/",
                    type: "post",
                    headers: {
                        Authorization: "Bearer " + jwt_token,
                    },
                    data: {
                        photo_id: photoId,
                    },
                    success(resp) {
                        /*
                        if (resp.error_message !== "success") {
                            alert(resp.error_message)
                        }*/
                        //console.log(photo.photoId + ":" + photo.address + "info:" + resp.name + ":" + resp.address);
                        console.log(resp.name + resp.photoId);
                        photo_info.value = resp;
                    },
                    error() {
                    }
                })
            }
        
        const showAuthority = (authority) => {
            if (String(authority) === "0")
                return "所有人可见";
            else if (String(authority) === "1")
                return "仅好友可见";
            else if (String(authority) === "2")
                return "仅自己可见";
            else
                return "状态出错";
        }
        const showStatus = (status) => {
            if (String(status) === "0")
                return "未审核";
            else if (String(status) === "1")
                return "审核通过";
            else if (String(status) === "2")
                return "审核失败";
            else
                return "状态出错";
        }

        return {
          get_photo_info,
          showAuthority,
          showStatus,
          photos,
          photo_info,
        }
    }
  }

  </script>

  <style scoped>

  .card {
    margin-top: 20px;
    background-color: rgba(255,255,255,0.5);
  }
  
  .album-table {
    margin-bottom: 150px;
  }

  .file-img {
  width: 120px;
  height: 120px;
  margin-right: 60px;
  float: left;
}
.file-img-2{
  width: 400px;
  height: 400px;
}
.file-name {
  margin-top: 5px;
  margin-left: 35px;
  /* height: 44px;  */
  margin-bottom: 5px;
  line-height: 22px;
  font-size: 15px;
}
.btn-group {
  margin-left: 30px;
}
  </style>