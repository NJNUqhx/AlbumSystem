<template>
    <div class="card">
        <div class="card-body">
            <div v-for="post in posts.posts" :key="post.id">
                <div class="card single">
                    <div align="left" class="card-body">
                        {{post.content}}
                    </div>
                </div>    
            </div>
        </div>
    </div>
</template>

<script>
export default {
        name: "UserProfilePost",
        props: {
            posts: {
                type: Object,
                required: true,
            },
        }
    }
</script>

<style scoped>
    .single {
        margin-bottom: 10px;
    }
</style>