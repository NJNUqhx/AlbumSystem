<template>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-3">
                    <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/142492_lg_7c84a89707.jpg" alt="">
                </div>
                <div class="col-9">
                    <div align="left" class="username">{{fullName}}</div>
                    <div align="left" class="fans">点赞: {{user.followers}}</div>
                    <div align="left">
                        <button @click="follow" v-if="!user.is_followed" align="left" type="button" class="btn btn-warning btn-sm">+点赞</button>
                        <button @click="unfollow" v-if="user.is_followed" align="left" type="button" class="btn btn-warning btn-sm">取消点赞</button>
                    </div>
                </div>
            </div>
        </div> 
    </div>
</template>
    
<script>
    import {computed} from 'vue';

    export default {
        name: "UserProfileInfo",
        props: {
            user: {
                type: Object,
                required: true,
            },
        },
        setup(props, context) {
            let fullName = computed(() => props.user.lastName + " " + props.user.firstName);
            
            const follow = () => {
                context.emit('follow');
            };

            const unfollow = () => {
                context.emit('unfollow');
            }

            return {
                fullName,
                follow,
                unfollow
            }
        }
    }
</script>
    
<style scoped>
    img {
        border-radius: 50%;
        margin-left: 5px;
    }
    .username {
        font-weight: bold;
    }
    .fans {
        font-size: 12px;
        color: grey;
    }
    button {
        padding: 2px 4px;
        font-size: 12px;
    }
</style>