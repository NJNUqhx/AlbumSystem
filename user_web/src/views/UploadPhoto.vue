<template>
    <el-upload class="upload-demo" action="http://localhost:3000/user/photo/upload/backend/" :on-success="onSuccess"
        accept=".jpg" :limit="1" :headers="token">
        <el-button size="small" type="primary">点击上传</el-button>
        <div class="el-upload__tip">只能上传jpg/png文件,且不超过10MB</div>
    </el-upload>
</template>

<script>

export default {
    name: "UploadPhoto",
    methods: {
        onSuccess(response) {
            console.log(response);
        }
    }
}

</script>

<style>

</style>