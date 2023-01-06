<template>
  <div class="container">
    <div class="row">
      <div class="col">
      </div>
      <div class="col-12">
        <div class="card">
          <h5 class="card-header">当前位置：好友相册</h5>
            <div class="card-body">
              <div class="album-table" :key="componentKey">
                <!--列举相册-->
                <ul style="display: flex;flex-wrap: wrap;">
                  <li style="	padding: 5px;list-style: none;margin-right: 60px;" v-for="album in albums" :key= "album.albumId">
                    <AlbumItem :album="album"></AlbumItem>
                  </li>
                </ul>

            <!-- </div>
              <a href="#" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#createAlbum">新建相册</a>
            </div> -->
        </div>
      </div>
    </div>
    <FooterView></FooterView>
    </div>
  </div>
</div>
</template>
  
  <script>
  import AlbumItem from '@/components/AlbumItem'
  import FooterView from '@/components/Footer'
  //import { reactive } from 'vue'
  import { ref } from 'vue'
  //import {useRoute} from 'vue-router'
  import $ from 'jquery'
  import router from '@/router/index';

  export default {
    name: 'MyAlbumView',
    components: {
      AlbumItem,
      FooterView,
    },
    props: {
      user_id: {
            type: String,
            required: true,
        },
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
    setup(props){
      
      //console.log("user_id:" +props.user_id);
      let albums = ref([]);
      const jwt_token = localStorage.getItem("jwt_token");

      //获取用户相册
      const get_albums = () =>{
            //console.log(id);
            $.ajax({
                url: "http://127.0.0.1:3000/user/album/getUsersAlbum/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data:{
                  friend_id: props.user_id,
                },
                success(resp) {
                    console.log(resp);
                    albums.value=resp;
                },
                error() {
                    /* console.log(id);
                    console.log(message); */
                }
            })
          }
      get_albums();
    
      const get_index= () => {
          console.log(router.currentRoute.value.path);
        }


      return {
        albums,
        get_albums,
        get_index,
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