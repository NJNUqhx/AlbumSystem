import $ from 'jquery'
export default {
    state: {
        adminId: "",
        account: "",
        token: "",
        is_login: false,
    },


    getters: {
        updateAdmin(state, admin) {
            state.adminId = admin.adminId;
            state.account = admin.account;
            state.token = admin.token;
            state.is_login = admin.is_login;
        },
        updateToken(state, token) {
            state.token = token;
        },
        logout(state) {
            state.adminId = "";
            state.account = "";
            state.token = "";
            state.is_login = false;
        }

    },

    mutations: {
    },

    actions: {
        login(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/admin/account/token/",
                type: "post",
                data: {
                  username: data.account,
                  password: data.password,
                },
                success(resp) {
                    if (resp.error_message === "success") {
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
            context.commit("logout");
        }

    },
    modules: {

    }
}