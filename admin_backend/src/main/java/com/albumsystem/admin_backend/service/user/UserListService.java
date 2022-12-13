package com.albumsystem.admin_backend.service.user;

import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.Moment;
import com.albumsystem.admin_backend.pojo.Photo;
import com.albumsystem.admin_backend.pojo.User;

import java.util.List;

public interface UserListService {
    List<User> userList();

    List<Photo> photosOfUser(Integer userId);
    List<Comment> commentsOfUser(Integer userId);
    List<Moment> momentsOfUser(Integer userId);
}
