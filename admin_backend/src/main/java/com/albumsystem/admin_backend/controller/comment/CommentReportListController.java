package com.albumsystem.admin_backend.controller.comment;

import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.CommentReport;
import com.albumsystem.admin_backend.service.comment.CommentReportListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentReportListController {
    @Autowired
    private CommentReportListService commentReportListService;

    @PostMapping("/admin/comment/content/list/")
    public List<Comment> commentContentList(){
        List<Integer> id = commentReportListService.commentReportListId();
        return commentReportListService.commentContentList(id);
    }

    @PostMapping("/admin/comment/report/list/")
    public List<CommentReport> commentReportList(){
        List<Integer> id = commentReportListService.commentReportListId();
        return commentReportListService.commentReportList(id);
    }

}
