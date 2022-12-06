package com.albumsystem.user_backend.controller.user.friend;

import com.albumsystem.user_backend.pojo.FriendApplication;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.user.friend.FriendGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FriendGetListController {
    @Autowired
    private FriendGetListService friendGetListService;

    @PostMapping("/user/friend/getList/")
    public List<User> getList() {
        return friendGetListService.getList();
    }

    @PostMapping("/user/friend/search/")
    public List<User> search(@RequestParam Map<String, String> data) {
        return friendGetListService.search(data);
    }

    @PostMapping("/user/friend/getApplicationList/")
    public List<FriendApplication> getApplicationList() { return friendGetListService.getApplicationList(); }
}
