package com.albumsystem.admin_backend.controller.admin;

import com.albumsystem.admin_backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/admin/account/token/")
    public Map<String,String> getToken(@RequestParam Map<String,String> map){
        String account = map.get("account");
        String password = map.get("password");
        return loginService.getToken(account, password);
    }
}
