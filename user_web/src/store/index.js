import { createStore } from 'vuex'
import ModuleUser from './user'

export default createStore({
  //用户名，id等
  state: {
    id:"",
    username: "",
    photo:"",
    followerCount: 0,
  },

  //通过计算获取state中的数据
  getters: {
  },
  //对state修改值
  mutations: {
  },
  //定义对state的操作/更新方式：更新用户名
  actions: {
    login(){
      
    }
  },
  //对state进行分割
  modules: {
    user: ModuleUser,
  }
})
