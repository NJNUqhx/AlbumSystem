package com.albumsystem.admin_backend.controller.photo;

import com.albumsystem.admin_backend.pojo.Photo;
import com.albumsystem.admin_backend.service.photo.PhotoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PhotoListController {
    @Autowired
    private PhotoListService photoListService;

    @PostMapping("/admin/photo/list/all/")
    public List<Photo> photoListAll(){
        return photoListService.photoList();
    }

    @PostMapping("/admin/photo/list/one/")
    public List<Photo> photoListOne(@RequestParam Map<String,String> map){
        Integer userId = Integer.valueOf(map.get("userId"));
        return photoListService.photoList(userId);
    }
}
