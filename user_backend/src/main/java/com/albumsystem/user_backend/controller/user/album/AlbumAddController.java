package com.albumsystem.user_backend.controller.user.album;

import com.albumsystem.user_backend.service.user.album.AlbumAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AlbumAddController {
    @Autowired
    private AlbumAddService albumAddService;

    @PostMapping("/user/album/add/")
    public Map<String,String> add(@RequestParam Map<String,String> data){
        return albumAddService.add(data);
    }
}
