 package com.albumsystem.user_backend.controller.user.moment;

import com.albumsystem.user_backend.pojo.*;
import com.albumsystem.user_backend.service.user.moment.MomentGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MomentGetListController {

    @Autowired
    private MomentGetListService momentGetListService;

    @PostMapping("/user/moment/getList/")//获取自己动态列表
    public List<Moment> getList(){
        return momentGetListService.getList();
    }

    @PostMapping("/user/moment/getCommentList/")//获取动态的评论列表
    public List<Comment> getCommentList(@RequestParam Map<String, String> data){
        return momentGetListService.getCommentList(data);
    }

    @PostMapping("/user/moment/getUsersMomentList/")//获取好友动态列表
    public List<Moment> getUsersMomentList(@RequestParam Map<String,String> data){
        return momentGetListService.getUsersList(data);
    }

    @PostMapping("/user/moment/getMoment/")////通过momentId返回动态
    public Moment getMoment(@RequestParam Map<String,String> data){
        return momentGetListService.getMoment(data);
    }

    @PostMapping("/user/moment/getPhoto/")//通过momentId返回照片
    public Photo getPhoto(@RequestParam Map<String,String> data){
        return momentGetListService.getPhoto(data);
    }

    @PostMapping("/user/moment/getPhotoId/")//通过momentId返回照片Id
    public int getPhotoId(@RequestParam Map<String,String> data){
        return momentGetListService.getPhotoId(data);
    }

    @PostMapping("/user/moment/getExamineResult/")//返回审核结果
    public List<MomentExamineResult> getMomentExamineResult(){
        return momentGetListService.getMomentExamineResultList();
    }

    @PostMapping("/user/moment/getHandleResult/")//返回举报处理结果
    public List<MomentHandleResult> getMomentHandleResult(){
        return momentGetListService.getMomentHandleResultList();
    }

    @PostMapping("/user/moment/getMomentUserName/")//返回好友名字
    public String getUserName(@RequestParam Map<String,String> data){
        return momentGetListService.getUsername(data);
    }
}
