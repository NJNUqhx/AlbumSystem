package com.albumsystem.user_backend.service.user.account;

import java.util.Map;

public interface RegisterService {
    //
    public Map<String,String> register(String nickname,String password,String confirmedPassword);
}
