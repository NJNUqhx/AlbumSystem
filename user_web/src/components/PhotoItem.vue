<template>
  <img class="file-img" :src="require('D:/GitHub/AlbumSystem/images/' + photo.userId + '/' + photo.photoId + '.jpg')" />
  <div class="file-name">{{photo.name}}</div>
  <div class="btn-group">
    <button type="button" class="btn btn-sm btn-danger dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
      操作
    </button>
    <ul class="dropdown-menu">
      <li><a class="dropdown-item" href="#" data-bs-toggle="modal" :data-bs-target="('#showPhoto' + photo.photoId)" >显示信息</a></li>
      <!-- <li><a class="dropdown-item" href="#">下载照片</a></li> -->
      <!-- <li><a class="dropdown-item" href="#" @click="update_photo()">编辑照片</a></li> -->
      <li><a class="dropdown-item" href="#" @click="delete_photo()">删除照片</a></li>
    </ul>
  </div>

    <!-- Modal -->
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
                  <div class="createtime"><b>权限：</b>{{showAuthority(photo.authority)}}</div>
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
</template>
  

<script>
import $ from 'jquery'
//import { emit } from 'process';
//import { ref } from 'vue'

export default {
  name: "PhotoItem",
  props: {
            photo: {
                type: Object,
                required: true,
            },
            album_id: {
                required: true,
            }
        },
  methods:{
      update_album(){
        this.$emit("refresh_photos");
      }
  },
  data(){
    return{
      photo_info: this.photo,
    }
  },
  watch: {
    photo(val){
        this.photo_info= val;
    }
  },
  setup(props){
    const jwt_token = localStorage.getItem("jwt_token");
    //let photo_info = ref([]);
    
    
    const get_photo_info = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/photo/getPhoto/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    photo_id: props.photo.photoId,
                },
                success(resp) {
                    /*
                    if (resp.error_message !== "success") {
                        alert(resp.error_message)
                    }*/
                    //console.log(photo.photoId + ":" + photo.address + "info:" + resp.name + ":" + resp.address);
                    return resp.name;
                },
                error() {

                }
            })
        }
    const update_photo = () => {
        $.ajax({
            url: "http://127.0.0.1:3000/user/photo/update/",
            type: "post",
            headers: {
                Authorization: "Bearer " + jwt_token,
            },
            data: {
                photo_id: props.photo.photoId,
                authority: 1,
                name: "new1"
            },
            success(resp) {
                if (resp.error_message !== "success") {
                    alert(resp.error_message)
                }
            },
            error() {

            }
        })
    }

    const delete_photo = () => {
        $.ajax({
            url: "http://127.0.0.1:3000/user/album/deletePhoto/",
            type: "post",
            headers: {
                Authorization: "Bearer " + jwt_token,
            },
            data: {
                photo_id: props.photo.photoId,
                album_id: props.album_id,
            },
            success(resp) {
                if (resp.error_message !== "success") {
                    alert(resp.error_message)
                }
                //emit('refresh_photos()');
                //methods.update_album();

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
        update_photo,
        showStatus,
        delete_photo,
        //photo_info,
      }
  }

}
</script>
  
<style scoped>
.container {
  margin-top: 20px;
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
  /* height: 44px; */
  margin-bottom: 5px;
  line-height: 22px;
  font-size: 15px;
}
.btn-group {
  margin-left: 30px;
}
</style>