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
  setup(){
    // $.ajax({
    //   url: "http://127.0.0.1:3000/admin/account/token/",
    //   type: "post",
    //   data:{
    //     account: "adm001",
    //     password: "njnu"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // });
    // $.ajax({
    //   url: "http://127.0.0.1:3000/admin/account/info/",
    //   type: "get",
    //   headers:{
    //     Authorization: "Bearer "+ "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJlMmYwOGQ3MDdlMjQ0ZGYyOGE1NTZkMjQ2YzBiNzI1OSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY2ODg2MTE5NCwiZXhwIjoxNjcwMDcwNzk0fQ.-Swdit9lGK9Yo-SbhcLYQ6MQVVRp0Di4pCddvatLiAs"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // });
    // $.ajax({
    //   url: "http://127.0.0.1:3000/admin/account/register/",
    //   type: "post",
    //   data:{
    //     account: "adm004",
    //     password: "njnu",
    //     confirmedPassword: "njnu"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })
  }
}
</script>

<style>
</style>
