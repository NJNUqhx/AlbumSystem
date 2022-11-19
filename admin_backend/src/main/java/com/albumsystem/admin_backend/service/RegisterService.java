package com.albumsystem.admin_backend.service;

import java.util.Map;

public interface RegisterService {
    Map<String,String> register(String account, String password, String confirmedPassword);
}
