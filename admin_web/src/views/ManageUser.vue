<template>
    <div class="container">
        <div>
            <button class="btn btn-lg btn-success" data-bs-toggle="modal" data-bs-target="#add-user-btn">添加用户</button>
            <div class="modal fade" id="add-user-btn" tabindex="-1">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">添加用户</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="add-user-nickname" class="form-label">昵称</label>
                                <input v-model="add_user.nickname" type="text" class="form-control"
                                    id="add-user-nickname" placeholder="请输入用户名称">
                            </div>
                            <div class="mb-3">
                                <label for="add-user-password" class="form-label">密码</label>
                                <input v-model="add_user.password" class="form-control" id="add-user-password"
                                    placeholder="请输入用户密码">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="error-message">{{ add_user.error_message }}</div>
                            <button type="button" class="btn btn-primary" @click="addUser">添加</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-lg btn-danger" data-bs-toggle="modal" data-bs-target="#manage-user-btn" @click="getUserManagementResultList
            ">管理记录</button>
            <div class="modal fade" id="manage-user-btn" tabindex="-1">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">用户管理操作记录</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <table class="table table-hover table-bordered">
                            <thead>
                                <th style="width: 10%;"> 管理员 </th>
                                <th style="width: 60%;"> 管理操作</th>
                                <th style="width: 30%;"> 时间 </th>
                            </thead>
                            <tbody>
                                <tr v-for="result in results" :key="result.id">
                                    <td>{{ result.adminId }}</td>
                                    <td>{{ result.message }}</td>
                                    <td>{{ result.time }}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">退出</button>
                    </div>
                </div>
            </div>
        </div>
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
                                <img src="https://tse2-mm.cn.bing.net/th/id/OIP-C.4uvuajAOVfVs7fzbr_agNQAAAA?w=174&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"
                                    class="img-fluid img-thumbnail" alt="待审核照片" id="user-photo">
                            </div>
                        </div>
                    </td>
                    <td>

                        <ul class="list-group">
                            <li class="list-group-item"><b>用户编号:</b>{{ user.userId }}</li>
                            <li class="list-group-item"><b>用户昵称:</b>{{ user.nickname }}</li>
                            <li class="list-group-item"><b>用户账号:</b>{{ user.account }}</li>
                            <li class="list-group-item"><b>用户密码:</b>***********</li>
                        </ul>
                    </td>
                    <td>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success" @click="removeUser(user)">删除用户</button>
                            <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#photos-user-btn"
                                @click="getPhotosOfUser(user)">用户照片</button>
                            <div class="modal fade" id="photos-user-btn" tabindex="-1">
                                <div class="modal-dialog modal-xl">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">查看用户照片</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                        </div>
                                        <table class="table table-hover table-bordered">
                                            <thead>
                                                <th style="width: 30%;"><span>用户照片</span></th>
                                                <th style="width: 70%"><span>照片信息</span></th>
                                            </thead>
                                            <tbody>
                                                <tr v-for="photo in photos" :key="photo.photoId">
                                                    <td>
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <img :src="require('D:/GitHub/AlbumSystem/images/' + photo.userId + '/' + photo.photoId + '.jpg')"
                                                                    class="img-fluid img-thumbnail" alt="用户照片">
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <ul class="list-group">
                                                        <li class="list-group-item"><b>用户编号:</b> {{ photo.userId }}</li>
                                                        <li class="list-group-item"><b>照片编号:</b> {{ photo.photoId }}
                                                        </li>
                                                        <li class="list-group-item"><b>照片名称:</b> {{ photo.name }}</li>
                                                        <li class="list-group-item"><b>上传时间:</b> {{ photo.time }}</li>
                                                        <li class="list-group-item"><b>访问权限:</b> {{
                                                                showPhotoAuthority(photo.authority)
                                                        }}</li>
                                                        <li class="list-group-item"><b>审核状态:</b> {{
                                                                showPhotoStatus(photo.status)
                                                        }}</li>
                                                        <li class="list-group-item"><b>存储地址:</b> {{ photo.address }}
                                                        </li>
                                                    </ul>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">退出</button>
                                    </div>
                                </div>
                            </div>

                            <button class="btn btn-info" data-bs-toggle="modal"
                                data-bs-target="#comments-user-btn" @click="getCommentsOfUser(user)">用户评论</button>
                            <div class="modal fade" id="comments-user-btn" tabindex="-1">
                                <div class="modal-dialog modal-xl">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">查看用户评论</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                        </div>
                                        <table class="table table-hover table-bordered">
                                            <thead>
                                                <th>评论信息</th>
                                            </thead>
                                            <tbody>
                                                <tr v-for="comment in comments" :key="comment.commentId">
                                                    <td>
                                                        <!-- <p>{{comment["report_time"]}}</p> -->
                                                        <ul class="list-group">
                                                            <li class="list-group-item"><b>评论编号:</b>{{
                                                                    comment["commentId"]
                                                            }}</li>
                                                            <li class="list-group-item"><b>用户编号:</b>{{ comment["userId"]
                                                            }}</li>
                                                            <li class="list-group-item"><b>评论内容:</b>{{
                                                                    comment["content"]
                                                            }}</li>
                                                            <li class="list-group-item"><b>评论时间:</b>{{
                                                                    comment["time"]
                                                            }}</li>
                                                            <li class="list-group-item"><b>评论状态:</b> {{
                                                                    show_comment_status(comment["comment_status"])
                                                            }}</li>
                                                        </ul>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">退出</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import { ref } from 'vue'
import { reactive } from 'vue';
import $ from 'jquery'
import { Modal } from 'bootstrap/dist/js/bootstrap'

export default {
    name: "ManageUser",
    setup() {
        let users = ref([]);
        let results = ref([]);
        const jwt_token = localStorage.getItem("jwt_token");
        const getUserList = () => {
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

        const addUser = () => {
            add_user.error_message = ""
            $.ajax({
                url: "http://127.0.0.1:3000/admin/user/add/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    nickname: add_user.nickname,
                    password: add_user.password
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        add_user.nickname = "";
                        add_user.password = "";
                        Modal.getInstance("#add-user-btn").hide();
                        getUserList();
                    } else {
                        add_user.error_message = resp.error_message;
                    }
                },
                error() {
                    alert("error");
                }
            })
            getUserList();
        }

        let add_user = reactive({
            nickname: "",
            password: "",
            error_message: ""
        })

        getUserList();

        const removeUser = (user) => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/user/remove/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    userId: user.userId
                },
                success(resp) {
                    if (resp.error_message !== "success") {
                        alert(resp.error_message);
                    }
                },
                error() {
                    alert("error");
                }
            })
            getUserList();
        }

        const getUserManagementResultList = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/user/management/result/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    results.value = resp;
                },
                error() {
                    alert("error");
                }
            })
        }

        let photos = ref([]);
        let comments = ref([]);
        const getPhotosOfUser = (user) => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/user/photo/list/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    userId: user.userId
                },
                success(resp) {
                    photos.value = resp;
                },
                error() {
                    alert("error");
                }
            })
        }

        const getCommentsOfUser = (user) => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/user/comment/list/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    userId: user.userId
                },
                success(resp) {
                    comments.value = resp;
                },
                error() {
                    alert("error");
                }
            })
        }

        const showPhotoStatus = (status) => {
            if (String(status) === "0")
                return "待审核";
            else if (String(status) === "1")
                return "审核通过";
            else if (String(status) === "2")
                return "审核失败";
            else
                return "状态出错";
        }
        const showPhotoAuthority = (authority) => {
            if (String(authority) === "0")
                return "所有人可见";
            else if (String(authority) === "1")
                return "仅好友可见";
            else if (String(authority) === "2")
                return "仅自己可见";
            else
                return "状态出错";
        }

        const show_comment_status = (status) =>{
            if(status === "0")
                return "默认状态";
            else if(status === "1")
                return "举报成功->删除";
            else
                return "举报失败->保留";
        }

        return {
            users,
            add_user,
            results,
            photos,
            comments,
            getUserList,
            addUser,
            removeUser,
            getUserManagementResultList,
            getPhotosOfUser,
            getCommentsOfUser,
            showPhotoAuthority,
            showPhotoStatus,
            show_comment_status
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
}

#add-btn {
    margin-top: 10px;
    margin-bottom: 10px;
}

div.error-message {
    color: red;
}

#user-photo {
    height: 125px;
}
</style>