package com.albumsystem.user_backend.controller.user.comment;

import com.albumsystem.user_backend.pojo.Comment;
import com.albumsystem.user_backend.service.user.comment.CommentGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommentGetListController {

    @Autowired
    private CommentGetListService commentGetListService;

    @PostMapping("/user/comment/getListAlbum/")
    public List<Comment> getListAlbum(@RequestParam Map<String,String> data){
        return commentGetListService.commentGetListAlbum(data);
    }

    @PostMapping("/user/comment/getListMoment/")
    public List<Comment> getListMoment(@RequestParam Map<String,String> data){
        return commentGetListService.commentGetListMoment(data);
    }
}
