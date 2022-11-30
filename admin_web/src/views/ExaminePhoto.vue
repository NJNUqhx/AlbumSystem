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
                                <img :src="require('D:/GitHub/AlbumSystem/images/2/5.jpg')" class="img-fluid img-thumbnail" alt="待审核照片">
                            </div>
                        </div>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">所属用户: {{ photo.userId }}</li>
                            <li class="list-group-item">照片编号: {{ photo.photoId }}</li>
                            <li class="list-group-item">上传时间: {{ photo.time }}</li>
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
import { reactive } from 'vue'

export default {
    name: "ExaminePhoto",
    components: {
    },
    setup() {
        const photos = reactive([
            {
                address: "http://ceai.njnu.edu.cn/UploadFiles/2022-04/2/2022042012064779273.png",
                userId: "njnuqhx",
                photoId: 1,
                time: "20221117"
            },
            {
                address: "http://ceai.njnu.edu.cn/UploadFiles/2021-12/2/2021121911222992737.jpg",
                userId: "njnuxzl",
                photoId: 2,
                time: "20221118"
            },
            {
                address: "http://ceai.njnu.edu.cn/UploadFiles/2020-12/13/2020122515095885244.jpg",
                userId: "njnucsq",
                photoId: 3,
                time: "20221119"
            }
        ]);
        return {
            photos
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