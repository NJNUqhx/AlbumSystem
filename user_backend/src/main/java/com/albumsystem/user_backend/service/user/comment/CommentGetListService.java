package com.albumsystem.user_backend.service.user.comment;

import com.albumsystem.user_backend.pojo.Comment;
import com.albumsystem.user_backend.pojo.Moment;

import java.util.List;
import java.util.Map;

public interface CommentGetListService {
    public List<Comment> commentGetListAlbum(Map<String,String> data);
    public List<Comment> commentGetListMoment(Map<String, String> data);
    String getUsername (Map<String,String> data);
}
