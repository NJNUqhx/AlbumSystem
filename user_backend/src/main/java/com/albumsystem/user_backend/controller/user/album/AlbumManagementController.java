package com.albumsystem.user_backend.controller.user.album;

import com.albumsystem.user_backend.service.user.album.AlbumManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AlbumManagementController {

    @Autowired
    private AlbumManagementService albumManagementService;

    @PostMapping("/user/album/addPhoto/")
    public Map<String,String> addPhoto(@RequestParam Map<String,String> data){
        return albumManagementService.addPhoto(data);
    }

    @PostMapping("/user/album/deletePhoto/")
    public Map<String,String> deletePhoto(@RequestParam Map<String,String> data){
        return albumManagementService.deletePhoto(data);
    }
}
