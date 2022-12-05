package com.albumsystem.user_backend.service.user.album;

import java.util.Map;

public interface AlbumManagementService {
    public Map<String,String> addPhoto(Map<String,String> data);
    public Map<String,String> deletePhoto(Map<String,String> data);
}
