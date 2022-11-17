<template>
    <ContentField>
        <div class="row">
            <div class="col-3">
                <img src="http://ceai.njnu.edu.cn/UploadFiles/2022-04/2/2022042012064779273.png" class="img-fluid"
                    alt="待审核照片">
            </div>
            <div class="col-3">
                <ul class="list-group">
                    <li class="list-group-item">所属用户: </li>
                    <li class="list-group-item">照片编号: </li>
                </ul>
            </div>
            <div class="col-3">
                <div class="input-group">
                    <textarea class="form-control" placeholder="审核意见"></textarea>
                </div>
            </div>
            <div class="col-3">
                <div class="btn-group-vertical">
                    <button class="btn btn-success">审核通过</button>
                    <button class="btn btn-danger">审核失败</button>
                </div>
            </div>
        </div>
    </ContentField>
</template>

<script>
import ContentField from '../components/ContentField'

export default {
    name: "ExaminePhoto",
    components: {
        ContentField
    }
}
</script>

<style scoped>
.container {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
}
</style>