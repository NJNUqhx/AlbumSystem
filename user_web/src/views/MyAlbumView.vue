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
                <!--列举相册-->
                <ul style="display: flex;flex-wrap: wrap;">
                  <li style="	padding: 5px;list-style: none;margin-right: 60px;" v-for="album in albums" :key= "album.albumId">
                    <AlbumItem :album="album"></AlbumItem>
                  </li>
                </ul>
              <!--   <tr>
                <td><AlbumItem>相册1</AlbumItem></td>
                <td><AlbumItem>相册2</AlbumItem></td>
                <td><AlbumItem>相册3</AlbumItem></td>
                </tr> -->
            </div>
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
            <input v-model="new_album.name" type="email" class="form-control" id="exampleFormControlInput1" placeholder="Album name">
          </div>
          
          <label for="basic-url" class="form-label">相册权限</label>
          <select v-model="new_album.authority" class="form-select" aria-label="Default select example">
            <option value="0">所有人可见</option>
            <option value="1">仅好友可见</option>
            <option value="2">仅自己可见</option>
          </select>

        <!--   <div class="mb-3">
            <label for="formFileMultiple" id="add-photo" class="form-label">添加照片</label>
            <input class="form-control" type="file" id="formFileMultiple" multiple>
          </div> -->
          <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">相册简介</label>
            <textarea v-model="new_album.description" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" @click="add_album()">创建</button>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
  import AlbumItem from '@/components/AlbumItem'
  import FooterView from '@/components/Footer'
  import { ref, reactive } from 'vue'
  import $ from 'jquery'
  import router from '@/router/index';
  import { Modal } from 'bootstrap/dist/js/bootstrap'

  //import { useStore } from 'vuex'

  export default {
    name: 'MyAlbumView',
    components: {
      AlbumItem,
      FooterView,
    },
    data(){
      return {
        componentKey: 0,
      }
    },
    methods: {
      forceRerender(){
        this.componentKey += 1;
      }
    },
    setup(){
      //const store = useStore();
      let albums = ref([]);
      //let new_album = ref([]);
      const new_album = reactive({
            name: "",
            authority: "0",
            description: "",
        });

      const jwt_token = localStorage.getItem("jwt_token");

      //获取相册列表
      const refresh_albums = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/album/getList/",
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
      refresh_albums();

      //添加相册
      const add_album = () => {
          $.ajax({
            url: "http://127.0.0.1:3000/user/album/add/",
            type: "post",
            headers:{
              Authorization: "Bearer " + jwt_token,
            },
            data:{
              name: new_album.name,
              authority: new_album.authority,
              description: new_album.description,
            }, 
            success(resp) {
              console.log(resp);
              Modal.getInstance('#createAlbum').hide();
              refresh_albums();
              this.forceRerender();
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
        albums,
        new_album,
        //get_index,

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
  #add-photo {
    margin-top: 10px;
  }
</style>