package com.albumsystem.admin_backend.service.impl.comment;

import com.albumsystem.admin_backend.mapper.CommentMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.Photo;
import com.albumsystem.admin_backend.service.comment.CommentListService;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentListServiceImpl implements CommentListService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> commentList() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Comment> commentList(Integer userId) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).orderByDesc("time");
        return commentMapper.selectList(queryWrapper);
    }
}
