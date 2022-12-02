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
                <tr v-for="commentContent in commentContentList" :key="commentContent.id">
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item"><b>评论编号:</b>{{commentContent.commentId}}</li>
                            <li class="list-group-item"><b>用户编号:</b>{{commentContent.userId}}</li>
                            <li class="list-group-item"><b>评论内容:</b>{{commentContent.content}}</li>
                            <li class="list-group-item"><b>评论时间:</b>{{commentContent.time}}</li>
                        </ul>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">被举报用户: </li>
                            <li class="list-group-item">评论编号: </li>
                            <li class="list-group-item">举报时间: </li>
                        </ul>
                    </td>
                    <td>
                        <div class="input-group">
                            <textarea class="form-control" placeholder="处理意见" style="height: 100px;"></textarea>
                        </div>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success">删除评论</button>
                            <button class="btn btn-danger">保留评论</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import {ref} from 'vue'
import $ from 'jquery'

export default {
    name: "HandleComment",
    components: {
    },
    setup(){
        const jwt_token = localStorage.getItem("jwt_token");
        let commentContentList = ref([]);
        let commentReportList = ref([]);
        const getCommentContentList = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/comment/content/list/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp){
                    commentContentList.value = resp;
                },
                error(){
                    console.log("error");
                }
            })
        }
        const getCommentReportList = () =>{
            $.ajax({
                url: "http://127.0.0.1:3000/admin/comment/report/list/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp){
                    commentReportList.value = resp;
                },
                error(){
                    console.log("error");
                }
            })
        }
        getCommentContentList();
        return{
            commentContentList,
            commentReportList,
            getCommentContentList,
            getCommentReportList
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
.table{
    text-align: center;
}
</style>