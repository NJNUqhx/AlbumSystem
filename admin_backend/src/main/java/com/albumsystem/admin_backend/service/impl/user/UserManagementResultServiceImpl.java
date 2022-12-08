package com.albumsystem.admin_backend.service.impl.user;

import com.albumsystem.admin_backend.mapper.UserManagementResultMapper;
import com.albumsystem.admin_backend.pojo.UserManagementResult;
import com.albumsystem.admin_backend.service.user.UserManagementResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementResultServiceImpl implements UserManagementResultService {

    @Autowired
    private UserManagementResultMapper userManagementResultMapper;

    @Override
    public List<UserManagementResult> UserManagementResultList() {
        return userManagementResultMapper.selectList(null);
    }
}
