package com.albumsystem.admin_backend.service;

import java.util.Map;

public interface LoginService {
    Map<String,String> getToken(String account, String password);
}
