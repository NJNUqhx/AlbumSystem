package com.albumsystem.admin_backend.controller.admin;

import com.albumsystem.admin_backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/admin/account/register/")
    Map<String,String> register(@RequestParam Map<String,String> map){
        String account = map.get("account");
        String password = map.get("password");
        String confirmedPassword = map.get("confirmedPassword");
        return registerService.register(account, password, confirmedPassword);
    }
}
