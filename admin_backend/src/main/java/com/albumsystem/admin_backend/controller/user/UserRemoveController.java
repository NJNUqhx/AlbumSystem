package com.albumsystem.admin_backend.controller.user;

import com.albumsystem.admin_backend.service.user.UserRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRemoveController {
    @Autowired
    private UserRemoveService userRemoveService;

    @PostMapping("/admin/user/remove/")
    public Map<String, String> userRemove(@RequestParam Map<String, String> map){
        return userRemoveService.userRemove(map);
    }
}
