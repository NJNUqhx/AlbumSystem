<template>
    <ContentBase>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="account" class="form-label">账号</label>
                        <input v-model="account" type="text" class="form-control" id="userid">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="exampleInputPassword1">
                    </div>
                    <div class="error-message">{{error_message}}</div>
                    <button type="submit" class="btn btn-primary">登录</button>
                </form>
            </div>    
        </div>
    </ContentBase>
</template>


<script>
import ContentBase from '../components/ContentBase'
import {ref} from 'vue'
import { useStore } from 'vuex';
import router from '@/router/index';

export default {
    name: 'LoginView',
    components: {
        ContentBase,
    },

    setup() {
        const store = useStore();
        let account = ref('');
        let password = ref('');
        let error_message = ref('');

        const jwt_token = localStorage.getItem("jwt_token");
        if(jwt_token){
            store.commit("updateToken", jwt_token);
            store.dispatch("getinfo",{
                success(){
                    router.put({name: "home"});
                    store.commit("updatePullingInfo", false);
                },
                error(){
                    store.commit("updatePullingInfo", false);
                }
            })
        }else{
            store.commit("updatePullingInfo", false);
        }

        const login = () => {
            error_message.value = "";
            store.dispatch("login", {
                account: account.value,
                password: password.value,
                success() {
                    console.log(account);
                    store.dispatch("getinfo", {
                        success() {
                            router.push({ name: 'home' });
                            // console.log(store.state.admin);
                        },
                        error(){
                            console.log("获取用户信息失败");
                        }
                    })
                },
                error() {
                    error_message.value = "账号或密码错误";
                }
            })
        };

        return {
            account,
            password,
            error_message,
            login,
        }
    }
}
</script>

<style scoped>

button {
    width: 100%;
}

.error-message {
    color: red;
}

</style>