package com.albumsystem.user_backend.service.impl.utils.user.album;

import com.albumsystem.user_backend.mapper.AlbumMapper;
import com.albumsystem.user_backend.pojo.Album;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.album.AlbumUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AlbumUpdateServiceImpl implements AlbumUpdateService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int albumId = Integer.parseInt(data.get("album_id"));
        Short authority = Short.parseShort(data.get("authority"));
        String name = data.get("name");
        //int star = Integer.parseInt(data.get("star"));
        String description = data.get("description");

        Map<String, String> map = new HashMap<>();

        Album album = albumMapper.selectById(albumId);
        if (album == null) {
            map.put("error_message","用户不存在或已被删除");
            return map;
        }
        if (!album.getUserId().equals(user.getUserId())) {
            map.put("error_message","没有权限修改该Album");
            return map;
        }
        if(description.length() == 0) description = "这个人很懒，什么也没留下......";

        Album new_album = new Album(
                album.getAlbumId(),
                user.getUserId(),
                authority,
                name,
                album.getStar(),
                new Date(),
                description
        );

        albumMapper.updateById(new_album);
        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> star(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        int albumId = Integer.parseInt(data.get("album_id"));
        Album album = albumMapper.selectById(albumId);
        if (album == null) {
            map.put("error_message","用户不存在或已被删除");
            return map;
        }
        int new_star = album.getStar() + 1;
        Album new_album = new Album(
                album.getAlbumId(),
                album.getUserId(),
                album.getAuthority(),
                album.getName(),
                new_star,
                new Date(),
                album.getDescription()
        );
        albumMapper.updateById(new_album);
        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> name(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        int albumId = Integer.parseInt(data.get("album_id"));
        String name = data.get("name");
        Album album = albumMapper.selectById(albumId);
        if (album == null) {
            map.put("error_message","用户不存在或已被删除");
            return map;
        }
        Album new_album = new Album(
                album.getAlbumId(),
                album.getUserId(),
                album.getAuthority(),
                name,
                album.getStar(),
                new Date(),
                album.getDescription()
        );
        albumMapper.updateById(new_album);
        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> authority(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        int albumId = Integer.parseInt(data.get("album_id"));
        Short authority = Short.parseShort(data.get("authority"));
        Album album = albumMapper.selectById(albumId);
        if (album == null) {
            map.put("error_message","用户不存在或已被删除");
            return map;
        }
        int new_star = album.getStar() + 1;
        Album new_album = new Album(
                album.getAlbumId(),
                album.getUserId(),
                authority,
                album.getName(),
                album.getStar(),
                new Date(),
                album.getDescription()
        );
        albumMapper.updateById(new_album);
        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> description(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        int albumId = Integer.parseInt(data.get("album_id"));
        String description = data.get("description");
        Album album = albumMapper.selectById(albumId);
        if (album == null) {
            map.put("error_message","用户不存在或已被删除");
            return map;
        }
        int new_star = album.getStar() + 1;
        Album new_album = new Album(
                album.getAlbumId(),
                album.getUserId(),
                album.getAuthority(),
                album.getName(),
                album.getStar(),
                new Date(),
                description
        );
        albumMapper.updateById(new_album);
        map.put("error_message","success");
        return map;
    }

}
