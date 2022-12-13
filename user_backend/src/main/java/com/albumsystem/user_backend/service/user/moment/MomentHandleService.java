package com.albumsystem.user_backend.service.user.moment;

import java.util.Map;

public interface MomentHandleService {
    Map<String,String> report(Map<String,String> data);
    Map<String,String> star(Map<String,String> data);
}
