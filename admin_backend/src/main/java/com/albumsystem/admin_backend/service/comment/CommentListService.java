package com.albumsystem.admin_backend.service.comment;

import com.albumsystem.admin_backend.pojo.Comment;

import java.util.List;

public interface CommentListService {
    List<Comment> commentList();

    List<Comment> commentList(Integer userId);
}
