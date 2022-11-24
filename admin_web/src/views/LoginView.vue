<template>
    <ContentField>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="account" class="form-label">账号</label>
                        <input v-model="account" type="text" class="form-control" id="account" placeholder="请输入账号">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码" autocomplete="off">
                    </div>
                    <div class="error-message">
                        {{error_message}}
                    </div>
                    <button type="submit" class="btn btn-primary">登录</button>
                </form>
            </div>
        </div>
    </ContentField>
</template>

<script>
import ContentField from '../components/ContentField'
import { useStore } from 'vuex'
import { ref } from 'vue'
import router from '../router/index'

export default {
    name: "LoginView",
    components: {
        ContentField
    },
    setup(){
        const store = useStore();
        let account = ref('');
        let password = ref('');
        let error_message = ref('');
        const login = () => {
            error_message.value = "";
            store.dispatch("login", {
                account: account.value,
                password: password.value,
                success() {
                    store.dispatch("getinfo", {
                        success() {
                            router.push({ name: 'home' });
                            // console.log(store.state.admin);
                        }
                    })
                },
                error() {
                    error_message.value = "账号或密码错误";
                }
            })
        };
        return{
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
div.error-message{
    color: red;
}
</style>