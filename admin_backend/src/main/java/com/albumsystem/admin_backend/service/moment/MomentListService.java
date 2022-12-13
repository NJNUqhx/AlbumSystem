package com.albumsystem.admin_backend.service.moment;

import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.Moment;
import com.albumsystem.admin_backend.pojo.Photo;

import java.util.List;

public interface MomentListService {
    List<Moment> momentList();
    List<Comment> commentsOfMoment(Integer momentId);
    List<Photo> photosOfMoment(Integer momentId);
}
