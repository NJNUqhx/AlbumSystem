package com.albumsystem.user_backend.service.user.friend;

import com.albumsystem.user_backend.pojo.FriendApplication;
import com.albumsystem.user_backend.pojo.User;

import java.util.List;
import java.util.Map;

public interface FriendGetListService {
    public List<User> getList();
    public List<User> search(Map<String,String> data);
    public List<FriendApplication> getApplicationList();
    public boolean isFriend(int userId, int friendId);
}
