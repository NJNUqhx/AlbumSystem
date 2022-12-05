<template>
  <div class="container">
    <div class="row">
      <div class="col">
      </div>
      <div class="col-12">
        <div class="card">
          <h5 class="card-header">当前位置：</h5>
            <div class="card-body">
              <table class="album-table">
                <!--列举相册-->
                <!--
                <tr v-for="album in albums" :key= "album.albumId">
                <td><AlbumItem :album="album"></AlbumItem></td>
                </tr>
                -->
                <tr>
                <td><AlbumItem>相册1</AlbumItem></td>
                <td><AlbumItem>相册2</AlbumItem></td>
                <td><AlbumItem>相册3</AlbumItem></td>
                </tr>
            </table>
              <a href="#" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#createAlbum">新建相册</a>
            </div>
        </div>
      </div>
    </div>
    <FooterView></FooterView>
  </div>

<!-- 新建相册 -->
  <div class="modal fade" id="createAlbum" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">新建相册</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">相册名</label>
            <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="Album name">
          </div>
          
          <label for="basic-url" class="form-label">相册权限</label>
          <select class="form-select" aria-label="Default select example">
            <option selected>选择相册权限</option>
            <option value="1">公开</option>
            <option value="2">私有</option>
          </select>

        <!--   <div class="mb-3">
            <label for="formFileMultiple" id="add-photo" class="form-label">添加照片</label>
            <input class="form-control" type="file" id="formFileMultiple" multiple>
          </div> -->
          <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">相册简介</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary">创建</button>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
  import AlbumItem from '@/components/AlbumItem'
  import FooterView from '@/components/Footer'
  import { ref } from 'vue'
  import $ from 'jquery'
  import router from '@/router/index';

  //import { useStore } from 'vuex'

  export default {
    name: 'MyAlbumView',
    components: {
      AlbumItem,
      FooterView,
    },

    setup(){
      //const store = useStore();
      let albums= ref([]);
      //let new_album = ref([]);

      const jwt_token = localStorage.getItem("jwt_token");

      //获取相册列表
      const refresh_albums = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/photo/getList/",
            type: "post",
            headers:{
              Authorization: "Bearer " + jwt_token, 
            },
            success(resp) {
              console.log(resp);
              albums.value = resp;
            },
            error(resp) {
              console.log(resp);
            }      
          });
        }
      //refresh_albums();

      //添加相册
      const add_album = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/photo/getList/",
            type: "post",
            headers:{
              Authorization: "Bearer " + jwt_token,
            },
            /* data:{
              album_name: new_album.album_name,
              album_authority: new_album.authority,
              album_description: new_album.description,
            }, */
            success(resp) {
              console.log(resp);
              albums.value = resp;
            },
            error(resp) {
              console.log(resp);
            }      
          });
        }

      const get_index= () => {
          console.log(router.currentRoute.value.path);
        }
      get_index();


      return {
        refresh_albums,
        add_album,
        //get_index,

      }
    }

  }

  </script>

  <style scoped>
  .card {
    margin-top: 20px;
  }
  .album-table {
    margin-bottom: 150px;
  }
  #add-photo {
    margin-top: 10px;
  }
</style>