package com.albumsystem.admin_backend.controller.comment;

import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.service.comment.CommentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommentListController {
    @Autowired
    private CommentListService commentListService;

    @PostMapping("/admin/comment/list/all/")
    public List<Comment> commentListAll(){
        return commentListService.commentList();
    }

    @PostMapping("/admin/comment/list/all")
    public List<Comment> commentListOne(@RequestParam Map<String,String> map){
        Integer userId = Integer.valueOf(map.get("userId"));
        return commentListService.commentList(userId);
    }
}
