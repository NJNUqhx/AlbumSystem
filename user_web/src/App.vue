<template>
  <NavBar :applications="applications"></NavBar>
  <router-view></router-view>
</template>

<script>
import NavBar from './components/NavBar'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap'
/* import {useStore} from 'vuex';
import $ from 'jquery'
import { ref } from 'vue' */

export default {
  name: 'App',
  components: {
    NavBar
  },
/*   setup() {
      const store = useStore();
      const jwt_token = localStorage.getItem("jwt_token");
      let applications = ref([]);

      const logout = () => {
        store.commit('logout');
      };

      const getAllApplicaitons = () =>{
            $.ajax({
                url: "http://127.0.0.1:3000/user/friend/getApplicationList/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    console.log(resp);
                    applications.value = resp;
                },
                error(resp) {
                  console.log(resp);
                }
            })
        }
      getAllApplicaitons();

      return {
        logout,
        getAllApplicaitons,
      }
    } */
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
