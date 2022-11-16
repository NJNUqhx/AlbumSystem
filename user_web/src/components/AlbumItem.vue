<template>
  <div>
    <div class="card">
      <div class="body">
        <img class="file-img" src='~@/assets/folder3.png' @click="album_display" />
        <div class="file-name">
          <slot></slot>
        </div>
      </div>
    </div>
  </div>
</template>
  


<script>
import router from '@/router/index';
export default {
  name: "AlbumItem",

  setup() {
    const album_display = () => {
      router.push({ name: 'myalbum_1' });
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
  width: 200px;
  height: 200px;
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