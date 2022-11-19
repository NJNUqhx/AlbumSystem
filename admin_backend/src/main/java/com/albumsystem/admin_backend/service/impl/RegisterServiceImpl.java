package com.albumsystem.admin_backend.service.impl;

import com.albumsystem.admin_backend.mapper.AdminMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.service.RegisterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String account, String password, String confirmedPassword) {
        Map<String,String> map = new HashMap<>();
        if(account==null) {
            map.put("error_message", "账号不能为空");
            return map;
        }
        if(password == null || confirmedPassword == null) {
            map.put("error_message", "密码不能为空");
            return map;
        }
        if(!password.equals(confirmedPassword)){
            map.put("error_message", "确认密码输入错误");
            return map;
        }
        account = account.trim();
        if(account.length()>16||password.length()>16){
            map.put("error_message", "长度超过限制");
            return map;
        }

        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        List<Admin> admins = adminMapper.selectList(queryWrapper);
        if(!admins.isEmpty()) {
            map.put("error_message", "账号已存在");
            return map;
        }
        String encodePassword = passwordEncoder.encode(password);
        Admin admin = new Admin(null, account, encodePassword);
        adminMapper.insert(admin);
        map.put("error_message", "success");
        return map;
    }
}
