package com.albumsystem.user_backend.controller.user.photo;

import com.albumsystem.user_backend.service.user.photo.PhotoAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PhotoAddController {
    @Autowired
    private PhotoAddService photoAddService;

    @PostMapping("/user/photo/add/")
    public Map<String,String> add(@RequestParam Map<String,String> data){
        System.out.println(data);
        return photoAddService.add(data);
    }
}
