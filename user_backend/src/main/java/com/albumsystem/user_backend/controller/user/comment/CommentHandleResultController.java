package com.albumsystem.user_backend.controller.user.comment;

import com.albumsystem.user_backend.pojo.CommentHandleResult;
import com.albumsystem.user_backend.service.user.comment.CommentHandleResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentHandleResultController {

    @Autowired
    private CommentHandleResultService commentHandleResultService;

    @PostMapping("/user/comment/handleResult/")
    public List<CommentHandleResult> handleResult(){
        return commentHandleResultService.commentHandle();
    }
}
