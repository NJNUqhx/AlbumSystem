<template>
  <NavBar/>
  <router-view/>
</template>

<script>
  import 'bootstrap/dist/css/bootstrap.css';
  import 'bootstrap/dist/js/bootstrap';
  import NavBar from './components/NavBar';
  import $ from 'jquery'

  export default {
    name: "APP",
    components: {
        NavBar
    },
    

    setup(){
     /*  $.ajax({
      url: "http://127.0.0.1:3000/user/photo/add/",
      type: "post",
      data: {
        name: "sch",
        authority: 0,
        address: "C:\\Users\\godzilla\\Desktop\\1.png"
      },
      headers:{
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhNjA0YzhjOWU0NDE0YTRkODk4NDQ4YjMxNTA5MGI5ZiIsInN1YiI6IjQiLCJpc3MiOiJzZyIsImlhdCI6MTY3MDEyNzAxNSwiZXhwIjoxNjcxMzM2NjE1fQ.oe6j3n0dMTKBY4JCLh6Mpm9lZDbnTfLcgIiNFYgHGT0", 
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    });  
    $.ajax({
        url: "http://127.0.0.1:3000/user/account/token/",
        type: "post",
        data: {
          account: 'njnu00004',
          password: '123',
        },
        success(resp){
          console.log(resp);
        },
        error(resp){
          console.log(resp);
        }
      });
*/
      $.ajax({
       url: "http://127.0.0.1:3000/user/photo/getList/",
       type: "post",
       headers:{
         Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDg1MjU0NWIwMjk0NTY1OTdjYTFlNGQ4ZGE5MTJiMyIsInN1YiI6IjQiLCJpc3MiOiJzZyIsImlhdCI6MTY3MDA3MDE2OSwiZXhwIjoxNjcxMjc5NzY5fQ.tx2F7JMzvhq2P7M6UIi9KK9d6bT8jQcnOzd7Yw6AL9c", 
       },
       success(resp) {
         console.log(resp);
       },
       error(resp) {
         console.log(resp);
       }      
     });
     /*
     $.ajax({
      url: "http://127.0.0.1:3000/user/photo/getPhoto/",
      type: "post",
      data: {
        photo_id: 4,
      },
      headers:{
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDg1MjU0NWIwMjk0NTY1OTdjYTFlNGQ4ZGE5MTJiMyIsInN1YiI6IjQiLCJpc3MiOiJzZyIsImlhdCI6MTY3MDA3MDE2OSwiZXhwIjoxNjcxMjc5NzY5fQ.tx2F7JMzvhq2P7M6UIi9KK9d6bT8jQcnOzd7Yw6AL9c", 
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }      
    });
    $.ajax({
      url: "http://127.0.0.1:3000/user/photo/remove/",
      type: "post",
      data:{
        photo_id:12,
      },
      headers:{
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDg1MjU0NWIwMjk0NTY1OTdjYTFlNGQ4ZGE5MTJiMyIsInN1YiI6IjQiLCJpc3MiOiJzZyIsImlhdCI6MTY3MDA3MDE2OSwiZXhwIjoxNjcxMjc5NzY5fQ.tx2F7JMzvhq2P7M6UIi9KK9d6bT8jQcnOzd7Yw6AL9c", 
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }      
    });
    $.ajax({
      url: "http://127.0.0.1:3000/user/photo/update/",
      type: "post",
      data:{
        photo_id: 13,
        authority: 1,
        name: "new",
      },
      headers:{
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDg1MjU0NWIwMjk0NTY1OTdjYTFlNGQ4ZGE5MTJiMyIsInN1YiI6IjQiLCJpc3MiOiJzZyIsImlhdCI6MTY3MDA3MDE2OSwiZXhwIjoxNjcxMjc5NzY5fQ.tx2F7JMzvhq2P7M6UIi9KK9d6bT8jQcnOzd7Yw6AL9c", 
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }      
    });
      /* 
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/info/",
        type: "get",
        headers: {
          Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzZWFhOWFhOTAyMTg0MmVjYTBjZDcxMDBjYzY4MjE4NyIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY2OTcxMTc2NiwiZXhwIjoxNjcwOTIxMzY2fQ.xjX6saMlP-YtywGC0cfRqVQdHM-SpwbhmtI8x8Ewpas",
        },
        success(resp){
          console.log(resp);
        },
        error(resp){
          console.log(resp);
        }
      });

      $.ajax({
        url: "http://127.0.0.1:3000/user/account/register/",
        type: "post",
        data: {
          password: "njnu",
          confirmedPassword: "njnu",
          nickname: "njnuxzl"
        },
        success(resp){
          console.log(resp);
        },
        error(resp){
          console.log(resp);
        }
      }); */
    }
  }

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

/*color: #42b983;*/ 
nav a.router-link-exact-active {
  color: white;
}

body{
  background-image: url("~@/assets/background.jpg");
  background-size: cover;
}
</style>
