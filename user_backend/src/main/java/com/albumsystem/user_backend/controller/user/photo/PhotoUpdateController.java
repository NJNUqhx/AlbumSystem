package com.albumsystem.user_backend.controller.user.photo;


import com.albumsystem.user_backend.service.user.photo.PhotoRemoveService;
import com.albumsystem.user_backend.service.user.photo.PhotoUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PhotoUpdateController {

    @Autowired
    private PhotoUpdateService photoUpdateService;

    @PostMapping("/user/photo/update/")
    public Map<String,String> update(@RequestParam Map<String,String> data){
        return photoUpdateService.update(data);
    }

}
