package com.albumsystem.admin_backend.service.account;

import java.util.Map;

public interface LoginService {
    Map<String,String> getToken(String account, String password);
}
