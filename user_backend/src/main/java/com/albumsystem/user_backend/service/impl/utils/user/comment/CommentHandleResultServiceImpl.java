package com.albumsystem.user_backend.service.impl.utils.user.comment;

import com.albumsystem.user_backend.mapper.CommentHandleResultMapper;
import com.albumsystem.user_backend.pojo.CommentHandleResult;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.comment.CommentHandleResultService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentHandleResultServiceImpl implements CommentHandleResultService {

    @Autowired
    private CommentHandleResultMapper commentHandleResultMapper;


    @Override
    public List<CommentHandleResult> commentHandle() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<CommentHandleResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        //System.out.println(user.getUserId());

        List<CommentHandleResult> commentHandleResultList = commentHandleResultMapper.selectList(queryWrapper);
        return commentHandleResultList;
    }
}
