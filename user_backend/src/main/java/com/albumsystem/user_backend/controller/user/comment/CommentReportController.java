package com.albumsystem.user_backend.controller.user.comment;

import com.albumsystem.user_backend.service.user.comment.CommentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentReportController {
    @Autowired
    private CommentReportService commentReportService;

    @PostMapping("/user/comment/report/")
    public Map<String, String> report(@RequestParam Map<String, String> data){
        return commentReportService.report(data);
    }
}
