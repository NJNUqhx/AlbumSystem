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
              <a href="#" class="btn btn-primary btn-lg">新建相册</a>
            </div>
        </div>
      </div>
    </div>
    <FooterView></FooterView>
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

  .td {
    text-align: center;
  }
  </style>