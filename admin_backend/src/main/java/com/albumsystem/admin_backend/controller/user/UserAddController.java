package com.albumsystem.admin_backend.controller.user;

import com.albumsystem.admin_backend.service.user.UserAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserAddController {
    @Autowired
    private UserAddService userAddService;

    public Map<String,String> userAdd(@RequestParam Map<String, String> map){
        return userAddService.userAdd(map);
    }
}
