package com.albumsystem.user_backend.controller.user.album;

import com.albumsystem.user_backend.service.user.album.AlbumUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ALbumUpdateController {

    @Autowired
    private AlbumUpdateService albumUpdateService;

    @PostMapping("/user/album/update/")
    public Map<String, String> update(@RequestParam Map<String, String> data){
        return albumUpdateService.update(data);
    }

    @PostMapping("/user/album/updateStar/")
    public Map<String, String> star(@RequestParam Map<String, String> data){
        return albumUpdateService.star(data);
    }

    @PostMapping("/user/album/updateName/")
    public Map<String, String> name(@RequestParam Map<String, String> data){
        return albumUpdateService.name(data);
    }

    @PostMapping("/user/album/updateAuthority/")
    public Map<String, String> authority(@RequestParam Map<String, String> data){
        return albumUpdateService.authority(data);
    }

    @PostMapping("/user/album/updateDescription/")
    public Map<String, String> description(@RequestParam Map<String, String> data){
        return albumUpdateService.description(data);
    }
}
