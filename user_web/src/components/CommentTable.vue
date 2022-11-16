<template>
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
            评论
        </div>
        <div class="card-body">
            <blockquote class="blockquote mb-0">
                <div class="row">
                <div class="col">
                    <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/142492_lg_7c84a89707.jpg" alt="">
                </div>
                <div class="col-11">
                    <p>A well-known quote, contained in a blockquote element.</p>
                    <footer class="blockquote-footer">posted at <cite title="Source Title">21:20</cite></footer>
                    
                </div>
            </div>
    
            </blockquote>
        </div>
    </div>
</template>
  
  <script>
      export default {
          name: "CommentTable",
      }
  </script>
  
  <style scoped>
   
   .img-fluid {
        border-radius: 50%;
        margin-left: 5px;
    }

    .comment-img {

        width: 30px;
        height: 30px;
        margin-right: 10px;
        margin-top: 5px;
        margin-bottom: 5px;
    }

  </style>