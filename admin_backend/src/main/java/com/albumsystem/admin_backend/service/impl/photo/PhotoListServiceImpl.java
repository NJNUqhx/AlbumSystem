package com.albumsystem.admin_backend.service.impl.photo;

import com.albumsystem.admin_backend.mapper.PhotoMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.Photo;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.photo.PhotoListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoListServiceImpl implements PhotoListService {
    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<Photo> photoList() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        QueryWrapper<Photo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("status").orderByDesc("time");
        return photoMapper.selectList(queryWrapper);
    }

    @Override
    public List<Photo> photoList(Integer userId) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        QueryWrapper<Photo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).orderByAsc("status").orderByDesc("time");

        return photoMapper.selectList(queryWrapper);
    }
}
