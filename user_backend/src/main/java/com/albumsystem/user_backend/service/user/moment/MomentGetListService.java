package com.albumsystem.user_backend.service.user.moment;

import com.albumsystem.user_backend.pojo.Album;
import com.albumsystem.user_backend.pojo.Comment;
import com.albumsystem.user_backend.pojo.Moment;

import java.util.List;
import java.util.Map;

public interface MomentGetListService {
    List<Moment> getList();
    List<Comment> getCommentList(Map<String, String> data);
    public List<Moment> getUsersList(Map<String, String> data);
}
