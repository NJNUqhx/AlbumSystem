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
                <td><PhotoItem>照片1</PhotoItem></td>
                <td><PhotoItem>照片2</PhotoItem></td>
                <td><PhotoItem>照片3</PhotoItem></td>
              </tr></table>
              <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#uploadPhoto">上传照片</a>
          
            </div>
        </div>
      </div>
    </div>
    <FooterView></FooterView>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="uploadPhoto" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">上传照片</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">照片名</label>
            <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="Album name">
          </div>

          <div class="mb-3">
            <label for="formFileMultiple" id="add-photo" class="form-label">选择照片</label>
            <input class="form-control" type="file" id="formFileMultiple" multiple>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary">添加</button>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
  //import ContentBase from '../components/ContentBase';
  //import HomeBase from '@/components/HomeBase';
  //import AlbumInfo from '@/components/AlbumInfo';
  //import AlbumItem from '@/components/AlbumItem'
  import FooterView from '@/components/Footer'
  import PhotoItem from '@/components/PhotoItem';

  export default {
    name: 'MyPhotoView',
    components: {
      //ContentBase,
      //HomeBase,
      //AlbumInfo,
      PhotoItem,
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
  </style>