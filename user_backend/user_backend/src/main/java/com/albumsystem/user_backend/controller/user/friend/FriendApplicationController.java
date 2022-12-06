package com.albumsystem.user_backend.controller.user.friend;

import com.albumsystem.user_backend.pojo.Friend;
import com.albumsystem.user_backend.pojo.FriendApplication;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.friend.FriendApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FriendApplicationController {
    @Autowired
    private FriendApplicationService friendApplicationService;

    @PostMapping("/user/friend/sendApplication/")
    public Map<String, String> sendApplication(@RequestParam Map<String, String> data) {
        return friendApplicationService.sendApplication(data);
    }

    @PostMapping("/user/friend/handleApplication")
    public Map<String, String> handleApplication(@RequestParam Map<String, String> data) {
        return friendApplicationService.handleApplication(data);
    }

}
