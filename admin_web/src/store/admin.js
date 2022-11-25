import $ from 'jquery'

export default {
    state: {
        adminId: "",
        account: "",
        token: "",
        is_login: false,
        pulling_info: true,
    },
    getters: {
    },
    mutations: {
        updateAdmin(state, admin) {
            state.adminId = admin.adminId;
            state.account = admin.account;
            state.token = admin.token;
            state.is_login = admin.is_login;
        },
        updateToken(state, token) {
            state.token = token;
        },
        updatePullingInfo(state, pulling_info){
            state.pulling_info = pulling_info;
        },
        logout(state) {
            state.adminId = "";
            state.account = "";
            state.token = "";
            state.is_login = false;
        }
    },

    actions: {
        login(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/account/token/",
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
                    } else {
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
                url: "http://127.0.0.1:3000/admin/account/info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateAdmin", {
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
    modules: {

    }
}