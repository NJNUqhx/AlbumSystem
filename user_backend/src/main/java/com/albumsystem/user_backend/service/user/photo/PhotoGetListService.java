package com.albumsystem.user_backend.service.user.photo;

import com.albumsystem.user_backend.pojo.Photo;

import java.util.List;
import java.util.Map;

public interface PhotoGetListService {
    List<Photo> getList();
    Photo getPhoto(Map<String, String> data);
}
