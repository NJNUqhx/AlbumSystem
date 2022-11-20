<template>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <div class="photo-name">相册名: 相册1</div>
                <div class="createtime">创建时间: 2022.11.12</div>
                <div class="createtime">权限：公开</div>
                <div class="description">简介：....</div>
                <div class="editalbum"><button class="btn btn-success btn-primary " data-bs-toggle="modal" data-bs-target="#editAlbum">编辑相册信息</button></div>
            </div>
        </div>
    </div>

  <!-- Modal -->
  <div class="modal fade" id="editAlbum" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">编辑相册</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">相册名</label>
            <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="Album name">
          </div>
          
          <label for="basic-url" class="form-label">相册权限</label>
          <select class="form-select" aria-label="Default select example">
            <option selected>更改相册权限</option>
            <option value="1">公开</option>
            <option value="2">私有</option>
          </select>

          <div class="mb-3">
            <label for="formFileMultiple" id="add-photo" class="form-label">添加照片</label>
            <input class="form-control" type="file" id="formFileMultiple" multiple>
          </div>
          <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">相册简介</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary">创建</button>
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
  #add-photo {
    margin-top: 10px;
  }
  </style>