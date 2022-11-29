<template>
  <NavBar/>
  <router-view/>
</template>

<script>
  import 'bootstrap/dist/css/bootstrap.css';
  import 'bootstrap/dist/js/bootstrap';
  import NavBar from './components/NavBar';

  export default {
    name: "APP",
    components: {
        NavBar
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
