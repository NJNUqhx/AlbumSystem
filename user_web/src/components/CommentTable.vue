<template>
     <div class="row">
            <div class="col-10">
            </div>
            <div class="col-2">
                <table class="album-table"><tr>
                    <td><img class="comment-img" src="~@/assets/1.png" alt="" @click="star()"></td>
                    <td>{{album_info.star}}</td>
<!--                     <td><img class="comment-img" src="~@/assets/2.png" alt=""></td>
                    <td><img class="comment-img" src="~@/assets/3.png" alt=""></td> -->
                </tr></table>
            </div>
     </div>
    <div class="card">
        <div class="card-header">
            评论
        </div>
        <div class="card-body">
          <!-- 列举所有评论 -->
          <div v-for="comment in comments" :key= "comment.commentId">
            <blockquote class="blockquote mb-0">
                <div class="row">
                    <div class="col">
                        <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/142492_lg_7c84a89707.jpg" alt="">
                    </div>
                    <div class="col-10">
                        <p>{{comment.content}}</p>
                        <footer class="blockquote-footer">posted at <cite title="Source Title">{{comment.time}}</cite></footer>
                    </div>
                    <div class="col">
                      <img class="comment-img" src="~@/assets/report.png" alt="" data-bs-toggle="modal" :data-bs-target="('#report-Comment'+comment.commentId)">
                    </div>
                </div>
            </blockquote>

            <!-- 举报评论 -->
              <div class="modal fade" :id="('report-Comment'+comment.commentId)" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">举报评论</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">          
                      <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">评论</label>
                        <textarea v-model="reason" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                      <button type="button" class="btn btn-primary"  @click="comment_report(comment)">确认</button>
                    </div>
                  </div>
                </div>
              </div>
          </div>

           <!--  <blockquote class="blockquote mb-0">
                <div class="row">
                    <div class="col">
                        <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/142492_lg_7c84a89707.jpg" alt="">
                    </div>
                    <div class="col-11">
                        <p>A well-known quote, contained in a blockquote element.</p>
                        <footer class="blockquote-footer">posted at <cite title="Source Title">21:20</cite></footer>
                    </div>
                </div>
            </blockquote> -->
            <button type="button" id="makeComment" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#make-Comment">评论</button>
        </div>
    </div>

<!-- 发表评论 -->
<div class="modal fade" id="make-Comment" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">发表评论</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">          
        <div class="mb-3">
          <label for="exampleFormControlTextarea1" class="form-label">评论</label>
          <textarea v-model="new_content" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" @click="add_comment()">发表</button>
      </div>
    </div>
  </div>
</div>

</template>
  
  <script>
  import { ref } from 'vue'
  import $ from 'jquery'
  //import router from '@/router/index';
  import { Modal } from 'bootstrap/dist/js/bootstrap'
//import { emit } from 'process'

      export default {
          name: "CommentTable",
          props: {
            comments: {
                required: true,
            },
            album_id: {
                type: String,
                required: true,
            },
        },
        data(props) {
            return {
              new_comments: props.comments
            }
          },
        watch: {
            comments(val){
                this.new_comments = val;
            }
          },
        setup(props) {
          //const new_comment = ref('');
          const jwt_token = localStorage.getItem("jwt_token");
         /*  const get_index= () => {
                console.log(router.currentRoute.value.path);
              }
          get_index(); */
          //添加评论
          let new_content = ref('');
          let likes = ref('');
          let album_info = ref('');
          let reason = ref('');

          const add_comment = () => {
              $.ajax({
                url: "http://127.0.0.1:3000/user/comment/addToAlbum/",
                type: "post",
                headers:{
                  Authorization: "Bearer " + jwt_token, 
                },
                data: {
                  album_id: props.album_id,
                  content: new_content.value,
                },
                success(resp) {
                  console.log(resp);
                  /* 
                    调用父函数更新
                    ... 
                  */
                  //this.emit('get_comments');
                  Modal.getInstance('#make-Comment').hide();
                },
                error(resp) {
                  console.log(resp);
                  console.log(new_content.value);
                  //console.log("id:" + props.album_id);
                }
              });
            }
          const star = () => {
            $.ajax({
              url: "http://127.0.0.1:3000/user/album/updateStar/",
              type: "post",
              headers:{
                Authorization: "Bearer " + jwt_token, 
              },
              data: {
                album_id: props.album_id,
              }, 
              success(resp) {
                console.log(resp);
                get_AlbumInfo();

              },
              error(resp) {
                console.log(resp);
              }      
            });
          }
          //获取相册信息
          const get_AlbumInfo = () => {
              $.ajax({
                url: "http://127.0.0.1:3000/user/album/getAlbum/",
                type: "post",
                headers:{
                  Authorization: "Bearer " + jwt_token, 
                },
                data: {
                  album_id: props.album_id,
                },
                success(resp) {
                  console.log(resp);
                  album_info.value = resp;
                },
                error(resp) {
                  console.log(resp);
                }      
              });
            }
          get_AlbumInfo();


          const comment_report = (comment) => {
            $.ajax({
              url: "http://127.0.0.1:3000/user/comment/report/",
              type: "post",
              headers:{
                Authorization: "Bearer " + jwt_token, 
              },
              data: {
                comment_id: comment.commentId,
                reason: reason.value,
              }, 
              success(resp) {
                console.log(resp);
                reason.value="";
                Modal.getInstance('#report-Comment' + comment.commentId).hide();
              },
              error(resp) {
                console.log(resp);
              }      
            });
          }

          return {
            album_info,
            new_content,
            likes,
            reason,
            comment_report,
            add_comment,
            star,
            get_AlbumInfo,
          }
        }
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
    .comment-img:hover {
      cursor: pointer;
    }
    #makeComment{
        float: right;
    }
  </style>