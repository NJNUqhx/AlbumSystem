package com.albumsystem.user_backend.controller.user.photo;

import com.albumsystem.user_backend.service.user.photo.PhotoRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PhotoRemoveController {

    @Autowired
    private PhotoRemoveService photoRemoveService;

    @PostMapping("/user/photo/remove/")
    public Map<String,String> remove(@RequestParam Map<String,String> data){
        return photoRemoveService.remove(data);
    }

}
