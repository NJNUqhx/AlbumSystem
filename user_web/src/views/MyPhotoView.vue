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
              <a href="#" class="btn btn-primary">上传照片</a>
          
            </div>
        </div>
      </div>
    </div>
    <FooterView></FooterView>
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