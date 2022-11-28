package com.albumsystem.user_backend.service.user.account;

import java.util.Map;

public interface LoginService {
    Map<String,String> getToken(String account, String password);
}
