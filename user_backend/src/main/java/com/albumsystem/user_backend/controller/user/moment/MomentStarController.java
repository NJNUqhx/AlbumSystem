package com.albumsystem.user_backend.controller.user.moment;

import com.albumsystem.user_backend.service.user.moment.MomentHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MomentStarController {

    @Autowired
    private MomentHandleService momentHandleService;

    @PostMapping("/user/moment/star/")
    public Map<String,String> star(@RequestParam Map<String,String> data){
        return momentHandleService.star(data);
    }
}
