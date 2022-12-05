package com.albumsystem.user_backend.service.impl.utils.user.photo;

import com.albumsystem.user_backend.mapper.PhotoMapper;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.photo.PhotoGetListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PhotoGetListServiceImpl implements PhotoGetListService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<Photo> getList() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Photo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());

        return photoMapper.selectList(queryWrapper);
    }

    @Override
    public Photo getPhoto(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int photoId = Integer.parseInt(data.get("photo_id"));

        QueryWrapper<Photo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("photo_id",photoId);
        //List<Photo> listPhoto = photoMapper.selectList(queryWrapper);
        Photo photo = photoMapper.selectOne(queryWrapper);
        if(photo.getAuthority()==2 && user.getUserId() != photo.getUserId()){
            System.out.println("权限不足，无法访问");
            return null;
        }
        return photo;
    }
}
