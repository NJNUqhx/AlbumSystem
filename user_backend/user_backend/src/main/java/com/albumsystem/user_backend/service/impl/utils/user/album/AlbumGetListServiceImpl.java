package com.albumsystem.user_backend.service.impl.utils.user.album;

import com.albumsystem.user_backend.mapper.AlbumMapper;
import com.albumsystem.user_backend.mapper.PhoToAlbumMapper;
import com.albumsystem.user_backend.mapper.PhotoMapper;
import com.albumsystem.user_backend.pojo.Album;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.PhotoToAlbum;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.album.AlbumGetListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AlbumGetListServiceImpl implements AlbumGetListService {

    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private PhoToAlbumMapper photoAlbumMapper;
    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<Album> getList() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());

        return albumMapper.selectList(queryWrapper);
    }

    @Override
    public Album getAlbum(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int albumId = Integer.parseInt(data.get("album_id"));

        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("album_id",albumId);
        //List<Photo> listPhoto = photoMapper.selectList(queryWrapper);
        Album album = albumMapper.selectOne(queryWrapper);
        if(album.getAuthority()==2 && user.getUserId() != album.getUserId()){
            System.out.println("权限不足，无法访问");
            return null;
        }
        return album;
    }

    @Override
    public List<Integer> getPhotoList(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<PhotoToAlbum> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Photo> photo_queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("album_id").orderByDesc("photo_id");

        int albumId = Integer.parseInt(data.get("album_id"));
        queryWrapper.eq("album_id", albumId);
        List<PhotoToAlbum> photoToAlbumList = photoAlbumMapper.selectList(queryWrapper);
        List<Photo> photoList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        for(PhotoToAlbum photoAlbum: photoToAlbumList){
            photo_queryWrapper.clear();
            photo_queryWrapper.eq("photo_id", photoAlbum.getPhotoId());
            Photo new_photo =photoMapper.selectOne(photo_queryWrapper);
            photoList.add(new_photo);
            idList.add(new_photo.getPhotoId());
        }
        return idList;
//        List<PhotoAlbum> list = photoAlbumMapper.selectList(queryWrapper);
////        System.out.println(list);
//        List<Integer> idList = new ArrayList<>();
//        for(PhotoAlbum photoAlbum:list){
//            idList.add(photoAlbum.getAlbumId());
//        }
//        List<Photo> ans = new ArrayList<>();
//        for(Integer id:idList){
//            queryWrapper.clear();
//            photo_queryWrapper.clear();
//            queryWrapper.eq("album_id", id);
//            PhotoAlbum photoAlbum = photoAlbumMapper.selectOne(queryWrapper);
//            photo_queryWrapper.eq("photo_id", photoAlbum.getPhotoId());
//            Photo photo = photoMapper.selectOne(photo_queryWrapper);
//            ans.add(photo);
//            return ans;
//        }
    }
}
