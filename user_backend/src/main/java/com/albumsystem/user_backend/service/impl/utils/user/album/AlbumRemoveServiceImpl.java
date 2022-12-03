//package com.albumsystem.user_backend.service.impl.utils.user.album;
//
//import com.albumsystem.user_backend.mapper.AlbumMapper;
//import com.albumsystem.user_backend.pojo.Album;
//import com.albumsystem.user_backend.pojo.User;
//import com.albumsystem.user_backend.service.user.album.AlbumRemoveService;
//import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class AlbumRemoveServiceImpl implements AlbumRemoveService {
//
//    @Autowired
//    private AlbumMapper albumMapper;
//
//    @Override
//    public Map<String, String> remove(Map<String, String> data) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
//        User user = loginUser.getUser();
//
//        int albumId = Integer.parseInt(data.get("album_id"));
//        Album album = albumMapper.selectById(albumId);
//        Map<String, String> map = new HashMap<>();
//
//        if (album == null) {
//            map.put("error_message","用户不存在或已被删除");
//            return map;
//        }
//        if (!album.getUserId().equals(user.getUserId())) {
//            map.put("error_message","没有权限删除该Album");
//            return map;
//        }
//
//        albumMapper.deleteById(albumId);
//
//        return null;
//    }
//}
