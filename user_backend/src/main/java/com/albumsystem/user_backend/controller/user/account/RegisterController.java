package com.albumsystem.user_backend.controller.user.account;

import com.albumsystem.user_backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/user/account/register/")
    public Map<String,String> register(@RequestParam Map<String,String> map){
        // 密码 确认密码 昵称
        String nickname = map.get("nickname");
        String password = map.get("password");
        String confirmedPassword = map.get("confirmedPassword");
        return registerService.register(nickname,password,confirmedPassword);
    }
}
