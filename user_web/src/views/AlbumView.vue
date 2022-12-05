<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <!--album_info传参-->
        <!-- <AlbumInfo :album_info="album_info"></AlbumInfo> -->
        <AlbumInfo></AlbumInfo>
      </div>
      <div class="col-9">
        <div class="card">
          <h5 class="card-header">当前位置: 相册1</h5>
          <div class="card-body">
            <table class="album-table">
              <tr>
               <!--  <td>
                  <PhotoItem>照片1</PhotoItem>
                </td>
                <td>
                  <PhotoItem>照片2</PhotoItem>
                </td>
                <td>
                  <PhotoItem>照片3</PhotoItem>
                </td> -->
              </tr>
            </table>
            <div class="btn-group">
              <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                aria-expanded="false">
                相册管理
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#addPhoto">添加照片</a></li>
                <li><a class="dropdown-item" href="#">删除照片</a></li>
              </ul>
            </div>
          </div>
        </div>
        <!-- comments传参 -->
        <!-- <CommentTable :comments="comments"></CommentTable> -->
        <CommentTable></CommentTable>
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
            <input class="form-control" type="file" id="formFileMultiple" multiple>
          </div>
          <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">相册简介</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>
    
<script>

import AlbumInfo from '@/components/AlbumInfo';
import CommentTable from '@/components/CommentTable'
import router from '@/router/index';
import { ref } from 'vue'
//import {reactive} from 'vue'
import $ from 'jquery'
//import PhotoItem from '@/components/PhotoItem';

export default {
  name: 'AlbumView',
  components: {
    AlbumInfo,
    CommentTable,
    //PhotoItem
  },
  setup(){
    const album_info = ref([]);
    const photos = ref([]);
    const comments = ref([]);
    const jwt_token = localStorage.getItem("jwt_token");
/*     const new_photo = reactive({
            photo_id: 1, 
        }); */

    const get_index= () => {
          console.log(router.currentRoute.value.path);
        }
    get_index();

    //获取当前相册下的照片列表
    const refresh_photos = () => {
        $.ajax({
          url: "http://127.0.0.1:3000/user/photo/getList/",
          type: "post",
          headers:{
            Authorization: "Bearer " + jwt_token, 
          },
          /*
          data: {
            albumId: albumId;
          },
          */
          success(resp) {
            console.log(resp);
            photos.value = resp;
          },
          error(resp) {
            console.log(resp);
          }      
        });
      }
    //refresh_photos();

    //获取相册信息
    const get_AlbumInfo = () => {
        $.ajax({
          url: "http://127.0.0.1:3000/user/photo/getList/",
          type: "post",
          headers:{
            Authorization: "Bearer " + jwt_token, 
          },
          /*
          data: {
            albumId: albumId;
          },
          */
          success(resp) {
            console.log(resp);
            comments.value = resp;
          },
          error(resp) {
            console.log(resp);
          }      
        });
      }
    
    //获取评论信息
    const get_comments = () => {
        $.ajax({
          url: "http://127.0.0.1:3000/user/photo/getList/",
          type: "post",
          headers:{
            Authorization: "Bearer " + jwt_token, 
          },
          /*
          data: {
            albumId: albumId;
          },
          */
          success(resp) {
            console.log(resp);
            album_info.value = resp;
          },
          error(resp) {
            console.log(resp);
          }      
        });
      }
    const add_photo = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/user/photo/getList/",
        type: "post",
        headers:{
          Authorization: "Bearer " + jwt_token, 
        },
        /*
        data: {
          albumId: albumId;
        },
        */
        success(resp) {
          console.log(resp);
          photos.value = resp;
        },
        error(resp) {
          console.log(resp);
        }      
      });
    }

    return {
      get_index,
      refresh_photos,
      get_AlbumInfo,
      get_comments,
      add_photo,
    }
  }
}

</script>
  
<style scoped>
.card {
  margin-top: 20px;
}
</style>