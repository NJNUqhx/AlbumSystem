package com.albumsystem.user_backend.service.user.album;

import com.albumsystem.user_backend.pojo.Album;
import com.albumsystem.user_backend.pojo.Photo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface AlbumGetListService {
    List<Album> getList();
    Album getAlbum(Map<String, String> data);
    public List<Photo> getPhotoList(Map<String, String> data);
    public List<Integer> getPhotoIdList(Map<String, String> data);
    List<Album> getUsersAlbum(Map<String, String> data);
    List<Photo> getUsersPhoto(Map<String,String> data);
}
