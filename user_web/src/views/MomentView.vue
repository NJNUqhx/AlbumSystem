<template>
  <ContentBase>
    <div class="row">
      <div class="col-3">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-3">
                <img id="head-img" class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/142492_lg_7c84a89707.jpg" alt="">
              </div>
              <div class="col-9">
                <div class="username">{{user.username}}</div>
                <div  class="fans">点赞: {{user.followers}}</div>
              </div>
            </div>
          </div> 
        </div>
        <a id="new-moment" href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createMoment">发表动态</a>
          <!-- <UserProfileWrite @posting="posting"></UserProfileWrite> -->
      </div>
      <div class="col-9">
        <div id="moment-card" class="card" v-for="(moment,index) in moments" :key= "moment.momentId">
        <div class="card-body">
          <MomentItem :moment="moment" :photo="photo_ids[index]" @get_moments="get_moments"></MomentItem>
        </div>
        </div>
      </div>
    </div>
  </ContentBase>

<!-- 创建动态 -->
<div class="modal fade" id="createMoment" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">创建动态</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
          <label for="exampleFormControlTextarea1" class="form-label">介绍</label>
          <textarea v-model="new_moment.description" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
          <label for="basic-url" class="form-label">动态权限</label>
          <select v-model="new_moment.authority" class="form-select" aria-label="Default select example">
            <option value="0">所有人可见</option>
            <option value="1">仅好友可见</option>
            <option value="2">仅自己可见</option>
          </select>
        </div>
        <div class="mb-3">
        <label for="formFileMultiple" id="add-photo" class="form-label">添加照片</label>
        <!--选择照片-->
        <div v-for="photo in photo_list" :key= "photo.photoId">
          <div id="photo-card" class="card">
            <div class="card-body">
              <div class="row">
                <div class="col-6">
                    <img class="img" style="margin-left:10px 100px; height: 100px" :src="require('D:/GitHub/AlbumSystem/images/' + photo.userId + '/' + photo.photoId + '.jpg')" alt="">
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
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" @click="add_moment()">创建</button>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import ContentBase from '../components/ContentBase'
//import UserProfileInfo from '../components/UserProfileInfo'
//import UserProfilePost from '../components/UserProfilePost'
//import UserProfileWrite from '../components/UserProfileWrite'
import MomentItem from '@/components/MomentItem'
import {reactive} from 'vue'
//import {useRoute} from 'vue-router'
import { ref } from 'vue'
import $ from 'jquery'
import { Modal } from 'bootstrap/dist/js/bootstrap'


export default {
    name: 'MomentView',
    components: {
    ContentBase,
    //UserProfileInfo,
    //UserProfilePost,
    //UserProfileWrite,
    MomentItem
},
    setup() {
        //?好像有问题，课程2 27：44
        const jwt_token = localStorage.getItem("jwt_token");
        let moments = ref([]);
        let moment_photo_id = ref('');
        let photo_list = ref([]);

        /* const route = useRoute();
        const userId = route.params.userId;
        console.log(userId); */
        
        let new_moment = reactive({
          photo_id: "",
          authority: "0",
          description: "",
        });

        const user = reactive({
            id: 1,
            username: "Godzilla",
            lastName: "Xue",
            firstName: "Zilong",
            followers: 150,
            is_followed: false, 
        });

        const follow = () => {
            if(user.is_followed) return;
            user.is_followed = true;
            user.followers++;
        };

        const unfollow = () => {
            if(!user.is_followed) return;
            user.is_followed = false;
            user.followers--;
        }
         
        //获取所有动态
        const get_moments = () => {
            $.ajax({
            url: "http://127.0.0.1:3000/user/moment/getList/",
            type: "post",
            headers:{
                Authorization: "Bearer " + jwt_token, 
            },
            /* data: {
                album_id: props.album_id,
            }, */
            success(resp) {
                console.log(resp);
                moments.value = resp;
            },
            error(resp) {
                console.log(resp);
            }      
            });
        }
        get_moments();
        
        //发表动态
        const add_moment = () => {
            $.ajax({
            url: "http://127.0.0.1:3000/user/moment/add/",
            type: "post",
            headers:{
                Authorization: "Bearer " + jwt_token, 
            },
            data: {
                authority: new_moment.authority,
                description: new_moment.description,
                photo_id: moment_photo_id.value,
                //photo_id:"50",
            },
            success(resp) {
                console.log(resp);
                //清空new_moment
                new_moment.description="";
                new_moment.authority="0";
                moment_photo_id.value="";
                Modal.getInstance('#createMoment').hide();
                get_moments();
            },
            error(resp) {
                console.log(resp);
            }      
            });
        }

        //更新新建动态的照片id
        const add_photo = (photo_id) => {
          moment_photo_id.value = photo_id;
          console.log('photo_id:' + moment_photo_id.value);
        }
        
        //获取用户所有照片
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
        
        let photo_ids = ref([]);
        const get_moment_photos = () => {
              $.ajax({
                url: "http://127.0.0.1:3000/user/moment/getPhotoIdList",
                type: "post",
                headers:{
                  Authorization: "Bearer " + jwt_token, 
                },
                /* data: {
                  moment_id: moment.momentId,
                }, */
                success(resp) {
                  console.log(resp);
                  photo_ids.value=resp;
                  console.log("id2::" + photo_ids.value[2]);
                  //this.photo_id=resp.photoId;
                  
                },
                error(resp) {
                  console.log(resp);
                  
                  //console.log("id:" + props.album_id);
                }
              });
            }
          get_moment_photos();
        return {
            user,
            new_moment,
            moments,
            photo_list,
            photo_ids,
            get_moments,
            add_moment,
            follow,
            unfollow,
            add_photo,
            get_moment_photos,
        }
    }
}
</script>

<style scoped>
#head-img {
    border-radius: 50%;
    margin-left: 5px;
}
.username {
    font-weight: bold;
}
.fans {
    font-size: 12px;
    color: grey;
}

#new-moment {
  margin-top: 10px;
  background-color: grey;
}

#moment-card{
  margin-bottom: 40px;
}

#add-photo{
  margin-left: 20px;
  margin-bottom: 10px;
}
#photo-card {
  margin-bottom: 20px;
  margin-left: 20px;
  margin-right: 20px;
}
/* button {
    padding: 2px 4px;
    font-size: 12px;
} */
</style>