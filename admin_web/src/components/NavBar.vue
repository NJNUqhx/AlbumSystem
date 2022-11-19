<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <router-link class="navbar-brand" :to="{name: 'home'}">AlbumSystem</router-link>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link :class="route_name == 'examinephoto' ? 'nav-link active' : 'nav-link'" :to="{name: 'examinephoto'}">审核照片</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'examinemoment' ? 'nav-link active' : 'nav-link'" :to="{name: 'examinemoment'}">审核动态</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'handlecomment' ? 'nav-link active' : 'nav-link'" :to="{name: 'handlecomment'}">处理评论</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'handlemoment' ? 'nav-link active' : 'nav-link'" :to="{name: 'handlemoment'}">处理动态</router-link>
        </li>
      </ul>
      <ul class="navbar-nav" style="margin-right:10%">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            QHX
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown" >
            <li><a class="dropdown-item" href="#">登录</a></li>
            <li><a class="dropdown-item" href="#">注销</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
</template>

<script>
import { useRoute } from 'vue-router'
import { computed } from 'vue'

export default{
  setup(){
    const route = useRoute();
    let route_name = computed(() => route.name)
    return {
      route_name
    }
  }
}
</script>

<style scoped>
</style>

