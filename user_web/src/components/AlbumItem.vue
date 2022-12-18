<template>
  <div>
<!--     <div class="card">
      <div class="body"> -->
        <img class="file-img" src='~@/assets/folder3.png' @click="album_display" />
<!--         <div class="file-name">
          <slot></slot>
        </div> -->
        <div class="file-name">{{album.name}}</div>
<!--       </div>
    </div> -->
  </div>
</template>
  


<script>
import router from '@/router/index';
export default {
  name: "AlbumItem",
  
  props: {
            album: {
                type: Object,
                required: true,
            },
        },
  
  setup(props) {
    //const album = 
    const album_display = () => {
      /*将album.name作为路由参数*/
      router.push({ name: 'myalbum_1', params:{album_id: props.album.albumId }});
    };

    return {
      album_display,
    }
  }
}
</script>
  
<style scoped>
.container {
  margin-top: 20px;
}

.file-img {
  width: 170px;
  height: 170px;
  text-align: center;
  cursor: pointer;
}

.file-name {
  /* margin-top: 5px;
  margin-left: 8px; */
  text-align: center;
  /* height: 44px;
  line-height: 22px; */
  font-size: 25px;
}
.card{
  margin-left:10px;
  margin-right: 10px;
}
</style>