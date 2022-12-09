package com.albumsystem.admin_backend.controller.user;

import com.albumsystem.admin_backend.pojo.UserManagementResult;
import com.albumsystem.admin_backend.service.user.UserManagementResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserManagementController {
    @Autowired
    private UserManagementResultService userManagementResultService;

    @PostMapping("/admin/user/management/result/")
    public List<UserManagementResult> UserManagementResult(){

        return userManagementResultService.UserManagementResultList();
    }
}
