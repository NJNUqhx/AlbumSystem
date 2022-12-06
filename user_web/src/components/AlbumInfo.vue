<template>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <div class="photo-name"><b>相册名:</b>{{album_info.name}}</div>
                <div class="createtime"><b>创建时间:</b>{{album_info.time}}</div>
                <div class="createtime"><b>权限:</b>{{showAuthority(album_info.authority)}}</div>
                <div class="description"><b>简介:</b>{{album_info.description}}</div>

  <!--               <div class="photo-name">相册名: 相册1</div>
                <div class="createtime">创建时间: 2022.11.12</div>
                <div class="createtime">权限：公开</div>
                <div class="description">简介：....</div> -->
                <div class="editalbum"><button class="btn btn-success btn-primary " data-bs-toggle="modal" data-bs-target="#editAlbum">编辑相册信息</button></div>
            </div>
        </div>
    </div>

  <!-- 相册信息编辑 -->
  <div class="modal fade" id="editAlbum" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">编辑相册</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">相册名</label>
            <input v-model="album.name" type="email" class="form-control" id="exampleFormControlInput1" placeholder="Album name">
          </div>
          <!--修改-->
          <label for="basic-url" class="form-label">相册权限</label>
          <select v-model="album.authority" class="form-select" aria-label="Default select example">
            <option value="0">所有人可见</option>
            <option value="1">仅好友可见</option>
            <option value="2">仅自己可见</option>
          </select>

          <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">相册简介</label>
            <textarea v-model="album.description"  class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" @click="update_AlbumInfo(album)">保存修改</button>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
  import $ from 'jquery'
  import { Modal } from 'bootstrap/dist/js/bootstrap'

  /* import { ref } from 'vue';
  import { toRefs } from 'vue' */

      export default {
          name: "AlbumInfo",
          props: {
            album_info: {
                required: true,
            },
          },

          data(props) {
            return {
              album: props.album_info
            }
          },
          watch: {
            album_info(val){
                this.album = val;
            }
          },
          setup() {
            const jwt_token = localStorage.getItem("jwt_token");
            //更新相册信息
            const update_AlbumInfo = (album) => {
              $.ajax({
                url: "http://127.0.0.1:3000/user/album/update/",
                type: "post",
                headers:{
                  Authorization: "Bearer " + jwt_token, 
                },
                data: {
                  album_id: album.albumId,
                  authority: album.authority,
                  name: album.name,
                  description: album.description,
                },
                success(resp) {
                  console.log(resp);
                  //album_info.value = resp;
                  Modal.getInstance('#editAlbum').hide();

                },
                error(resp) {
                  console.log(resp);
                }      
              });
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
            return {
              showAuthority,
              update_AlbumInfo,
              //album,
            }
          }

      }
  </script>
  
  <style scoped>
  .container{
    margin-top: 20px;
  }
  .createtime {
    margin-top: 10px;
  }
  .description {
    margin-top: 10px;
    margin-bottom: 10px;
  }
  #add-photo {
    margin-top: 10px;
  }
  </style>