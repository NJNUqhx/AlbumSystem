<template>
    <ContentBase>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="userid" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="userid">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="exampleInputPassword1">
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

export default {
    name: 'RegisterView',
    components: {
        ContentBase,
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