<template>
    <div class="container">
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th style="width: 40%;"><span>待审核照片</span></th>
                    <th style="width: 40%"><span>照片信息</span></th>
                    <th style="width: 20%"><span>审核操作</span></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="photo in photos" :key="photo.photoId">
                    <td>
                        <div class="card">
                            <div class="card-body">
                                <img :src="require('D:/GitHub/AlbumSystem/images/' + photo.userId + '/' + photo.photoId + '.jpg')"
                                    class="img-fluid img-thumbnail" alt="待审核照片">
                            </div>
                        </div>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item"><b>用户编号:</b> {{ photo.userId }}</li>
                            <li class="list-group-item"><b>照片编号:</b> {{ photo.photoId }}</li>
                            <li class="list-group-item"><b>照片名称:</b> {{ photo.name }}</li>
                            <li class="list-group-item"><b>上传时间:</b> {{ photo.time }}</li>
                            <li class="list-group-item"><b>访问权限:</b> {{ showAuthority(photo.authority) }}</li>
                            <li class="list-group-item"><b>审核状态:</b> {{ showStatus(photo.status) }}</li>
                            <li class="list-group-item"><b>存储地址:</b> {{ photo.address }}</li>
                        </ul>
                    </td>
                    <td>
                        <div class="input-group">
                            <textarea class="form-control" :id="'advice' + photo.photoId" placeholder="审核意见"
                                style="height: 100px;"></textarea>
                        </div>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success" @click="photoExamine(photo, 1)">审核通过</button>
                            <button class="btn btn-danger" @click="photoExamine(photo, 2)">审核失败</button>
                            <button class="btn btn-info" @click="photoExamine(photo, 0)">撤销审核</button>
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
        let advice = ref('');
        let target = ref('');
        const jwt_token = localStorage.getItem("jwt_token");
        const getPhotoListAll = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/photo/list/all/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    photos.value = resp;
                },
                error() {
                    console.log("error");
                }
            })
        }
        const showStatus = (status) => {
            if (String(status) === "0")
                return "待审核";
            else if (String(status) === "1")
                return "审核通过";
            else if (String(status) === "2")
                return "审核失败";
            else
                return "状态出错";
        }
        const showAuthority = (authority) => {
            if (String(authority) === "0")
                return "所有人可见";
            else if (String(authority) === "1")
                return "仅好友可见";
            else if (String(authority) === "2")
                return "仅自己可见";
            else
                return "状态出错";
        }
        const photoExamine = (photo, examination) => {
            target = "#advice" + photo.photoId;
            advice = $(target).val();
            $.ajax({
                url: "http://127.0.0.1:3000/admin/photo/examine/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    photoId: photo.photoId,
                    userId: photo.userId,
                    advice: advice,
                    examine: examination
                },
                success(resp) {
                    if (resp.error_message !== "success") {
                        alert(resp.error_message)
                    }
                },
                error() {

                }
            })
            $(target).val("");
            getPhotoListAll();
        }
        getPhotoListAll();
        return {
            photos,
            getPhotoListAll,
            showStatus,
            showAuthority,
            photoExamine,
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

.error-message {
    color: red;
}
</style>