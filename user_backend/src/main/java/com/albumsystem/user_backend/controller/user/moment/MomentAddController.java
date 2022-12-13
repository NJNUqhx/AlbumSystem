package com.albumsystem.user_backend.controller.user.moment;

import com.albumsystem.user_backend.service.user.moment.MomentAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MomentAddController {
    @Autowired
    private MomentAddService momentAddService;

    @PostMapping("/user/moment/add/")
    public Map<String,String> add(@RequestParam Map<String,String> data){
        return momentAddService.add(data);
    }

}
