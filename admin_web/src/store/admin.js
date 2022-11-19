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
                    account: data.account,
                    password: data.password
                },
                success(resp) {
                    if (resp.error_message === "success"){
                        context.commit("updateToken", resp.token);
                        data.success(resp);
                    }else{
                        data.error(resp);
                    }
                        
                },
                error(resp) {
                    console.log(resp);
                }
            });
        }
    },

    modules: {

    }
}