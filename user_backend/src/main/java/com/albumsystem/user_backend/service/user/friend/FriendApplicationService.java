package com.albumsystem.user_backend.service.user.friend;

import java.util.Map;

public interface FriendApplicationService {
    public Map<String,String> sendApplication(Map<String,String> data);
    public Map<String,String> handleApplication(Map<String,String> data);
    public Map<String,String> rejectApplication(Map<String,String> data);
}
