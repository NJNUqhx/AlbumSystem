<template>
    <div class="container">
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th style="width: 40%;"><span>待处理评论</span></th>
                    <th style="width: 25%"><span>评论信息</span></th>
                    <th style="width: 25%"><span>处理意见</span></th>
                    <th style="width: 10%"><span>处理操作</span></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="photo in photos" :key="photo.id">
                    <td>
                        <div class="card">
                            <div class="card-body">
                                
                            </div>
                        </div>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">所属用户: </li>
                            <li class="list-group-item">评论编号: </li>
                            <li class="list-group-item">举报时间: </li>
                        </ul>
                    </td>
                    <td>
                        <div class="input-group">
                            <textarea class="form-control" placeholder="处理意见" style="height: 100px;"></textarea>
                        </div>
                    </td>
                    <td>
                        <div class="btn-group-vertical">
                            <button class="btn btn-success" style="margin-top: 10%">通过</button>
                            <button class="btn btn-danger">拒绝</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

export default {
    name: "HandleComment",
    components: {
    }
}
</script>

<style scoped>
/* .container {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
} */
.table{
    text-align: center;
}
</style>