package com.albumsystem.user_backend.controller.user.comment;

import com.albumsystem.user_backend.service.user.comment.CommentAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentAddController {

    @Autowired
    CommentAddService commentAddService;

    @PostMapping("/user/comment/addToAlbum/")
    public Map<String,String> addToAlbum(@RequestParam Map<String,String> data){
        return commentAddService.addToAlbum(data);
    }

}
