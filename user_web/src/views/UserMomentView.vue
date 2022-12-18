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
          <!-- <UserProfileWrite @posting="posting"></UserProfileWrite> -->
      </div>
      <div class="col-9">
        <div id="moment-card" class="card" v-for="moment in moments" :key= "moment.momentId">
        <div class="card-body">
          <MomentItem :moment="moment"></MomentItem>
        </div>
        </div>
      </div>
    </div>
  </ContentBase>
</template>


<script>
import ContentBase from '../components/ContentBase'
//import UserProfileInfo from '../components/UserProfileInfo'
//import UserProfilePost from '../components/UserProfilePost'
//import UserProfileWrite from '../components/UserProfileWrite'
import MomentItem from '@/components/MomentItem'
import {reactive} from 'vue'
import {useRoute} from 'vue-router'
import { ref } from 'vue'
import $ from 'jquery'
//import { Modal } from 'bootstrap/dist/js/bootstrap'


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
        //let moment_photo_id = ref('');
        let photo_list = ref([]);

        //获取路由moments参数
        const route = useRoute();
        let moments = route.query.moments;
        /* const userId = route.params.userId;
        console.log(userId); */

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
           
        return {
            user,
            moments,
            photo_list,
            get_moments,
            follow,
            unfollow,
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