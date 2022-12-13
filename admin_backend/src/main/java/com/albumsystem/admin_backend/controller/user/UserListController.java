package com.albumsystem.admin_backend.controller.user;

import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.Moment;
import com.albumsystem.admin_backend.pojo.Photo;
import com.albumsystem.admin_backend.pojo.User;
import com.albumsystem.admin_backend.service.user.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserListController {
    @Autowired
    private UserListService userListService;

    @PostMapping("/admin/user/list/")
    public List<User> userList(){
        return userListService.userList();
    }
    
    @PostMapping("/admin/user/photo/list/")
    public List<Photo> photoList(@RequestParam Map<String,String> map){
        Integer userId = Integer.valueOf(map.get("userId"));
        return userListService.photosOfUser(userId);
    }

    @PostMapping("/admin/user/comment/list/")
    public List<Comment> commentList(@RequestParam Map<String,String> map){
        Integer userId = Integer.valueOf(map.get("userId"));
        return userListService.commentsOfUser(userId);
    }

    @PostMapping("/admin/user/moment/list/")
    public List<Moment> momentList(@RequestParam Map<String,String> map){
        Integer userId = Integer.valueOf(map.get("userId"));
        return userListService.momentsOfUser(userId);
    }
}
