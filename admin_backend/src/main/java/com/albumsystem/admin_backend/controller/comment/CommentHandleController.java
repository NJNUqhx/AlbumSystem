package com.albumsystem.admin_backend.controller.comment;

import com.albumsystem.admin_backend.service.comment.CommentHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentHandleController {
    @Autowired
    private CommentHandleService commentHandleService;

    @PostMapping("/admin/comment/handle/")
    public Map<String,String> commentHandle(@RequestParam Map<String,String> map){
        Integer reportId = Integer.valueOf(map.get("reportId"));
        Integer userId = Integer.valueOf(map.get("userId"));
        Integer handle = Integer.valueOf(map.get("handle"));
        String advice = map.get("advice");
        return commentHandleService.commentHandle(reportId,userId,handle,advice);
    }
}
