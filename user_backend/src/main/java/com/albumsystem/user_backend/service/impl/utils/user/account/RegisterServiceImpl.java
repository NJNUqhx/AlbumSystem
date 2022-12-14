package com.albumsystem.user_backend.service.impl.utils.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.albumsystem.user_backend.mapper.UserMapper;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String nickname, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if (nickname == null) {map.put("error_message", "昵称不能为空");
            map.put("error_message", "昵称不能为空");
            return map;
        }
        if (password == null || confirmedPassword == null) {
            map.put("error_message", "密码不能为空");
            return map;
        }

        nickname = nickname.trim();
        if (nickname.length() == 0) {
            map.put("error_message", "昵称不能为空");
            return map;
        }

        if (password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error_message", "密码不能为空");
        }

        if (nickname.length() > 100) {
            map.put("error_message", "昵称长度不能大于100");
            return map;
        }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        if (!password.equals(confirmedPassword)) {
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname", nickname);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, null, encodedPassword, nickname);
        userMapper.insert(user);

        queryWrapper.clear();
        queryWrapper.eq("nickname",nickname);
        User updateUser = userMapper.selectOne(queryWrapper);
        updateUser.setAccount();
        userMapper.updateById(updateUser);
        map.put("error_message", "success");
        map.put("account",updateUser.getAccount());
        map.put("nickname",updateUser.getNickname());
        map.put("password","******");
        return map;
    }
}
