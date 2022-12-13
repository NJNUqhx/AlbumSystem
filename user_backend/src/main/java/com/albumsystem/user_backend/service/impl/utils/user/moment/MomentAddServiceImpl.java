package com.albumsystem.user_backend.service.impl.utils.user.moment;

import com.albumsystem.user_backend.mapper.*;
import com.albumsystem.user_backend.pojo.*;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.moment.MomentAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MomentAddServiceImpl implements MomentAddService {

    @Autowired
    private MomentMapper momentMapper;
    @Autowired
    private PhotoToMomentMapper photoToMomentMapper;
    @Autowired
    private CommentToMomentMapper commentToMomentMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        //新建动态
        int userId = user.getUserId();
        Short authority = Short.parseShort(data.get("authority"));
        String description = data.get("description");
        int star = 0;
        Date now = new Date();
        int status = 0;
        int photoId = Integer.parseInt(data.get("photo_id"));

        Map<String, String> map = new HashMap<>();
        if(authority!=0&&authority!=1&&authority!=2){
            map.put("error_message","权限设置有误！");
            return map;
        }

        Moment moment = new Moment(null,userId,authority,description,star,now,status);
        momentMapper.insert(moment);
        int momentId = moment.getMomentId();
        PhotoToMoment photoToMoment = new PhotoToMoment(photoId,momentId);
        photoToMomentMapper.insert(photoToMoment);

        map.put("error_message","success");
        return map;
    }
}
