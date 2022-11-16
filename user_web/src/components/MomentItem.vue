<template>
        <table class="album-table"><tr>
            <td><img class="user-img" src="https://cdn.acwing.com/media/user/profile/photo/170346_lg_d31ea692f7.png" alt=""></td>
            <td>
                <div class="user-name">John</div>
                <div class="createtime"> 今天 21:20</div>
            </td>
        </tr></table>

        <div class="moment-text">Hello!</div>
        <img class="moment-img" src="https://cdn.acwing.com/media/user/profile/photo/220549_lg_6499ed3641.jfif" alt="">
        <div class="row">
                <div class="col-10">
                </div>
                <div class="col-2">
                    <table class="album-table"><tr>
                        <td><img class="comment-img" src="~@/assets/1.png" alt=""></td>
                        <td><img class="comment-img" src="~@/assets/2.png" alt=""></td>
                        <td><img class="comment-img" src="~@/assets/3.png" alt=""></td>
                    </tr></table>
                </div>
        </div>
        <div class="card">
            <div class="card-header">
                评论区
            </div>
            <div class="card-body">
                <blockquote class="blockquote mb-0">
                    <div class="row">
                    <div class="col">
                        <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/196315_lg_6d53d078d3.jpg" alt="">
                    </div>
                    <div class="col-11">
                        <p>Hi, nice to meet you!</p>
                        <footer class="blockquote-footer">posted at <cite title="Source Title">21:20</cite></footer>
                        
                    </div>
                </div>

                </blockquote>
            </div>
        </div>
</template>
 
 <script>
     export default {
         name: "MomentItem",
     }
 </script>
 
 <style scoped>
  .user-name {
    font-size: 18px;
    margin-left: 10px;
  }
  .createtime {
    font-size: 14px;
    color: gray;
    margin-top: 5px;
    margin-left: 10px;
  }
  .user-img {
       border-radius: 50%;
       margin-left: 5px;
       width: 50px;
       height: 50px;
   }

   .comment-img {
       width: 30px;
       height: 30px;
       margin-right: 10px;
       margin-top: 5px;
       margin-bottom: 5px;
   }

   .img-fluid {
        border-radius: 50%;
        margin-left: 5px;
    }

    .moment-text {
        margin-top: 5px;
        margin-left: 5px;
    }

    .moment-img {
        margin-left: 5px;
    }
 </style>