<template>
  <NavBar/>
  <router-view/>
</template>

<script>
  import 'bootstrap/dist/css/bootstrap.css';
  import 'bootstrap/dist/js/bootstrap';
  import NavBar from './components/NavBar';
  //import $ from 'jquery'

  export default {
    name: "APP",
    components: {
        NavBar
    },
/*
    setup(){
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/token/",
        type: "post",
        data: {
          account: 'njnu00001',
          password: 'njnu',
        },
        success(resp){
          console.log(resp);
        },
        error(resp){
          console.log(resp);
        }
      });

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
      });
    }*/
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
