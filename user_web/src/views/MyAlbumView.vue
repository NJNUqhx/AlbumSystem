<template>
  <div class="container">
    <div class="row">
      <div class="col">
      </div>
      <div class="col-12">
        <div class="card">
          <h5 class="card-header">当前位置：</h5>
            <div class="card-body">
              <table class="album-table"><tr>
                <td><AlbumItem>相册1</AlbumItem></td>
                <td><AlbumItem>相册2</AlbumItem></td>
                <td><AlbumItem>相册3</AlbumItem></td>
              </tr></table>
              <a href="#" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#createAlbum">新建相册</a>
            </div>
        </div>
      </div>
    </div>
    <FooterView></FooterView>
  </div>

<!-- Modal -->
  <div class="modal fade" id="createAlbum" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">新建相册</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">相册名</label>
            <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="Album name">
          </div>
          
          <label for="basic-url" class="form-label">相册权限</label>
          <select class="form-select" aria-label="Default select example">
            <option selected>选择相册权限</option>
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
  import AlbumItem from '@/components/AlbumItem'
  import FooterView from '@/components/Footer'

  export default {
    name: 'MyAlbumView',
    components: {
      AlbumItem,
      FooterView,
    },

  }

  </script>

  <style scoped>
  .card {
    margin-top: 20px;
  }
  .album-table {
    margin-bottom: 150px;
  }
  #add-photo {
    margin-top: 10px;
  }
</style>