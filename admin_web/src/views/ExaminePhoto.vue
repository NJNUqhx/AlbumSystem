<template>
    <div class="container">
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th style="width: 40%;"><span>待审核照片</span></th>
                    <th style="width: 25%"><span>照片信息</span></th>
                    <th style="width: 25%"><span>审核意见</span></th>
                    <th style="width: 10%"><span>审核操作</span></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="photo in photos" :key="photo.id">
                    <td>
                        <div class="card">
                            <div class="card-body">
                                <img :src="require('D:/GitHub/AlbumSystem/images/'+ photo.userId + '/' + photo.photoId + '.jpg')" class="img-fluid img-thumbnail" alt="待审核照片">
                            </div>
                        </div>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">用户编号: {{ photo.userId }}</li>
                            <li class="list-group-item">照片编号: {{ photo.photoId }}</li>
                            <li class="list-group-item">上传时间: {{ photo.time }}</li>
                            <li class="list-group-item">访问权限: {{ photo.authority }}</li>
                            <li class="list-group-item">审核状态: {{ photo.status }}</li>
                        </ul>
                    </td>
                    <td>
                        <div class="input-group">
                            <textarea class="form-control" placeholder="审核意见" style="height: 100px;"></textarea>
                        </div>
                    </td>
                    <td>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success" style="margin-top: 10%">通过</button>
                            <button class="btn btn-danger">拒绝</button>
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
    name: "ExaminePhoto",
    components: {
    },
    setup() {
        let photos = ref([]);
        const jwt_token = localStorage.getItem("jwt_token");
        const getPhotoListAll = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/photo/list/all/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp){
                    photos.value = resp;
                },
                error(){
                    console.log("error");
                }
            })
        }
        getPhotoListAll();
        return {
            photos,
            getPhotoListAll
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