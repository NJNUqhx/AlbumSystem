<template>
    <div class="container">
        <div class="card text-center">
            <div class="card-header">
                用户指南
            </div>
            <div class="card-body">
                <h2 class="card-title">欢迎使用电子相册系统</h2>
                <p class="card-text">简介...</p>
                <a href="#" class="btn btn-primary" @click="visitPhoto()">Click to start</a>
            </div>
        </div>
    </div>
</template>
  
  <script>
  import router from '@/router/index';

      export default {
          name: "HomeBase",
          setup(){
            //访问用户相册
            const visitPhoto = () =>{
              //跳转到用户相册
              router.push( 'myphoto');
            }
            return {
              visitPhoto,
            }
          }
      }
  </script>
  
  <style scoped>
    .btn-primary {
        margin-top: 20px;
        color: white;
        background-color: black;
    }
    .card{
        margin-top: 30px;
        background-color: rgba(255,255,255,0.5);
    }
    .card-header {
        color: black;
        font-weight: bold;
    }
  </style>