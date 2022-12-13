package com.albumsystem.admin_backend.service.impl.user;

import com.albumsystem.admin_backend.mapper.*;
import com.albumsystem.admin_backend.pojo.*;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.user.UserListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserListServiceImpl implements UserListService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MomentMapper momentMapper;

    @Autowired
    private UserManagementResultMapper userManagementResultMapper;

    @Override
    public List<User> userList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("user_id");

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        Date date = new Date();
        String message = "管理员" + admin.getAccount() + "获取用户列表";
        UserManagementResult userManagementResult = new UserManagementResult(null, admin.getAdminId(), message,date);
        userManagementResultMapper.insert(userManagementResult);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public List<Photo> photosOfUser(Integer userId) {

        QueryWrapper<Photo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        return photoMapper.selectList(queryWrapper);
    }

    @Override
    public List<Comment> commentsOfUser(Integer userId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Moment> momentsOfUser(Integer userId) {
        QueryWrapper<Moment> momentQueryWrapper = new QueryWrapper<>();
        momentQueryWrapper.eq("user_id", userId);
        return momentMapper.selectList(momentQueryWrapper);
    }
}
