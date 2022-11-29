import $ from 'jquery';
//import jwt_decode from 'jwt-decode';

export default  {
    state: {
        userId: "",
        account: "",
        nickname: "",
        token: "",
        is_login: false,
        pulling_info: true,
    },
  
    //通过计算获取state中的数据
    getters: {
    },
    //对state修改值
    mutations: {
        updateUser(state, user) {
            state.userId = user.userId;
            state.account = user.account;
            state.nickname = user.nickname;
            state.is_login = user.is_login;
        },
        updateToken(state, token) {
            state.token = token;
        },
        logout(state) {
            state.userId= "";
            state.account = "";
            state.nickname = "";
            state.token = "";
            state.is_login = false;
        },
        updatePullingInfo(state, pulling_info){
            state.pulling_info = pulling_info;
        },
    },
    //定义对state的操作/更新方式：更新用户名
    actions: {
        login(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/token/",
                type: "post",
                data: {
                    account: data.account,
                    password: data.password,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        localStorage.setItem("jwt_token", resp.token);
                        context.commit("updateToken", resp.token); 
                        data.success(resp);        
                    }else {
                        data.error(resp);
                    }
                },
                error(resp) {
                  data.error(resp); 
                }
              });
        },
          getinfo(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateUser", {
                            ...resp,
                            is_login: true,
                        });
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }
            })
        },
        logout(context) {
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        }
    },
    //对state进行分割
    modules: {
    }
}
