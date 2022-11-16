<template>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <div class="photo-name">相册名: 相册1</div>
                <div class="createtime">创建时间: 2022.11.12</div>
                <div class="description">简介：....</div>
                <div class="editalbum"><button class="btn btn-success btn-primary">编辑相册信息</button></div>
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
  .container{
    margin-top: 20px;
  }
  .createtime {
    margin-top: 10px;
  }
  .description {
    margin-top: 10px;
    margin-bottom: 10px;
  }
  </style>