package com.albumsystem.admin_backend.service.impl.user;

import com.albumsystem.admin_backend.mapper.UserManagementResultMapper;
import com.albumsystem.admin_backend.pojo.UserManagementResult;
import com.albumsystem.admin_backend.service.user.UserManagementResultService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementResultServiceImpl implements UserManagementResultService {

    @Autowired
    private UserManagementResultMapper userManagementResultMapper;

    @Override
    public List<UserManagementResult> UserManagementResultList() {
        QueryWrapper<UserManagementResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        final int NUM = 15;
        List<UserManagementResult> list = userManagementResultMapper.selectList(queryWrapper);
        List<UserManagementResult> userManagementResultList = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            userManagementResultList.add(list.get(i));
        }
        return userManagementResultList;
    }
}
