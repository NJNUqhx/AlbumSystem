<template>
    <div class="container content-field">
        <div class="card">
            <div class="cardbody">
               <slot></slot>
            </div>
        </div>
    </div>
</template>

<script>
export default{
    name: "ContentField"
}
</script>

<style scoped>
div.content-field{
    margin-top: 20px;
}
</style>