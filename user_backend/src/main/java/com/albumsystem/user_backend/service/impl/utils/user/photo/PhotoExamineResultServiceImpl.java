package com.albumsystem.user_backend.service.impl.utils.user.photo;

import com.albumsystem.user_backend.mapper.PhotoExamineResultMappper;
import com.albumsystem.user_backend.mapper.PhotoMapper;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.PhotoExamineResult;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.photo.PhotoExamineResultService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhotoExamineResultServiceImpl implements PhotoExamineResultService {

    @Autowired
    private PhotoMapper photoMapper;
    @Autowired
    private PhotoExamineResultMappper photoExamineResultMappper;

    @Override
    public List<PhotoExamineResult> photoExamine() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        //Map<String,String> map = new HashMap<>();
        QueryWrapper<PhotoExamineResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());

        List<PhotoExamineResult> photoExamineResultList = photoExamineResultMappper.selectList(queryWrapper);
        return photoExamineResultList;
    }
}
