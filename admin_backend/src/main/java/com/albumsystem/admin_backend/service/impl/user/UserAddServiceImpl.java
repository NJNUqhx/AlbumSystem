package com.albumsystem.admin_backend.service.impl.user;

import com.albumsystem.admin_backend.mapper.UserManagementResultMapper;
import com.albumsystem.admin_backend.mapper.UserMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.User;
import com.albumsystem.admin_backend.pojo.UserManagementResult;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.user.UserAddService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAddServiceImpl implements UserAddService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManagementResultMapper userManagementResultMapper;

    @Override
    public Map<String, String> userAdd(Map<String,String> data) {
        Map<String, String> map = new HashMap<>();

        String password = data.get("password");
        String nickname = data.get("nickname");

        String encodedPassword = passwordEncoder.encode(password);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname", nickname);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }

        User user = new User(null, null, encodedPassword, nickname);
        userMapper.insert(user);

        queryWrapper.clear();
        queryWrapper.eq("nickname",nickname);
        User updateUser = userMapper.selectOne(queryWrapper);
        updateUser.setAccount();
        userMapper.updateById(updateUser);
        map.put("error_message", "success");
        map.put("userId", updateUser.getUserId().toString());
        map.put("account",updateUser.getAccount());
        map.put("nickname",updateUser.getNickname());
        map.put("password",password);


        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        Date date = new Date();
        String message = "管理员" + admin.getAccount() + "添加用户" + updateUser.getAccount();
        UserManagementResult userManagementResult = new UserManagementResult(null, admin.getAdminId(), message,date);
        userManagementResultMapper.insert(userManagementResult);
        return map;
    }
}
