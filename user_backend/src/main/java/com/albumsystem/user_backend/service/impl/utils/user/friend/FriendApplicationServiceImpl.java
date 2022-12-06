package com.albumsystem.user_backend.service.impl.utils.user.friend;

import com.albumsystem.user_backend.mapper.FriendApplicationMapper;
import com.albumsystem.user_backend.mapper.FriendMapper;
import com.albumsystem.user_backend.pojo.Friend;
import com.albumsystem.user_backend.pojo.FriendApplication;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.friend.FriendApplicationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FriendApplicationServiceImpl implements FriendApplicationService {

    @Autowired
    private FriendApplicationMapper friendApplicationMapper;
    @Autowired
    private FriendMapper friendMapper;

    @Override
    public Map<String, String> sendApplication(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        int recipientId = Integer.parseInt(data.get("recipient_id"));
        int applicantId = user.getUserId();
        Short status = 0;
        String message = data.get("message");

        if (recipientId == applicantId){
            map.put("error_message","不可选择自己为好友");
            return map;
        }
        QueryWrapper<Friend> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user1_id",applicantId).eq("user2_id",recipientId);
        QueryWrapper<Friend> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user2_id",applicantId).eq("user1_id",recipientId);
//        System.out.println(friendMapper.exists(queryWrapper1));
//        System.out.println(friendMapper.exists(queryWrapper2));
        if(friendMapper.exists(queryWrapper1) || friendMapper.exists(queryWrapper2)){
            map.put("error_success","已为好友，不可重复申请");
            return map;
        }

        if(message.length()>128){
            map.put("error_message","描述信息长度过长");
            return map;
        }
        else if (message.length()==0){
            message = "null";
        }
        FriendApplication friendApplication = new FriendApplication(null,applicantId,recipientId, message, status);

        friendApplicationMapper.insert(friendApplication);
        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> handleApplication(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        QueryWrapper<FriendApplication> friendApplicationQueryWrapper = new QueryWrapper<>();
        int Id = Integer.parseInt(data.get("id"));
        Short status = Short.parseShort(data.get("status"));
        FriendApplication friendApplication = friendApplicationMapper.selectById(Id);
        FriendApplication new_friendApplication = new FriendApplication(
                friendApplication.getId(),
                friendApplication.getApplicantId(),
                friendApplication.getRecipientId(),
                friendApplication.getMessage(),
                status
        );
        friendApplicationMapper.updateById(new_friendApplication);

        if(status == 1){//同意
            int userId1 = Math.min(friendApplication.getApplicantId(),friendApplication.getRecipientId());
            int userId2 = Math.max(friendApplication.getApplicantId(),friendApplication.getRecipientId());
            Friend friend = new Friend(userId1,userId2);
            QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user1_id",userId1).eq("user2_id",userId2);
            //System.out.println(friendMapper.exists(queryWrapper));
            if(!friendMapper.exists(queryWrapper)) friendMapper.insert(friend);
        }

        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> rejectApplication(Map<String, String> data) {
        return null;
    }
}
