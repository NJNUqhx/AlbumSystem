<template>
    <ContentBase>
        <div>
            <div class="row">
                <div class="col-4">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="输入昵称或账号" aria-label="Recipient's username"
                            aria-describedby="button-addon2">
                        <div class="btn-group">
                            <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                搜索
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">按昵称搜索</a></li>
                                <li><a class="dropdown-item" href="#">按账号搜索</a></li>
                            </ul>
                        </div>

                    </div>
                </div>
                <div class="col-8">
                    <div class="card" v-for="user in users" :key="user.id" @click="open_user_profile(user.id)">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-2">
                                    <img class="img-fluid" :src="user.photo" alt="">
                                </div>
                                <div class="col-4">
                                    <div align="left" class="username">{{ user.username }}</div>
                                </div>
                                <div class="col-6">
                                    <div class="btn-group-vertical">
                                        <div class="btn btn-danger">添加好友</div>
                                        <div class="btn btn-success">访问相册</div>
                                        <div class="btn btn-info">访问动态</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </ContentBase>
</template>


<script>
import ContentBase from '../components/ContentBase'
import $ from 'jquery'
import { ref } from 'vue'
import router from '@/router/index';
import { useStore } from 'vuex';

export default {
    name: 'UserListView',
    components: {
        ContentBase,
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
</style>