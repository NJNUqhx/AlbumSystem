<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <AlbumInfo></AlbumInfo>
      </div>
      <div class="col-9">
        <div class="card">
          <h5 class="card-header">当前位置: 相册1</h5>
          <div class="card-body">
            <table class="album-table">
              <tr>
                <td>
                  <PhotoItem>照片1</PhotoItem>
                </td>
                <td>
                  <PhotoItem>照片2</PhotoItem>
                </td>
                <td>
                  <PhotoItem>照片3</PhotoItem>
                </td>
              </tr>
            </table>
            <div class="btn-group">
              <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                aria-expanded="false">
                操作
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">添加照片</a></li>
                <li><a class="dropdown-item" href="#">删除照片</a></li>
              </ul>
            </div>
          </div>
        </div>
        <CommentTable></CommentTable>
      </div>
    </div>
  </div>
</template>
    
<script>

import AlbumInfo from '@/components/AlbumInfo';
import CommentTable from '@/components/CommentTable'
import PhotoItem from '@/components/PhotoItem';

export default {
  name: 'AlbumView',
  components: {
    AlbumInfo,
    CommentTable,
    PhotoItem
  },

}

</script>
  
<style scoped>
.card {
  margin-top: 20px;
}
</style>