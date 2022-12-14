package com.albumsystem.admin_backend.service.impl.moment;

import com.albumsystem.admin_backend.mapper.*;
import com.albumsystem.admin_backend.pojo.*;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.moment.MomentListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MomentListServiceImpl implements MomentListService {
    @Autowired
    private MomentMapper momentMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PhotoMapper photoMapper;
    @Autowired
    private CommentToMomentMapper commentToMomentMapper;
    @Autowired
    private PhotoToMomentMapper photoToMomentMapper;
    
    @Override
    public List<Moment> momentList() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        QueryWrapper<Moment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("status").orderByDesc("time");
        
        return momentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Comment> commentsOfMoment(Integer momentId) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        
        QueryWrapper<CommentToMoment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("moment_id", momentId);
        // ???????????????????????????
        List<CommentToMoment> commentToMomentList = commentToMomentMapper.selectList(queryWrapper);
        if(commentToMomentList.isEmpty()) return null;
        // ????????????????????????
        List<Integer> commentIdList = new ArrayList<>();
        for ( CommentToMoment elem: commentToMomentList) {
            commentIdList.add(elem.getCommentId());
        }
        return commentMapper.selectBatchIds(commentIdList);
    }

    @Override
    public List<Photo> photosOfMoment(Integer momentId) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        QueryWrapper<PhotoToMoment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("moment_id", momentId);
        // ???????????????????????????
        List<PhotoToMoment> photoToMomentList = photoToMomentMapper.selectList(queryWrapper);
        if(photoToMomentList.isEmpty()) return null;
        System.out.println(photoToMomentList);
        // ????????????????????????
        List<Integer> photoIdList = new ArrayList<>();
        for ( PhotoToMoment elem: photoToMomentList) {
            photoIdList.add(elem.getPhotoId());
        }
        System.out.println(photoIdList);
        return photoMapper.selectBatchIds(photoIdList);
    }
}
