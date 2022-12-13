package com.albumsystem.user_backend.controller.user.photo;

import com.albumsystem.user_backend.pojo.PhotoExamineResult;
import com.albumsystem.user_backend.service.user.photo.PhotoExamineResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PhotoExamineController {
    @Autowired
    private PhotoExamineResultService photoExamineResultService;

    @PostMapping("/user/photo/examineResult/")
    public List<PhotoExamineResult> photoExamine(){
        return photoExamineResultService.photoExamine();
    }

}
