package com.albumsystem.user_backend.controller.user.account;

import com.albumsystem.user_backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    //获取GET，修改删除添加POST
    @GetMapping("/user/account/info/")

    public Map<String,String> getInfo() {
        return infoService.getinfo();
    }
}
