//package com.albumsystem.user_backend.controller.user.photo;
//
//import com.albumsystem.user_backend.service.photo.PhotoExamineService;
//import com.albumsystem.user_backend.service.user.photo.PhotoExamineService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//public class PhotoExamineController {
//    @Autowired
//    private PhotoExamineService photoExamineService;
//
//    @PostMapping("/admin/photo/examine")
//    public Map<String, String> photoExamine(@RequestParam Map<String, String> map){
//        Integer photoId = Integer.valueOf(map.get("photoId"));
//        Integer userId = Integer.valueOf(map.get("userId"));
//        Integer examine = Integer.valueOf(map.get("examine"));
//        String advice = map.get("advice");
//        return photoExamineService.photoExamine(photoId, userId, examine, advice);
//    }
//
//}
