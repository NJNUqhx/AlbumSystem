package com.albumsystem.admin_backend.controller.moment;

import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.Moment;
import com.albumsystem.admin_backend.pojo.Photo;
import com.albumsystem.admin_backend.service.moment.MomentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MomentListController {
    @Autowired
    private MomentListService momentListService;

    @PostMapping("/admin/moment/list/")
    public List<Moment> momentList(){
        return momentListService.momentList();
    }

    @PostMapping("/admin/moment/comment/")
    public List<Comment> commentsOfMoment(@RequestParam Map<String, String> map){
        Integer momentId = Integer.valueOf(map.get("moment_id"));
        return momentListService.commentsOfMoment(momentId);
    }

    @PostMapping("/admin/moment/photo/")
    public List<Photo> photosOfMoment(@RequestParam Map<String, String> map){
        Integer momentId = Integer.valueOf(map.get("moment_id"));
        return momentListService.photosOfMoment(momentId);
    }
}
