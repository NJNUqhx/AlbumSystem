<template>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <div class="input-group mb-3">
                    <input type="text" v-model="content" class="form-control" placeholder="输入昵称或账号">
                    <div class="btn-group">
                        <button type="button" id="search-btn" class="btn btn-success dropdown-toggle"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            搜索
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" @click="getSearchList(content, '1')">按昵称搜索</a></li>
                            <li><a class="dropdown-item" @click="getSearchList(content, '2')">按账号搜索</a></li>
                        </ul>
                    </div>
                </div>
                <!--@click="open_user_profile(user.id)"-->
                <div class="card" v-for="user in users" :key="user.userId">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-2">
                                <img class="img-fluid" src="https://tse2-mm.cn.bing.net/th/id/OIP-C.4uvuajAOVfVs7fzbr_agNQAAAA?w=174&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7" alt="">
                            </div>
                            <div class="col-4">
                                <!-- <div class="account">{{ user.userId }}</div> -->
                                <div class="account" style="margin-top: 25px">{{ user.account }}</div>
                                <div class="account">{{ user.nickname }}</div>
                            </div>
                            <div class="col-6">
                                <div class="btn-group-vertical">
                                    <div v-if="(if_add == 'false')" id="addFriend" class="btn" data-bs-toggle="modal"
                                        :data-bs-target="('#addFriend'+ user.userId)">添加好友</div>
                                    <!-- Modal -->
                                    <div class="modal fade" :id="('addFriend'+ user.userId)" tabindex="-1"
                                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">好友申请</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="mb-3">
                                                        <label for="exampleFormControlInput1"
                                                            class="form-label">申请</label>
                                                        <input type="text" v-model="message" class="form-control"
                                                            id="exampleFormControlInput1" placeholder="交个朋友吧~">
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">取消</button>
                                                    <button type="button" class="btn btn-primary" @click="sendApplication(user.userId)">发送</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="visitAlbum" class="btn" @click="visitAlbum(user.userId)">访问相册</div>
                                    <div id="visitMoment" class="btn" @click="visitMoment(user.userId)">访问动态</div>
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
import { ref } from 'vue'
import { Modal } from 'bootstrap/dist/js/bootstrap'
import router from '@/router/index';

export default {
    name: 'UserListView',
    components: {
        //ContentBase,
    },

    setup() {
        const jwt_token = localStorage.getItem("jwt_token");
        let if_add = ref("true");
        let users = ref([]);
        let message = ref("交个朋友吧~");
        let content = ref("");

        const getSearchList = (content, choice) => {
            let nickname = ref("");
            let account  = ref("");
            if(choice === "1") nickname.value = content;
            else account.value = content;    
            if_add.value = "false";

            $.ajax({
                url: "http://127.0.0.1:3000/user/friend/search/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data:{
                    nickname: nickname.value,
                    account: account.value,
                },
                success(resp) {
                    users.value = resp;
                },
                error() {
                }
            })
        }
        
        const getAllFriends = () =>{
            $.ajax({
                url: "http://127.0.0.1:3000/user/friend/getList/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    users.value = resp;
                },
                error() {
                    
                }
            })
        }

        const sendApplication = (id) =>{
            //console.log(id);
            $.ajax({
                url: "http://127.0.0.1:3000/user/friend/sendApplication/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data:{
                    recipient_id: id,
                    message: message.value,
                },
                success(resp) {
                    console.log(resp);
                    getAllFriends();
                    Modal.getInstance('#addFriend' + id).hide();

                    // 此处关闭弹窗
                   /*  if(resp.error_message !== "success"){
                        alert(resp.error_message);
                    } */
                },
                error() {
                    /* console.log(id);
                    console.log(message); */
                }
            })
        }

        getAllFriends();

        //用户的照片
        let user_photos = ref([]);

        //访问用户相册
        const visitAlbum = (id) =>{
            //console.log(id);
            $.ajax({
                url: "http://127.0.0.1:3000/user/friend/sendApplication/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data:{
                    user_id: id,
                },
                success(resp) {
                    console.log(resp);
                    user_photos.value=resp;
                    /*
                      页面跳转 
                     */
                     router.push({ name: 'user_photos', query: {photos: user_photos}});
                },
                error() {
                    /* console.log(id);
                    console.log(message); */
                }
            })
          }
        
        //用户动态
        let user_moments = ref([]);
        //访问用户动态
        const visitMoment = (id) =>{
            //console.log(id);
            $.ajax({
                url: "http://127.0.0.1:3000/user/friend/sendApplication/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data:{
                    user_id: id,
                },
                success(resp) {
                    console.log(resp);
                    user_photos.value=resp;
                    /*
                      页面跳转
                     */  
                    router.push({ name: 'user_moments', query: {moments: user_moments}});

                },
                error() {
                  /* router.push({ name: 'user_moments', query: {moments: user_moments}}); */
                }
            })
          }


        return {
            users,
            message,
            content,
            if_add,
            getSearchList,
            getAllFriends,
            sendApplication,
            visitAlbum,
            visitMoment,
        }
    }
}
</script>

<style scoped>
.container {
    margin-top: 20px;
    width: 60%;
}

.form-control {
    margin-right: 10px;
}

#search-btn {
    width: 130px;
}

.dropdown-menu{
    width: 100px;
}

img {
    border-radius: 50%;
}

.username {
    font-weight: bold;
    height: 50%;
}

.followers {
    font-size: 12px;
    color: gray;
    height: 50%;
}

.card {
    margin-bottom: 20px;
    cursor: pointer;
}

.card:hover {
    box-shadow: 2px 2px 10px lightgrey;
    transition: 500ms;
}

.li {
    text-align: center;
}

#addFriend {
    background-color: #FFCC99;
    border-radius: 5px;
}

#visitAlbum {
    background-color: #FFFFCC;
    margin-top: 5px;
    border-radius: 5px;
}

#visitMoment {
    background-color: #99CCFF;
    margin-top: 5px;
    border-radius: 5px;
}
</style>