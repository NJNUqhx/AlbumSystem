//package com.albumsystem.user_backend.controller.user.album;
//
//import com.albumsystem.user_backend.pojo.Album;
//import com.albumsystem.user_backend.service.user.album.AlbumGetListService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class AlbumGetListController {
//    @Autowired
//    private AlbumGetListService albumGetListService;
//
//    @PostMapping("/user/album/getList/")
//    public List<Album> getList(){
//        return albumGetListService.getList();
//    }
//}
