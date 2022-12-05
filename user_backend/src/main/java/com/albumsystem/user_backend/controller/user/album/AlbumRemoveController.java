package com.albumsystem.user_backend.controller.user.album;

import com.albumsystem.user_backend.service.user.album.AlbumRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AlbumRemoveController {
    @Autowired
    private AlbumRemoveService albumRemoveService;

    @PostMapping("/user/album/remove/")
    public Map<String,String> remove(@RequestParam Map<String,String> data){
        return albumRemoveService.remove(data);
    }

}
