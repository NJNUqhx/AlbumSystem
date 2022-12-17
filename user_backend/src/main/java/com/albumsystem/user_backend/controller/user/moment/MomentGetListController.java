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

    @PostMapping("/user/moment/getList/")
    public List<Moment> getList(){
        return momentGetListService.getList();
    }

    @PostMapping("/user/moment/getCommentList/")
    public List<Comment> getCommentList(@RequestParam Map<String, String> data){
        return momentGetListService.getCommentList(data);
    }

    @PostMapping("/user/moment/getUsersMomentList/")
    public List<Moment> getUsersMomentList(@RequestParam Map<String,String> data){
        return momentGetListService.getUsersList(data);
    }

    @PostMapping("/user/moment/getMoment/")
    public Moment getMoment(@RequestParam Map<String,String> data){
        return momentGetListService.getMoment(data);
    }

    @PostMapping("/user/moment/getPhoto/")
    public Photo getPhoto(@RequestParam Map<String,String> data){
        return momentGetListService.getPhoto(data);
    }

    @PostMapping("/user/moment/getExamineResult/")
    public List<MomentExamineResult> getMomentExamineResult(){
        return momentGetListService.getMomentExamineResultList();
    }

    @PostMapping("/user/moment/getHandleResult/")
    public List<MomentHandleResult> getMomentHandleResult(){
        return momentGetListService.getMomentHandleResultList();
    }

    @PostMapping("/user/moment/getMomentUserName/")
    public String getUserName(@RequestParam Map<String,String> data){
        return momentGetListService.getUsername(data);
    }
}
