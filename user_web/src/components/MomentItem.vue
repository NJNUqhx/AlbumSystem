<template>
      <!-- 动态内容 -->
        <table class="album-table"><tr>
            <!-- 照片&姓名需要修改 -->
            <td><img class="user-img" src="https://cdn.acwing.com/media/user/profile/photo/220549_lg_6499ed3641.jfif" alt=""></td>
            <td>
                <div class="user-name">John</div>
                <div class="createtime"> {{moment.time}}</div>
            </td>
        </tr></table>
      <!-- 点赞 -->
      <!-- <el-image v-for=" item in urllist" :key="item.url" :src="require('C://tmp//'+item.url+'.png')" style="width: 100px;height:100px;display: block;margin-bottom: 20px" /> -->
        <div class="moment-text"></div>
        <img class="moment-img" :src="require('D:/GitHub/AlbumSystem/images/' + moment.userId + '/' + photo1 + '.jpg')" alt="">
        <div class="row">
          <div class="col-10">
          </div>
          <div class="col-2">
              <table class="album-table"><tr>
                  <td><img class="comment-img" src="~@/assets/1.png" alt="" @click="star()"></td>
                  <td>{{moment.star}}</td>
                  <!-- <td><img class="comment-img" src="~@/assets/2.png" alt=""></td>
                  <td><img class="comment-img" src="~@/assets/3.png" alt=""></td> -->
              </tr></table>
          </div>
        </div>

        <div class="card">
          <div class="card-header">
              评论区
          </div>
          <!-- 列举评论区-评论暂时无法获取 -->
            <div class="card-body">
              <div v-for="comment in comments" :key= "comment.commentId">
                <blockquote class="blockquote mb-0">
                  <div class="row">
                  <div class="col">
                    <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/196315_lg_6d53d078d3.jpg" alt="">
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
 import { Modal } from 'bootstrap/dist/js/bootstrap'
 
     export default {
        name: "MomentItem",
        props: {
            moment: {
                type: Object,
                required: true,
            },
            photo: {
                type: Number,
                required: true,
            },
        },
        data(props) {
            return {
              new_moment: props.moment,
              /* photoid: props.photo, */
              //photo_id: "",
            }
          },
        watch: {
            moment(val){
                this.new_moment = val;
            },
            /* photo(val){
              this.photoid = val;
            } */
          },
        /* methods: {
          get_photo(data, props) {
              data.photoid = props.photo.toString();
              console.log("data.photo::" + data.photoid);
            }
        },
        created() {
          this.get_photo();
        }, */

        setup(props, context) {
          const jwt_token = localStorage.getItem("jwt_token");
          let comments = ref([]);
          let new_content = ref('');
          let reason = ref('');
          //let photo = ref([]);
          console.log("photo_id:" + props.photo_id);
          
          let photo1 = ref('');

           //获取动态照片
           const get_photo = () => {
              $.ajax({
                url: "http://127.0.0.1:3000/user/moment/getPhoto/",
                type: "post",
                headers:{
                  Authorization: "Bearer " + jwt_token, 
                },
                data: {
                  moment_id: props.moment.momentId,
                },
                success(resp) {
                  console.log(resp);
                  photo1.value=resp.photoId;
                  console.log("photo1:" + photo1.value);
                },
                error(resp) {
                  console.log(resp);
                  //console.log("id:" + props.album_id);
                }
              });
            }
          get_photo();
          //评论区获取
          const get_comments = () => {
            $.ajax({
              url: "http://127.0.0.1:3000/user/comment/getListMoment/",
              type: "post",
              headers:{
                Authorization: "Bearer " + jwt_token, 
              },
              data: {
                moment_id: props.moment.momentId,
              },
              success(resp) {
                console.log(resp);
                comments.value = resp;
              },
              error(resp) {
                console.log(resp);
                console.log('moment_id:' + props.moment.moment_id);
              }      
            });
          }
          //获取评论
          get_comments();
          
          //发表评论
          const add_comment = () => {
              $.ajax({
                url: "http://127.0.0.1:3000/user/comment/addToMoment/",
                type: "post",
                headers:{
                  Authorization: "Bearer " + jwt_token, 
                },
                data: {
                  moment_id: props.moment.momentId,
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

          //举报评论
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

          //动态点赞
          const star = () => {
              $.ajax({
              url: "http://127.0.0.1:3000/user/moment/star/",
              type: "post",
              headers:{
                  Authorization: "Bearer " + jwt_token, 
              },
              data: {
                moment_id: props.moment.momentId,
              }, 
              success(resp) {
                  console.log(resp);
                  context.emit('get_moments');
                  //父组件 刷新
              },
              error(resp) {
                  console.log(resp);
              }      
              });
          }

          return {
            comments,
            new_content,
            reason,
            photo1,
            star,
            comment_report,
            get_comments,
            add_comment,
            get_photo,
          }
        }
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
    .comment-img:hover {
      cursor: pointer;
    }
    #makeComment{
        float: right;
    }
 </style>