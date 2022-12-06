package com.albumsystem.user_backend.service.impl.utils.user.friend;

import com.albumsystem.user_backend.mapper.FriendApplicationMapper;
import com.albumsystem.user_backend.mapper.FriendMapper;
import com.albumsystem.user_backend.mapper.UserMapper;
import com.albumsystem.user_backend.pojo.Friend;
import com.albumsystem.user_backend.pojo.FriendApplication;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.friend.FriendGetListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FriendGetListServiceImpl implements FriendGetListService {

    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FriendApplicationMapper friendApplicationMapper;

    @Override
    public List<User> getList() {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        List<User> allFriend = new ArrayList<>();
        QueryWrapper<Friend> queryWrapper1 = new QueryWrapper<>();
        QueryWrapper<Friend> queryWrapper2 = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper1.eq("user1_id",user.getUserId());
        queryWrapper2.eq("user2_id",user.getUserId());

        List<Friend> list1 = friendMapper.selectList(queryWrapper1);
        for(Friend friend : list1){
            queryWrapper.clear();
            queryWrapper.eq("user_id",friend.getUser2Id());
            allFriend.add(userMapper.selectOne(queryWrapper));
        }
        List<Friend> list2 = friendMapper.selectList(queryWrapper2);
        for(Friend friend : list2){
            queryWrapper.clear();
            queryWrapper.eq("user_id",friend.getUser1Id());
            allFriend.add(userMapper.selectOne(queryWrapper));
        }

        return allFriend;
    }

    @Override
    public List<User> search(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        //User user = loginUser.getUser();

        String nickname = data.get("nickname");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("nickname",nickname);
        List<User> allFriend = getList();
        List<User> likeList = userMapper.selectList(queryWrapper);

        List<User> result = new ArrayList();
        for (User user : allFriend) {
            if (likeList.contains(user)) {
                result.add(user);
            }
        }

        return result;
    }

    @Override
    public List<FriendApplication> getApplicationList() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<FriendApplication> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("applicant_id",user.getUserId());
        QueryWrapper<FriendApplication> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("recipient_id",user.getUserId());

        List<FriendApplication> friendApplications = friendApplicationMapper.selectList(queryWrapper1);
        friendApplications.addAll(friendApplicationMapper.selectList(queryWrapper2));

        return friendApplications;
    }
}
