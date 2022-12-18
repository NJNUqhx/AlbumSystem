<template>
    <ContentBase>
        <div class="row">
            <div class="col-3">
                <UserProfileInfo @follow="follow" @unfollow="unfollow" :user="user"></UserProfileInfo>
                <UserProfileWrite @posting="posting"></UserProfileWrite>
            </div>
            <div class="col-9">
                <div class="card">
                <div class="card-body">
                    <MomentItem></MomentItem>
                </div>
                </div>
            </div>
        </div>
    </ContentBase>
</template>


<script>
import ContentBase from '../components/ContentBase'
import UserProfileInfo from '../components/UserProfileInfo'
//import UserProfilePost from '../components/UserProfilePost'
import UserProfileWrite from '../components/UserProfileWrite'
import MomentItem from '@/components/MomentItem'
import {reactive} from 'vue'
import {useRoute} from 'vue-router'


export default {
    name: 'MomentView',
    components: {
    ContentBase,
    UserProfileInfo,
    //UserProfilePost,
    UserProfileWrite,
    MomentItem
},
    setup() {
        //?好像有问题，课程2 27：44
        const route = useRoute();
        const userId = route.params.userId;
        console.log(userId);

        const user = reactive({
            id: 1,
            username: "Godzilla",
            lastName: "Xue",
            firstName: "Zilong",
            followers: 150,
            is_followed: false, 
        });
        
        const posts = reactive({
            count: 3,
            posts: [
                {
                    id: 1,
                    userId: 1,
                    content: "这是我的第一篇帖子。"
                },
                {
                    id: 3,
                    userId: 1,
                    content: "这是我的第二篇帖子。"
                },
                {
                    id: 3,
                    userId: 1,
                    content: "这是我的第三篇帖子。"
                }
            ]
        });

        const follow = () => {
            if(user.is_followed) return;
            user.is_followed = true;
            user.followers++;
        };

        const unfollow = () => {
            if(!user.is_followed) return;
            user.is_followed = false;
            user.followers--;
        }
        
        const posting = (content) => {
            posts.count++;
            posts.posts.unshift ({
                id: posts.count,
                userId: 1,
                content: content,
            })
        }
        return {
            user,
            follow,
            unfollow,
            posts,
            posting
        }
    }
}
</script>

<style scoped>

</style>