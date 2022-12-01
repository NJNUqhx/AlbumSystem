<template>
    <ContentBase>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="register">
                    <div class="mb-3">
                        <label for="nickname" class="form-label">昵称</label>
                        <input v-model="nickname" type="text" class="form-control" id="userid">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password">
                    </div>
                    <div class="mb-3">
                        <label for="confirmedPassword" class="form-label">确认密码</label>
                        <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword">
                    </div>
                    <div class="error-message">{{error_message}}</div>
                    <button type="submit" class="btn btn-primary">注册</button>
                </form>
            </div>    
        </div>
    </ContentBase>
</template>


<script>
import ContentBase from '../components/ContentBase'
import { ref } from 'vue'
import router from '../router/index'
import $ from 'jquery'

export default {
    name: 'RegisterView',
    components: {
        ContentBase,
    },
    setup() {
        let nickname = ref('');
        let password = ref('');
        let confirmedPassword = ref('');
        let error_message = ref('');

        const register = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/register/",
                type: "post",
                data: {
                    password: password.value,
                    confirmedPassword: confirmedPassword.value,
                    nickname: nickname.value
                },
                success(resp) {
                    if (resp.error_message == "success")
                        router.push({ name: "login" });
                    else {
                        error_message.value = resp.error_message;
                    }
                }
            })
        }
        return {
            nickname,
            password,
            confirmedPassword,
            error_message,
            register
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