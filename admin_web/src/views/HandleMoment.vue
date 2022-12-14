<template>
    <div class="container">
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th style="width: 40%;"><span>待处理动态</span></th>
                    <th style="width: 40%"><span>举报信息</span></th>
                    <th style="width: 20%"><span>处理操作</span></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="moment in momentList" :key="moment.reportId">
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item"><b>动态编号:</b>{{ moment["momentId"] }}</li>
                            <li class="list-group-item"><b>用户编号:</b>{{ moment["userId"] }}</li>
                            <li class="list-group-item"><b>动态描述:</b>{{ moment["description"] }}</li>
                            <li class="list-group-item"><b>点赞数量:</b>{{ moment["star"] }}</li>
                            <li class="list-group-item"><b>发表时间:</b>{{ moment["momentTime"] }}</li>
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
                                <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#comments-user-btn"
                                    @click="getCommentsOfMoment(moment)">查看动态的评论</button>
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
                        <ul class="list-group">
                            <li class="list-group-item"><b>举报编号:</b> {{ moment["reportId"] }}</li>
                            <li class="list-group-item"><b>动态编号:</b> {{ moment["momentId"] }}</li>
                            <li class="list-group-item"><b>被举报用户编号:</b> {{ moment["userId"] }}</li>
                            <li class="list-group-item"><b>举报理由:</b> {{ moment["reason"] }}</li>
                            <li class="list-group-item"><b>举报时间:</b> {{ moment["reportTime"] }}</li>
                            <li class="list-group-item"><b>处理状态:</b> {{ show_report_status(moment["reportStatus"]) }}
                            </li>
                        </ul>
                    </td>
                    <td>
                        <div class="input-group">
                            <textarea class="form-control" :id="'advice' + moment['reportId']" placeholder="处理意见"
                                style="height: 100px;"></textarea>
                        </div>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success" @click="momentHandle(moment, 1)">举报成功</button>
                            <button class="btn btn-danger" @click="momentHandle(moment, 2)">举报失败</button>
                            <button class="btn btn-info" @click="momentHandle(moment, 0)">撤销处理</button>
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
    name: "HandleMoment",
    components: {

    },
    setup() {
        let momentList = ref([]);
        let photos = ref([]);
        let comments = ref([]);
        let jwt_token = localStorage.getItem("jwt_token");
        const show_report_status = (status) => {
            if (status === "0")
                return "待处理";
            else if (status === "1")
                return "举报成功->删除动态";
            else
                return "举报失败->保留动态";
        }

        const getMomentReportList = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/moment/report/all/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    momentList.value = resp;
                },
                error() {
                    alert("error");
                }

            })
        }
        
        getMomentReportList();
        const momentHandle = (moment, handle) => {
            let advice = ref("");
            let target = ref("");
            target = "#advice" + moment["reportId"];
            advice = $(target).val();
            $.ajax({
                url: "http://127.0.0.1:3000/admin/moment/handle/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data:{
                    reportId: moment["reportId"],
                    userId: moment["userId"],
                    momentId: moment["momentId"],
                    handle: handle,
                    advice: advice
                },
                success(resp) {
                    if(resp.error_message !== "success")
                        alert(resp.error_message)
                },
                error() {
                    console.log("error");
                }
            })
            window.location.reload();
        }

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

       
        return {
            photos,
            comments,
            momentList,
            show_report_status,
            momentHandle,
            getMomentReportList,
            getPhotosOfMoment,
            getCommentsOfMoment
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