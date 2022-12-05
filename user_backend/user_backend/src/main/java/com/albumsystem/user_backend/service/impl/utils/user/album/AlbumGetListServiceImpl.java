//package com.albumsystem.user_backend.service.impl.utils.user.album;
//
//import com.albumsystem.user_backend.mapper.AlbumMapper;
//import com.albumsystem.user_backend.pojo.Album;
//import com.albumsystem.user_backend.pojo.User;
//import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
//import com.albumsystem.user_backend.service.user.album.AlbumGetListService;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AlbumGetListServiceImpl implements AlbumGetListService {
//
//    @Autowired
//    private AlbumMapper albumMapper;
//
//    @Override
//    public List<Album> getList() {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
//        User user = loginUser.getUser();
//
//        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id",user.getUserId());
//
//        return albumMapper.selectList(queryWrapper);
//    }
//}
