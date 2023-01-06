<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <!--album_info传参-->
        <AlbumInfo :album_info="album_info"></AlbumInfo>
        <!-- <AlbumInfo></AlbumInfo> -->
      </div>
      <div class="col-9">
        <div class="card">
          <h5 class="card-header">当前位置: {{album_info.name}}</h5>
          <div class="card-body">
            <div class="album-table">
              <ul style="display: flex;flex-wrap: wrap;">
                <li style="	padding: 5px;list-style: none;margin-right: 20px;" v-for="photo in photos" :key= "photo.photoId">
                  <PhotoItem :photo="photo" :album_id="album_id"></PhotoItem>
                </li>
              </ul>
            </div>
            <div class="btn-group">
              <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                aria-expanded="false">
                相册管理
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#addPhoto">添加照片</a></li>
                <!-- <li><a class="dropdown-item" href="#">删除照片</a></li> -->
              </ul>
            </div>
          </div>
        </div>
        <!-- comments传参 -->
        <!-- <CommentTable :comments="comments"></CommentTable> -->
        <CommentTable :comments="comments" :album_id="album_id" ></CommentTable>
      </div>
    </div>
  </div>

  <!-- 添加照片 -->
  <div class="modal fade" id="addPhoto" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">管理相册</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <div class="mb-3">
            <label for="formFileMultiple" id="add-photo" class="form-label">添加照片</label>
            <!--选择照片-->
            <div v-for="photo in photo_list" :key= "photo.photoId">
              <div class="card">
                <div class="card-body">
                  <div class="row">
                    <div class="col-6">
                        <img class="img" style="width: 100px; height: 100px" :src="require('D:/GitHub/AlbumSystem/images/' + photo.userId + '/' + photo.photoId + '.jpg')" alt="">
                    </div>
                    <div class="col-3">
                      <div class="photo-name">{{photo.name}}</div>
                    </div>
                    <div class="col-3">
                      <button type="button" class="btn btn-primary" @click="add_photo(photo.photoId)">添加</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">相册简介</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div> -->
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" data-bs-dismiss="modal">确认</button>
        </div>
      </div>
    </div>
  </div>
</template>
    
<script>

import AlbumInfo from '@/components/AlbumInfo';
import CommentTable from '@/components/CommentTable'
//import router from '@/router/index';
import { ref } from 'vue'
//import {reactive} from 'vue'
import $ from 'jquery'
import PhotoItem from '@/components/PhotoItem';

export default {
  name: 'AlbumView',
  components: {
    AlbumInfo,
    CommentTable,
    PhotoItem,
  },
  //props: ['album_id'],
  props: {
    album_id: {
          type: String,
          required: true,
      },
  },
  setup(props){
    let album_info = ref([]);
    let photos = ref([]);
    let photo_list = ref([]);
    let comments = ref([]);

    const jwt_token = localStorage.getItem("jwt_token");
/*     const new_photo = reactive({
            photo_id: 1, 
        }); */

   /*  const get_index= () => {
          console.log(router.currentRoute.value.path);
          console.log(props.album_id);
        }
    get_index(); */

    //获取当前相册下的照片列表
    const refresh_photos = () => {
        $.ajax({
          url: "http://127.0.0.1:3000/user/album/getPhotoList/",
          type: "post",
          headers:{
            Authorization: "Bearer " + jwt_token, 
          },
          data: {
            album_id: props.album_id,
          },
          success(resp) {
            console.log(resp);
            photos.value = resp;
          },
          error(resp) {
            console.log(resp);
          }      
        });
      }
    refresh_photos();

    const get_photolist = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/photo/getList/",
            type: "post",
            headers:{
              Authorization: "Bearer " + jwt_token, 
            },
            success(resp) {
              console.log(resp);
              photo_list.value = resp;
              //methods.forceRerender();
              //photo_name = ' ';
            },
            error(resp) {
              console.log(resp);
            }      
          });
        }
    get_photolist();

    //获取相册信息
    const get_AlbumInfo = () => {
        $.ajax({
          url: "http://127.0.0.1:3000/user/album/getAlbum/",
          type: "post",
          headers:{
            Authorization: "Bearer " + jwt_token, 
          },
          data: {
            album_id: props.album_id,
          },
          success(resp) {
            console.log(resp);
            album_info.value = resp;
          },
          error(resp) {
            console.log(resp);
          }      
        });
      }
    get_AlbumInfo();

    //更新相册信息
    const update_AlbumInfo = (album) => {
      $.ajax({
        url: "http://127.0.0.1:3000/user/album/getAlbum/",
        type: "post",
        headers:{
          Authorization: "Bearer " + jwt_token, 
        },
        data: {
          album_id: props.album_id,
          authority: album.authority,
          name: album.name,
          description: album.description,
        },
        success(resp) {
          console.log(resp);
          //album_info.value = resp;
          get_AlbumInfo();
        },
        error(resp) {
          console.log(resp);
        }      
      });
    }
    
    //获取评论信息
    const get_comments = () => {
        $.ajax({
          url: "http://127.0.0.1:3000/user/comment/getListAlbum/",
          type: "post",
          headers:{
            Authorization: "Bearer " + jwt_token, 
          },
          data: {
            album_id: props.album_id,
          },
          success(resp) {
            console.log(resp);
            comments.value = resp;
          },
          error(resp) {
            console.log(resp);
          }      
        });
      }
    get_comments();

    const add_photo = (photo_id) => {
      $.ajax({
        url: "http://127.0.0.1:3000/user/album/addPhoto/",
        type: "post",
        headers:{
          Authorization: "Bearer " + jwt_token, 
        },
        
        data: {
          photo_id: photo_id,
          album_id: props.album_id,
        },
        
        success(resp) {
          console.log(resp);
          refresh_photos();

        },
        error(resp) {
          console.log(resp);
        }      
      });
    }

    return {
      //get_index,
      refresh_photos,
      get_AlbumInfo,
      get_comments,
      add_photo,
      update_AlbumInfo,
      get_photolist,
      comments,
      photo_list,
      photos,
      album_info,
    }
  }
}

</script>
  
<style scoped>
.card {
  margin-top: 20px;
}
</style>