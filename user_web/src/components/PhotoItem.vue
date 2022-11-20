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
      <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#editPhoto">显示信息</a></li>
      <li><a class="dropdown-item" href="#">下载照片</a></li>
      <li><a class="dropdown-item" href="#">编辑照片</a></li>
      <li><a class="dropdown-item" href="#">删除照片</a></li>
    </ul>
  </div>

    <!-- Modal -->
  <div class="modal fade" id="editPhoto" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">照片信息</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-7">
              <img class="file-img-2" src='~@/assets/scaleImg.jpg' />
            </div>
            <div class="col-5">
              <div class="card">
                <div class="card-body">
                  <div class="photo-name">照片名: 照片1</div>
                  <div class="createtime">创建时间: 2022.11.12</div>
                  <div class="createtime">权限：公开</div>
                  <div class="description">简介：....</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
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
.file-img-2{
  width: 400px;
  height: 400px;
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