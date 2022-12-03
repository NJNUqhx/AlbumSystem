package com.albumsystem.user_backend.controller.user.photo;

import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.service.user.photo.PhotoGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PhotoGetListController {

    @Autowired
    private PhotoGetListService photoGetListService;

    @PostMapping("/user/photo/getList/")
    public List<Photo> getList(){
        return photoGetListService.getList();
    }

    @PostMapping("/user/photo/getPhoto/")
    public Photo getPhoto(@RequestParam Map<String, String> data ){ return photoGetListService.getPhoto(data); }
}
