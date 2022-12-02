<template>
    <div class="container">
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th style="width: 40%;"><span>待处理评论</span></th>
                    <th style="width: 40%"><span>举报信息</span></th>
                    <th style="width: 20%"><span>处理操作</span></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="comment in commentList" :key="comment.id">
                    <td>
                        <!-- <p>{{comment["report_time"]}}</p> -->
                        <ul class="list-group">
                            <li class="list-group-item"><b>评论编号:</b>{{ comment["commentId"] }}</li>
                            <li class="list-group-item"><b>用户编号:</b>{{ comment["userId"] }}</li>
                            <li class="list-group-item"><b>评论内容:</b>{{ comment["content"] }}</li>
                            <li class="list-group-item"><b>评论时间:</b>{{ comment["comment_time"] }}</li>
                            <li class="list-group-item"><b>评论状态:</b> {{ show_comment_status(comment["comment_status"]) }}</li>
                        </ul>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item"><b>举报编号:</b> {{ comment["reportId"] }}</li>
                            <li class="list-group-item"><b>评论编号:</b> {{ comment["commentId"] }}</li>
                            <li class="list-group-item"><b>被举报用户编号:</b> {{ comment["userId"] }}</li>
                            <li class="list-group-item"><b>举报理由:</b> {{ comment["reason"] }}</li>
                            <li class="list-group-item"><b>举报时间:</b> {{ comment["report_time"] }}</li>
                            <li class="list-group-item"><b>处理状态:</b> {{ show_report_status(comment["report_status"]) }}</li>
                        </ul>
                    </td>
                    <td>
                        <div class="input-group">
                            <textarea class="form-control" :id="'advice' + comment['reportId']" placeholder="处理意见" style="height: 100px;"></textarea>
                        </div>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success" @click="commentHandle(comment, 1)">举报成功</button>
                            <button class="btn btn-danger"  @click="commentHandle(comment, 2)">举报失败</button>
                        </div>
                        <div class="error-message" :id="'error-message' + comment['reportId']">
                            {{ error_message }}
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
    name: "HandleComment",
    components: {
    },
    setup() {
        const jwt_token = localStorage.getItem("jwt_token");
        // let commentBothList = ref([]);
        // let commentReportList = ref([]);
        // let commentContentList = ref([]);
        let commentList = ref([]);
        let error_message = ref("");
        let advice = ref("");
        let target = ref("");
        // const getCommentContentList = () => {
        //     $.ajax({
        //         url: "http://127.0.0.1:3000/admin/comment/content/list/",
        //         type: "post",
        //         headers: {
        //             Authorization: "Bearer " + jwt_token,
        //         },
        //         success(resp){
        //             commentContentList.value = resp;
        //             console.log(commentContentList.value);
        //         },
        //         error(){
        //             console.log("error");
        //         }
        //     })
        // }
        // const getCommentReportList = () => {
        //     $.ajax({
        //         url: "http://127.0.0.1:3000/admin/comment/report/list/",
        //         type: "post",
        //         headers: {
        //             Authorization: "Bearer " + jwt_token,
        //         },
        //         success(resp){
        //             commentReportList.value = resp;
        //         },
        //         error(){
        //             console.log("error");
        //         }
        //     })
        // }
        // const getCommentBothList = () => {
        //     $.ajax({
        //         url: "http://127.0.0.1:3000/admin/comment/both/list/",
        //         type: "post",
        //         headers: {
        //             Authorization: "Bearer " + jwt_token,
        //         },
        //         success(resp){
        //             commentBothList.value = resp;
        //         },
        //         error(){
        //             console.log("error");
        //         }
        //     })
        // }
        const getCommentList = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/comment/map/list",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    commentList.value = resp;

                },
                error() {
                    console.log("error");
                }
            })
        }
        const show_comment_status = (status) =>{
            if(status === "0")
                return "默认状态";
            else if(status === "1")
                return "举报成功->删除";
            else
                return "举报失败->保留";
        }
        const show_report_status = (status) =>{
            if(status === "0")
                return "待处理";
            else if(status === "1")
                return "举报成功";
            else
                return "举报失败";
        }

        const commentHandle = (comment,handle) =>{
            error_message = "";
            target = "#advice" + comment["reportId"];
            advice = $(target).val();
            $.ajax({
                url: "http://127.0.0.1:3000/admin/comment/handle/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data:{
                    reportId: comment["reportId"],
                    userId: comment["userId"],
                    handle: handle,
                    advice: advice
                },
                success(resp) {
                    if(resp.error_message !== "success")
                        error_message.value = resp.error_message;
                },
                error() {
                    console.log("error");
                }
            })
            getCommentList();
        }

        getCommentList();
        return {
            commentList,
            getCommentList,
            show_comment_status,
            show_report_status,
            commentHandle,
            error_message
        }
    }
}
</script>

<style scoped>
/* .container {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
} */
.table {
    text-align: center;
}

button {
    margin-top: 10px;
    width: 100%;
}
div.error-message{
    color: red;
}
</style>