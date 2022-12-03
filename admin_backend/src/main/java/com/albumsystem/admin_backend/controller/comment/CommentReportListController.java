package com.albumsystem.admin_backend.controller.comment;

import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.CommentReport;
import com.albumsystem.admin_backend.service.comment.CommentReportListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/admin/comment/both/list/")
    public List<Map<Comment,CommentReport>> commentContentAndReportList(){
        List<Integer> id = commentReportListService.commentReportListId();
        List<Comment> commentContentList = commentReportListService.commentContentList(id);
        List<CommentReport> commentReportList = commentReportListService.commentReportList(id);
        List<Map<Comment,CommentReport>> list = new ArrayList<>();
        if(commentContentList.size() != commentReportList.size()) return null;
        for(int i = 0; i < commentContentList.size(); i++)
        {
            Map<Comment, CommentReport> map = new HashMap<>();
            map.put(commentContentList.get(i), commentReportList.get(i));
            list.add(map);
        }
        return list;
    }

    @PostMapping("/admin/comment/map/list")
    public List<Map<String,String>> commentMapList(){
        List<Integer> id = commentReportListService.commentReportListId();
        List<Comment> commentContentList = commentReportListService.commentContentList(id);
        List<CommentReport> commentReportList = commentReportListService.commentReportList(id);
//        System.out.println(id);
//        System.out.println(commentContentList);
//        System.out.println(commentReportList);
        List<Map<String, String>> list = new ArrayList<>();
        if(commentContentList.size() != commentReportList.size()) return null;
        for(int i = 0; i < commentContentList.size(); i++)
        {
           Map<String, String> map = new HashMap<>();
           Comment comment = commentContentList.get(i);
           CommentReport commentReport = commentReportList.get(i);
           map.put("commentId", comment.getCommentId().toString());
           map.put("userId", comment.getUserId().toString());
           map.put("content", comment.getContent());
           map.put("comment_time", comment.getTime().toString());
           map.put("comment_status", comment.getStatus().toString());

           map.put("reportId", commentReport.getReportId().toString());
           map.put("reason", commentReport.getReason());
           map.put("report_status", commentReport.getStatus().toString());
           map.put("report_time", commentReport.getTime().toString());
           list.add(map);
        }
        return list;
    }

}
