package com.albumsystem.user_backend.controller.user;

import com.albumsystem.user_backend.mapper.UserMapper;
import com.albumsystem.user_backend.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired//用到mapper就要加上
    UserMapper userMapper;

    @GetMapping("/user/all/")
    public List<User> getAll(){
        return userMapper.selectList(null);
    }

    @GetMapping("/user/{userId}/")
    public User getUser(@PathVariable int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userMapper.selectOne(queryWrapper);
    }

    @GetMapping("/user/add/{userId}/{account}/{password}/{nickname}/")
    public String addUser(
            @PathVariable int userId,
            @PathVariable String account,
            @PathVariable String password,
            @PathVariable String nickname) {
        if(password.length()<6){
            return "密码太短，请输入六位以上密码";
        }
//          PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//          String encodedPassword = passwordEncoder.encode(password);
        User user = new User(userId, account, password,nickname);
        userMapper.insert(user);
        return "Add User Successfully";
    }

    @GetMapping("/user/delete/{userId}/")
    public String deleteUser(@PathVariable int userId) {
        userMapper.deleteById(userId);
        return "Delete User Successfully";
    }
}
