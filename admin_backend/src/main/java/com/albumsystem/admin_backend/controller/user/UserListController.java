package com.albumsystem.admin_backend.controller.user;

import com.albumsystem.admin_backend.pojo.User;
import com.albumsystem.admin_backend.service.user.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserListController {
    @Autowired
    private UserListService userListService;

    @PostMapping("/admin/user/list/")
    public List<User> userList(){
        return userListService.userList();
    }
}
