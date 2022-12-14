<template>
    <div class="container">
        <table class="table table-hover table-bordered">
            <thead>
                <th style="width: 60%;">待审核动态</th>
                <th style="width: 30%;">审核操作</th>
            </thead>
            <tbody>
                <tr v-for="moment in moments" :key="moment.momentId">
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item"><b>用户编号:</b> {{ moment.userId }}</li>
                            <li class="list-group-item"><b>动态编号:</b> {{ moment.momentId }}</li>
                            <li class="list-group-item"><b>发表时间:</b> {{ moment.time }}</li>
                            <li class="list-group-item"><b>动态描述:</b> {{ moment.description }}</li>
                            <li class="list-group-item"><b>点赞数量:</b> {{ moment.star }}</li>
                            <li class="list-group-item"><b>访问权限:</b> {{ showMomentAuthority(moment.authority) }}</li>
                            <li class="list-group-item"><b>审核状态:</b> {{ showMomentStatus(moment.status) }}</li>
                            <li class="list-group-item">
                                <button class="btn btn-success" data-bs-toggle="modal"
                                    data-bs-target="#photos-moment-btn"
                                    @click="getPhotosOfMoment(moment)">查看动态的照片</button>
                                <div class="modal fade" id="photos-moment-btn" tabindex="-1">
                                    <div class="modal-dialog modal-xl">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">查看动态的照片</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <table>
                                                <thead>
                                                    <th><b>照片</b></th>
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
                                                            <ul class="list-group">
                                                                <li class="list-group-item"><b>照片名称:</b> {{ photo.name
                                                                }}
                                                                </li>
                                                                <li class="list-group-item"><b>上传时间:</b> {{ photo.time
                                                                }}
                                                                </li>
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
                            </li>
                            <li class="list-group-item">
                                <button class="btn btn-info" data-bs-toggle="modal"
                                data-bs-target="#comments-user-btn" @click="getCommentsOfMoment(moment)">查看动态的评论</button>
                            <div class="modal fade" id="comments-user-btn" tabindex="-1">
                                <div class="modal-dialog modal-xl">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">查看评论</h5>
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
                                                        <ul class="list-group">
                                                            <li class="list-group-item"><b>评论内容:</b>{{
                                                                    comment["content"]
                                                            }}</li>
                                                            <li class="list-group-item"><b>评论时间:</b>{{
                                                                    comment["time"]
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
                            </li>
                        </ul>

                    </td>
                    <td>
                        <div class="input-group">
                            <textarea class="form-control" :id="'advice' + moment.momentId" placeholder="审核意见"
                                style="height: 100px;"></textarea>
                        </div>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success" @click="momentExamine(moment, 1)">审核通过</button>
                            <button class="btn btn-danger" @click="momentExamine(moment, 2)">审核失败</button>
                            <button class="btn btn-info" @click="momentExamine(moment, 0)">撤销审核</button>
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


export default {
    name: "ExamineMoment",
    components: {

    },
    setup() {
        let moments = ref([]);
        let photos = ref([]);
        let comments = ref([]);
        const jwt_token = localStorage.getItem("jwt_token");
        const getMomentList = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/moment/list/all/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    moments.value = resp;
                },
                error() {
                    alert("error");
                }

            })
        }
        getMomentList();
        const getPhotosOfMoment = (moment) => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/moment/photo/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    momentId: moment.momentId
                },
                success(resp) {
                    photos.value = resp;
                },
                error() {
                    alert("error");
                }

            })
        }

        const getCommentsOfMoment = (moment) => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/moment/comment/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    momentId: moment.momentId
                },
                success(resp) {
                    comments.value = resp;
                },
                error() {
                    console.log("error");
                }
            })
        }

        const showMomentStatus = (status) => {
            if (String(status) === "0")
                return "待审核";
            else if (String(status) === "1")
                return "审核通过";
            else if (String(status) === "2")
                return "审核失败";
            else
                return "状态出错";
        }
        const showMomentAuthority = (authority) => {
            if (String(authority) === "0")
                return "所有人可见";
            else if (String(authority) === "1")
                return "仅好友可见";
            else if (String(authority) === "2")
                return "仅自己可见";
            else
                return "状态出错";
        }
        let advice = ref("");
        let target = ref("");
        const momentExamine = (moment, examination) => {
            target = "#advice" + moment.momentId;
            advice = $(target).val();
            $.ajax({
                url: "http://127.0.0.1:3000/admin/moment/examine/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    momentId: moment.momentId,
                    userId: moment.userId,
                    examination: examination,
                    advice: advice
                },
                success(resp) {
                    if(resp.error_message !== "success")
                        alert(resp.error_message);
                },
                error() {
                    alert("error");
                }
            })
            $(target).val("");
            window.location.reload();
        }

        return {
            moments,
            photos,
            comments,
            getMomentList,
            getPhotosOfMoment,
            getCommentsOfMoment,
            showMomentAuthority,
            showMomentStatus,
            momentExamine,
        }
    }
}
</script>

<style scoped>
.table {
    text-align: center;
}

.error-message {
    color: red;
}
</style>