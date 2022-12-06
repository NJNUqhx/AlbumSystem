package com.albumsystem.user_backend.service.impl.utils.user.album;

import com.albumsystem.user_backend.mapper.AlbumMapper;
import com.albumsystem.user_backend.mapper.PhoToAlbumMapper;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.PhotoToAlbum;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.album.AlbumManagementService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlbumManagementServiceImpl implements AlbumManagementService {

    @Autowired
    private PhoToAlbumMapper photoAlbumMapper;
    @Autowired
    private AlbumMapper albumMapper;

    public Map<String,String> addPhoto(Map<String,String> data){
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        //User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();

        int photoId = Integer.parseInt(data.get("photo_id"));
        int albumId = Integer.parseInt(data.get("album_id"));
        PhotoToAlbum new_photoToAlbum = new PhotoToAlbum(photoId,albumId);
        System.out.println(new_photoToAlbum);
        photoAlbumMapper.insert(new_photoToAlbum);

        map.put("error_message","success");
        return map;
    }

    public Map<String,String> deletePhoto(Map<String,String> data){
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        //User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();

        int photoId = Integer.parseInt(data.get("photo_id"));
        int albumId = Integer.parseInt(data.get("album_id"));
        QueryWrapper<PhotoToAlbum> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("photo_id",photoId).eq("album_id",albumId);
        photoAlbumMapper.delete(queryWrapper);

        map.put("error_message","success");
        return map;
    }
}
