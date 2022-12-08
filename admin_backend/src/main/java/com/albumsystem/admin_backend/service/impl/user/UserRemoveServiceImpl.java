package com.albumsystem.admin_backend.service.impl.user;

import com.albumsystem.admin_backend.mapper.UserManagementResultMapper;
import com.albumsystem.admin_backend.mapper.UserMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.User;
import com.albumsystem.admin_backend.pojo.UserManagementResult;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.user.UserRemoveService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserRemoveServiceImpl implements UserRemoveService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserManagementResultMapper userManagementResultMapper;

    @Override
    public Map<String, String> userRemove(Map<String, String> map) {
        Integer userId = Integer.valueOf(map.get("userId"));
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);
        if(userMapper.selectList(userQueryWrapper).isEmpty()){
            map.put("error_message","不存在该用户");
            return map;
        }
        userMapper.deleteById(userId);
        map.put("error_message", "success");

        User user = userMapper.selectOne(userQueryWrapper);

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        Date date = new Date();
        String message = "管理员" + admin.getAccount() + "删除用户" + user.getAccount();
        UserManagementResult userManagementResult = new UserManagementResult(null, admin.getAdminId(), message,date);
        userManagementResultMapper.insert(userManagementResult);

        return map;
    }
}
