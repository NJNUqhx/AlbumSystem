<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from './components/NavBar'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap'
import $ from 'jquery'

export default {
  name: "App",
  components: {
    NavBar
  },
  setup() {
    $.ajax({
      url: "http://127.0.0.1:4000/user/account/token/",
      type: "post",
      data: {
        password: "njnu",
        account: "njnu00001"
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    });
  }
}
</script>

<style>
body {
  background-image: url("~@/assets/images/background.jpg");
  background-size: cover;
}
</style>
