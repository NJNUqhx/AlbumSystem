<template>
  <img class="file-img" src='~@/assets/scaleImg.jpg' />
  <div class="file-name">
    <slot></slot>
  </div>
  <div class="btn-group">
    <button type="button" class="btn btn-sm btn-danger dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
      操作
    </button>
    <ul class="dropdown-menu">
      <li><a class="dropdown-item" href="#">下载照片</a></li>
      <li><a class="dropdown-item" href="#">编辑照片</a></li>
      <li><a class="dropdown-item" href="#">删除照片</a></li>
    </ul>
  </div>
</template>
  

<script>
export default {
  name: "AlbumInfo",

}
</script>
  
<style scoped>
.container {
  margin-top: 20px;
}

.file-img {
  width: 120px;
  height: 120px;
  margin-right: 60px;
}

.file-name {
  margin-top: 5px;
  margin-left: 35px;
  /* height: 44px; */
  margin-bottom: 5px;
  line-height: 22px;
  font-size: 15px;
}
.btn-group {
  margin-left: 30px;
}
</style>