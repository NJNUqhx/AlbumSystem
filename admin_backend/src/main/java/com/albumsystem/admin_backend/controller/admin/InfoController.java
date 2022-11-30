package com.albumsystem.admin_backend.controller.admin;

import com.albumsystem.admin_backend.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("/admin/account/info/")
    public Map<String,String> getInfo(){
        return infoService.getinfo();
    }
}
