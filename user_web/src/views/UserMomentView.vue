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
                <div class="username">{{user_name}}</div>
                <div  class="fans">账号: {{account}}</div>
              </div>
            </div>
          </div> 
        </div>
        <!-- <a id="new-moment" href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createMoment">发表动态</a> -->
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
</template>


<script>
import ContentBase from '../components/ContentBase'
import MomentItem from '@/components/MomentItem'
//import {reactive} from 'vue'
//import {useRoute} from 'vue-router'
import { useStore } from 'vuex';
import { ref } from 'vue'
import $ from 'jquery'
//import { Modal } from 'bootstrap/dist/js/bootstrap'


export default {
      name: 'UserMomentView',
      components: {
      ContentBase,
      MomentItem
    },
    props: {
      user_id: {
            type: String,
            required: true,
        },
    },
    setup(props) {
        //?好像有问题，课程2 27：44
        const store = useStore();
        let user_name = store.state.user.nickname;
        let account = store.state.user.account;

        const jwt_token = localStorage.getItem("jwt_token");
        let moments = ref([]);
        let photo_list = ref([]);

        //获取所有动态
        const get_moments = () => {
            $.ajax({
            url: "http://127.0.0.1:3000/user/moment/getList/",
            type: "post",
            headers:{
                Authorization: "Bearer " + jwt_token, 
            },
            data: {
                friend_id: props.user_id,
            },
            success(resp) {
                console.log(resp);
                moments.value = resp;
            },
            error(resp) {
                console.log(resp);
            }      
            });
        }
        //get_moments();
        
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
        //get_photolist();
        
        return {
            user_name,
            account,
            moments,
            photo_list,
            get_moments,
            get_photolist,
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