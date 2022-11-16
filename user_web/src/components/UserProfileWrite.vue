<template>
    <div class="card edit-field">
        <div class="card-body">
            <label for="edit-post" class="form-label">编辑动态</label>
            <textarea v-model="content" class="form-control" id="edit-post" rows="3"></textarea>
            <div align="right">
            <button @click="posting" class="btn btn-primary" btn-sm>发表动态</button>
            </div>
        </div>
    </div>
</template>

<script>
import { ref } from '@vue/reactivity'

export default {
    name: "UserProfileWrite",
    setup(props, context) {
        let content = ref('');
        
        const posting = () => {
            context.emit('posting', content.value);
            content.value = "";
        };

        return {
            content,
            posting,
        }
    }
}
</script>

<style scoped>
.edit-field {
    margin-top: 20px;
}

button {
    margin-top: 10px;
}
</style>