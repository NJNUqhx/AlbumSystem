package com.albumsystem.admin_backend.service.comment;

import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.CommentReport;

import java.util.List;

public interface CommentReportListService {
    List<Integer> commentReportListId();
    List<Comment> commentContentList(List<Integer> id);
    List<CommentReport> commentReportList(List<Integer> id);
}
