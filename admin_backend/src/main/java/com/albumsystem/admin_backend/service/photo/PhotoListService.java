package com.albumsystem.admin_backend.service.photo;

import com.albumsystem.admin_backend.pojo.Photo;

import java.util.List;

public interface PhotoListService {
    List<Photo> photoList();
    List<Photo> photoList(Integer userId);
}
