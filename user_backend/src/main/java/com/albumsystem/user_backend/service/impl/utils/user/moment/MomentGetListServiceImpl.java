package com.albumsystem.user_backend.service.impl.utils.user.moment;

import com.albumsystem.user_backend.mapper.CommentMapper;
import com.albumsystem.user_backend.mapper.CommentToMomentMapper;
import com.albumsystem.user_backend.mapper.FriendMapper;
import com.albumsystem.user_backend.mapper.MomentMapper;
import com.albumsystem.user_backend.pojo.*;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.moment.MomentGetListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.management.MemoryManagerMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MomentGetListServiceImpl implements MomentGetListService {

    @Autowired
    private MomentMapper momentMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentToMomentMapper commentToMomentMapper;
    @Autowired
    private FriendMapper friendMapper;

    @Override
    public List<Moment> getList() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Moment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());

        return momentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Comment> getCommentList(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        //User user = loginUser.getUser();

        QueryWrapper<CommentToMoment> queryWrapper_ctm = new QueryWrapper<>();
        QueryWrapper<Comment> queryWrapper_c = new QueryWrapper<>();
        queryWrapper_c.orderByAsc("time");

        int momentId = Integer.parseInt(data.get("moment_id"));
        queryWrapper_ctm.eq("moment_id",momentId);
        List<CommentToMoment> commentToMomentList = commentToMomentMapper.selectList(queryWrapper_ctm);
        List<Comment> commentList = new ArrayList<>();
        for (CommentToMoment commentToMoment: commentToMomentList){
            queryWrapper_c.clear();
            queryWrapper_c.eq("comment_id",commentToMoment.getCommentId());
            Comment new_comment = commentMapper.selectOne(queryWrapper_c);
            commentList.add(new_comment);
        }

        return commentList;
    }

    @Override
    public List<Moment> getUsersList(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        int userId = user.getUserId();

        int friendId = Integer.parseInt(data.get("friend_id"));
        QueryWrapper<Moment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",friendId);
        List<Moment> list = momentMapper.selectList(queryWrapper);

        int userId1 = Math.min(userId,friendId), userId2 = Math.max(userId,friendId);
        QueryWrapper<Friend> friendQueryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user1_id",userId1).eq("user2_id",userId2);
        boolean isFriend = friendMapper.exists(friendQueryWrapper);

        List<Moment> ans = new ArrayList<>();
        for(Moment moment:list)
            if(moment.getAuthority().equals(0) || (moment.getAuthority().equals(1) && isFriend)){
                //开放或对好友开放且为好友
                ans.add(moment);
            }
        return ans;
    }
}
