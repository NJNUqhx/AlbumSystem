<template>
    <div class="container">
        <div class="card">
            <div class="card-body">          
                <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="输入昵称或账号" aria-label="Recipient's username"
                                aria-describedby="button-addon2">
                            <div class="btn-group">
                                <button type="button" id="search-btn" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    搜索
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">按昵称搜索</a></li>
                                    <li><a class="dropdown-item" href="#">按账号搜索</a></li>
                                </ul>
                            </div>
                </div>
                <!--@click="open_user_profile(user.id)"-->
                <div class="card" v-for="user in users" :key="user.id">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-2">
                                <img class="img-fluid" :src="user.photo" alt="">
                            </div>
                            <div class="col-4">
                                <div class="username">{{ user.username }}</div>
                            </div>
                            <div class="col-6">
                                <div class="btn-group-vertical">
                                    <div id="addFriend" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal">添加好友</div>
                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">好友申请</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label for="exampleFormControlInput1" class="form-label">申请</label>
                                                <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="交个朋友吧~">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                            <button type="button" class="btn btn-primary">发送</button>
                                        </div>
                                        </div>
                                    </div>
                                    </div>
                                    <div id="visitAlbum" class="btn">访问相册</div>
                                    <div id="visitMoment" class="btn">访问动态</div>
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
//import ContentBase from '../components/ContentBase'
import $ from 'jquery'
import { ref } from 'vue'
import router from '@/router/index';
import { useStore } from 'vuex';

export default {
    name: 'UserListView',
    components: {
        //ContentBase,
    },

    setup() {
        const store = useStore();
        let users = ref([]);

        $.ajax({
            url: "https://app165.acapp.acwing.com.cn/myspace/userlist/",
            type: "get",
            success(resp) {
                users.value = resp;
            }
        });

        const open_user_profile = userId => {
            if (store.state.user.is_login) {
                router.push({
                    name: "userprofile",
                    params: {
                        userId
                    }
                })
            } else {
                router.push({
                    name: "login"
                });
            }
        }

        return {
            users,
            open_user_profile
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