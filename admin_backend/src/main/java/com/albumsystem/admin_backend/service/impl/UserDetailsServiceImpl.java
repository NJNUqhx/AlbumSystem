package com.albumsystem.admin_backend.service.impl;

import com.albumsystem.admin_backend.mapper.AdminMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminMapper adminmapper;

    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {

        QueryWrapper<Admin> queryMapper = new QueryWrapper<>();
        queryMapper.eq("admin_id", adminId);
        Admin admin = adminmapper.selectOne(queryMapper);
        if(admin == null){
            throw new RuntimeException("管理员账号不存在");
        }
        return new UserDetailsImpl(admin);
    }
}
