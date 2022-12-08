<template>
    <div class="container">
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th style="width: 30%;"><span>用户列表</span></th>
                    <th style="width: 50%"><span>用户信息</span></th>
                    <th style="width: 20%"><span>用户管理</span></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in users" :key="user.userId"> 
                    <td>
                        <div class="card">
                            <div class="card-body">
                                <img src="https://tse2-mm.cn.bing.net/th/id/OIP-C.4uvuajAOVfVs7fzbr_agNQAAAA?w=174&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7" class="img-fluid img-thumbnail" alt="待审核照片">
                            </div>
                        </div>
                    </td>
                    <td>
                        
                        <ul class="list-group">
                            <li class="list-group-item"><b>用户编号:</b>{{user.userId}}</li>
                            <li class="list-group-item"><b>用户昵称:</b>{{user.nickname}}</li>
                            <li class="list-group-item"><b>用户账号:</b>{{user.account}}</li>
                            <li class="list-group-item"><b>用户密码:</b>***********</li>
                        </ul>
                    </td>
                    <td>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success">删除用户</button>
                            <button class="btn btn-danger">查看照片</button>
                            <button class="btn btn-info">查看动态</button>
                            <button class="btn btn-primary">查看评论</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import { ref } from 'vue'
import $ from 'jquery'
export default{
    name: "ManageUser",
    setup(){
        let users = ref([]);
        const jwt_token = localStorage.getItem("jwt_token");
        const getUserList = () =>{
            $.ajax({
                url: "http://127.0.0.1:3000/admin/user/list/",
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

        const addUser = () =>{
            $.ajax({
                url: "http://127.0.0.1:3000/admin/user/add/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    nickname: "njnuqhx",
                    password: "njnu"
                },
                success() {
                    alert("success");
                },
                error() {
                    alert("error");
                }
            })
            getUserList();
        }
        addUser();
        getUserList();
        return{
            users,
            getUserList,
            addUser
        }
    }
}

</script>

<style scoped>
.table {
    text-align: center;
}

button {
    margin-top: 10px;
    width: 100%;
}
</style>